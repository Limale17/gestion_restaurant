package sn.niit.restauranManagementApplication.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	private String prenom;
	private Long nom;
	
	@Column(nullable = false, unique = true, length = 40) 
	private String email;
	
	@Column(nullable = false, unique = true, length = 10)
	private String password;
	 
	
	public User() {}
	public User(Long userId, String prenom, String nom, String email,String password)
	{
		
		this.prenom= prenom;
		this.prenom= nom;
		this.email= email;
		this.password= password;
	}
	
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
