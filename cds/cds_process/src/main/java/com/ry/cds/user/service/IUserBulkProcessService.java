package com.ry.cds.user.service;

/**
 * 批量处理数据
 * @author 幸仁强
 *
 */
public interface IUserBulkProcessService {
	/**
	 * 导入本地文件到mysql数据库
	 */
	public int bulkInsert(String fileUrl, String tableName);
}
