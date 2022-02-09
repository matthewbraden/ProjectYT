package com.example.YTProj.YT;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.YTProj.YT.Model.*;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

@RestController
@RequestMapping(path = "yt")
public class YtController {
	
	private final YtService ytService;
	
	private static final String apiKey = "AIzaSyA7YqPbWi7q2QHHomrXzB0FsOs19623yoU";

	@Autowired
	public YtController(YtService ytService) {
		this.ytService = ytService;
	}
	
	@SuppressWarnings("unchecked")
	private static ArrayList<Yt> getYoutubeData() {
		
		JsonFactory JSON_FACTORY = new JacksonFactory();
		HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
		
	    YouTube youtube = new YouTube.Builder(
	    		HTTP_TRANSPORT, 
	    		JSON_FACTORY, 
	    		(HttpRequest request) -> {
	    		}).setApplicationName("YTProj").build();

        YouTube.Search.List search;
        
        ArrayList<Yt> result = new ArrayList<Yt>();
        
		try {
			search = youtube.search().list("id,snippet");
			search.setQ("telecom");
	        search.setType("video");
	        search.setFields("items(id/videoId,snippet/title)");
	        search.setKey(apiKey);
	        search.setRelevanceLanguage("en");
	        search.setMaxResults((long) 25);
	        
	        SearchListResponse searchRes = search.execute();
	        
	        ArrayList<SearchResult> searchResults = (ArrayList<SearchResult>) searchRes.getItems();
	        
	        for (int i = 0; i < searchResults.size(); i++) {
	        	Map<String, Object> currentSearch = searchResults.get(i);
				Map<String, Object> idMap = (Map<String, Object>) currentSearch.get("id");
	        	Map<String, Object> titleMap = (Map<String, Object>) currentSearch.get("snippet");
	        	
	        	String videoId = (String) idMap.get("videoId");
	        	String title = (String) titleMap.get("title");
	        	
	        	String lowerCaseTitle = title.toLowerCase();
	        	String pattern = "\\btelecom\\b";

	            Pattern r = Pattern.compile(pattern);
	            Matcher m = r.matcher(lowerCaseTitle);
	            
	            if (m.find()) {
	            	String url = "https://www.youtube.com/watch?v=" + videoId;
		        	Yt yt = new Yt(url, title);
		        	
		        	result.add(yt);
	            }
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@PostConstruct
	public void addAdmissionsData() {
		ArrayList<Yt> youtubeData = getYoutubeData();
		ytService.saveYtData(youtubeData);
	}
	
	@GetMapping
	public ArrayList<Yt> getYtData() {
		return ytService.getYtData();
	}
}
