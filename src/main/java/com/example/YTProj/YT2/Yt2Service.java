package com.example.YTProj.YT2;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.YTProj.YT.Model.Yt;
import com.example.YTProj.YT.Repository.*;
import com.example.YTProj.YT2.Model.Yt2;
import com.example.YTProj.YT2.Repository.*;

@Service
public class Yt2Service {

	private final YtRepository ytRepository;
	private final Yt2Repository yt2Repository;
	
	@Autowired
	public Yt2Service(Yt2Repository yt2Repository, YtRepository ytRepository) {
		this.yt2Repository = yt2Repository;
		this.ytRepository = ytRepository;
	}
	
	public ArrayList<Yt2> getModifiedYtData() {	
		ArrayList<Yt> allQueueaData = (ArrayList<Yt>) ytRepository.findAll();
		
		ArrayList<Yt2> newQueueData = new ArrayList<Yt2>();
		for (int i = 0; i < allQueueaData.size(); i++) {
			Yt indexData = allQueueaData.get(i);
			
			Long id = indexData.getId();
			String url = indexData.getUrl();
			String title = indexData.getTitle();
			
			
			String pattern1 = "\\btelecom\\b";
			Pattern r1 = Pattern.compile(pattern1);
	        Matcher m1 = r1.matcher(title);
	        if (m1.find()) {
	        	title = title.replaceAll("telecom", "telco");
	        }
	        
	        String pattern2 = "\\bTelecom\\b";
	        Pattern r2 = Pattern.compile(pattern2);
	        Matcher m2 = r2.matcher(title);
	        if (m2.find()) {
	        	title = title.replaceAll("Telecom", "Telco");
	        }
	        
	        String pattern3 = "\\bTELECOM\\b";
	        Pattern r3 = Pattern.compile(pattern3);
	        Matcher m3 = r3.matcher(title);
	        if (m3.find()) {
	        	title = title.replaceAll("TELECOM", "TELCO");
	        }
	        
			Yt2 replacementData = new Yt2(id, url, title);
			
			newQueueData.add(replacementData);
		}
	
		return newQueueData;
	}
	
	public void saveModifiedData() {
		ArrayList<Yt2> modifiedData = getModifiedYtData();
		
		yt2Repository.saveAll(modifiedData);
	}
	
	public ArrayList<Yt2> getYt2Data() {
		return (ArrayList<Yt2>) yt2Repository.findAll();
	}
}
