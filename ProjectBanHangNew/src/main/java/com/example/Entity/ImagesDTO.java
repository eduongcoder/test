package com.example.Entity;

import java.sql.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Service.ImageHandelService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImagesDTO {

	private int images_id;

	private CompletableFuture<String> image_url;
	private String image_urlString;

//	private byte[] imageByte;
	private Date created_at;

	private Date updated_at;

	private Integer variant_id;
}
