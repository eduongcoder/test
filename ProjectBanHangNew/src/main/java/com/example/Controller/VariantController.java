package com.example.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.Entity.Account;
import com.example.Entity.Images;
import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.Orders;

import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.Entity.VariantNoAccountDTO;

import com.example.From.ImageForm;
import com.example.From.OrderitemForm;
import com.example.From.OrdersForm;
import com.example.From.VariantForm;
import com.example.Service.IAccountService;
import com.example.Service.IColorService;
import com.example.Service.IImageService;
import com.example.Service.IOrderItemService;
import com.example.Service.IOrderService;
import com.example.Service.ISaleDiscountService;
import com.example.Service.ISaleService;
import com.example.Service.ISizeService;
import com.example.Service.IVariantService;
import com.example.Service.ImageHandelService;

@RequestMapping("/api/variant")
@RestController
public class VariantController {
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedOrigins("http://26.22.243.2:5173", "http://localhost:5173") // URL của ứng
//																										// dụng React
//				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
//	}implements WebMvcConfigurer

	@Autowired
	private IVariantService service;

	@Autowired
	private IColorService colorService;

	@Autowired
	private ISizeService sizeService;
	@Autowired
	private IOrderService serviceOrder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAccountService serviceAccount;

	@Autowired
	private IOrderItemService serviceIOrderItem;

	@Autowired
	private IImageService imageService;

	@Autowired
	private ImageHandelService handelService;

	@Autowired
	private ISaleDiscountService saleDiscountService;

	@Autowired
	private ISaleService saleService;

	
	
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {

		System.out.println("File được tải lên thành công: " + file.getOriginalFilename());
		try {
			return handelService.getImageBase64String(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

	@PostMapping("/creatImage")
	public int creatImage(@RequestParam("file") MultipartFile file, @RequestParam("idproduct") int idproduct) {
		ImageForm form = new ImageForm();
		try {
			form.setImageByte(file.getBytes());
			form.setProductid(idproduct);
			Images images = imageService.createImage(form);
			return images.getImages_id();
		} catch (IOException e) {
			return -1;
		}

	}

	@GetMapping
	private List<VariantDTO> getallVariant() {
		List<Variant> list = service.getAllVariants();
		List<VariantDTO> dtos = modelMapper.map(list, new TypeToken<List<VariantDTO>>() {
		}.getType());
		return dtos;
	}

	@PostMapping("/create")
	public VariantDTO createVariant(@RequestBody VariantForm form) {
		Variant variant = service.createVariant(form);
		return modelMapper.map(variant, VariantDTO.class);
	}

	@PostMapping("/createint")
	public int createVariantInt(@RequestBody VariantForm form) {
		Variant variant = service.createVariant(form);
		return variant.getVariants_id();
	}

	@GetMapping(value = "/getVariant/{idvariant}")
	public VariantNoAccountDTO createOrderItemNoAccount(@PathVariable(name = "idvariant") int idvariant) {

		VariantNoAccountDTO variantNoAccountDTO = modelMapper.map(service.getVariantByID(idvariant),
				VariantNoAccountDTO.class);
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
//			dto.setVariants(variantDTO);
//			dto.setIdvariant(idVariant);
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
	private OrderItemDTOVariant postMethodName(@RequestBody OrderitemForm form, @RequestParam int idAccount) {
	
		Orders orders = serviceOrder.getOrderPrepare(idAccount);
		if (orders != null) {
			OrderItem orderItem = serviceIOrderItem.createOrderItem(form,orders.getOrders_id());
			
//			List<Variant> variants=service.getAllVariants();
//			for (Variant variant : variants) {
//				int idProduct=variant.getProductversion().getProduct().getProduct_id();
//				int idColor=variant.getColor().getColor_id();
//				int idSize=variant.getSize().getSize_id();
//				if (idProduct==orderItem.getProduct().getProduct_id()
//						&& idColor==orderItem.getColor().getColor_id()
//						&& idSize==orderItem.getSize().getSize_id()) {
//					List<Inventories> inventories=variant.getInventories();
//					int index=inventories.size()-1;
//					if (inventories.get(index).getChange_amount()>0) {
//						OrderitemForm itemformForm=new OrderitemForm();
//						itemformForm.setProduct_price(saleService.getSalePrice(variant.getVariants_id()));
//					}
//				}
//			}
//			return orderItem.getOrder_items_id();

			return modelMapper.map(orderItem, OrderItemDTOVariant.class);
		} else {
			Account account = serviceAccount.findAccountByID(idAccount);

			OrdersForm ordersForm = new OrdersForm();

			ordersForm.setTotal_amount(0);
			ordersForm.setOrderItems(null);
			ordersForm.setStatus("Prepare");
			ordersForm.setAccount(account);

			Orders orderscreateOrders = serviceOrder.createOrder(ordersForm,account.getAccount_id());

//			form.setOrders(orderscreateOrders.getOrders_id());
//			form.setTypeOfVariant(idVariant);
			OrderItem orderItem = serviceIOrderItem.createOrderItem(form, orderscreateOrders.getOrders_id());


			return modelMapper.map(orderItem, OrderItemDTOVariant.class);
//			return orderItem.getOrder_items_id();
		}
	}

}
