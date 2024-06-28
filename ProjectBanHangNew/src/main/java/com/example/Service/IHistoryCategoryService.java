package com.example.Service;

import java.util.List;

import com.example.Entity.HistoryCategory;
import com.example.From.HistoryCategoryForm;

public interface IHistoryCategoryService {
	public List<HistoryCategory> getAllHistoryCategories();

	public HistoryCategory getHistoryCategoriesByID(int id);

	public HistoryCategory createHistoryCategories(HistoryCategoryForm form);

	public HistoryCategory deleteHistoryCategoriesByID(int id);

	public HistoryCategory updateHistoryCategoriesByID(HistoryCategoryForm form);

	
}
