package com.example.Entity;

import java.time.LocalDateTime;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryLoginDTO {
	private Integer history_login_id;
	
	private LocalDateTime datelogin;
	
	private String note;

	private Integer id_account;
}
