package com.example.Service;

import java.util.List;

import com.example.Entity.HistoryPuchaseOrder;
import com.example.From.HistoryPuchaseOrderForm;

public interface IHistoryPuchaseOrderService {
	
	public List<HistoryPuchaseOrder> getAlList();
	public HistoryPuchaseOrder geHistoryPuchaseOrderByID(int id);
	public HistoryPuchaseOrder createHistoryPuchaseOrder(HistoryPuchaseOrderForm form);
	public boolean deleteHistoryPuchaseOrder(int id);
	public HistoryPuchaseOrder updateHistoryPuchaseOrder(HistoryPuchaseOrderForm form);
}
