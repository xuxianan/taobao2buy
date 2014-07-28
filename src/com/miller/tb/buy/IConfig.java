package com.miller.tb.buy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IConfig {

	// new
	public static final int OPEN_THREAD_COUNT = 1;
	public static final long LAZY_TIME = 2000;
	public static final String REQUEST_URL = "http://s.2.taobao.com/list/list.htm?spm=2007.1000337.6.2.T5J2FS&st_edtime=1&q=psv&ist=0";
	public static final Set<String> BLACK = new HashSet<String>();
	public static final Map<String, Double> ITEM_MAP = new HashMap<String, Double>();
	static {
		ITEM_MAP.put("神秘海域_psn", 100.0);
		ITEM_MAP.put("ace_psn", 150.0);
		ITEM_MAP.put("fifa_psn", 50.0);
		ITEM_MAP.put("海贼_psn", 130.0);
		ITEM_MAP.put("极品_psn", 80.0);
		ITEM_MAP.put("裸卡_psn", 85.0);
		ITEM_MAP.put("打包_psn", 300.0);
		ITEM_MAP.put("13_psn", 70.0);
		ITEM_MAP.put("杀戮_psn", 100.0);
		ITEM_MAP.put("闪之轨迹_psn", 120.0);
		ITEM_MAP.put("闪轨_psn", 120.0);
		ITEM_MAP.put("战神_psn", 100.0);
		ITEM_MAP.put("合金装备_psn", 100.0);
		ITEM_MAP.put("ss_psn", 80.0);
		ITEM_MAP.put("ssd_psn", 100.0);
		ITEM_MAP.put("灵魂_psn", 70.0);
		ITEM_MAP.put("32_psn", 200.0);
		ITEM_MAP.put("64_psn", 300.0);
		ITEM_MAP.put("秋叶原_psn", 300.0);
	}
	
}
