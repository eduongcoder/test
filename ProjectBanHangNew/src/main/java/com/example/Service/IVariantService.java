package com.example.Service;

import java.util.List;

import com.example.Entity.Variant;
import com.example.From.VariantForm;

public interface  IVariantService {
	public List<Variant> getAllVariants();
	public Variant getVariantByID(int id);
	public int deleteVariant(int id);
	public Variant updateVariant(VariantForm form);
}
