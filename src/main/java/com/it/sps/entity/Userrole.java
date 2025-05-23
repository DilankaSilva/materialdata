package com.it.sps.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USERROLE database table.
 * 
 */
@Entity
@NamedQuery(name="Userrole.findAll", query="SELECT u FROM Userrole u")
public class Userrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_TYPE")
	private String roleType;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	//bi-directional many-to-one association to UserroleActivity
	@OneToMany(mappedBy="userrole")
	private List<UserroleActivity> userroleActivities;

	public Userrole() {
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public List<UserroleActivity> getUserroleActivities() {
		return this.userroleActivities;
	}

	public void setUserroleActivities(List<UserroleActivity> userroleActivities) {
		this.userroleActivities = userroleActivities;
	}

	public UserroleActivity addUserroleActivity(UserroleActivity userroleActivity) {
		getUserroleActivities().add(userroleActivity);
		userroleActivity.setUserrole(this);

		return userroleActivity;
	}

	public UserroleActivity removeUserroleActivity(UserroleActivity userroleActivity) {
		getUserroleActivities().remove(userroleActivity);
		userroleActivity.setUserrole(null);

		return userroleActivity;
	}

}