package com.example.ReportsDto;

public class MrrSummaryDto {

	private String date;

	private String source;

	private String nmExchange;

	private String mrr;

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

	public String getMrr() {
		return mrr;
	}

	public void setMrr(String mrr) {
		this.mrr = mrr;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	@Override
	public String toString() {
		return "MrrSummaryDto [date=" + date + ", source=" + source + ", nmExchange=" + nmExchange + ", mrr=" + mrr
				+ ", difference=" + difference + "]";
	}

}
