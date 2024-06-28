package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Category;
import com.example.Entity.HistoryCategory;
import com.example.From.HistoryCategoryForm;
import com.example.Repository.IHistoryCategoryRepository;

@Service
public class HistoryCategoryService implements IHistoryCategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IHistoryCategoryRepository historyCategoryRepository;

	@Autowired
	private ICategoryService categoryService;

	@Override
	public List<HistoryCategory> getAllHistoryCategories() {
		// TODO Auto-generated method stub
		return historyCategoryRepository.findAll();
	}

	@Override
	public HistoryCategory getHistoryCategoriesByID(int id) {
		// TODO Auto-generated method stub
		return historyCategoryRepository.findById(id).get();
	}

	@Override
	public HistoryCategory createHistoryCategories(HistoryCategoryForm form) {
		HistoryCategory historyCategory = modelMapper.map(form, HistoryCategory.class);

		Category category = categoryService.findCategoryByID(form.getCategory());
		if (category == null) {
			throw new RuntimeException("Category not found for ID: " + form.getCategory());

		}
		historyCategory.setCategory(category);

		return historyCategoryRepository.save(historyCategory);
	}

	@Override
	public HistoryCategory deleteHistoryCategoriesByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoryCategory updateHistoryCategoriesByID(HistoryCategoryForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
