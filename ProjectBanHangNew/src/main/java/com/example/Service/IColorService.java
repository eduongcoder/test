package com.example.Service;

import java.util.List;

import com.example.Entity.Color;
import com.example.From.ColorForm;

public interface IColorService {
	public List<Color> getAllList();
	public Color getColorByID(int id);
	public boolean deleteColor(int id);
	public Color createColor(ColorForm form);
}
