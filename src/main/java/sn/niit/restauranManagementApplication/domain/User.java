package sn.niit.restauranManagementApplication.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String prenom;
	private Long nom;
	
	@Column(nullable = false, unique = true, length = 40) 
	private String email;
	
	@Column(nullable = false, unique = true, length = 10)
	private String password;
	
	
	 @OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	 List<Order> orderList;
	 
	
	public User() {}
	public User(Long id, String prenom, String nom, String email,String password)
	{
		
		this.prenom= prenom;
		this.prenom= nom;
		this.email= email;
		this.password= password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Long getNom() {
		return nom;
	}
	public void setNom(Long nom) {
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
	
	@Override
	public String toString() {
		return "user [username=" + email + ", password=" + password + "]";
	}
	
}
