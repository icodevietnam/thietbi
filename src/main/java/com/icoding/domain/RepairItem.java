package com.icoding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "repair_item")
public class RepairItem {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "reason")
	private String reason;

	@Column(name = "detailed")
	private String detaild;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDetaild() {
		return detaild;
	}

	public void setDetaild(String detaild) {
		this.detaild = detaild;
	}

}
