package com.example.Service;

import java.util.List;

import com.example.Entity.Inventories;
import com.example.From.InventoriesForm;

public interface IInventoryService {
	public List<Inventories> getAllInventories();
	public Inventories getInventoriesById(int id);
	public Inventories createInventory(InventoriesForm form);
	public int updateInventory(InventoriesForm form);
	public boolean deleteInventory(int id);
	
}
