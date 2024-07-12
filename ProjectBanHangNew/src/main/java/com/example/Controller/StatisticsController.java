package com.example.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Inventories;
import com.example.Entity.InventoriesReasonDTO;
import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemStatisticDTO;
import com.example.Entity.Orders;
import com.example.Entity.OrdersStatisticsDTO;
import com.example.Entity.Product;
import com.example.Entity.ProductVersion;
import com.example.Entity.TotalnAmount;
import com.example.Entity.Variant;
import com.example.Service.IOrderItemService;
import com.example.Service.IOrderService;
import com.example.Service.IProductService;
import com.example.Service.IProductVersionService;
import com.example.Service.IVariantService;

@RequestMapping("/api/statistic")
@RestController
public class StatisticsController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductVersionService productVersionService;

	@Autowired
	private IOrderItemService orderItemService;

	@Autowired
	private IVariantService variantService;

	@Autowired
	private IOrderService orderService;

	@GetMapping("/purchase")
	private Map<String, Map<Integer, Integer>> getTotalProduct(@RequestParam LocalDate startDate,
			@RequestParam LocalDate endDate) {
		Map<String, Map<Integer, Integer>> myMap = new TreeMap<>();
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			List<ProductVersion> productVersion = product.getProductVersion();
			for (ProductVersion version : productVersion) {
				if (version.getCreated_at().toLocalDate().isAfter(startDate)
						&& version.getCreated_at().toLocalDate().isBefore(endDate)) {
					String date = version.getCreated_at().toLocalDate().toString();
					myMap.computeIfAbsent(date, k -> new HashMap<>()).merge(product.getProduct_id(),
							version.getQuantity_in_stock(), Integer::sum);
				}
				;
			}
		}
		return myMap;
	}

	// api thống kê thời gian chi tiết kho hàng theo chi tiết
	@GetMapping("/inventory")
	private Map<String, Map<String, List<InventoriesReasonDTO>>> getStock() {
		Map<String, Map<String, List<InventoriesReasonDTO>>> myMap = new TreeMap<>();
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			Integer idProduct = product.getProduct_id();
			List<ProductVersion> productVersions = product.getProductVersion();
			for (ProductVersion version : productVersions) {
				List<Variant> variants = version.getVariants();
				for (Variant variant : variants) {
					List<Inventories> inventories = variant.getInventories();
					int idVariant = variant.getVariants_id();
					String sizeColor = variant.getSize().getSize_name() + "/" + variant.getColor().getColor_name();
					if (!inventories.isEmpty()) {
						for (Inventories inventories2 : inventories) {
							LocalDateTime dateTime = inventories2.getEvent_date();
							LocalDate date = dateTime.toLocalDate();
							int changAmount = inventories2.getChange_amount();

							InventoriesReasonDTO inven = new InventoriesReasonDTO();
							inven.setIdInventory(inventories2.getInventory_id());
							inven.setIdproduct(idProduct);
							inven.setIdVariant(idVariant);
							inven.setAmount(changAmount);
							inven.setSizeColor(sizeColor);
							inven.setChange_amount(inventories2.getAmount());
							inven.setReason(inventories2.getEvent_type());
							myMap.computeIfAbsent(date.toString(), k -> new TreeMap<>())
									.computeIfAbsent(dateTime.toString(), k -> new ArrayList<>()).add(inven);

						}
					}
				}
			}
		}
		return myMap;
	}

	@GetMapping("/stock")
	private Map<Integer, Map<String, Integer>> getStockk() {
		Map<Integer, Map<String, Integer>> myMap = new TreeMap<>();
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
//			String name=product.getName();
			Integer idProduct = product.getProduct_id();
			List<ProductVersion> productVersions = product.getProductVersion();
			for (ProductVersion version : productVersions) {
				List<Variant> variants = version.getVariants();
				for (Variant variant : variants) {
					List<Inventories> inventories = variant.getInventories();
					String sizeColor = variant.getColor().getColor_name() + "/"
							+ variant.getSize().getSize_name().toString();
					if (!inventories.isEmpty()) {
						int amount = inventories.get(variant.getInventories().size() - 1).getChange_amount();
						myMap.computeIfAbsent(idProduct, k -> new HashMap<>()).put(sizeColor, amount);
					}
				}
			}
		}
		return myMap;
	}

	// api thống kê số lượng và tổng tiền hàng hóa bán ra trong ngày
	@GetMapping("/selling")
	private Map<String, Map<String, List<OrderItemStatisticDTO>>> getSelling() {
		Map<String, Map<String, List<OrderItemStatisticDTO>>> myMap = new TreeMap<>();
		List<Orders> orders = orderService.getallOrders();
		for (Orders order : orders) {
			List<OrderItem> oderItems = order.getOrderItems();

			for (OrderItem item : oderItems) {
				String date = item.getCreatedAt().toLocalDate().toString();
//				Variant variant = variantService.getVariantByID(item.getTypeOfVariant());
				String sizeColor = item.getSize().getSize_name() + "/" + item.getColor().getColor_name();
				String productName = item.getProduct().getName();
				int amount = item.getProduct_price(), quantity = item.getQuantity(), baseMoney = item.getBase_price();
				OrderItemStatisticDTO orderItem = new OrderItemStatisticDTO();
//				orderItem.setIdOrderItem(item.getOrder_items_id());
				orderItem.setSizeColor(sizeColor);
				orderItem.setMoney(amount);
				orderItem.setQuantity(quantity);
				int totalAmount = amount * quantity;

				List<OrderItemStatisticDTO> itemList = myMap.computeIfAbsent(date, k -> new TreeMap<>())
						.computeIfAbsent(productName, k -> new ArrayList<>());

				// Kiểm tra nếu đã có OrderItemStatisticDTO cho sizeColor đó, nếu có thì cộng
				// dồn
				OrderItemStatisticDTO existingItem = itemList.stream()
						.filter(dto -> dto.getSizeColor().equals(sizeColor)).findFirst().orElse(null);

				if (existingItem != null) {
					existingItem.setMoney(existingItem.getMoney() + totalAmount);
					existingItem.setQuantity(existingItem.getQuantity() + quantity);
					existingItem.setBaseMoney(baseMoney);
				} else {
					OrderItemStatisticDTO newItem = new OrderItemStatisticDTO();
					newItem.setMoney(totalAmount);
					newItem.setQuantity(quantity);
					newItem.setSizeColor(sizeColor);
					newItem.setBaseMoney(baseMoney);
					itemList.add(newItem);
				}
//				myMap.computeIfAbsent(date, k -> new HashMap<>()).computeIfAbsent(productName, k -> new ArrayList<>()).add(orderItem);
			}
		}
		return myMap;
	}

	// api thống kê số lượng và tổng tiền hàng hóa bán ra trong ngày theo giới tinh
	// sản phẩm
	@GetMapping("/getrevenuebyTypeGender")
	public Map<String, Map<String, TotalnAmount>> getrevenuebyTypeGender() {

		Map<String, Map<String, TotalnAmount>> myMap = new TreeMap<>();
		List<Orders> orders = orderService.getallOrders();
		for (Orders order : orders) {
			if (order.getStatus().equals("Completed")) {
				List<OrderItem> oderItems = order.getOrderItems();

				for (OrderItem item : oderItems) {
					String date = item.getCreatedAt().toLocalDate().toString();
					String gender = item.getProduct().getTypeOfProductGender().getTypeOfProductGender().toString();
					int amount = item.getProduct_price() * item.getQuantity();
					int quantity = item.getQuantity();

					// Lấy map của ngày hiện tại
					Map<String, TotalnAmount> genderMap = myMap.computeIfAbsent(date, k -> new HashMap<>());

					// Lấy TotalnAmount hiện tại cho gender
					TotalnAmount totalnAmount = genderMap.get(gender);

					// Nếu chưa có, tạo mới
					if (totalnAmount == null) {
						totalnAmount = new TotalnAmount();
						totalnAmount.setTotalMoney(0);
						totalnAmount.setTotalQuantity(0);
						genderMap.put(gender, totalnAmount);
					}

					// Cộng dồn giá trị
					totalnAmount.setTotalMoney(totalnAmount.getTotalMoney() + amount);
					totalnAmount.setTotalQuantity(totalnAmount.getTotalQuantity() + quantity);
				}
			}

		}
		return myMap;
	}

	// api thống kê số lượng và tổng tiền hàng hóa bán ra trong ngày theo loại sản
	// phẩm
	@GetMapping("/getrevenuebyTypeProduct")
	public Map<String, Map<String, TotalnAmount>> getrevenuebyTypeProduct() {

		Map<String, Map<String, TotalnAmount>> myMap = new TreeMap<>();
		List<Orders> orders = orderService.getallOrders();
		for (Orders order : orders) {
			if (order.getStatus().equals("Completed")) {
				List<OrderItem> oderItems = order.getOrderItems();

				for (OrderItem item : oderItems) {
					String date = item.getCreatedAt().toLocalDate().toString();
					String typeProduct = item.getProduct().getTypeOfProductNew().getTypeofproduct().toString();
					int amount = item.getProduct_price() * item.getQuantity();
					int quantity = item.getQuantity();

					// Lấy map của ngày hiện tại
					Map<String, TotalnAmount> productMap = myMap.computeIfAbsent(date, k -> new HashMap<>());

					// Lấy TotalnAmount hiện tại cho gender
					TotalnAmount totalnAmount = productMap.get(typeProduct);

					// Nếu chưa có, tạo mới
					if (totalnAmount == null) {
						totalnAmount = new TotalnAmount();
						totalnAmount.setTotalMoney(0);
						totalnAmount.setTotalQuantity(0);
						productMap.put(typeProduct, totalnAmount);
					}

					// Cộng dồn giá trị
					totalnAmount.setTotalMoney(totalnAmount.getTotalMoney() + amount);
					totalnAmount.setTotalQuantity(totalnAmount.getTotalQuantity() + quantity);
				}
			}
		}
		return myMap;
	}
}
