package com.example.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RelativeDTO {
	@NonNull
	private Integer id;
//	@NonNull
	private String name;
	@NonNull
	private Short age;
//	@NonNull
	private Integer idEmployee;
}
