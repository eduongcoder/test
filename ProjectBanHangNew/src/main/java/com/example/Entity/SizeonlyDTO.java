package com.example.Entity;

import com.example.Enum.SizeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SizeonlyDTO {
private int size_id;
	
	private SizeEnum size_name;
}
