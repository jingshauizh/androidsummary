package com.jason.listviewoverview.adapter.generic;

import java.util.List;
import java.util.Map;

import com.jason.listviewoverview.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GenericAdapter extends BaseAdapter {
	
	private List<Map<String, String>> itemDataList;
	
	private LayoutInflater listContainer;   
	public GenericAdapter(Context context, List<Map<String, String>> itemDataList){
		this.itemDataList = itemDataList;
		listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文   
	}
	
    private final class ListItemView{                //自定义控件集合     
        public ImageView image;     
        public TextView title;
        public TextView subTitle;   
             
 } 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.e("", "itemDataList.size()="+itemDataList.size());
		return itemDataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemDataList.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		final ListItemView  listItemView;
		if(convertView == null){
			convertView = listContainer.inflate(R.layout.list_item, null);  
			listItemView = new  ListItemView(); 
			listItemView.image = (ImageView)convertView.findViewById(R.id.imageId);
			listItemView.title = (TextView)convertView.findViewById(R.id.title);
			listItemView.subTitle = (TextView)convertView.findViewById(R.id.subTitle);
		}
		else{
			listItemView = (ListItemView)convertView.getTag();   
		}
		Log.e("", "position="+position);
		Log.e("", "itemDataList.get(position)="+itemDataList.get(position).toString());
		if(listItemView != null && listItemView.image != null){
			//int resId=listContainer.getContext().getResources().getIdentifier("icon","drawable",null);
			listItemView.image.setImageResource(R.drawable.ic_launcher);
		}
		if(listItemView != null && listItemView.title != null){
			listItemView.title.setText((String) itemDataList.get(position).get("title")); 
		}
		if(listItemView != null && listItemView.subTitle != null){
			listItemView.subTitle.setText((String) itemDataList.get(position).get("subTitle")); 
		}
		return convertView;
	}
}
