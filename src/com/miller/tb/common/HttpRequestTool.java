package com.miller.tb.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTool {


	/**
	 * 请求页面
	 * @param strUrl
	 * @param strPostRequest
	 * @param maxLength
	 * @param code
	 * @return
	 */
	public static String getPageContent(String strUrl, String strPostRequest, int maxLength, String code) {
		// 读取结果网页
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection connection = null;
		try {
			URL url = new URL(strUrl);
			// 打开url连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod(strPostRequest);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), code));
			int ch;
			for (int length = 0; (ch = in.read()) > -1 && (maxLength <= 0 || length < maxLength); length++) {
				buffer.append((char) ch);
				//System.out.print((char) ch);
			}
			in.close();
			connection.disconnect();
			return buffer.toString().trim();
		} catch (Exception e) {
			if(connection != null) {
				connection.disconnect();
			}
			System.out.println(strUrl + "，" + e.getMessage());
			return null;
		}
	}
	
	public static void openURL(String url) {
		try {
			browse(url);
		} catch (Exception e) {
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void browse(String url) throws Exception {
		String osName = System.getProperty("os.name", "");
		if (osName.startsWith("Mac OS")) {
			Class fileMgr = Class.forName("com.apple.eio.FileManager");
			Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
			openURL.invoke(null, new Object[] { url });
		} else if (osName.startsWith("Windows")) {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		} else {
			String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
			String browser = null;
			for (int count = 0; count < browsers.length && browser == null; count++)
				if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
					browser = browsers[count];
			if (browser == null)
				throw new Exception("Could not find web browser");
			else
				Runtime.getRuntime().exec(new String[] { browser, url });
		}
	}
}
