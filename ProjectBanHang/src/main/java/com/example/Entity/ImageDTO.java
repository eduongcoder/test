package com.example.Entity;



import com.example.Enum.ImageEnum;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ImageDTO {

	@NonNull
	private ImageEnum imagetype;
	@NonNull
	private String imageupload;

}
