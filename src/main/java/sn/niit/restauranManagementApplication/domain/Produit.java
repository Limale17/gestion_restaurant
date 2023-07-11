package sn.niit.restauranManagementApplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.validation.constraints.NotNull;

@Entity
public class Produit 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Le nom du produit est obligatoire.")
	@NotEmpty(message="Le nom du produit est obligatoire.")
	private String name;
	
	private String description;
	
	@NotNull(message="Le nom du produit est obligatoire.") 
	private Double price;
	

	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey= @ForeignKey(name="categorie_id"), name="categorie_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Categorie categorie;

	public Produit() {}

	public Produit(@NotEmpty(message = "Le nom du produit est obligatoire.") String name, String description,
			Double price, String image) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
}
