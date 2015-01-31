/***********************************************************************
 * Author: Jason.Zhang
 *
 *
 *
 *********************************************************************************/

package com.ericsson.iodsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class HttpConnection {

	/**
	 * 卤拢麓忙脢媒戮脻
	 * 
	 * @param title
	 *            卤锚脤芒
	 * @param length
	 *            脢卤鲁陇
	 * @return
	 */
	public static boolean save(String title, String length) {
		String path = "http://192.168.0.168:8080/web/ManageServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		params.put("timelength", length);
		try {
			return sendHttpClientPOSTRequest(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 脥篓鹿媒HttpClient路垄脣脥Post脟毛脟贸
	 * 
	 * @param path
	 *            脟毛脟贸脗路戮露
	 * @param params
	 *            脟毛脟贸虏脦脢媒
	 * @param encoding
	 *            卤脿脗毛
	 * @return 脟毛脟贸脢脟路帽鲁脡鹿娄
	 */
	public static boolean sendHttpClientPOSTRequest(String path,
			Map<String, String> params, String encoding) throws Exception {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();// 麓忙路脜脟毛脟贸虏脦脢媒
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				pairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, encoding);
		HttpPost httpPost = new HttpPost(path);
		httpPost.setEntity(entity);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * 路垄脣脥Post脟毛脟贸
	 * 
	 * @param path
	 *            脟毛脟贸脗路戮露
	 * @param params
	 *            脟毛脟贸虏脦脢媒
	 * @param encoding
	 *            卤脿脗毛
	 * @return 脟毛脟贸脢脟路帽鲁脡鹿娄
	 */
	private static boolean sendPOSTRequest(String path,
			Map<String, String> params, String encoding) throws Exception {
		// title=liming&timelength=90
		StringBuilder data = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				data.append(entry.getKey()).append("=");
				data.append(URLEncoder.encode(entry.getValue(), encoding));
				data.append("&");
			}
			data.deleteCharAt(data.length() - 1);
		}
		byte[] entity = data.toString().getBytes();// 脡煤鲁脡脢碌脤氓脢媒戮脻
		HttpURLConnection conn = (HttpURLConnection) new URL(path)
				.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 脭脢脨铆露脭脥芒脢盲鲁枚脢媒戮脻
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entity);
		if (conn.getResponseCode() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * 路垄脣脥GET脟毛脟贸
	 * 
	 * @param path
	 *            脟毛脟贸脗路戮露
	 * @param params
	 *            脟毛脟贸虏脦脢媒
	 * @param encoding
	 *            卤脿脗毛
	 * @return 脟毛脟贸脢脟路帽鲁脡鹿娄
	 */
	public static boolean sendGETRequest(String path,
			Map<String, String> params, String ecoding) throws Exception {
		// http://192.168.1.100:8080/web/ManageServlet?title=xxx&timelength=90
		StringBuilder url = new StringBuilder(path);
		url.append("?");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			url.append(entry.getKey()).append("=");
			url.append(URLEncoder.encode(entry.getValue(), ecoding));
			url.append("&");
		}
		url.deleteCharAt(url.length() - 1);
		Log.e("Remote", "sendGETRequest url=" + url);
		HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
				.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			return true;
		}
		return false;
	}

	public static String sendGETJsonRequest(String path,
			Map<String, String> params, String ecoding) throws Exception {
		// http://192.168.1.100:8080/web/ManageServlet?title=xxx&timelength=90
		StringBuilder url = new StringBuilder(path);
		InputStreamReader in = null;
		String resultData = "";		
		BufferedReader buffer = null;
		url.append("?");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			url.append(entry.getKey()).append("=");
			url.append(URLEncoder.encode(entry.getValue(), ecoding));
			url.append("&");
		}
		url.deleteCharAt(url.length() - 1);
		Log.e("Remote", "sendGETRequest url=" + url);
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
					.openConnection();
			conn.setConnectTimeout(20000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				try {
					in = new InputStreamReader(conn.getInputStream());
				} catch (ConnectException e) {
					Log.e("HTTP GET", "服务器宕机了...");
					return resultData;
				}
				buffer = new BufferedReader(in);
				String inputLine = null;

				while ((inputLine = buffer.readLine()) != null) {
					resultData += inputLine + "\n";
				}
				conn.disconnect();

			}
		} catch (MalformedURLException e) {
			Log.e("HTTP GET", "域名无法解析");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (buffer != null) {
					buffer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultData;
	}
	
	public static Bitmap getHttpBitmap(String url) {
	     URL myFileUrl = null;
	     Bitmap bitmap = null;
	     try {
	          
	          myFileUrl = new URL(url);
	     } catch (MalformedURLException e) {
	          e.printStackTrace();
	     }
	     try {
	          HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
	          conn.setConnectTimeout(0);
	          conn.setDoInput(true);
	          conn.connect();
	          InputStream is = conn.getInputStream();
	          bitmap = BitmapFactory.decodeStream(is);
	          is.close();
	     } catch (IOException e) {
	          e.printStackTrace();
	     }
	     return bitmap;
	}
	
	public static String sendPOSTInfoRequest(String path,
			Map<String, String> params, String encoding) throws Exception {
		// title=liming&timelength=90
		StringBuilder url = new StringBuilder(path);
		InputStreamReader in = null;
		String resultData = "";		
		BufferedReader buffer = null;
		StringBuilder data = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				data.append(entry.getKey()).append("=");
				data.append(URLEncoder.encode(entry.getValue(), encoding));
				data.append("&");
			}
			data.deleteCharAt(data.length() - 1);
		}
		byte[] entity = data.toString().getBytes();// 脡煤鲁脡脢碌脤氓脢媒戮脻
		HttpURLConnection conn = (HttpURLConnection) new URL(path)
				.openConnection();
		conn.setConnectTimeout(20000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 脭脢脨铆露脭脥芒脢盲鲁枚脢媒戮脻
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entity);
		if (conn.getResponseCode() == 200) {
			try {
				in = new InputStreamReader(conn.getInputStream());
			} catch (ConnectException e) {
				Log.e("HTTP GET", "服务器宕机了...");
				return resultData;
			}
			buffer = new BufferedReader(in);
			String inputLine = null;

			while ((inputLine = buffer.readLine()) != null) {
				resultData += inputLine + "\n";
			}
			conn.disconnect();
		}
		return resultData;
	}
	


	
	

}
