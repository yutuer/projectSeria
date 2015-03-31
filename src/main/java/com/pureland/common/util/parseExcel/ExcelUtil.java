package com.pureland.common.util.parseExcel;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * excel辅助工具类
 * 
 * @author 王烁
 * @date 2012-11-14
 * @version 1.0
 */
public class ExcelUtil {

	public static final Logger log = Logger.getRootLogger();

	public static <T> List<T> transferObjFromData(List<List<Object>> lists, Class<T> c, List<ExcelHead> heads) {
		try {
			List<T> objs = Lists.newArrayList();
			List<Class> ll = Lists.newArrayList();
			for (ExcelHead eh : heads) {
				ll.add(String.class);
			}
			assert heads.size() == ll.size();
			Constructor<T> con = c.getConstructor(ll.toArray(new Class[0]));
			for (List<Object> list : lists) {
				T t = con.newInstance(list.toArray());
				objs.add(t);
			}
			return objs;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取excel列数
	 * 
	 * @param row
	 *            excel第一行对象
	 * @return excel列数
	 */
	public static int getColumnsNum(Row row) {
		return row.getLastCellNum();
	}

	/**
	 * 获取excel中的模板表头信息
	 * 
	 * @param filePath
	 * @return
	 */
	public static Map<String, List<ExcelHead>> getExcelBeans(boolean isAbsolutePath, String filePath) {
		Map<String, List<ExcelHead>> map = new LinkedHashMap<String, List<ExcelHead>>();
		if (!(filePath.endsWith(".xls") || filePath.endsWith(".xlsx")))
			return null;
		Workbook wb = null;
		InputStream inp = null;

		boolean flag = filePath.endsWith(".xls") ? true : false;
		try {
			inp = isAbsolutePath ? new FileInputStream(new File(filePath)) : Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(filePath);
			FormulaEvaluator eval = null;
			if (flag) {
				wb = new HSSFWorkbook(new POIFSFileSystem(inp));
				eval = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
			} else {
				wb = new XSSFWorkbook(inp);
				eval = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
			}

			for (int i = 0; i < 1; i++) {
				Sheet sheet = wb.getSheetAt(i);
				Row row0 = sheet.getRow(0);
				Row row1 = sheet.getRow(1);
				Row row2 = sheet.getRow(2);
				if (row0 == null)
					continue;
				if (row1 == null)
					continue;
				if (row2 == null)
					continue;

				List<ExcelHead> list = Lists.newArrayList();
				for (int k = 0; k < getColumnsNum(row0); k++) {
					ExcelHead bean = new ExcelHead();
					bean.desc = GPoiUtils.getStringValue(eval, row0.getCell(k));
					bean.title = GPoiUtils.getStringValue(eval, row1.getCell(k));
					bean.type = GPoiUtils.getStringValue(eval, row2.getCell(k));
					if (StringUtils.isEmpty(bean.desc) || StringUtils.isEmpty(bean.title) || StringUtils.isEmpty(bean.type)) {
						log.error(String.format("%s 检查%d列, 有为''的cell\n", filePath, k + 1));
						continue;
					}
					list.add(bean);
				}
				map.put(sheet.getSheetName(), list);
			}
			eval.clearAllCachedResultValues();
			inp.close();
		} catch (IOException e) {
			log.error("解析表头错误", e);
		}
		return map;
	}

	public static String getExcelSheetName(boolean isAbsolutePath, String filePath) {
		Map<String, List<ExcelHead>> map = new LinkedHashMap<String, List<ExcelHead>>();
		if (!(filePath.endsWith(".xls") || filePath.endsWith(".xlsx")))
			return "";
		Workbook wb = null;
		InputStream inp = null;

		boolean flag = filePath.endsWith(".xls") ? true : false;
		String result = "";
		try {
			inp = isAbsolutePath ? new FileInputStream(new File(filePath)) : Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(filePath);
			FormulaEvaluator eval = null;
			if (flag) {
				wb = new HSSFWorkbook(new POIFSFileSystem(inp));
				eval = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
			} else {
				wb = new XSSFWorkbook(inp);
				eval = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
			}

			List<String> list = new ArrayList<String>();
			if (wb.getNumberOfSheets() > 0) {
				result = wb.getSheetAt(0).getSheetName();
			}
			inp.close();
		} catch (IOException e) {
			log.error("解析表头错误", e);
		}
		return result;
	}

	/**
	 * 获取excel中的数据
	 * 
	 * @param filePath
	 *            文件名字
	 * @return
	 */
	public static Map<String, List<List<Object>>> getExcelData(boolean isAbsolutePath, String filePath, Map<String, List<ExcelHead>> heads) {
		if (!(filePath.endsWith(".xls") || filePath.endsWith(".xlsx")))
			return null;
		Workbook wb = null;
		InputStream inp = null;
		boolean flag = filePath.endsWith(".xls") ? true : false;
		Map<String, List<List<Object>>> result = new LinkedHashMap<String, List<List<Object>>>();
		try {
			inp = isAbsolutePath ? new FileInputStream(new File(filePath)) : Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(filePath);
			if (flag) {
				wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			} else {
				wb = new XSSFWorkbook(inp);
			}
			FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();

			// for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			for (int i = 0; i < 1; i++) {
				Sheet sheet = wb.getSheetAt(i);
				if (sheet == null)
					continue;
				if (sheet.getPhysicalNumberOfRows() <= 3)
					continue;

				List<ExcelHead> head = heads.get(sheet.getSheetName());
				Map<String, List<Object>> rows = new LinkedHashMap<String, List<Object>>();
				int strat = sheet.getFirstRowNum() + 3;
				int end = sheet.getLastRowNum();
				for (int j = strat; j <= end; j++) {
					Row row = sheet.getRow(j);
					if (row == null)
						continue;
					List<Object> cells = Lists.newArrayList();
					String id = "";
					for (int k = 0; k < getColumnsNum(row); k++) {
						if (k >= head.size())
							break;
						Cell cell = row.getCell(k);
						try {
							String str = GPoiUtils.getStringValue(eval, cell).trim();
							if (k == 0 && (str == null || str.equals("")))
								break;
							if (k == 0)
								id = str;
							cells.add(str);
						} catch (Exception e) {
							ExcelHead eh = head.get(k);
							if (!eh.desc.equals("") && !eh.title.equals("") && !eh.type.equals("")) {
								log.error("发生错误的表：" + filePath + "  " + (j + 1) + "行    " + k + "列", e);
							}
							cells.add("");
						}
					}
					if (cells.size() > 0 && !"".equals(id)) {
						if (rows.containsKey(id)) {
							IllegalStateException e = new IllegalStateException("ID重复的表：" + filePath + " id=" + id);
							log.error("ID重复的表：" + filePath + " id=" + id, e);
							throw e;
						}
						rows.put(id, cells);
					}
				}
				result.put(sheet.getSheetName(), new ArrayList<List<Object>>(rows.values()));
			}
			eval.clearAllCachedResultValues();
			inp.close();
		} catch (IOException e1) {
			log.error("解析表数据错误", e1);
		}
		return result;
	}

	/**
	 * 获取分隔符
	 * 
	 * @param titleDesc
	 *            表头描述
	 * @return
	 */
	public static String[] getStringArrayForExcel(String titleDesc, String value) {

		titleDesc = titleDesc.replace("；", ";").replace("，", ",");
		value = value.replace("；", ";").replace("，", ",");

		String mark = null;
		if (titleDesc.indexOf(";") != -1) {
			mark = ";";
		} else if (titleDesc.indexOf(",") != -1) {
			mark = ",";
		}

		String[] result = null;
		boolean isHaveF = value.indexOf(";") != -1;
		// 有分号
		if (isHaveF) {
			result = value.split(";");
		}
		// 没有分号
		else {
			// 先判断title中是否有分号
			if (titleDesc.indexOf(";") != -1) {
				result = value.split(";");
			}
			// title中也没有分号，说明是以逗号作为分隔
			else {
				result = value.split(",");
			}
		}
		if (result == null) {
			result = new String[] { value };
		}
		return result;
	}

	private static int parseInt(String s, boolean isforce) throws NumberFormatException {
		if (!isforce) {
			return Integer.parseInt(s, 10);
		}
		if (s == null) {
			throw new NumberFormatException("null");
		}
		if (s.equals("")) {
			throw new NumberFormatException("\"\"");
		}
		int index = s.indexOf(".");
		int isadd = 0;
		if (s.indexOf(".") != -1 && index > 0) {
			if (index < s.length() - 1) {
				int t = Integer.parseInt(s.substring(index + 1, index + 2));
				isadd = t > 4 ? 1 : 0;
			}
			s = s.substring(0, index);
		}
		return Integer.parseInt(s, 10) + isadd;
	}
}
