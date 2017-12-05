package co.simplon.PoleEmploi.patrimoine.dao;

import java.util.List;

import co.simplon.PoleEmploi.patrimoine.modele.Visite;

public interface VisiteDao {
	List<Visite> findAll(int first, int size);

	Visite getVisiteById(Long id);

	void deleteVisiteById(Long id);

	Visite createVisite(Visite ville);

	Visite updateVisite(Visite ville);
	
	List<Visite> findAllForMonumentId(Long idMonument, int first, int size);

	Visite createVisiteForMonument(Visite visiteACreer, Long id);
	
}
