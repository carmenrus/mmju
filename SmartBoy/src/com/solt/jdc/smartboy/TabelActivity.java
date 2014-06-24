package com.solt.jdc.smartboy;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.solt.jdc.smartboy.adapter.TableDetailsAdapter;
import com.solt.jdc.smartboy.dto.Order;
import com.solt.jdc.smartboy.dto.OrderItem;
import com.solt.jdc.smartboy.dto.Table;
import com.solt.jdc.smartboy.util.SmartBoyApplication;
import com.solt.jdc.smartboy.util.RestaurantBroker;
import com.solt.jdc.smartboy.util.RestaurantTestBroker;

public class TabelActivity extends ActionBarActivity {

	private Table table;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabel);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		table = (Table) getIntent().getSerializableExtra(MainActivity.TABLE);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SmartBoyApplication service = (SmartBoyApplication) getApplication();
		service.clear();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.tabel, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_check_out:
			new Builder(this)
					.setMessage(
							String.format("Check Out : %s",
									this.table.getName()))
					.setPositiveButton("Check Out", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getApplicationContext(),
									"Check Out action has been done.",
									Toast.LENGTH_SHORT).show();
							finish();
						}
					}).setNegativeButton("Cancel", null).show();
			break;
		case R.id.action_new_order:
			Intent intent = new Intent(this, NewOrderActivity.class);
			intent.putExtra(MainActivity.TABLE, table);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Table table = (Table) getActivity().getIntent()
					.getSerializableExtra(MainActivity.TABLE);
			View rootView = inflater.inflate(R.layout.fragment_order_list,
					container, false);

			RestaurantBroker broker = RestaurantTestBroker.getTestBroker();
			List<OrderItem> allOrders = new ArrayList<OrderItem>();
			int total = 0;
			for (Order o : broker.getOrders(table.getId())) {
				allOrders.addAll(o.getItems());
				for (OrderItem oi : o.getItems()) {
					total = total + (oi.getCount() * oi.getItem().getPrice());
				}
			}

			TableDetailsAdapter adpater = new TableDetailsAdapter(allOrders,
					inflater);
			ListView listView = (ListView) rootView
					.findViewById(R.id.details_list);
			listView.setAdapter(adpater);

			((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(
					String.format("T %s : (%d)", table.getName(), total));

			return rootView;
		}
	}

}
