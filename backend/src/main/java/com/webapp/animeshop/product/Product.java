package com.webapp.animeshop.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;

@Entity
@Table(name="products")
public class Product implements Serializable, Comparable<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonView(Basic.class)
	private String name;
	@JsonView(Basic.class)
	private String franchise;
	@JsonView(Basic.class)
	private double price;
	@JsonView(Basic.class)
	private String description;
	@JsonView(Basic.class)
	private int width;
	@JsonView(Basic.class)
	private int height;
	@JsonView(Basic.class)
	private int weight;
	@JsonView(Basic.class)
	private String distributor;
	@JsonView(Basic.class)
	private String reference;
	@JsonView(Basic.class)
	private int stock;
	@JsonView(Basic.class)
	private int actual_Stock;
	@JsonView(Basic.class)
	private String image;
	@JsonView(Basic.class)
	private String imagefull;
//	private boolean images;
//	private boolean imagesFull;
	//private Image image;
	
	public Product() {};
	
	public Product(String name, String franchise, double price, String description,
					int width, int height, int weight, String distributor, String reference,
					int stock, String image, String imagefull) {
		this.name = name;
		this.franchise = franchise;
		this.price = price;
		this.description = description;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.distributor = distributor;
		this.reference = reference;
		this.stock = stock;
		this.actual_Stock = this.stock;
		this.image = image;
		this.imagefull = imagefull;
	};
	
	
	
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
	
	public String getFranchise() {
		return franchise;
	}
	
	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getDistributor() {
		return distributor;
	}
	
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getActual_Stock() {
		return actual_Stock;
	}

	public void setActual_Stock(int actual_Stock) {
		this.actual_Stock = actual_Stock;
	}
	
	public void updateActual_Stock(int venta) {
		this.actual_Stock-=venta;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImagefull() {
		return imagefull;
	}

	public void setImagefull(String imagefull) {
		this.imagefull = imagefull;
	}
	
//	public boolean hasImage() {
//		return images;
//	}
//	
//	public void setImages(boolean images) {
//		this.images = images;
//	}
//	
//	public boolean getImagesFull() {
//		return imagesFull;
//	}
//	
//	public void setImagesFull(boolean imagesFull) {
//		this.imagesFull = imagesFull;
//	}

	public int compareTo(Product o2) {
		int compareQuantity = (int) ((Product) o2).getPrice();
		return (int) (compareQuantity - this.price);
	}
	
	public int compareTo2(Product o2) {
		int compareQuantity = (int) ((Product) o2).getPrice();
		return (int) (this.price - compareQuantity);
	}
	
	@Override
	public String toString() {
		return this.name + " ";
	}
}