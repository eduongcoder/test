package com.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.ImageProductDTO;
import com.example.Entity.Product;
import com.example.Entity.ProductDTO;
import com.example.Repository.IProductRepository;

@RequestMapping("/api/anh")
@RestController
public class productController {
	 @Autowired
	    private imageService imageService;
		@Autowired 
		private IProductRepository service;
	 @Autowired
	 private ModelMapper modelMapper;
	    @GetMapping
	    public List<ProductDTO> getAllProducts() {
	        List<Product> products = service.findAll();
	        List<ProductDTO> dtos = products.stream()
	                                        .map(this::convertToDTO)
	                                        .collect(Collectors.toList());
	        return dtos;
	    }

	    private ProductDTO convertToDTO(Product product) {
	        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
	        List<CompletableFuture<Void>> futures = dto.getImage().stream()
	            .map(imageDTO -> loadImageAsync(imageDTO))
	            .collect(Collectors.toList());
	        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
	        return dto;
	    }

	    private CompletableFuture<Void> loadImageAsync(ImageProductDTO imageDTO) {
	        return imageService.loadImageAsync(imageDTO.getImageupload())
	                             .thenAccept(imageData -> imageDTO.setImageupload(imageData));
	    }
}
