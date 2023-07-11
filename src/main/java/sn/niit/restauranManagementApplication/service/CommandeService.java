package sn.niit.restauranManagementApplication.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import sn.niit.restauranManagementApplication.domain.Commande;
import sn.niit.restauranManagementApplication.domain.User;

public interface CommandeService  
{
	void saveCommande(Commande commande);
	Optional<Commande> getCommandeById(Long id);
	List<Commande> getAllCommande();
	List<Commande> listCommandeParUser(User user);
	List<Commande> listCommandeParDate(LocalDate date);
	List<Commande> listCommandeParState();
	Page<Commande> findPaginated(int pageNumber, int pageSize);
}
