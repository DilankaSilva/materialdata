package com.it.sps.entity;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the SPESTMTM database table.
 * 
 */
@Embeddable
public class SpestmtmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long phase;

	@Column(name="CONNECTION_TYPE")
	private long connectionType;

	@Column(name="WIRING_TYPE")
	private String wiringType;

	@Column(name="MAT_CD", insertable=false, updatable=false)
	private String matCd;

	@Column(name="DEPT_ID", insertable=false, updatable=false)
	private String deptId;

	public SpestmtmPK() {
	}
	public long getPhase() {
		return this.phase;
	}
	public void setPhase(long phase) {
		this.phase = phase;
	}
	public long getConnectionType() {
		return this.connectionType;
	}
	public void setConnectionType(long connectionType) {
		this.connectionType = connectionType;
	}
	public String getWiringType() {
		return this.wiringType;
	}
	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}
	public String getMatCd() {
		return this.matCd;
	}
	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpestmtmPK)) {
			return false;
		}
		SpestmtmPK castOther = (SpestmtmPK)other;
		return 
			(this.phase == castOther.phase)
			&& (this.connectionType == castOther.connectionType)
			&& this.wiringType.equals(castOther.wiringType)
			&& this.matCd.equals(castOther.matCd)
			&& this.deptId.equals(castOther.deptId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + ((int) (this.connectionType ^ (this.connectionType >>> 32)));
		hash = hash * prime + this.wiringType.hashCode();
		hash = hash * prime + this.matCd.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
	}
}