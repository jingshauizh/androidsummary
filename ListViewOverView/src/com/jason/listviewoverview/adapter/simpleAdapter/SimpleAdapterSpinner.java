package com.jason.listviewoverview.adapter.simpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jason.listviewoverview.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Spinner;



public class SimpleAdapterSpinner extends Activity {
    //声明Spinner
    private Spinner sp;
    //声明Adapter
    private SimpleAdapter adapter;
    //数据源
    private String[] data = { "我是第1个列表项", "我是第2个列表项", "我是第3个列表项", "我是第4个列表项",
            "我是第5个列表项", "我是第6个列表项", "我是第7个列表项", "我是第8个列表项", "我是第9个列表项" };// 数据源-->M
    //存放数据源
    ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_spinner);
        //找到Spinner
        sp = (Spinner) findViewById(R.id.spinner1);
        //将数据添加到List<Map>中，因为SimpleAdapter只能装这样的数据
        Map<String, Object> map;
        for (int i = 0; i < data.length; i++) {
            map = new HashMap<String, Object>();
            map.put("data", data[i]);
            list.add(map);
        }
        //实例化Adapter
        adapter = new SimpleAdapter(this, list, R.layout.simple_spinner_cell, new String[] {
                "data"},
                new int[] { R.id.textView1 });
        //绑定Adapter到Spinner上
        sp.setAdapter(adapter);
    }
}

