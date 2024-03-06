package com.app.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.app.model.Base;

@Entity
@Table(name = "invoice")
public class Invoice extends Base{

	private String inum;
	private String strDate;
	private String gtin;
	private String billedtoname;
	private String statename;
	private Double quantity;
	private Double rateperitem;
	private Double discount;
	private Double taxablevalue;
	private Double igstrate;
	private Double igstamount;
	private Double cgstrate;
	private Double cgstamount;
	private Double sgstrate;
	private Double sgstamount;
	private Double total;

	public String getInum() {
		return inum;
	}

	public void setInum(String inum) {
		this.inum = inum;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getBilledtoname() {
		return billedtoname;
	}

	public void setBilledtoname(String billedtoname) {
		this.billedtoname = billedtoname;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getRateperitem() {
		return rateperitem;
	}

	public void setRateperitem(Double rateperitem) {
		this.rateperitem = rateperitem;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTaxablevalue() {
		return taxablevalue;
	}

	public void setTaxablevalue(Double taxablevalue) {
		this.taxablevalue = taxablevalue;
	}

	public Double getIgstrate() {
		return igstrate;
	}

	public void setIgstrate(Double igstrate) {
		this.igstrate = igstrate;
	}

	public Double getIgstamount() {
		return igstamount;
	}

	public void setIgstamount(Double igstamount) {
		this.igstamount = igstamount;
	}

	public Double getCgstrate() {
		return cgstrate;
	}

	public void setCgstrate(Double cgstrate) {
		this.cgstrate = cgstrate;
	}

	public Double getCgstamount() {
		return cgstamount;
	}

	public void setCgstamount(Double cgstamount) {
		this.cgstamount = cgstamount;
	}

	public Double getSgstrate() {
		return sgstrate;
	}

	public void setSgstrate(Double sgstrate) {
		this.sgstrate = sgstrate;
	}

	public Double getSgstamount() {
		return sgstamount;
	}

	public void setSgstamount(Double sgstamount) {
		this.sgstamount = sgstamount;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Invoice [inum=" + inum + ", strDate=" + strDate + ", gtin=" + gtin + ", billedtoname=" + billedtoname
				+ ", statename=" + statename + ", quantity=" + quantity + ", rateperitem=" + rateperitem + ", discount="
				+ discount + ", taxablevalue=" + taxablevalue + ", igstrate=" + igstrate + ", igstamount=" + igstamount
				+ ", cgstrate=" + cgstrate + ", cgstamount=" + cgstamount + ", sgstrate=" + sgstrate + ", sgstamount="
				+ sgstamount + ", total=" + total + "]";
	}
}
