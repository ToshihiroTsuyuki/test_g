package com.example.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpStatus;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetHTML {

	 public static String getHtmltoString(String urlS) {
		
		 
		 try {int sdk = android.os.Build.VERSION.SDK_INT;

			// アンドロイ�?系以降用
			if (sdk > android.os.Build.VERSION_CODES.GINGERBREAD_MR1) {
				HttpURLConnection http = null;
				InputStream in = null;
				ByteArrayOutputStream bo = new ByteArrayOutputStream();
				ByteArrayInputStream in2 = null;			
				
				// URLにHTTP接�?
				URL url = new URL(urlS);
				http = (HttpURLConnection) url.openConnection();
				// タイ�?��ウト�?�??�リクエスト�?
				// 読み取りタイ�?��ウトを、指定されたミリ秒単位�?タイ�?��ウトに設定します�?
				http.setReadTimeout(10000);
				// 参�?するリソースへの通信リンクのオープン時に、指定されたミリ秒単位�?タイ�?��ウト�?が使用されるよ�?��設定します�?
				http.setConnectTimeout(10000);

				// GET通信に設�?
				http.setRequestMethod("GET");

				// 接続（リクエスト�?信??
				http.connect();

				// レスポンスコード�?取�?
				int status = http.getResponseCode();

				// エラー
				if (status != HttpStatus.SC_OK) {
					throw new Exception("");
				} else {
					// ストリー�?���??タ取�?
					in = http.getInputStream();
					int i = 0;
					while ((i = in.read()) != -1) {
					    bo.write(i);
					}
					
					byte[] bytes = bo.toByteArray();					
					in2 = new ByteArrayInputStream(bytes);
					
					// ストリー�?��ら文字�?変換
					InputStreamReader reader = new InputStreamReader(in2);					
					StringBuilder builder = new StringBuilder();
					char[] buf = new char[1024];
					int numRead;
					while (0 <= (numRead = reader.read(buf))) {
						builder.append(buf, 0, numRead);
					}
					
					
					String type = "";
					String b = builder.toString();
					
					// タグに入ってるCharset = ?�？？�?部�?��とってくる�?
					int aaa = b.indexOf("charset=");					
					if (aaa == -1) {
						aaa = b.indexOf("encoding=");
					}					
					b = b.substring(aaa);			
					int bbb = b.indexOf(">");			
					String target = b.substring(0,bbb);
					
					if (target.indexOf("UTF-8") > 0) {
						type = "UTF-8";
					}else if (target.indexOf("Shift_JIS") > 0){
						type = "Shift_JIS";
					}else {
						type = "UTF-8";
					}
					
					
					// そ�?中身のコードを�?���?に変換
					in2 = new ByteArrayInputStream(bytes);
					reader = new InputStreamReader(in2,type);
					builder = new StringBuilder();
					buf = new char[1024];
					numRead = 0;
					while (0 <= (numRead = reader.read(buf))) {
						builder.append(buf, 0, numRead);
					}
					return builder.toString();
				}
				
				
			} else {
				// アンドロイ�?.3系以下用
				HttpGet httpGet = new HttpGet(urlS);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				// タイ�?��ウト�?�?
				HttpParams httpParams = httpClient.getParams();
				//接続�?タイ�?��ウト（単位：ms??
				HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
				//�??タ取得�?タイ�?��ウト（単位：ms??
				HttpConnectionParams.setSoTimeout(httpParams, 10000);
				httpGet.setHeader("Connection", "Keep-Alive");
				HttpResponse response = httpClient.execute(httpGet);
				int status = response.getStatusLine().getStatusCode();
				if (status != HttpStatus.SC_OK) {
					throw new Exception("");
				} else {
					String htmlsource = EntityUtils.toString(
							response.getEntity(), "UTF-8");
					return htmlsource;
				}
			}
		} catch (Exception e) {
			return e.toString();
		}
	}
}
