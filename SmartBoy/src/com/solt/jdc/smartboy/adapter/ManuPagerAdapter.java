package com.solt.jdc.smartboy.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.solt.jdc.smartboy.dto.Category;
import com.solt.jdc.smartboy.fragment.ManuItemsFragment;
import com.solt.jdc.smartboy.util.LocalManager;
import com.solt.jdc.smartboy.util.LocalTestManager;

public class ManuPagerAdapter extends FragmentPagerAdapter{
	private List<Category> categories;
	private LocalManager manager;
	
	public ManuPagerAdapter(FragmentManager fm) {
		super(fm);
		manager = LocalTestManager.getTestLocalManaget();
		categories = manager.getCategories();
	}

	@Override
	public Fragment getItem(int position) {
		return ManuItemsFragment.newInstance(this.categories.get(position).getId());
	}

	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return this.categories.get(position).getName();
	}
}
