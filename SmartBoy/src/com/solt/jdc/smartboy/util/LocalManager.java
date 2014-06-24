package com.solt.jdc.smartboy.util;

import java.util.List;

import com.solt.jdc.smartboy.dto.Category;
import com.solt.jdc.smartboy.dto.Item;
import com.solt.jdc.smartboy.dto.Table;

public interface LocalManager {

	public List<Table> getTables();
	public List<Category> getCategories();

	public Table getTable(int tableId);
	public List<Item> getItems();
	public List<Item> getItems(int category);
	public Item getItem(int id);

}
