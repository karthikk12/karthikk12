package com.example.ReportsDto;

public class VantageDto {

	private String Date;

	private String contentType;

	private String vantage;

	private String vantageHp;

	private String difference;

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getVantage() {
		return vantage;
	}

	public void setVantage(String vantage) {
		this.vantage = vantage;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getVantageHp() {
		return vantageHp;
	}

	public void setVantageHp(String vantageHp) {
		this.vantageHp = vantageHp;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	@Override
	public String toString() {
		return "VantageDto [Date=" + Date + ", contentType=" + contentType + ", vantage=" + vantage + ", vantageHp="
				+ vantageHp + ", difference=" + difference + "]";
	}

}
