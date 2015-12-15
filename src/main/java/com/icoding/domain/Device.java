package com.icoding.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "device")
public class Device {
	
	@Id
	@GenericGenerator(name = "seq_device_code", strategy = "com.icoding.generator.DeviceCodeGenerator")
	@GeneratedValue(generator = "seq_device_code", strategy = GenerationType.SEQUENCE)
	@Column(name = "code", unique = true, nullable = false, length = 20)
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	
	@Column(name = "quaranty")
	private String quaranty;
	
	@Column(name = "created_date")
	private Date createdDate;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getQuaranty() {
		return quaranty;
	}

	public void setQuaranty(String quaranty) {
		this.quaranty = quaranty;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
