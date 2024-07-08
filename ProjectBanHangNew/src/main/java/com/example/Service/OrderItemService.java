package com.example.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Inventories;
import com.example.Entity.OrderItem;
import com.example.Entity.Variant;
import com.example.Enum.SizeEnum;
import com.example.From.InventoriesForm;
import com.example.From.OrderitemForm;
import com.example.Repository.IOrderItemRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderItemService implements IOrderItemService {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private IOrderItemRepository service;

	@Autowired
	private ISizeService sizeService;
	
	@Autowired
	private IVariantService variantService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IProductVersionService serviceProductVersionService;

	@Autowired
	private IOrderService serviceOrder;

	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private ISaleService saleService;
	

	
	@Override
	public List<OrderItem> getAllOrderItems() {

		return service.findAll();
	}

	@Override
	@Transactional
	public OrderItem createOrderItem(OrderitemForm form, int idOrder, int idProductVersion) {
		try {

			OrderItem orderItem = modelMapper.map(form, OrderItem.class);
			orderItem.setUpdatedAt(LocalDateTime.now());
			orderItem.setOrders(serviceOrder.getOrderByID(idOrder));
			orderItem.setProductVersion(serviceProductVersionService.getProductVersionByID(idProductVersion));
			
			InventoriesForm form2=new InventoriesForm();
			//chắc chắn phải có inventory của variant đó đầu tiên thì code mới ko lỗi
			Variant variant=variantService.getVariantByID(form.getTypeOfVariant());
			List<Inventories> inventories=variant.getInventories();
			form2.setChange_amount(inventories.get(inventories.size()-1).getChange_amount()-form.getQuantity());
			form2.setEvent_type("Đặt_hàng");
			form2.setInventoryVariant(form.getTypeOfVariant());
			form2.setOrder_id(idOrder);
			form2.setAmount(form.getQuantity());
			inventoryService.createInventory(form2);
			
			OrderItem orderItem2=service.save(orderItem);
			entityManager.refresh(orderItem2);
			return orderItem2;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OrderItem> findbyIDOrdersItem(int id) {
		List<OrderItem> list = service.findAll();

		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (OrderItem orderItem : list) {
			if (orderItem.getOrders().getOrders_id() == id) {

				orderItems.add(orderItem);
			}
		}
		return orderItems;
	}

	@Override
	public OrderItem findbyID(int id) {
		return service.findById(id).get();
	}

	@Override
	public OrderItem updateOrderItem(OrderitemForm form) {

		OrderItem orderItem = modelMapper.map(form, OrderItem.class);

		service.save(orderItem);
		return null;
	}

	@Override
	public boolean updateOrderItemQuantity(int id, int quantity) {
		try {
			OrderItem orderItem = findbyID(id);
			orderItem.setQuantity(orderItem.getQuantity() + quantity);
			service.save(orderItem);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean deleteOrderItem(int id) {
		try {
			service.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
    private static final Logger logger = LoggerFactory.getLogger(OrderItemService.class);

	@Transactional
	@Override
    public boolean deleteOrderItemAll(int orderId) {
        try {
//            Query deleteSalesQuery = entityManager.createQuery("DELETE FROM Sales s WHERE s.orderitem.id = :orderId");
//            deleteSalesQuery.setParameter("orderId", orderId);
//            deleteSalesQuery.executeUpdate();
        	
            Query query = entityManager.createQuery("DELETE FROM OrderItem e WHERE e.orders.id = :orderId");
            query.setParameter("orderId", orderId);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.error("Error deleting order items by orderId: {}", orderId, e);
            return false;
        }
    }

	@Override
	public SizeEnum getSizeEnum(int idVariant) {
		Variant variant=variantService.getVariantByID(idVariant);
		SizeEnum sizeEnum=variant.getSize().getSize_name();
		return sizeEnum;
	}

	@Override
	public String getColor(int idVariant) {
		Variant variant=variantService.getVariantByID(idVariant);
		String color=variant.getColor().getColor_name();
		return color;
	}
}
