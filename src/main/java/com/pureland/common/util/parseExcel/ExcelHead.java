package com.pureland.common.util.parseExcel;

public class ExcelHead implements IHead {

	/**
	 * 表格描述
	 */
	public String desc;
	/**
	 * 表格名称
	 */
	public String title;
	/**
	 * 表格字段类型
	 */
	public String type;

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getType() {
		return type;
	}

}
