package com.example.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.Account;
import com.example.Entity.AddressesDTO;
import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.Orders;
import com.example.Entity.OrdersDTO;
import com.example.Entity.ProductVersionShowDTO;
import com.example.Entity.TypeOfProductDTO;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.Entity.VariantNoAccountDTO;
import com.example.From.OrderitemForm;
import com.example.From.OrdersForm;
import com.example.From.VariantForm;
import com.example.Repository.IOrderItemRepository;
import com.example.Service.IAccountService;
import com.example.Service.IOrderItemService;
import com.example.Service.IOrderService;
import com.example.Service.IVariantService;

@RequestMapping("/api/variant")
@RestController
public class VariantController  {
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedOrigins("http://26.22.243.2:5173", "http://localhost:5173") // URL của ứng
//																										// dụng React
//				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
//	}implements WebMvcConfigurer

	@Autowired
	private IVariantService service;

	@Autowired
	private IOrderService serviceOrder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAccountService serviceAccount;

	@Autowired
	private IOrderItemService serviceIOrderItem;

	@GetMapping
	private List<VariantDTO> getallVariant() {
		List<Variant> list = service.getAllVariants();
		List<VariantDTO> dtos = modelMapper.map(list, new TypeToken<List<VariantDTO>>() {
		}.getType());
		return dtos;
	}

	@PostMapping("/create")
	public VariantDTO createVariant(@RequestBody VariantForm form) {
		Variant variant=service.createVariant(form);
		return modelMapper.map(variant, VariantDTO.class);
	}
	
	@PostMapping("/createint")
	public int createVariantInt(@RequestBody VariantForm form) {
		Variant variant=service.createVariant(form);
		return variant.getVariants_id();
	}
	
	@GetMapping(value = "/getVariant/{idvariant}")
	public VariantNoAccountDTO createOrderItemNoAccount(@PathVariable(name = "idvariant") int idvariant) {

		VariantNoAccountDTO variantNoAccountDTO= modelMapper.map(service.getVariantByID(idvariant), VariantNoAccountDTO.class);
		variantNoAccountDTO.setQuantity(1);
		variantNoAccountDTO.setCreateTime(LocalDateTime.now());
		return variantNoAccountDTO;
	}

	@GetMapping("/orderitem")
	private List<OrderItemDTOVariant> getallOrderItems() {
		List<OrderItem> list = serviceIOrderItem.getAllOrderItems();
		List<OrderItemDTO> dtos = modelMapper.map(list, new TypeToken<List<OrderItemDTO>>() {
		}.getType());
		List<OrderItemDTOVariant> dtoVariants = modelMapper.map(dtos, new TypeToken<List<OrderItemDTOVariant>>() {
		}.getType());
		return dtoVariants;
	}

	@GetMapping("/ordermultiitem")
	private List<OrderItemDTO> getAllOrderItemByid(@RequestParam int id, @RequestParam int idVariant) {
		List<OrderItem> list = serviceIOrderItem.findbyIDOrdersItem(id);
		List<OrderItemDTO> dtos = modelMapper.map(list, new TypeToken<List<OrderItemDTO>>() {
		}.getType());
//		List<OrderItemDTOVariant> dtoVariants=modelMapper.map(dtos, new TypeToken<List<OrderItemDTOVariant>>()
//		{}.getType());
		return dtos;
	}

	@GetMapping("/ordersigleitem")
	private OrderItemDTO getItemDTO(@RequestParam int idOrderItem, @RequestParam int idVariant) {
		try {
			OrderItem item = serviceIOrderItem.findbyID(idOrderItem);
			OrderItemDTO dto = modelMapper.map(item, OrderItemDTO.class);
			VariantDTO variantDTO = modelMapper.map(service.getVariantByID(idVariant), VariantDTO.class);
			dto.setVariants(variantDTO);
			dto.setIdvariant(idVariant);
			return dto;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	
	}
	
	@PutMapping("/updateVariant")
	private int updateVariant(@RequestBody VariantForm form) {
		return modelMapper.map(service.updateVariant(form), VariantDTO.class).getVariants_id();
	}
	

	@PostMapping("/createorderitem")
	private int postMethodName(@RequestBody OrderitemForm form, @RequestParam int idAccount,
			@RequestParam int idVariant) {

		Orders orders = serviceOrder.getOrderPrepare(idAccount);
		if (orders != null) {
			form.setOrders(orders.getOrders_id());
			form.setTypeOfVariant(idVariant);
			OrderItem orderItem = serviceIOrderItem.createOrderItem(form, form.getOrders(), form.getProductVersion());

			return orderItem.getOrder_items_id();
		} else {
			Account account = serviceAccount.findAccountByID(idAccount);

			OrdersForm ordersForm = new OrdersForm();
			
			ordersForm.setTotal_amount(BigDecimal.valueOf(0.00));
			ordersForm.setOrderItems(null);
			ordersForm.setStatus("Prepare");
			ordersForm.setAccount(account);

			Orders orderscreateOrders = serviceOrder.createOrder(ordersForm);

			form.setOrders(orderscreateOrders.getOrders_id());
			form.setTypeOfVariant(idVariant);
			OrderItem orderItem = serviceIOrderItem.createOrderItem(form, form.getOrders(), form.getProductVersion());

			return orderItem.getOrder_items_id();
		}
	}

}
