package com.lovo.dto;

public class PowerDto {
     private String userName;
     private String powerUri;

     public PowerDto(){}
	public PowerDto(String userName, String powerUri) {

		this.userName = userName;
		this.powerUri = powerUri;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPowerUri() {
		return powerUri;
	}

	public void setPowerUri(String powerUri) {
		this.powerUri = powerUri;
	}
     
     
}
