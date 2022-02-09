package com.example.YTProj.YT2;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.YTProj.YT2.Model.Yt2;

@RestController
@RequestMapping(path = "yt2")
public class Yt2Controller {
	
	private final Yt2Service yt2Service;
	
	@Autowired
	public Yt2Controller(Yt2Service yt2Service) {
		this.yt2Service = yt2Service;
	}
	
	
	@PostConstruct
	public void saveYt2Data() {
		yt2Service.saveModifiedData();
	}
	
	@GetMapping
	public List<Yt2> getYt2Data() {
		return yt2Service.getYt2Data();
	}
	
}