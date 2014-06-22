package com.solt.jdc.smartboy.adapter;

import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.solt.jdc.smartboy.MainActivity;
import com.solt.jdc.smartboy.R;
import com.solt.jdc.smartboy.dto.Item;

public class MenuItemsAdapter extends BaseAdapter {
	
	private List<Item> items;
	private LayoutInflater inflater;

	public MenuItemsAdapter(List<Item> items, LayoutInflater inflater) {
		super();
		Log.d(MainActivity.TABLE, String.format("Menu Size : %d", items.size()));
		this.items = items;
		this.inflater = inflater;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = inflater.inflate(R.layout.grid_menu_item, null);
			Item item = this.items.get(position);
			TextView text = (TextView) view.findViewById(R.id.menu_name);
			text.setText(String.format("%s : %d", item.getName(), item.getPrice()));
		} else {
			view = (LinearLayout) convertView;
		}
		return view;
	}

}
