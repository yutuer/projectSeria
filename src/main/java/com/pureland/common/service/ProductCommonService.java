package com.pureland.common.service;

import java.util.List;

import com.pureland.common.db.data.Product;
import com.pureland.common.error.CoreException;

/**
 * @author qinpeirong
 */
public interface ProductCommonService {

    public Long addProduct(Long userRaceId, Long buildingSid, Integer cid, Integer amount, Long beginTime, Long endTime) throws CoreException;

    public Product getProduct(Long userRaceId, Long buildingSid, Integer cid) throws CoreException;

    public Product getProduct(String productId) throws CoreException;

    public List<Product> getProducts(Long userRaceId, Long buildingSId) throws CoreException;

    public void updateProduct(Product product) throws CoreException;

    public void delelteProduct(Long userRaceId, Long buildingSid, Integer cid) throws CoreException;

    public void deleteQuene(Long userRaceId, Long buildingSid, Integer cid, Long productId) throws CoreException;

    public void updateNextQuene(Long userRaceId, Long buildingSId, Long endTime) throws CoreException;

    public String getFirstQueue(Long userRaceId, Long buildingSid) throws CoreException;

    public Boolean isFirstQuene(Long userRaceId, Long buildingSid, Long productId) throws CoreException;

    public int getArmyBuildingsCurSpace(Long userRaceId, Long buildingSid) throws CoreException;

    public void updateFirstQueueNextTime(Long userRaceId, Long buildingSid, Long lastEndTime) throws CoreException;

    public int getAllProductsFinishSecond(Long userRaceId, Long buildingSid, Long beginTime) throws CoreException;

}
