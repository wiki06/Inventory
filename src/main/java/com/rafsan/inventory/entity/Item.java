package com.rafsan.inventory.entity;

public class Item {

    private String itemName;
    private double unitPrice;
    private double quantity;
    private double total;
    private double cgst;
    private double sgst;
    /*private double cgstPer;
    private double sgstPer;*/

    public Item() {
    }

    /*public Item(String itemName, double unitPrice, double quantity, double total) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
    }*/
	@Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + 
                ", unitPrice=" + unitPrice + 
                ", quantity=" + quantity + 
                ", total=" + total + 
                ", cgst=" + cgst+
                ", sgst=" + sgst+
                /*", cgstPer=" + cgstPer+
                ", cgstPer=" + sgstPer+*/'}';
    }

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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

/*	public double getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(double cgstPer) {
		this.cgstPer = cgstPer;
	}

	public double getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(double sgstPer) {
		this.sgstPer = sgstPer;
	}*/

	public Item(String itemName, double unitPrice, double quantity, double total, double cgst, double sgst) {
		super();
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.total = total;
		this.cgst = cgst;
		this.sgst = sgst;
		/*this.cgstPer = cgstPer;
		this.sgstPer = sgstPer;*/
	}
}
