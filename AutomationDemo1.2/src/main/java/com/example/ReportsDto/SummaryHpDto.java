package com.example.ReportsDto;

public class SummaryHpDto {

	private String date;

	private String source;

	private String nmExchange;

	private String hpExchange;

	private String difference;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNmExchange() {
		return nmExchange;
	}

	public void setNmExchange(String nmExchange) {
		this.nmExchange = nmExchange;
	}

	public String getHpExchange() {
		return hpExchange;
	}

	public void setHpExchange(String hpExchange) {
		this.hpExchange = hpExchange;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	@Override
	public String toString() {
		return "SummaryHpDto [date=" + date + ", source=" + source + ", nmExchange=" + nmExchange + ", hpExchange="
				+ hpExchange + ", difference=" + difference + "]";
	}

}
