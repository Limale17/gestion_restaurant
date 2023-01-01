package sn.niit.restauranManagementApplication.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Categorie 
{ 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Le nom de la catégorie est obligatoire")
	@NotEmpty(message="Le nom de la catégorie est obligatoire ")
	private String nom;
	
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="categorie")
    List<Produit> listProduit;
	
	public  Categorie(){}
	
	public  Categorie(String nom,String description) 
	{
		this.nom = nom;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CategorieService [id=" + id + ", nom=" + nom + ", description=" + description + "]";
	}
	
	

}
