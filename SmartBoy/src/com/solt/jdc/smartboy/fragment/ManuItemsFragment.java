package com.solt.jdc.smartboy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.solt.jdc.smartboy.R;

public class ManuItemsFragment extends Fragment{

	private static final String ARG_CATEGORY_ID = "category_id";

	public static ManuItemsFragment newInstance(int categoryId) {
		ManuItemsFragment fragment = new ManuItemsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_CATEGORY_ID, categoryId);
		fragment.setArguments(args);
		return fragment;
	}

	public ManuItemsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_new_order,
				container, false);
		GridView grid = (GridView) rootView.findViewById(R.id.menu_items);
		return rootView;
	}

}
