package com.wx.ad.util;

import my.dao.Page;
import my.dao.pool.DBManager;
import my.dao.utils.Record;
import my.dao.utils.RecordMapping;
import my.web.RequestContext;

import com.wx.ad.bean.GridDataModel;

public class PageFactory {
	
	public static <T> GridDataModel<T> newPage(Class<T> cls, String filter,String orderby,
			Object... params) {
		int start = RequestContext.get().param("start", 0);
		int limit = RequestContext.get().param("limit", Integer.MAX_VALUE);
		Page<T> page = DaoUtil.getDao().page(cls, filter,orderby, start, limit, params);
		GridDataModel<T> gridDataModel = new GridDataModel<T>();
		gridDataModel.setRows(page.getContent());
		gridDataModel.setTotal(page.getTotalElements());
		return gridDataModel;
	}
	
	/**
	 * 根据自定义sql查询分页
	 * @param sql
	 * @param orderby
	 * @param params
	 * @return
	 */
	public static   GridDataModel<Record> newPage(String sql,String orderby,
			Object... params) {
		int start = RequestContext.get().param("start", 0);
		int limit = RequestContext.get().param("limit", Integer.MAX_VALUE);
		Page<Record> page = DaoUtil.getDao().pageFromSql(DBManager.DEFAULT_POOL_NAME,sql, orderby,new RecordMapping(), start, limit, params);
		GridDataModel<Record> gridDataModel = new GridDataModel<Record>();
		gridDataModel.setRows(page.getContent());
		gridDataModel.setTotal(page.getTotalElements());
		return gridDataModel;
	}
}
