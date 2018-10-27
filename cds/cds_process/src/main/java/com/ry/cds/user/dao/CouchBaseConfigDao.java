package com.ry.cds.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ry.cds.user.bo.CouchBaseConfig;
import com.ry.cds.user.mapper.CouchBaseConfigMapper;

@Repository
public class CouchBaseConfigDao implements ICouchBaseConfigDao {

	@Autowired
	CouchBaseConfigMapper couchBaseConfigMapper;

	/**
	 * 查询所有有效的couchbaseconfig配置
	 * 
	 * @return couchbaseconfig集合
	 */
	public List<CouchBaseConfig> couchBaseConfigs() {
		return couchBaseConfigMapper.couchBaseConfigs();
	}
}
