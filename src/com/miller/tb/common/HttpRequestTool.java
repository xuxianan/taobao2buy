package com.miller.tb.common;

import java.lang.reflect.Method;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpRequestTool {
	
	/**
	 * Http请求
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String request(String url) throws Exception {
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 1000 * 10;
		int timeoutSocket = 1000 * 60 * 10;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		DefaultHttpClient client = new DefaultHttpClient(httpParameters);
		HttpPost post = new HttpPost(url);
		
		HttpResponse response = client.execute(post);
		return EntityUtils.toString(response.getEntity());
	}
	
	/**
	 * 打开url
	 * @param url
	 */
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
