package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Entity.Images;
import com.example.Entity.ImagesDTO;
import com.example.Entity.ImagesDTOOnlyID;
import com.example.Entity.PurchaseOderItems;
import com.example.Enum.SizeEnum;
import com.example.From.PurchaseOderItemsForm;


public interface IPurchaseOderItemsService {
	public List<PurchaseOderItems> getAllPurchaseOderItems();

	public PurchaseOderItems getPurchaseOderItemsByID(int id);

	public PurchaseOderItems createPurchaseOderItems(PurchaseOderItemsForm form);

	public PurchaseOderItems updatePurchaseOderItems(PurchaseOderItemsForm form);

	public PurchaseOderItems deletePurchaseOderItems(int id);
	
	public SizeEnum getSizeVariant(int id);

	public String getColorVariant(int id);

	public List<ImagesDTOOnlyID> getImages(int idvariant);
	
	public PurchaseOderItems updatePurchaseOderItemsQuantity(PurchaseOderItemsForm form);
 
}
