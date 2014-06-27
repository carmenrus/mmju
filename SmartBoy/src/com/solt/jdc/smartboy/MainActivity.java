package com.solt.jdc.smartboy;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.solt.jdc.smartboy.dto.Category;
import com.solt.jdc.smartboy.dto.Item;
import com.solt.jdc.smartboy.dto.Table;
import com.solt.jdc.smartboy.fragment.TablesFragment;
import com.solt.jdc.smartboy.util.JSonPerser;
import com.solt.jdc.smartboy.util.LocalDataGeeter;
import com.solt.jdc.smartboy.util.LocalDatabaseManager;

public class MainActivity extends ActionBarActivity {

	public static final String TABLE = "TABLE";
	private List<Category> categories;
	private List<Item> items;
	private List<Table> tables;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new TablesFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_update_menu) {
			MasterDataGetterTask task = new MasterDataGetterTask();
			task.execute();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class MasterDataGetterTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			LocalDataGeeter getter = new LocalDataGeeter(new JSonPerser());
			categories = getter.getCategories();
			items = getter.getItems();
			tables = getter.getTables();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			AlertDialog.Builder b = new Builder(MainActivity.this);
			b.setMessage("Download complete. Do you want to reload master data?");
			b.setNegativeButton("Cancel", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			b.setPositiveButton("OK", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					MasterDataLoaderTask task = new MasterDataLoaderTask();
					task.execute();
					dialog.dismiss();
				}
			});
			b.create().show();
		}

	}

	private class MasterDataLoaderTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			LocalDatabaseManager dbm = new LocalDatabaseManager(
					getApplicationContext());
			dbm.reload(categories, items, tables);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(),
					"Complete Data load operation.", Toast.LENGTH_SHORT).show();
		}

	}

}
