package com.example.Enums;

public enum ChromeType {

	openType("headless"), waitTime("35");

	private String action;

	public String getAction() {
		return action;
	}

	private ChromeType(String action) {

		this.action = action;

	}

}
