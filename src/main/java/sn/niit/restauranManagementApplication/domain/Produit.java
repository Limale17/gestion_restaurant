package sn.niit.restauranManagementApplication.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Produit 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Le nom du produit est obligatoire.")
	@NotEmpty(message="Le nom du produit est obligatoire.")
	private String name;
	
	private String description;
	
	@NotNull(message="Le nom du produit est obligatoire.") 
	private Double price;
	
	private String image;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey= @ForeignKey(name="categorie_id"), name="categorie_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Categorie categorie;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey= @ForeignKey(name="orderItems.id"), name="orderItems.id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private OrderItems orderItems;
	
	
	
	public  Produit() {}

	public Produit(long id, String name, String description, Double price,String image) 
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	
	public OrderItems getOrderItems () {
		return orderItems;
	}

	public void setOrderItems (OrderItems order) {
		this.orderItems = order;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Plat [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}
	
}
