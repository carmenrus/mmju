package com.solt.jdc.smartboy.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.solt.jdc.smartboy.R;
import com.solt.jdc.smartboy.dto.Item;
import com.solt.jdc.smartboy.dto.OrderItem;

public class TableDetailsAdapter extends BaseAdapter {
	
	private List<OrderItem> items;
	private LayoutInflater inflater;
	
	public TableDetailsAdapter(List<OrderItem> items,
			LayoutInflater inflater) {
		super();
		this.items = items;
		this.inflater = inflater;
	}

	@Override
	public int getCount() {
		return (null == items)? 0 : items.size();
	}

	@Override
	public Object getItem(int position) {
		return (null == items)? null : items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null)
			v =	inflater.inflate(R.layout.table_details_row, null);
		
		TextView item = (TextView) v.findViewById(R.id.item);
		TextView count = (TextView) v.findViewById(R.id.count);
		TextView cost = (TextView) v.findViewById(R.id.cost);
		
		Item selected = items.get(position).getItem();
		int cntValue = items.get(position).getCount();
		item.setText(selected.getName());
		count.setText(String.valueOf(cntValue));
		cost.setText(String.valueOf(cntValue * selected.getPrice()));
		
		return v;
	}

}
