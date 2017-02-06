package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mysql.jdbc.StringUtils;

import anno.Column;
import anno.Entity;
import bean.BaseBean;
import bean.SenderInfo;
import bean.TableParseInfo;

public class DBUtil<T extends BaseBean> {

	private static String dirverClassName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://120.26.119.149:3306/zhuqingwei?useUnicode=true&characterEncoding=utf8";
	private static String user = "root";
	private static String password = "tryme";

	public static Connection makeConnection() {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
		} catch (ClassNotFoundException e) {
			LogUtil.error(DBUtil.class, e.getMessage());
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			LogUtil.error(DBUtil.class, e.getMessage());
		}
		return conn;
	}

	public static void main(String[] args) throws SQLException {
		// List<SenderInfo> results = find(new SenderInfo("15068832559"));
		// for(SenderInfo result:results){
		// System.out.println(result);
		// }
		// insert(new SenderInfo("朱江桃", "121212121", "121212121", "上海", "顺丰"));
		new DBUtil<SenderInfo>().insert2(new SenderInfo("朱江桃2", "1212312312212121", "123213213213", "上海", "顺丰"));
		;
	}

	public void insert2(T entity) throws SQLException {
		TableParseInfo parseInfo = getParseInfo(entity);
		System.out.println("-------------test_insert()-------------");
		// 创建连接
		Connection conn = makeConnection();
		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();
		// 执行SQL插入
		List<String> cols = parseInfo.getColumns();
		HashMap<String, Object> colToValue = parseInfo.getColToValue();
		cols.add(0, "id");
		colToValue.put("id", UUID.randomUUID().toString());
		parseInfo.setParamsCount(parseInfo.getParamsCount()+1);
		int n = qRunner.update(conn, "insert into `" + parseInfo.getTableName() + "` (" + parseInfo.getColumnsStr()
				+ ") values(" + parseInfo.getParamsStr() + ")", parseInfo.getValues().toArray());
		System.out.println("成功插入[" + n + "]行");
		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}

	public List<T> find(T entity) throws SQLException {
		Connection conn = makeConnection();
		QueryRunner qRunner = new QueryRunner();
		TableParseInfo parseInfo = getParseInfo(entity);
		String sql = "select  " + parseInfo.getColumnsStr() + " from " + parseInfo.getTableName()
				+ " where delete_flag = 0";
		BeanListHandler<T> rsh = new BeanListHandler<>((Class<T>) entity.getClass());
		List<T> queryResult = null;
		List<Object> paramList = new ArrayList<>();
		List<String> columns = parseInfo.getColumns();
		for (String col : columns) {
			Object value = parseInfo.getColToValue().get(col);
			if(value != null){
				sql += " and " + col + " = ? ";
				paramList.add(value);
			}
		}
		queryResult = qRunner.query(conn, sql, rsh, paramList.toArray());
		DbUtils.closeQuietly(conn);
		return queryResult;
	}

	private Object getter(T obj, String att) {
		try {
			String upperAttr = att.substring(0, 1).toUpperCase() + att.substring(1);
			Method method = obj.getClass().getMethod("get" + upperAttr);
			Object result = method.invoke(obj);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private TableParseInfo getParseInfo(T entity) {
		Class<T> cls = (Class<T>) entity.getClass();
		Entity[] entitys = cls.getDeclaredAnnotationsByType(Entity.class);
		String tableName = entitys[0].tableName();
		Field[] fields = cls.getDeclaredFields();
		List<String> columns = new ArrayList<>();
		int paramsCount = 0;
		HashMap<String, Object> colToValue = new HashMap<>();
		for (Field field : fields) {
			Column col = field.getDeclaredAnnotation(Column.class);
			if (col != null) {
				String colName = col.columnName();
				columns.add(colName);
				paramsCount++;
				colToValue.put(colName, getter(entity, field.getName()));
			}
		}
		return new TableParseInfo(tableName, columns, paramsCount, colToValue);
	}

}
