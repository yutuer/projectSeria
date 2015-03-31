package com.pureland.common.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.ProductDAO;
import com.pureland.common.db.data.Product;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.ProductCommonService;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;

/**
 * @author qinpeirong
 */
public class ProductCommonServiceImpl extends BaseService implements ProductCommonService {

	protected ProductDAO productDAO = (ProductDAO) SpringContextUtil.getBean(ProductDAO.class.getSimpleName());

	@Override
	public Long addProduct(Long userRaceId, Long buildingSid, Integer cid, Integer amount, Long beginTime, Long endTime) throws CoreException {
		Long id = null;
		try {
			Product product = new Product();
			product.setUserRaceId(userRaceId);
			product.setBuildingSid(buildingSid);
			product.setCid(cid);
			product.setAmount(amount);
			product.setBeginTime(beginTime);
			product.setNextEndTime(endTime);
			id = productDAO.addProduct(product);
		} catch (DBException e) {
			throw new CoreException(e);
		}
		return id;
	}

	public Product getProduct(String productId) throws CoreException {
		Product product = null;
		try {
			product = productDAO.getProduct(Long.valueOf(productId));
		} catch (DBException e) {
			throw new CoreException(e);
		}
		return product;
	}

	@Override
	public Product getProduct(Long userRaceId, Long buildingSid, Integer cid) throws CoreException {
		if (userRaceId == null || buildingSid == null || cid == null) {
			throw new CoreException("userRaceId, buildingSid or cid is illegal");
		}
		Product product = null;
		try {
			String keyId = Product.generatorIdKey(new String[] { userRaceId.toString(), buildingSid.toString(), cid.toString() });
			String id = RString.get(keyId);
			if (StringUtils.isNotEmpty(id)) {
				product = productDAO.getProduct(Long.parseLong(id));
			}
		} catch (RedisException e) {
			throw new CoreException(e);
		} catch (DBException e) {
			throw new CoreException(e);
		}
		return product;
	}

	@Override
	public List<Product> getProducts(Long userRaceId, Long buildingSId) throws CoreException {
		List<Product> products = Lists.newArrayList();
		try {
			Set<String> productIds = productDAO.getSortedSetCollection(userRaceId, buildingSId, null, null);
			Iterator<String> iterator = productIds.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				Long productId = new Long(next);
				Product product = productDAO.getProduct(productId);
				products.add(product);
			}
		} catch (Exception e) {
			throw new CoreException(e);
		}
		return products;
	}

	@Override
	public void updateProduct(Product product) throws CoreException {
		try {
			productDAO.updateProduct(product);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public void delelteProduct(Long userRaceId, Long buildingSid, Integer cid) throws CoreException {
		try {
			productDAO.deleteProduct(userRaceId, buildingSid, cid);
		} catch (DBException e) {
			throw new CoreException(e);
		}

	}

	@Override
	public void deleteQuene(Long userRaceId, Long buildingSid, Integer cid, Long productId) throws CoreException {
		try {
			productDAO.deleteSortedSetElement(userRaceId, buildingSid, productId.toString());
			delelteProduct(userRaceId, buildingSid, cid);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public void updateNextQuene(Long userRaceId, Long buildingSId, Long lastEndTime) throws CoreException {
		try {
			Set<String> setQuene = productDAO.getSortedSetCollection(userRaceId, buildingSId, 0, 0);
			Iterator<String> iterator = setQuene.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				Product nextProduct = productDAO.getProduct(Long.parseLong(next));
				Integer cid = nextProduct.getCid();
				EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
				Long trainTime = entityModel.getTrainTime();
				Long nextEndtime = lastEndTime + trainTime * 1000L;
				nextProduct.setNextEndTime(nextEndtime);
				updateProduct(nextProduct);
			}
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public Boolean isFirstQuene(Long userRaceId, Long buildingSid, Long productId) throws CoreException {
		try {
			Set<String> setQuene = productDAO.getSortedSetCollection(userRaceId, buildingSid, 0, 0);
			Iterator<String> iterator = setQuene.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				Long first = new Long(next);
				if (first.equals(productId)) {
					return true;
				} else {
					return false;
				}

			}
		} catch (DBException e) {
			throw new CoreException(e);
		}
		return false;
	}

	@Override
	public String getFirstQueue(Long userRaceId, Long buildingSid) throws CoreException {
		String ret = null;
		try {
			Set<String> setQuene = productDAO.getSortedSetCollection(userRaceId, buildingSid, 0, 0);
			if (CollectionUtils.isNotEmpty(setQuene)) {
				Iterator<String> iterator = setQuene.iterator();
				return iterator.next();
			}
		} catch (DBException e) {
			throw new CoreException(e);
		}
		return ret;
	}

	@Override
	public int getArmyBuildingsCurSpace(Long userRaceId, Long buildingSid) throws CoreException {
		int sum = 0;
		List<Product> productList = getProducts(userRaceId, buildingSid);
		if (CollectionUtils.isEmpty(productList)) {
			return 0;
		}
		for (Product product : productList) {
			EntityModel productEm = EntityModelHelper.ENTITIES.get(product.getCid());
			sum += product.getAmount() * productEm.getSpaceUse();
		}
		return sum;
	}

	@Override
	public void updateFirstQueueNextTime(Long userRaceId, Long buildingSid, Long lastEndTime) throws CoreException {
		// 更新下一个队列的时间
		String firstProductId = getFirstQueue(userRaceId, buildingSid);
		if (firstProductId != null) {
			Product firstProduct = getProduct(firstProductId);
			firstProduct.setNextEndTime(lastEndTime);
			updateProduct(firstProduct);
		}
	}

	/**
	 * 得到生产队列里面最后结束到当前的秒数
	 *
	 * @param userRaceId
	 * @param buildingSid
	 * @param beginTime
	 * @return
	 * @throws CoreException
	 */
	@Override
	public int getAllProductsFinishSecond(Long userRaceId, Long buildingSid, Long beginTime) throws CoreException {
		long sumMillions = 0;
		List<Product> productList = getProducts(userRaceId, buildingSid);
		boolean isFirst = true;
		for (Product product : productList) {
			int cidd = product.getCid();
			EntityModel productEm = EntityModelHelper.ENTITIES.get(cidd);
			if (isFirst) {
				// (结束时间 - 开始时间 ) + (数量-1)* 训练单位时间
				sumMillions += (product.getNextEndTime() - beginTime) + (product.getAmount() - 1) * productEm.getTrainTime() * 1000L;
			} else {
				sumMillions += product.getAmount() * productEm.getTrainTime() * 1000L;
			}
			isFirst = false;
		}
		return (int) (sumMillions / 1000);
	}

}
