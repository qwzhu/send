package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableParseInfo {
	private String tableName;
	private List<String> columns;
	private String columnsStr = "";
	private Integer paramsCount;
	private String paramsStr = "";
	private HashMap<String, Object> colToValue;
	private List<Object> values = new ArrayList<>();
	public TableParseInfo(String tableName, List<String> columns, Integer paramsCount,
			HashMap<String, Object> colToValue) {
		this.tableName = tableName;
		this.columns = columns;
		this.paramsCount = paramsCount;
		this.colToValue = colToValue;
	}

	public TableParseInfo() {

	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getColumnsStr() {
		int index = 0;
		for (String col : columns) {
			if (index > 0) {
				columnsStr += ",";
			}
			columnsStr += col;
			index++;
		}
		return columnsStr;
	}

	public void setColumnsStr(String columnsStr) {
		this.columnsStr = columnsStr;
	}

	public Integer getParamsCount() {
		return paramsCount;
	}

	public void setParamsCount(Integer paramsCount) {
		this.paramsCount = paramsCount;
	}

	public String getParamsStr() {
		for (int i = 0; i < paramsCount; i++) {
			if (i > 0) {
				paramsStr += ",";
			}
			paramsStr += "?";
		}
		return paramsStr;
	}

	public void setParamsStr(String paramsStr) {
		this.paramsStr = paramsStr;
	}

	public HashMap<String, Object> getColToValue() {
		return colToValue;
	}

	public void setColToValue(HashMap<String, Object> colToValue) {
		this.colToValue = colToValue;
	}

	public List<Object> getValues() {
		for(String col:columns){
			values.add(colToValue.get(col));
		}
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}
	

}
