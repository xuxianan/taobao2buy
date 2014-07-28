package com.miller.tb.buy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

import com.miller.tb.common.HttpRequestTool;

/**
 * 刷新tb二手市场指定物品
 */
public class Refresh {
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < IConfig.OPEN_THREAD_COUNT; i++) {
			pool.submit(new RefreshThread(i));
		}
	}

	/**
	 * 任务开始
	 * @param step
	 * @throws Exception
	 */
	public static void start(int step) throws Exception {
		while (true) {
			Thread.sleep(IConfig.LAZY_TIME); step++;
			System.out.println("总共请求次数为：" + step + "，已经成功刷到：" + IConfig.BLACK.size() + "件物品！");
			// parsing(HttpRequestTool.getPageContent(IConfig.REQUEST_URL, "GET", 100500, "GBK"));
			parsing(HttpRequestTool.request(IConfig.REQUEST_URL));
			start(step);
		}
	}
	
	/**
	 * 解析请求url对应的html得到item-info div
	 * @param inputHtml
	 */
	private static void parsing(String inputHtml) throws Exception {
		Set<String> urlLinkSet = new HashSet<String>();
		Parser parser = new Parser(inputHtml);
		AndFilter filter1 = new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("class", "item-info"));
		NodeList nodes = parser.extractAllNodesThatMatch(filter1);
		if (nodes != null) {
			for (int i = 0; i < nodes.size(); i++) {
				Div div = (Div) nodes.elementAt(i);
				Double price = getPrice(div.getChildren());
				getLinks(div.getChildren(), price, urlLinkSet);
			}
		}
		
		for (String url : urlLinkSet) {
			IConfig.BLACK.add(url);
			HttpRequestTool.openURL(url);
		}
	}
	
	/**
	 * 获取物品链接
	 * @param childNode item-info div 包含链接、描述、价格等
	 * @param price 当前物品价格
	 * @param urlLinkSet 链接地址set
	 */
	private static void getLinks(NodeList childNode, Double price, Set<String> urlLinkSet) {
		for (int i = 0; i < childNode.size(); i++) {
			if (childNode.elementAt(i) instanceof HeadingTag) {
				HeadingTag headingTag = (HeadingTag) childNode.elementAt(i);
				LinkTag linkTag = (LinkTag) headingTag.getFirstChild();
				for (String key : IConfig.ITEM_MAP.keySet()) {
					if (!key.contains("_")) 
						continue;
					
					String has = key.split("_")[0];
					String noHas = key.split("_")[1];
					String urlLowerVal = linkTag.getLinkText().toLowerCase();
					
					if (urlLowerVal.contains(has) && !urlLowerVal.contains(noHas)) {
						if (!IConfig.BLACK.contains(linkTag.getLink()) && price <= IConfig.ITEM_MAP.get(key)) {
							urlLinkSet.add(linkTag.getLink());
						}
					}
				}
			}
		}
	}
	
	/**
	 * 获取当前物品价格
	 * @param childNode item-info div 包含链接、描述、价格等
	 * @return 当前物品价格
	 */
	private static Double getPrice(NodeList childNode) {
		Double price = 0.0;
		for (int i = 0; i < childNode.size(); i++) {
			if (childNode.elementAt(i) instanceof Div) {
				if (((Div) childNode.elementAt(i)).getAttribute("class").equalsIgnoreCase("item-price price-block")) {
					Span span = (Span) ((Div) childNode.elementAt(i)).getFirstChild();
					TextNode textNode = (TextNode) span.getChild(4);
					price = Double.valueOf(textNode.getText());
				}
			}
		}
		return price;
	}
}
