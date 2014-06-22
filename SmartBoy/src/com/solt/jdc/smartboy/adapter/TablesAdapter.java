package com.solt.jdc.smartboy.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.solt.jdc.smartboy.R;
import com.solt.jdc.smartboy.dto.Table;

public class TablesAdapter extends BaseAdapter  {
	private List<Table> tables;
	private LayoutInflater inflater;

	public TablesAdapter(List<Table> tables, LayoutInflater inflater) {
		super();
		this.tables = tables;
		this.inflater = inflater;
	}

	@Override
	public int getCount() {
		return tables.size();
	}

	@Override
	public Object getItem(int position) {
		return tables.get(position);
	}

	@Override
	public long getItemId(int position) {
		return tables.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = inflater.inflate(R.layout.grid_main_item, null);
			TextView text = (TextView) view.findViewById(R.id.table_name);
			text.setText(tables.get(position).getName());
		} else {
			view = (LinearLayout) convertView;
		}
		return view;
	}
}
