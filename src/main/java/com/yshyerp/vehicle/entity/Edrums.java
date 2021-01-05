package com.yshyerp.vehicle.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

 @Data
public class Edrums implements Serializable {

    private String edmno;

    private Date date;

    private Date date1;

    private String customer;

    private String scustomer;

    private Boolean cover;

    private String color;

    private BigDecimal tare;

    private String n;

    private Boolean charge;

    private Integer balance;

    private Integer quantity;

    private  String remarks;

    public void setRemarks(String remarks){
        this.remarks=remarks;
    }

    public  String getRemarks(){
        return  remarks;
    }

    private static final long serialVersionUID = 1L;


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getCustomer() {
        return customer;
    }


    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }


    public Date getDate1() {
        return date1;
    }


    public void setDate1(Date date1) {
        this.date1 = date1;
    }


    public Boolean getCover() {
        return cover;
    }


    public void setCover(Boolean cover) {
        this.cover = cover;
    }


    public String getEdmno() {
        return edmno;
    }


    public void setEdmno(String edmno) {
        this.edmno = edmno == null ? null : edmno.trim();
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }


    public BigDecimal getTare() {
        return tare;
    }


    public void setTare(BigDecimal tare) {
        this.tare = tare;
    }


    public String getN() {
        return n;
    }


    public void setN(String n) {
        this.n = n;
    }



    public Integer getBalance() {
        return balance;
    }


    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String getScustomer() {
        return scustomer;
    }

    public void setScustomer(String scustomer) {
        this.scustomer = scustomer == null ? null : scustomer.trim();

    }

    public Boolean getCharge() {
        return charge;
    }


    public void setCharge(Boolean charge) {
        this.charge = charge;
    }

}
