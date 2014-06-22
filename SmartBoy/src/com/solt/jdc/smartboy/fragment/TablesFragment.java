package com.solt.jdc.smartboy.fragment;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.solt.jdc.smartboy.MainActivity;
import com.solt.jdc.smartboy.R;
import com.solt.jdc.smartboy.TabelActivity;
import com.solt.jdc.smartboy.adapter.TablesAdapter;
import com.solt.jdc.smartboy.dto.Table;
import com.solt.jdc.smartboy.util.LocalTestManager;

public class TablesFragment extends Fragment{
	public TablesFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		List<Table> tables = LocalTestManager.getTestLocalManaget()
				.getTables();
		final TablesAdapter adapter = new TablesAdapter(tables, (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		

		GridView view = (GridView)rootView;
		view.setAdapter(adapter);
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), TabelActivity.class);
				intent.putExtra(MainActivity.TABLE, (Table)adapter.getItem(position));
				startActivity(intent);
			}
		});
		
		return rootView;
	}
}
