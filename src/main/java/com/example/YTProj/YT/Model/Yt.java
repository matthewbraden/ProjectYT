package com.example.YTProj.YT.Model;

import javax.persistence.*;

@Entity
@Table(name = "yt")
public class Yt {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "title")
	private String title;
	
	public Yt(){}
	
	public Yt(Long id, String url, String title) {
		this.id = id;
		this.url = url;
		this.title = title;
	}

	
	public Yt(String url, String title) {
		this.url = url;
		this.title = title;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString() {
		return "yt{\n" +
				"id=" + id + "\n" +
				", url=" + url + "\n" +
				", titl=" + title + "\n" +
				"}";
	}

}
