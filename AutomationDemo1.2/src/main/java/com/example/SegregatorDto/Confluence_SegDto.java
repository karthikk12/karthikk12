package com.example.SegregatorDto;

public class Confluence_SegDto {

	private String date;

	private String exchangeToDs;

	private String DsFromExchange;

	private String variance;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExchangeToDs() {
		return exchangeToDs;
	}

	public void setExchangeToDs(String exchangeToDs) {
		this.exchangeToDs = exchangeToDs;
	}

	public String getDsFromExchange() {
		return DsFromExchange;
	}

	public void setDsFromExchange(String dsFromExchange) {
		DsFromExchange = dsFromExchange;
	}

	public String getVariance() {
		return variance;
	}

	public void setVariance(String variance) {
		this.variance = variance;
	}

	@Override
	public String toString() {
		return "Confluence_SegDto [date=" + date + ", exchangeToDs=" + exchangeToDs + ", DsFromExchange="
				+ DsFromExchange + ", variance=" + variance + "]";
	}

}
