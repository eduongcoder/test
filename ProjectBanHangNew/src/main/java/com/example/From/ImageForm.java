package com.example.From;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageForm {
	private int images_id;

	private byte[] imageByte;

	private Integer productid;

}
