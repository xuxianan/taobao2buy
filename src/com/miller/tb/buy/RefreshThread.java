package com.miller.tb.buy;

import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RefreshThread implements Callable<Object> {
	
	private static Log log = LogFactory.getLog(RefreshThread.class);
	private int index;
	
	@Override
	public Object call() throws Exception {
		log.info("【总共：" + IConfig.OPEN_THREAD_COUNT+ "个线程】，线程：" + index + " 开始执行！");
		Refresh.start(0);
		return null;
	}

	public RefreshThread(int index) {
		this.index = index;
	}
}
