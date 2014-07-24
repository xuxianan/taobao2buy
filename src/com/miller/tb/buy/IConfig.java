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
		ITEM_MAP.put("忍龙", 80.0);
		ITEM_MAP.put("神秘海域", 130.0);
		ITEM_MAP.put("ace", 180.0);
		ITEM_MAP.put("ACE", 180.0);
		ITEM_MAP.put("FIFA", 60.0);
		ITEM_MAP.put("海贼", 130.0);
		ITEM_MAP.put("极品", 80.0);
		ITEM_MAP.put("裸卡", 85.0);
		ITEM_MAP.put("打包", 300.0);
		ITEM_MAP.put("13", 70.0);
		ITEM_MAP.put("杀戮", 150.0);
		ITEM_MAP.put("闪之轨迹", 120.0);
		ITEM_MAP.put("闪轨", 120.0);
		ITEM_MAP.put("战神", 120.0);
		ITEM_MAP.put("合金装备", 110.0);
		ITEM_MAP.put("ss", 80.0);
		ITEM_MAP.put("ssd", 100.0);
		ITEM_MAP.put("灵魂", 80.0);
	}
	
}
