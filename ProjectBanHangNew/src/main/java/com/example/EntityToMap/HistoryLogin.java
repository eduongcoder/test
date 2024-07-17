package com.example.EntityToMap;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryLogin {
    private int historyLoginId;
    private String dateLogin;
    private String note;
    private int accountId;
}
