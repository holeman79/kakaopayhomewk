package com.wonjin.domain.reserveinfo;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.wonjin.domain.dto.ReserveInfoRequestDto;

@SqlResultSetMapping(
		name = "ReserveInfoRequestDto", classes = @ConstructorResult(targetClass = ReserveInfoRequestDto.class, 
		columns = {
		@ColumnResult(name = "date", type = String.class),
		@ColumnResult(name = "time_from", type = String.class), 
		@ColumnResult(name = "time_to", type = String.class),
		@ColumnResult(name = "conf_id", type = Integer.class),
		@ColumnResult(name = "conf_name", type = String.class),
		@ColumnResult(name = "reserver", type = String.class),
		@ColumnResult(name = "repeat_count", type = Integer.class), }))
@Entity
@Table(name = "RESERVE_INFO")
public class ReserveInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int confId;
	private String date;
	private int repeatCount;
	private String reserver;
	private String timeFrom;
	private String timeTo;
	
	public ReserveInfo(Long id, int confId, String date, int repeatCount, String reserver, String timeFrom,
			String timeTo) {
		super();
		this.id = id;
		this.confId = confId;
		this.date = date;
		this.repeatCount = repeatCount;
		this.reserver = reserver;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	public ReserveInfo() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public int getConfId() {
		return confId;
	}
	public void setConfId(int confId) {
		this.confId = confId;
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
