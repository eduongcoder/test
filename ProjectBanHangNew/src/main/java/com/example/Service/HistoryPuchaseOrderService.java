package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.HistoryPuchaseOrder;
import com.example.From.HistoryPuchaseOrderForm;
import com.example.Repository.IHistoryCategoryRepository;
import com.example.Repository.IHistoryPuchaseOrderRepository;

@Service
public class HistoryPuchaseOrderService implements IHistoryPuchaseOrderService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IHistoryPuchaseOrderRepository service;

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IPurchaseOrdersService purchaseOrdersService;
	
	@Override
	public List<HistoryPuchaseOrder> getAlList() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public HistoryPuchaseOrder geHistoryPuchaseOrderByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public HistoryPuchaseOrder createHistoryPuchaseOrder(HistoryPuchaseOrderForm form) {
		HistoryPuchaseOrder historyPuchaseOrder=modelMapper.map(form, HistoryPuchaseOrder.class);
		historyPuchaseOrder.setPuchase_order_id(purchaseOrdersService.getPurchaseOrdersByID(form.getPuchase_order_id()));
		
		return service.save(historyPuchaseOrder) ;
	}

	@Override
	public boolean deleteHistoryPuchaseOrder(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HistoryPuchaseOrder updateHistoryPuchaseOrder(HistoryPuchaseOrderForm form) {
		// TODO Auto-generated method stub
		return null;
	}


}
