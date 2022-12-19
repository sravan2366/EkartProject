package com.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Products {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Prices> getPricesDetails() {
		return pricesDetails;
	}

//	public Products(int id, String name, String shortDesc, String description, String category,
//			List<Prices> pricesDetails) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.shortDesc = shortDesc;
//		this.description = description;
//		this.category = category;
//		this.pricesDetails = pricesDetails;
//	}

	public void setPricesDetails(List<Prices> pricesDetails) {
		this.pricesDetails = pricesDetails;
	}

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String shortDesc;
	private String description;
	private String category;
	
    @OneToMany(targetEntity = Prices.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="pid_fk",referencedColumnName = "id")
    private List<Prices> pricesDetails;
}
