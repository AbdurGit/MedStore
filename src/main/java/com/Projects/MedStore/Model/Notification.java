package com.Projects.MedStore.Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Notification {

    @Id
    private String id;
    private String type;
    private boolean muted;
    private String pdtName;
    private String batch;
    private Date expDate;
    private int expiringIn;
    public Notification() {
    }
    public Notification(String id, String type, boolean muted, String pdtName, String batch, Date expDate,
            int expiringIn) {
        this.id = id;
        this.type = type;
        this.muted = muted;
        this.pdtName = pdtName;
        this.batch = batch;
        this.expDate = expDate;
        this.expiringIn = expiringIn;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isMuted() {
        return muted;
    }
    public void setMuted(boolean muted) {
        this.muted = muted;
    }
    public String getPdtName() {
        return pdtName;
    }
    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }
    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }
    public Date getExpDate() {
        return expDate;
    }
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
    public int getExpiringIn() {
        return expiringIn;
    }
    public void setExpiringIn(int expiringIn) {
        this.expiringIn = expiringIn;
    }

    

    




    
}
