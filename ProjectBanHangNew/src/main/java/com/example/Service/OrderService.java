package com.example.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Account;
import com.example.Entity.Category;
import com.example.Entity.Inventories;
import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.Orders;
import com.example.Entity.Product;
import com.example.Entity.ProductVersion;
import com.example.Entity.SaleDiscount;
import com.example.Entity.SaleDiscountId;
import com.example.Entity.Variant;
import com.example.From.InventoriesForm;
import com.example.From.OrdersForm;
import com.example.Repository.IOrderRepository;
import com.example.Repository.ISaleDiscountRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class OrderService implements IOrderService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private IOrderRepository service;

	@Autowired
	private IVariantService variantService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountService accountService;
	@Autowired
	private IInventoryService inventoryService;

	@Autowired
	private IProductService productService;

	@Autowired
	private ISaleService saleService;
	
	@Autowired
	private ISaleDiscountService saleDiscountService;

	@Autowired
	private ISaleDiscountRepository saleDiscountRepository;
	
	@Override
	public List<Orders> getallOrders() {
		return service.findAll();
	}

	@Transactional
	@Override
	public Orders createOrder(OrdersForm form, int id) {
		try {
			form.setAccount(accountService.findAccountByID(id));
			Orders orders = modelMapper.map(form, Orders.class);
			Orders orders2 = service.save(orders);
			entityManager.refresh(orders2);
			return orders2;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Orders updateOrder(int idAccount, OrdersForm form) {

//		try {
		Orders orders = getOrderByID(form.getOrders_id());
		orders.setAddressorder(form.getAddressorder());
		orders.setTotal_amount(form.getTotal_amount());
		Account account = accountService.findAccountByID(idAccount);
		orders.setAccount(account);
		orders.setStatus("Pending");
		orders.setUpdated_at(LocalDateTime.now());
		return service.save(orders);
//		} catch (Exception e) {
//			return null;
//		}

	}

	@Override
	public Orders getOrderPrepare(int id) {
		List<Orders> list = getallOrders();

		for (Orders orders : list) {
			if (orders.getAccount().getAccount_id() == id && orders.getStatus().equals("Prepare")) {
				return orders;
			}
		}
		return null;
	}

	@Override
	public Orders getOrderByID(int id) {

		return service.findById(id).get();
	}

	@Override
	public int createOrderPending(OrdersForm form) {
		try {
			form.setStatus("Pending");
			Orders orders = modelMapper.map(form, Orders.class);

			return service.save(orders).getOrders_id();
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public boolean updateAdressOrder(int id, String address) {
		try {
			Orders orders = getOrderByID(id);

			orders.setAddressorder(address);
			service.save(orders);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Map<LocalDate, Integer> totalOrderMoney() {
		Map<LocalDate, Integer> myMap = new LinkedHashMap<LocalDate, Integer>();
		List<Orders> list = getallOrders();
		for (Orders orders : list) {
			LocalDate date = orders.getCreated_at().toLocalDate();
			int sum = 0;
			for (Orders orders2 : list) {

				if (orders2.getOrders_id() == orders.getOrders_id()) {
					continue;
				} else if (date.equals(orders2.getCreated_at().toLocalDate()) && !orders2.getStatus().equals("Prepare")
						&& !orders2.getStatus().equals("Cancel")) {
					sum += orders2.getTotal_amount();
				}
			}
			myMap.put(date, sum);
		}

		return myMap;
	}

	@Override
	public Orders updateStatusOrders(int id, String status) {
		Orders orders = getOrderByID(id);
		if (status.equals("Shipping")) {
			orders.setShipping_at(LocalDateTime.now());
			
			List<OrderItem> orderItems = orders.getOrderItems();
			for (OrderItem orderItem : orderItems) {

				List<Product> product = productService.getAllProducts();
				for (Product pro : product) {
					List<Category> categories = pro.getCategories();
					for (Category cate : categories) {
						String colorCate = cate.getCatetoryColor().getColor_name(),
								colorItem = orderItem.getColor().getColor_name();
						String sizeCate = cate.getCatetorySize().getSize_name().toString(),
								sizeItem = orderItem.getSize().getSize_name().toString();
						if (colorCate.equals(colorItem) && sizeCate.equals(sizeItem)) {
							List<Variant> variants = cate.getCatetoryColor().getVariants();
							for (Variant var : variants) {
								String colorVar = var.getColor().getColor_name();
								String sizeVar = var.getSize().getSize_name().toString();
								int idProduct = var.getProductversion().getProduct().getProduct_id();
								if (colorItem.equals(colorVar) && sizeItem.equals(sizeVar)
										&& idProduct == orderItem.getProduct().getProduct_id()) {
									List<Inventories> inventories = var.getInventories();
									int index = inventories.size() - 1;
									if (inventories.get(index).getChange_amount() > 0) {
										InventoriesForm form = new InventoriesForm();
										int amountleft = inventories.get(index).getChange_amount()
												- orderItem.getQuantity();
										form.setChange_amount(amountleft);
										form.setInventoryVariant(var.getVariants_id());
										form.setEvent_type("Đang_giao_hàng");
										form.setOrder_id(orders.getOrders_id());
										form.setAmount(orderItem.getQuantity());
										Inventories iven = inventoryService.createInventory(form);

										var.getInventories().add(iven);

										SaleDiscountId saleDiscountId = saleDiscountService
												.getSaleDiscountId(var.getSales().getId());
										if (saleDiscountId!=null) {
											int saleID=saleDiscountId.getSales(),discountID=saleDiscountId.getDiscount();
											if (saleID!=0 &&discountID!=0) {
												saleDiscountService.updateSaleDiscountAll(saleID,
														discountID, orderItem.getQuantity(),saleService.getSalePrice(var.getVariants_id()));
											}
										}
										
										
//										saleDiscountRepository.save(null)
										if (iven.getChange_amount() < 10) {
											List<ProductVersion> productVersions = pro.getProductVersion();
											for (ProductVersion pVersion : productVersions) {
												List<Variant> vList = pVersion.getVariants();
												for (Variant variant : vList) {
													int indextemp = variant.getInventories().size() - 1;
													List<Inventories> iList = variant.getInventories();
													int invenId = iList.get(indextemp).getInventory_id();
													int idProductVariant = variant.getProductversion().getProduct()
															.getProduct_id();
													String variantColor = variant.getColor().getColor_name(),
															variantSize = variant.getSize().getSize_name().toString();

													if (invenId != iven.getInventory_id()
															&& variantColor.equals(colorItem)
															&& variantSize.equals(sizeItem)
															&& idProductVariant == idProduct) {
														InventoriesForm inventoriesForm = new InventoriesForm();
														inventoriesForm.setAmount(amountleft);
														inventoriesForm.setChange_amount(
																iList.get(indextemp).getChange_amount() + amountleft);
														inventoriesForm.setEvent_type("Cộng hàng thêm từ inventory id: "
																+ iven.getInventory_id());
														inventoriesForm.setOrder_id(0);
														inventoryService.updateInventory(form);
													}
													
													

												}
											}
										}

									}

								}
							}
						}
					}
				}

			}
			orders.setStatus(status);
			return service.save(orders);
			
			
		} else if (status.equals("Completed")) {
			orders.setComplete_at(LocalDateTime.now());
			
			List<OrderItem> orderItems = orders.getOrderItems();
			for (OrderItem orderItem : orderItems) {

				List<Product> product = productService.getAllProducts();
				for (Product pro : product) {
					List<Category> categories = pro.getCategories();
					for (Category cate : categories) {
						String colorCate = cate.getCatetoryColor().getColor_name(),
								colorItem = orderItem.getColor().getColor_name();
						String sizeCate = cate.getCatetorySize().getSize_name().toString(),
								sizeItem = orderItem.getSize().getSize_name().toString();
						if (colorCate.equals(colorItem) && sizeCate.equals(sizeItem)) {
							List<Variant> variants = cate.getCatetoryColor().getVariants();
							for (Variant var : variants) {
								String colorVar = var.getColor().getColor_name();
								String sizeVar = var.getSize().getSize_name().toString();
								int idProduct = var.getProductversion().getProduct().getProduct_id();
								if (colorItem.equals(colorVar) && sizeItem.equals(sizeVar)
										&& idProduct == orderItem.getProduct().getProduct_id()) {
									List<Inventories> inventories = var.getInventories();
									int index = inventories.size() - 1;
									if (inventories.get(index).getChange_amount() > 0) {
										InventoriesForm form = new InventoriesForm();
										int amountleft = inventories.get(index).getChange_amount();
										form.setChange_amount(amountleft);
										form.setInventoryVariant(var.getVariants_id());
										form.setEvent_type("Giao hàng thành công");
										form.setOrder_id(orders.getOrders_id());
										form.setAmount(orderItem.getQuantity());
										Inventories iven = inventoryService.createInventory(form);

										var.getInventories().add(iven);														
									}


								}
							}
						}
					}
				}

			}
		} else if (status.equals("Cancel")) {
			if (orders.getStatus().equals("Shipping") || orders.getStatus().equals("Pending")) {
				List<OrderItem> orderItems = orders.getOrderItems();
				for (OrderItem orderItem : orderItems) {

					List<Product> product = productService.getAllProducts();
					for (Product pro : product) {
						List<Category> categories = pro.getCategories();
						for (Category cate : categories) {
							String colorCate = cate.getCatetoryColor().getColor_name(),
									colorItem = orderItem.getColor().getColor_name();
							String sizeCate = cate.getCatetorySize().getSize_name().toString(),
									sizeItem = orderItem.getSize().getSize_name().toString();
							if (colorCate.equals(colorItem) && sizeCate.equals(sizeItem)) {
								List<Variant> variants = cate.getCatetoryColor().getVariants();
								for (Variant var : variants) {
									String colorVar = var.getColor().getColor_name();
									String sizeVar = var.getSize().getSize_name().toString();
									if (colorItem.equals(colorVar) && sizeItem.equals(sizeVar)) {
										InventoriesForm form = new InventoriesForm();
										List<Inventories> inventories = var.getInventories();
										int index = inventories.size() - 1;
										form.setChange_amount(inventories.get(index).getChange_amount());
										form.setInventoryVariant(var.getVariants_id());
										form.setEvent_type("Trả_hàng");
										form.setOrder_id(orders.getOrders_id());
										form.setAmount(orderItem.getQuantity());
										inventoryService.createInventory(form);
										orders.setStatus(status);
										return service.save(orders);
									}
									SaleDiscountId saleDiscountId = saleDiscountService
											.getSaleDiscountId(var.getSales().getId());
									if (saleDiscountId!=null) {
										int saleID=saleDiscountId.getSales(),discountID=saleDiscountId.getDiscount();
										if (saleID!=0 &&discountID!=0) {
											saleDiscountService.updateSaleDiscountAll(saleID,
													discountID, -orderItem.getQuantity(),-saleService.getSalePrice(var.getVariants_id()));
										}
									}
								}
							}
						}
					}

				}
			} else {
				List<OrderItem> orderItems = orders.getOrderItems();
				for (OrderItem orderItem : orderItems) {

					List<Product> product = productService.getAllProducts();
					for (Product pro : product) {
						List<Category> categories = pro.getCategories();
						for (Category cate : categories) {
							String colorCate = cate.getCatetoryColor().getColor_name(),
									colorItem = orderItem.getColor().getColor_name();
							String sizeCate = cate.getCatetorySize().getSize_name().toString(),
									sizeItem = orderItem.getSize().getSize_name().toString();
							if (colorCate.equals(colorItem) && sizeCate.equals(sizeItem)) {
								List<Variant> variants = cate.getCatetoryColor().getVariants();
								for (Variant var : variants) {
									String colorVar = var.getColor().getColor_name();
									String sizeVar = var.getSize().getSize_name().toString();
									if (colorItem.equals(colorVar) && sizeItem.equals(sizeVar)) {
										InventoriesForm form = new InventoriesForm();
										List<Inventories> inventories = var.getInventories();
										int index = inventories.size() - 1;
										form.setChange_amount(
												inventories.get(index).getChange_amount() + orderItem.getQuantity());
										form.setInventoryVariant(var.getVariants_id());
										form.setEvent_type("Trả_hàng");
										form.setOrder_id(orders.getOrders_id());
										form.setAmount(orderItem.getQuantity());
										inventoryService.createInventory(form);
										orders.setStatus(status);
										return service.save(orders);
									}
								}
							}
						}
					}
				}

			}
		}
		orders.setStatus(status);
		return service.save(orders);
	}

	@Override
	public Orders updateMoneyOrders(int id) {
		Orders orders = getOrderByID(id);
		List<OrderItem> orderItems = orders.getOrderItems();
		int sum = 0;
		for (OrderItem item : orderItems) {
			sum += item.getProduct_price();
		}
		orders.setTotal_amount(sum);
		return service.save(orders);
	}

}
