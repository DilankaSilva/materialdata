package com.example.materialdata.entity;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the WIRING_LAND_DETAIL database table.
 * 
 */
@Embeddable
public class WiringLandDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_ID")
	private String applicationId;

	@Column(name="DEPT_ID")
	private String deptId;

	public WiringLandDetailPK() {
	}
	public String getApplicationId() {
		return this.applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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
		if (!(other instanceof WiringLandDetailPK)) {
			return false;
		}
		WiringLandDetailPK castOther = (WiringLandDetailPK)other;
		return 
			this.applicationId.equals(castOther.applicationId)
			&& this.deptId.equals(castOther.deptId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
	}
}