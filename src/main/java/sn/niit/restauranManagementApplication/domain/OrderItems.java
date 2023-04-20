package sn.niit.restauranManagementApplication.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name= "orders")
public class OrderItems 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private  LocalDate date;
	private double totalPrice;
	private Boolean state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	 
	  private User user;
		
		  @OneToMany(cascade=CascadeType.ALL,mappedBy="orderItems")
		  List<Produit>ListProduit;
		 
		 
	public OrderItems() {}
	
	public OrderItems(LocalDate date, double totalPrice, Boolean state, User user) 
	{
		this.date = date;
		this.totalPrice = totalPrice;
		this.state = state;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", totalPrice=" + totalPrice + ", state=" + state + ", user="
				+ user + "]";
	}
	
	
	
	
	
}

