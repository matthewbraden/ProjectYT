package com.example.YTProj.YT;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.YTProj.YT.Model.*;
import com.example.YTProj.YT.Repository.*;

@Service
public class YtService {
	private final YtRepository ytRepository;
		
	@Autowired
	public YtService(YtRepository ytRepository) {
		this.ytRepository = ytRepository;
	}
	
	public void saveYtData(ArrayList<Yt> data) {
		ytRepository.saveAll(data);
	}
	
	public ArrayList<Yt> getYtData() {
		return (ArrayList<Yt>) ytRepository.findAll();
	}
	
}
