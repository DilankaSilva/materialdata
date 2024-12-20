package com.example.materialdata.dto;

import java.math.BigDecimal;

public class AvailableMaterial {
	
	public AvailableMaterial(String materialName, BigDecimal unitPrice, BigDecimal qtyOnHand) {
		this.materialName = materialName;
		this.unitPrice = unitPrice;
		this.qtyOnHand = qtyOnHand;
	}
	private String materialName;
    private BigDecimal unitPrice;
    private BigDecimal qtyOnHand;
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(BigDecimal qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}


}
