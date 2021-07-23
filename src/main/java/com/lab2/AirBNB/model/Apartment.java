package com.lab2.AirBNB.model;

import java.sql.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "apartment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private double total_occupancy;
	private int total_bedrooms;
	private int total_bathrooms;
	private String address;
	private boolean has_tv;
	private boolean has_kitchen;
	private boolean has_air_con;
	private boolean has_heating;
	private boolean has_internet;
	private double price;
	private Date published_at;

}
