package com.rafsan.inventory.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "products")
public class Product implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String productName;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "description")
    private String description;
    @Column(name = "cgst")
    private double cgst;
    @Column(name = "sgst")
    private double sgst;
    
    @Transient
    private double cgstPer;
    @Transient
    private double sgstPer;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryId")
    private Category category;
    
   /* @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;*/

    public Product() {
    }

  /*  public Product(long id, String productName, double price, 
            double quantity, String description, Category category, Supplier supplier) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.supplier = supplier;
    }

    public Product(String productName, double price, 
            double quantity, String description, Category category, Supplier supplier) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.supplier = supplier;
    }*/
    
	public Product(String productName, double price, double quantity, String description, double cgst,
			double sgst, Category category, double cgstPer, double sgstPer) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.cgst = cgst;
		this.sgst = sgst;
		this.category = category;
		this.cgstPer = cgstPer;
		this.sgstPer = sgstPer;
	}

	public Product(long id, String productName, double price, double quantity, String description, double cgst,
			double sgst, Category category/*, double cgstPer, double sgstPer*/) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.cgst = cgst;
		this.sgst = sgst;
		this.category = category;
		/*this.cgstPer = cgstPer;
		this.sgstPer = sgstPer;*/
//		this.supplier = supplier;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

   /* public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }*/
    
    

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

	
	
	public double getCgstPer() {
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
	}

	@Override
    public String toString() {
        return "Product{" + "id=" + id + 
                ", productName=" + productName + 
                ", price=" + price + 
                ", quantity=" + quantity + 
                ", description=" + description + 
                ", category=" + category + 
                ", cgst="+cgst+
                ", sgst="+sgst+
                ", cgstPer="+cgstPer+
                ", sgstPer="+sgstPer+
                '}';
    }
}
