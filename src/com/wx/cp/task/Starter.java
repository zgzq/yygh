package com.wx.cp.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import my.starter.IStarter;
import my.util.Util;

import org.springframework.stereotype.Component;

@Component
public class Starter implements IStarter {

	@Override
	public void destroy() throws Exception {
		Util.scheduledExecutorService.shutdown();
	}

	@Override
	public void start() {
		// Ticket.start("com.wx.cp");
		// 日报任务 每天执行一次
		// WokflowTips tips = new WokflowTips();
		// Util.scheduledExecutorService.scheduleWithFixedDelay(tips, 1,
		// 60*60*24, TimeUnit.SECONDS);
		timerstart();
		timerstart1();
		// 周报任务 每周执行一次

		// 月报任务 每月执行一次

		// 季报任务 每季度执行一次

		// 年报任务 每年执行一次

	}

	public void timerstart() {
		Calendar calendar = Calendar.getInstance();

		/*** 定制每日10:00执行方法 ***/

		calendar.set(Calendar.HOUR_OF_DAY,10);
		calendar.set(Calendar.MINUTE,00);
		calendar.set(Calendar.SECOND, 00);

		Date date = calendar.getTime();// 第一次执行定时任务的时间

		// 如果第一次执行定时任务的时间 小于 当前的时间
		// 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}

		Timer timer = new Timer();
		MyTimerTask task = new MyTimerTask();
		// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(task, date, PERIOD_DAY);

	}
	public void timerstart1() {
		Calendar calendar = Calendar.getInstance();
		
		/*** 定制每日10:00执行方法 ***/
		
		calendar.set(Calendar.HOUR_OF_DAY,10);
		calendar.set(Calendar.MINUTE,00);
		calendar.set(Calendar.SECOND, 00);
		
		Date date = calendar.getTime();// 第一次执行定时任务的时间
		
		// 如果第一次执行定时任务的时间 小于 当前的时间
		// 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}
		
		Timer timer = new Timer();
		MyTimerTask4 task4 = new MyTimerTask4();
		// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(task4, date, PERIOD_DAY);
		
	}

	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

}
