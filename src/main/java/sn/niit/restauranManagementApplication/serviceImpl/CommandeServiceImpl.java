package sn.niit.restauranManagementApplication.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.Commande;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.repository.CommandeRepository;
import sn.niit.restauranManagementApplication.service.CommandeService;

@Service
public class CommandeServiceImpl implements CommandeService

{
	@Autowired
	private CommandeRepository commandeRepository;

	@Override
	public void saveCommande(Commande commande) {
		commande.setDate(LocalDate.now());
		User user = commande.getPaiement().getUser();
		
		commande.setUser(user);
		commande.setMontantPaye(commande.getPaiement().getShoppingCart().getTotalPrice());
		commandeRepository.save(commande);
	}

	@Override
	public Optional<Commande> getCommandeById(Long id) {
		
		return commandeRepository.findById(id);
	}

	@Override
	 @Query(value = "select * from Commande c ORDER BY date DESC",
     nativeQuery = true)
	public List<Commande> getAllCommande() {
		return commandeRepository.findAll();
	}

	@Override
	public List<Commande> listCommandeParUser(User user) {
		return commandeRepository.getCommandeByUser(user);
	}

	@Override
	public List<Commande> listCommandeParDate(LocalDate date) {
		return commandeRepository.getCommandeByDate(date);
	}

	@Override
	public Page<Commande> findPaginated(int pageNumber, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNumber-1, pageSize);
		return commandeRepository.findAll(pageable);
	}

	@Override
	public List<Commande> listCommandeParState() {
		return commandeRepository.getCommandeByState();
	}
	
}
