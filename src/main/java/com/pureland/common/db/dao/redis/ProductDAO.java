package com.pureland.common.db.dao.redis;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Product;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.log.PurelandLog;

/**
 * @author qinpeirong
 */
public class ProductDAO extends RedisDAO {

    private String TAG = PurelandLog.getClassTag(ProductDAO.class);

    public Long addProduct(Product product) throws DBException {
        Long id = product.getId();
        Long userRaceId = product.getUserRaceId();
        Long buildingSid = product.getBuildingSid();
        Integer cid = product.getCid();
        Long endTime = product.getNextEndTime();

        if (userRaceId == null || buildingSid == null || cid == null) {
            throw new DBException("userRaceId or cid is illegal");
        }

        try {
            if (id == null) {
                id = RString.generator(Entity.PRODUCT.getName());
            }
            String keyId = Product.generatorIdKey(new String[]{userRaceId.toString(), buildingSid.toString(), cid.toString()});
            String keyCid = Product.generatorFieldKey(id, Entity.Product.CID.getName());
            String keyUserRaceId = Product.generatorFieldKey(id, Entity.Product.USERRACEID.getName());
            String keyBuildingSid = Product.generatorFieldKey(id, Entity.Product.BUILDING.getName());
            String keyAmount = Product.generatorFieldKey(id, Entity.Product.AMOUNT.getName());
            String keyBeginTime = Product.generatorFieldKey(id, Entity.Product.BEGINTIME.getName());
            String keyEndtime = Product.generatorFieldKey(id, Entity.Product.NEXTENDTIME.getName());

            RString.set(keyId, String.valueOf(id));
            RString.set(keyBuildingSid, String.valueOf(buildingSid));
            RString.set(keyCid, String.valueOf(cid));
            RString.set(keyUserRaceId, String.valueOf(userRaceId));
            RString.set(keyAmount, String.valueOf(product.getAmount()));
            if (product.getBeginTime() != null) {
                RString.set(keyBeginTime, String.valueOf(product.getBeginTime()));
            }
            if (endTime != null) {
                RString.set(keyEndtime, String.valueOf(endTime));
            }

            addSortedSetCollection(userRaceId, buildingSid, Double.parseDouble(product.getBeginTime().toString()), String.valueOf(id));

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

        return id;
    }

    public Product getProduct(Long id) throws DBException {
        Product product = new Product();

        try {
            String keyCid = Product.generatorFieldKey(id, Entity.Product.CID.getName());
            String keyUserRaceId = Product.generatorFieldKey(id, Entity.Product.USERRACEID.getName());
            String keyAmount = Product.generatorFieldKey(id, Entity.Product.AMOUNT.getName());
            String keyBeginTime = Product.generatorFieldKey(id, Entity.Product.BEGINTIME.getName());
            String keyEndtime = Product.generatorFieldKey(id, Entity.Product.NEXTENDTIME.getName());
            String keyBuildingSid = Product.generatorFieldKey(id, Entity.Product.BUILDING.getName());

            product.setId(id);
            product.setCid(Integer.parseInt(RString.get(keyCid)));
            product.setBuildingSid(Long.parseLong(RString.get(keyBuildingSid)));
            product.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
            product.setAmount(Integer.parseInt(RString.get(keyAmount)));
            product.setBeginTime(Long.valueOf(RString.get(keyBeginTime)));
            String endTime = RString.get(keyEndtime);
            if (StringUtils.isNotEmpty(endTime)) {
                product.setNextEndTime(Long.parseLong(endTime));
            }
        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

        return product;
    }

    public void updateProduct(Product product) throws DBException {
        Long id = product.getId();
        Integer cid = product.getCid();
        Integer amount = product.getAmount();
        Long endTime = product.getNextEndTime();

        if (id == null) {
            throw new DBException("");
        }

        try {
            if (cid != null) {
                String keyCid = Product.generatorFieldKey(id, Entity.Product.CID.getName());
                RString.set(keyCid, String.valueOf(cid));
            }
            if (amount != null) {
                String keyAmount = Product.generatorFieldKey(id, Entity.Product.AMOUNT.getName());
                RString.set(keyAmount, String.valueOf(amount));
            }
            if (endTime != null) {
                String keyEndtime = Product.generatorFieldKey(id, Entity.Product.NEXTENDTIME.getName());
                RString.set(keyEndtime, String.valueOf(endTime));
            }

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
    }

    public void deleteProduct(Long userRaceId, Long buildingSid, Integer cid) throws DBException {
        if (userRaceId == null || buildingSid == null || cid == null) {
            throw new DBException("");
        }
        try {
            String keyId = Product.generatorIdKey(new String[]{userRaceId.toString(), buildingSid.toString(), cid.toString()});
            RString.del(keyId);
        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
    }

    public void addSortedSetCollection(Long userRaceId, Long buildingSid, Double score, String value) throws DBException {
        super.addSortedSetCollection(Entity.PRODUCTQUENE, score, value, new String[]{userRaceId.toString(), buildingSid.toString()});
    }

    public Set<String> getSortedSetCollection(Long userRaceId, Long buildingSid, Integer start, Integer end) throws DBException {
        return super.getSortedSetCollection(Entity.PRODUCTQUENE, start, end, new String[]{userRaceId.toString(), buildingSid.toString()});
    }

    public Long deleteSortedSetElement(Long userRaceId, Long buildingSid, String value) throws DBException {
        return super.deleteSortedSetElement(Entity.PRODUCTQUENE, value, new String[]{userRaceId.toString(), buildingSid.toString()});
    }

}
