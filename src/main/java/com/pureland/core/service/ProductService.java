package com.pureland.core.service;

import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ProductBean;

/**
 * @author qinpeirong
 */
public interface ProductService {

    public void product(ProductBean productBean) throws CoreException;

}
