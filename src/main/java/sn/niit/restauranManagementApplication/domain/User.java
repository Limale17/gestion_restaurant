package sn.niit.restauranManagementApplication.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "user")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String prenom;
	@NotEmpty
	private String nom;

	@NotEmpty(message="L'email de l'utlisateur  est obligatoire ")
	private String email;

	@NotEmpty(message="Le mot de pass  de l'utlisateur  est obligatoire ")
	private String password;
	
	@NotEmpty
	private String adresse;
	
	@NotEmpty
	private String telephone;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//	@JoinTable(
//			name = "user_roles", 
//			joinColumns = @JoinColumn(name="user_id"),
//			inverseJoinColumns = @JoinColumn(name = "roles_id")
//			)
	private List<Role> roles= new ArrayList<>();
	
	public User() {}
	public User(Long id, String prenom, String nom, String email,String password)
	{
		
		this.prenom= prenom;
		this.prenom= nom;
		this.email= email;
		this.password= password;
	}



	public Long getUserId() {
		return id;
	}
	public void setUserId(Long userId) {
		this.id = userId;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email= email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
