package com.example.Service;

import java.util.List;

import com.example.Entity.HistoryLogin;

public interface IHistoryLoginService {
	public List<HistoryLogin> getHistoryLogin();
	public HistoryLogin getHistoryLoginById(int id);
	public HistoryLogin updateHistoryLogin(int id);
}
