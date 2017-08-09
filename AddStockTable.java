package com.company;

/**
 * Created by Khubaib CH on 8/6/2017.
 */
public class AddStockTable {
    private String batchNo,particulars,expdate,qty,rate,mrp;
    Integer month,year;
    public AddStockTable() {
        this.batchNo = "";
        this.particulars = "";
        this.expdate = "";
        this.qty = "";
        this.rate = "";
        this.mrp = "";
        this.month=0;
        this.year=0;
    }

    public AddStockTable(String batchNo, String particulars, String expdate, String qty, String rate, String mrp,int month,int year) {
        this.batchNo = batchNo;
        this.particulars = particulars;
        this.expdate = expdate;
        this.qty = qty;
        this.rate = rate;
        this.mrp = mrp;
        this.month=month;
        this.year=year;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
