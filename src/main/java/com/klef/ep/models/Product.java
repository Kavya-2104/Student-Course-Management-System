package com.klef.ep.models;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="product_table")
public class Product implements Serializable
{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id  // primary key
	    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment
	    private int id;
	    
		@Column(nullable = false)
	    private String category;
		@Column(nullable = false)
	    private String name;
		@Column(nullable = false)
	    private double cost;
		@Column(nullable = false)
	    private String description;
	    
	    @Lob
	    @Column(nullable = false)
	    private Blob imagedata;    // Lob means Large Object & Blob means binary large object

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getCost() {
			return cost;
		}

		public void setCost(double cost) {
			this.cost = cost;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Blob getImagedata() {
			return imagedata;
		}

		public void setImagedata(Blob imagedata) {
			this.imagedata = imagedata;
		}

		
}
