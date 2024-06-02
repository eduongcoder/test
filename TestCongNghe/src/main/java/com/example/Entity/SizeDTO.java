package com.example.Entity;

import com.example.Enum.SizeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class SizeDTO {
//	@NonNull
//	private Integer sizeeid;
	@NonNull
	private SizeEnum productsize;
//	@NonNull
//	private Integer productid;
}
