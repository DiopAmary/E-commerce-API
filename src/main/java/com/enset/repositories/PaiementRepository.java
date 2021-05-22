package com.enset.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.enset.entities.CommandeEntity;
import com.enset.entities.PaiementEntity;

@Repository
public interface PaiementRepository extends JpaRepository<PaiementEntity,Long> {
 	public PaiementEntity findById(long id);
	public void deleteById(long id);
	public PaiementEntity findByCommande(CommandeEntity commande);
	public PaiementEntity findByCodePaiement(String codePaiement);
}
