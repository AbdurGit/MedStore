package com.Projects.MedStore.Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"productname"})})
public class Product implements Serializable {

	@Id
	
	private String id;
	
	@Column(name="productname")
	private String productName;

	private String productDesc;
	private String batch;
;


	@Basic
    private Date mfgDate;
	@Basic
    private Date expDate;
	
	private double mrp;
	private String sellerName;
	private String medType;
	private double percentageDiscount;
	private int noBoxBought;
	private int noBoxSold;
	private String additionalNotes;
	private String productImagePath;
	
	
	public Product() {
		
	}
	
	
	public Product(String id, String productName, String productDesc, String batch, Date mfgDate, Date expDate,
			double mrp, String sellerName, String medType, double percentageDiscount, int noBoxBought, int noBoxSold,
			String additionalNotes) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDesc = productDesc;
		this.batch = batch;
		this.mfgDate = mfgDate;
		this.expDate = expDate;
		this.mrp = mrp;
		this.sellerName = sellerName;
		this.medType = medType;
		this.percentageDiscount = percentageDiscount;
		this.noBoxBought = noBoxBought;
		this.noBoxSold = noBoxSold;
		this.additionalNotes = additionalNotes;
	}


	public String getId() {
		return id;
	}


	public void setId(String uuid) {
		this.id = uuid;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDesc() {
		return productDesc;
	}


	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}


	public String getBatch() {
		return batch;
	}


	public void setBatch(String batch) {
		this.batch = batch;
	}


	public double getMrp() {
		return mrp;
	}


	public void setMrp(double mrp) {
		this.mrp = mrp;
	}


	public String getSellerName() {
		return sellerName;
	}


	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}


	public String getMedType() {
		return medType;
	}


	public void setMedType(String medType) {
		this.medType = medType;
	}


	public double getPercentageDiscount() {
		return percentageDiscount;
	}


	public void setPercentageDiscount(double percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}


	public int getNoBoxBought() {
		return noBoxBought;
	}


	public void setNoBoxBought(int noBoxBought) {
		this.noBoxBought = noBoxBought;
	}


	public int getNoBoxSold() {
		return noBoxSold;
	}


	public void setNoBoxSold(int noBoxSold) {
		this.noBoxSold = noBoxSold;
	}


	public String getAdditionalNotes() {
		return additionalNotes;
	}


	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}


	public Date getMfgDate() {
		return mfgDate;
	}


	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}


	public Date getExpDate() {
		return expDate;
	}


	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}


	public String getProductImagePath() {
		return productImagePath;
	}


	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}
	


}
