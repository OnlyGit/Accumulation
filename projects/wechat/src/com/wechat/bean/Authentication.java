package com.wechat.bean;

public class Authentication {

	private String token;
	private String tokenExpires;
	private String ticket;
	private String ticketExpires;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenExpires() {
		return tokenExpires;
	}
	public void setTokenExpires(String tokenExpires) {
		this.tokenExpires = tokenExpires;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getTicketExpires() {
		return ticketExpires;
	}
	public void setTicketExpires(String ticketExpires) {
		this.ticketExpires = ticketExpires;
	}
	
}
