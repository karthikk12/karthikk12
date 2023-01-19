package com.example.ReportsDto;

public class RiverRunDto {

	private String Date;

	private String contentType;

	private String riverRunData;

	private String hp;

	private String difference;

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getRiverRunData() {
		return riverRunData;
	}

	public void setRiverRunData(String riverRunData) {
		this.riverRunData = riverRunData;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	@Override
	public String toString() {
		return "RiverRunDto [Date=" + Date + ", contentType=" + contentType + ", riverRunData=" + riverRunData + ", hp="
				+ hp + ", difference=" + difference + "]";
	}

}
