package com.example.Entity;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImagesDTO {

	private int images_id;

	private String image_url;

	private Date created_at;

	private Date updated_at;

	private Integer variant_id;

}
