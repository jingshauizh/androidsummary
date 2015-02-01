package com.ericsson.iodsearch.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class AppJsonFileReader {
	public static String getJson(Context context, String fileName) {

		StringBuilder stringBuilder = new StringBuilder();
		try {
			AssetManager assetManager = context.getAssets();
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					assetManager.open(fileName)));
			String line;
			while ((line = bf.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	public static List<Map<String, String>> setData(String str) {
		try {
			List<Map<String, String>> data = new ArrayList<Map<String, String>>();
			JSONArray array = new JSONArray(str);
			int len = array.length();
			Map<String, String> map;
			for (int i = 0; i < len; i++) {
				JSONObject object = array.getJSONObject(i);
				map = new HashMap<String, String>();
				map.put("operator", object.getString("operator"));
				map.put("loginDate", object.getString("loginDate"));
				map.put("logoutDate", object.getString("logoutDate"));
				data.add(map);
			}
			return data;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static List<Map<String, String>> setListData(String str) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		try {

			JSONArray array = new JSONArray(str);
			int len = array.length();
			Map<String, String> map;
			for (int i = 0; i < len; i++) {
				JSONObject object = array.getJSONObject(i);
				map = new HashMap<String, String>();
				map.put("imageId", object.getString("imageId"));
				map.put("title", object.getString("title"));
				map.put("subTitle", object.getString("subTitle"));
				map.put("type", object.getString("type"));
				data.add(map);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;

	}
	
	public static List<Map<String, String>> setImageListData(String str) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		try {

			JSONArray array = new JSONArray(str);
			int len = array.length();
			Map<String, String> map;
			for (int i = 0; i < len; i++) {
				JSONObject object = array.getJSONObject(i);
				map = new HashMap<String, String>();
				map.put("imageUrl", object.getString("imageUrl"));
				map.put("imageName", object.getString("imageName"));			
				data.add(map);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;

	}
	
	public static Map<String, String> setInfoMapData(String str) {		
		Map<String, String> map = null;	
		try {
			Log.i("map", "str="+ str);
			JSONObject object = new JSONObject(str);
			Log.i("map", "name="+ object.getString("name"));
			map = new HashMap<String, String>();
			map.put("name", object.getString("name"));
			map.put("id", object.getString("id"));
			map.put("category", object.getString("category"));
			map.put("attribute", object.getString("attribute"));
			map.put("description", object.getString("description"));
			map.put("imageUrlFull", object.getString("imageUrlFull"));
			
			Log.i("map", "name="+ object.getString("name"));
			Log.i("map", "id="+ object.getString("id"));
			Log.i("map", "category="+ object.getString("category"));
			Log.i("map", "attribute="+ object.getString("attribute"));
			Log.i("map", "description="+ object.getString("description"));
			Log.i("map", "imageUrlFull="+ object.getString("imageUrlFull"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;

	}
}
