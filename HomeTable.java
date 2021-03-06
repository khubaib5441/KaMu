package com.company;

/**
 * Created by Khubaib CH on 8/1/2017.
 */
public class HomeTable {
    private String particulars,batchNo,mrp,rate, date;
    private Integer qty,totalqty,amount;

    public HomeTable(String particulars,Integer totalqty,String batchNo,String date, String mrp, String rate) {
        this.particulars = particulars;
        this.totalqty=totalqty;
        this.batchNo = batchNo;
        this.mrp = mrp;
        this.rate = rate;
        this.date = date;

    }

    public Integer getTotalqty() {
        return totalqty;
    }

    public void setTotalqty(Integer totalqty) {
        this.totalqty = totalqty;
    }

    public HomeTable(Integer qty) {
        this.qty = qty;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
