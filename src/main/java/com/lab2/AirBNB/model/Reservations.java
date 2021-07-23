package com.lab2.AirBNB.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end_date;;
	private double price;
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "apartment_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Apartment apartment;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "reservation_user",
			joinColumns = { @JoinColumn(name = "reservation_id") },
			inverseJoinColumns = { @JoinColumn(name = "user_id") }
	)
	private Set<User> users = new HashSet<>();


	public int compareTo(Reservations o) {
		return this.getId().compareTo(o.getId());
	}

	public Date getDateStartDate() throws ParseException {
		String date2 = this.start_date.toString();
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
		return date1;
	}

}
