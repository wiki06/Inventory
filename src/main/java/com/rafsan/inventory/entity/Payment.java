package com.rafsan.inventory.entity;

public class Payment {
    
    private double subTotal;
    private double cgst;
    private double sgst;
    private double discount;
    private double payable;

    public Payment(double subTotal, double cgst,double sgst, double discount, double payable) {
        this.subTotal = subTotal;
        this.cgst = cgst;
        this.sgst = sgst;
        this.discount = discount;
        this.payable = payable;
    }

    public double getCgst() {
		return cgst;
	}

	public void setCgst(double cgst) {
		this.cgst = cgst;
	}

	public double getSgst() {
		return sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPayable() {
        return payable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }
    
    
    
}
