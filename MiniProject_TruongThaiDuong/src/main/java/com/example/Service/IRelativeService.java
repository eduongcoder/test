package com.example.Service;

import java.util.List;

import com.example.Entity.Relative;
import com.example.Form.RelativeFrom;

public interface IRelativeService {
	public Relative getRelativeByID(int id);
	public void updateRelativeByID(RelativeFrom form);
	public void deleteRelativeByID(int id);
	public List<Relative> getAllRelatives();
	public Relative createRelative(RelativeFrom form);
}
