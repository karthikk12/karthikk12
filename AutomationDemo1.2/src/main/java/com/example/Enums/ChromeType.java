package com.example.Enums;

public enum ChromeType {

	openType("headful"), waitTime("40");

	private String action;

	public String getAction() {
		return action;
	}

	private ChromeType(String action) {

		this.action = action;

	}

}
