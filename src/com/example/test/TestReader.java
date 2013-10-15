package com.example.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

// XMLデータ解析クラス
final class TestReader {

	// 文字コード
	private static final String ENCODE = "UTF-8";
	// Google News Feed
	private static final String GOOGLE_NEWS_RSS_TOPIC = "http://weather.livedoor.com/forecast/rss/earthquake.xml";
	private static final String GOOGLE_NEWS_RSS_TOPIC2 =
	"https://help.github.com/articles/creating-an-oauth-token-for-command-line-use";
	// コンストラクタ										
	private TestReader() {
	}

	// getGoogleNewsItemListメソッド(配信記事受信処理)
	static List<TestItem> getZisinList(String data2) {
		List<TestItem> zisinItems = new ArrayList<TestItem>();
		HttpClient client = null;
		InputStream in = null;
		//通信結果用
		int httpStatus = 0;

		try {
			
			//httpClientクラスのclientインスタンス作成
			client = new DefaultHttpClient();
			
			// タイムアウト処理 
			HttpParams httpParams = client.getParams();
			//接続のタイムアウト（単位：ms）
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			//データ取得のタイムアウト（単位：ms）
			HttpConnectionParams.setSoTimeout(httpParams, 10000);
			
			//HttpGetクラスのgetインスタンス作成
			HttpGet get = new HttpGet();
			get.setURI(new URI(GOOGLE_NEWS_RSS_TOPIC));
			if (HttpStatus.SC_OK >= httpStatus) {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();
			xmlPullParser.setInput(new StringReader(data2));
			
			int eventType = xmlPullParser.next();
			boolean findZisinTag = false;
			
			String title = null;
			String category = null;
			String description = null;
			String link = null;
				
			while (XmlPullParser.END_DOCUMENT != eventType) {
				
				switch (eventType) {
				
				case XmlPullParser.START_TAG:
					String tagName = xmlPullParser.getName();
					
					
					if (!findZisinTag) {
						findZisinTag = "item".equals(tagName);
						break;
					}
					
					
					if ("title".equals(tagName)) {
						// 記事の見出し取得
						title = xmlPullParser.nextText();
					} else if ("category".equals(tagName)) {
						category = xmlPullParser.nextText();
					} else if ("description".equals(tagName)) {
						description = xmlPullParser.nextText();
					} else if ("link".equals(tagName)) {
						link = xmlPullParser.nextText();
					
					
					//if ("title".equals(tagName)) {
					//	title = xmlPullParser.getAttributeValue(null, "title");
					//	link = xmlPullParser.getAttributeValue(null, "link");
					//	category = xmlPullParser.getAttributeValue(null, "category");
					//	description = xmlPullParser.getAttributeValue(null, "description");
						zisinItems.add(new TestItem(title, category,description,link));
					}
					break;
				default:
					break;
				}
				eventType = xmlPullParser.next();
			}
			for (int i = 0; i < zisinItems.size(); i++) {
				Log.d("title", "title[" + i + "] =" + zisinItems.get(i).getTitle());
				Log.d("category", "category[" + i + "] =" + zisinItems.get(i).getCategory());
				Log.d("description", "description[" + i + "] =" + zisinItems.get(i).getDescription());
			}}
		} catch (URISyntaxException e) {
			e.printStackTrace();
	} catch (ClientProtocolException e) {
		e.printStackTrace();		
	} catch (XmlPullParserException e) {		
	} catch (IOException e) {
	}
	return zisinItems;
}
}