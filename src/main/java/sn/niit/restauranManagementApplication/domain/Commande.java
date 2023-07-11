package sn.niit.restauranManagementApplication.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Commande {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate date;
	private String heure;
	private double montantPaye;
	
	@OneToOne()
	@JoinColumn(name = "paiement_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Paiement paiement;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey= @ForeignKey(name="user_id"), name="user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	private State state;

	public Commande() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Paiement getPaiement() {
		return paiement;
	}

	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public double getMontantPaye() {
		return montantPaye;
	}

	public void setMontantPaye(double montantPaye) {
		this.montantPaye = montantPaye;
	}
	
	
}
