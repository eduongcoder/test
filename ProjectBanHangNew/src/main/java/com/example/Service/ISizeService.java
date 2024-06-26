package com.example.Service;

import java.util.List;

import com.example.Entity.Size;
import com.example.From.SizeForm;

public interface ISizeService {
	public List<Size> getAllList();
	public Size getSizeByID(int id);
	public boolean deleteSize(int id);
	public Size createSize(SizeForm form);
}
