package sn.niit.restauranManagementApplication.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.Commande;
import sn.niit.restauranManagementApplication.domain.User;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
	List<Commande> getCommandeByUser(User user);
	
	List<Commande> getCommandeByDate(LocalDate date);
	
	 @Query(value = "select * from Commande c where c.state=1 ORDER BY date DESC",
	            nativeQuery = true)
	List<Commande> getCommandeByState();

	
}
