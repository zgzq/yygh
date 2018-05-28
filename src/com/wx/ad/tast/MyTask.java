package com.wx.ad.tast;

import java.lang.reflect.Field;
import java.sql.SQLException;

import my.dao.pool.DBManager;
import my.dao.pool.DBPool;
import my.starter.IStarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.druid.filter.encoding.EncodingConvertFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.wx.cp.task.WxcpMenu;

import me.chanjar.weixin.common.exception.WxErrorException;

@Component
public class MyTask implements IStarter {
	static Logger logger = LoggerFactory.getLogger(MyTask.class);

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy");
	}

	@Override
	public void start() {
		System.out.println("启动定时任务");
		//initProxy();
		/**
		 * tomcat启动时重新生成微信菜单
		 */
//		System.out.println("生成微信菜单");
//		//先删除
//		WxcpMenu.menuDelete();
//		//再生成(tomcat启动后需要等待5分钟左右手机端菜单才能生效)
//		WxcpMenu.menuCreate();

	}
	
	private static void initProxy(){
		try {
			DBManager.getConnection().getCatalog();
			DBManager.closeAllConnection();

			DBPool pool = DBManager.getDefaultPool();
			Field f = pool.getClass().getDeclaredField("dataSource");
			f.setAccessible(true);
			DruidDataSource ds = (DruidDataSource) f.get(pool);
			
			EncodingConvertFilter filter = new EncodingConvertFilter();
			ds.getProxyFilters().add(filter);
			System.out.println(ds.getClass());
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
