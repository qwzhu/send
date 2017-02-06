package dao;

import java.sql.SQLException;
import java.util.List;

import bean.SenderInfo;
import util.DBUtil;
import util.LogUtil;

public class SenderInfoDao {
	private DBUtil<SenderInfo> dbUtil = new DBUtil<SenderInfo>();

	public void insert(SenderInfo sendInfo) {
		List<SenderInfo> exists = null;
		try {
			SenderInfo condition = new SenderInfo(sendInfo.getName(), sendInfo.getMobile(), sendInfo.getPhone(),
					sendInfo.getAddress(), sendInfo.getCompany());
			exists = dbUtil.find(condition);
			if (exists != null && exists.size() > 0) {
				LogUtil.warn(SenderInfoDao.class, "记录已经存在了，别插入了。：" + sendInfo);
				return;
			}
			dbUtil.insert2(sendInfo);
		} catch (SQLException e) {
			LogUtil.error(SenderInfoDao.class, e.getMessage());
		}
	}

	public static void main(String[] args) {
		new SenderInfoDao().insert(new SenderInfo("朱江桃2", "1212312312212121", "123213213213", "上海", "顺丰"));
	}
}
