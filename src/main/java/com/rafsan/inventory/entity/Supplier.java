package com.rafsan.inventory.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "saddress")
    private String saddress;
    @Column(name = "gstnnumber")
    private String gstnnumber;

    public Supplier() {
    }

    public Supplier(String name, String phone, String address, String saddress, String gstnnumber) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.saddress = saddress;
        this.gstnnumber = gstnnumber;
    }

    public Supplier(long id, String name, String phone, String address, String saddress, String gstnnumber) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.saddress=saddress;
        this.gstnnumber = gstnnumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getGstnnumber() {
		return gstnnumber;
	}

	public void setGstnnumber(String gstnnumber) {
		this.gstnnumber = gstnnumber;
	}
    
}
