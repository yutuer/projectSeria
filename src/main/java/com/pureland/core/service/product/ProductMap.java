package com.pureland.core.service.product;

import java.util.Map;

import com.pureland.core.service.ProductService;

/**
 * 
 * @author qinpeirong
 *
 */
public class ProductMap {
    private Map<String, ProductService> productMaps;

	/**
	 * @return the productMaps
	 */
	public Map<String, ProductService> getProductMaps() {
		return productMaps;
	}

	/**
	 * @param productMaps the productMaps to set
	 */
	public void setProductMaps(Map<String, ProductService> productMaps) {
		this.productMaps = productMaps;
	}
    
}
