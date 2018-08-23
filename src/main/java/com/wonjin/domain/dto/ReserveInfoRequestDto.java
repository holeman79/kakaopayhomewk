package com.wonjin.domain.dto;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


public class ReserveInfoRequestDto {
	
	private String date;
	private String timeFrom;
	private String timeTo;
	private int confId;
	private String confName;
	private String reserver;
	private int repeatCount;
	
	public ReserveInfoRequestDto() {
	}
	
	public ReserveInfoRequestDto(String date, String timeFrom, String timeTo, int confId, String confName,
			String reserver, int repeatCount) {
		super();
		this.date = date;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.confId = confId;
		this.confName = confName;
		this.reserver = reserver;
		this.repeatCount = repeatCount;
	}



	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getConfId() {
		return confId;
	}
	public void setConfId(int confId) {
		this.confId = confId;
	}
	public String gettimeFrom() {
		return timeFrom;
	}
	public void settimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public String gettimeTo() {
		return timeTo;
	}
	public void settimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName;
	}
	public String getReserver() {
		return reserver;
	}
	public void setReserver(String reserver) {
		this.reserver = reserver;
	}
	public int getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
}
