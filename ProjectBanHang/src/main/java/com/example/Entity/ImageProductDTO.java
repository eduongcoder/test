package com.example.Entity;

//import java.util.Base64;

import com.example.Enum.ImageEnum;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ImageProductDTO {
	@NonNull
	private Integer imageid;
	@NonNull
	private ImageEnum imagetype;
	@NonNull
	private String imageupload;
	@NonNull
	private Integer productid;
	

}
