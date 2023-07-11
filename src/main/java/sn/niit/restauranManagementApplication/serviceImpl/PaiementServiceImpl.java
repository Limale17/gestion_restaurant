package sn.niit.restauranManagementApplication.serviceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.Paiement;
import sn.niit.restauranManagementApplication.repository.PaiementRepository;
import sn.niit.restauranManagementApplication.service.PaiementService;

@Service
public class PaiementServiceImpl implements PaiementService{

	@Autowired
	PaiementRepository paiementRepository;
	
	@Override
	public void savePaiment(Paiement paiement) {
		paiement.setDate(LocalDate.now());
		paiement.setMontant(paiement.getShoppingCart().getTotalPrice());
		paiementRepository.save(paiement);
	}

}
