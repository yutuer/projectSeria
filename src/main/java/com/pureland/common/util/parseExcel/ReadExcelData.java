package com.pureland.common.util.parseExcel;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

public class ReadExcelData {

	public static <T> Map<String, List<T>> readData(String fileName) {
		Map<String, List<ExcelHead>> map_head = ExcelUtil.getExcelBeans(false, fileName);
		Map<String, List<List<Object>>> map_data = ExcelUtil.getExcelData(false, fileName, map_head);
		Map<String, List<T>> retMap = Maps.newHashMap();
		try {
			for (Entry<String, List<List<Object>>> entry : map_data.entrySet()) {
				String cname = entry.getKey();
				Class c = Class.forName("com.pureland.common.db.statics." + cname);
				retMap.put(cname, ExcelUtil.transferObjFromData(entry.getValue(), c, map_head.get(cname)));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return retMap;
	}

	public static <T> List<T> GetExcelData(String excelFileName, String beanName) {
		final String name = excelFileName;
		String filePath = "excels/" + name + ".xlsx";
		return ReadExcelData.<T> readData(filePath).get(beanName);
	}
}
