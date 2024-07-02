package com.example.Entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImagesDTO {

	private int images_id;

	private String image_urlString;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private Integer productid;
}
