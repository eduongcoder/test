package com.example.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TestDTO {
	@NonNull
	private Integer id;
//	@NonNull
	private String imageName;
//	@NonNull
	private Integer imagePrice;
//	@NonNull
	private Byte[] image;
}
