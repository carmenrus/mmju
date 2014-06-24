package com.solt.jdc.smartboy.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.solt.jdc.smartboy.MainActivity;
import com.solt.jdc.smartboy.R;
import com.solt.jdc.smartboy.adapter.MenuItemsAdapter;
import com.solt.jdc.smartboy.adapter.TableDetailsAdapter;
import com.solt.jdc.smartboy.dto.Item;
import com.solt.jdc.smartboy.dto.OrderItem;
import com.solt.jdc.smartboy.util.LocalManager;
import com.solt.jdc.smartboy.util.SmartBoyApplication;

public class ManuItemsFragment extends Fragment {

	private static final String ARG_CATEGORY_ID = "ARG_CATEGORY_ID";
	private int categoryId;
	private LocalManager local;

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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		local = (LocalManager) getActivity().getApplication();
		
		if (getArguments() != null) {
			this.categoryId = getArguments().getInt(ARG_CATEGORY_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_new_order,
				container, false);
		GridView grid = (GridView) rootView.findViewById(R.id.menu_items);

		final MenuItemsAdapter adapter = new MenuItemsAdapter(local.getItems(categoryId), inflater);
		grid.setAdapter(adapter);

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d(MainActivity.TABLE,
						String.format("Position : %d , ID : %d", position, id));
				loadItemOrderDialog(Integer.valueOf(String.valueOf(id)));
			}
		});

		return rootView;
	}

	private void loadItemOrderDialog(int itemId) {
		AlertDialog.Builder b = new Builder(getActivity());
		LayoutInflater inf = getActivity().getLayoutInflater();

		final SmartBoyApplication service = (SmartBoyApplication) getActivity().getApplication();
		
		// item
		final Item item = local.getItem(itemId);
		b.setTitle(String.format("%s : %d", item.getName(), item.getPrice()));

		// view
		View view = inf.inflate(R.layout.item_dialog, null);
		b.setView(view);
		final NumberPicker num = (NumberPicker) view.findViewById(R.id.number);
		num.setMaxValue(20);
		num.setMinValue(1);

		// buttons
		b.setNegativeButton("Cancel", null);
		b.setPositiveButton("Check", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				service.add(new OrderItem(item, num.getValue(), 0));
				loadOrderListDialog();
			}
		});
		b.setNeutralButton("Continue", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// order item
				service.add(new OrderItem(item, num.getValue(), 0));
				Toast.makeText(getActivity(), "Add to Order List",
						Toast.LENGTH_SHORT).show();
			}
		});
		b.show();
	}

	private void loadOrderListDialog() {
		LayoutInflater inf = getActivity().getLayoutInflater();
		AlertDialog.Builder b = new Builder(getActivity());
		final SmartBoyApplication service = (SmartBoyApplication) getActivity().getApplication();
		
		int total = 0;
		
		for(OrderItem o : service.getOrders()) {
			total += (o.getCount() * o.getItem().getPrice());
		}
		
		b.setTitle(String.format("Total : %d", total));
		
		View view = inf.inflate(R.layout.fragment_order_list, null);
		TableDetailsAdapter adpater = new TableDetailsAdapter(service.getOrders(), inf);
		ListView listView = (ListView) view.findViewById(R.id.details_list);
		listView.setAdapter(adpater);
		
		// button (cancel)
		b.setNegativeButton("Cancel", null);
		
		// button (Clear)
		b.setNeutralButton("Clear", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				service.clear();
				Toast.makeText(getActivity(), "Orders List has been cleared.", Toast.LENGTH_SHORT).show();
			}
		});
		
		// button (Order)
		b.setPositiveButton("Order", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				getActivity().finish();
			}
		});
		b.setView(view);
		b.show();
	}

}
