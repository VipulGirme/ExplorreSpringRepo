package com.example.demo.model.request;

import java.util.List;

import lombok.Data;

@Data
public class BookLendRequest {
	private List<String> bookIds;
	private String memberId;

	public List<String> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<String> bookIds) {
		this.bookIds = bookIds;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
