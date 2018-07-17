package com.example.apiDeneme;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
		
        value = {"eklemekValue", "guncellemekValue"},
        allowGetters = true
)

public abstract class denetimModel implements Serializable  {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "eklemek" , nullable=false, updatable=false)
	@CreatedDate
	 private Date eklemekValue;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="guncellemek" , nullable=false)
    @LastModifiedDate
    private Date guncellemekValue;

	public Date getEklemekValue() {
		return eklemekValue;
	}

	public void setEklemekValue(Date eklemekValue) {
		this.eklemekValue = eklemekValue;
	}

	public Date getGuncellemekValue() {
		return guncellemekValue;
	}

	public void setGuncellemekValue(Date guncellemekValue) {
		this.guncellemekValue = guncellemekValue;
	}


	
	
	
	
	
	
	
}
