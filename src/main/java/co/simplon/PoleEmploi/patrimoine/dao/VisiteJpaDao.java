package co.simplon.PoleEmploi.patrimoine.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import co.simplon.PoleEmploi.patrimoine.modele.Monument;
import co.simplon.PoleEmploi.patrimoine.modele.Ville;
import co.simplon.PoleEmploi.patrimoine.modele.Visite;

@Named
public class VisiteJpaDao implements VisiteDao {

	@Inject
	private EntityManager entityManager;

	public VisiteJpaDao() {
		super();
	}

	@Override
	public Visite getVisiteById(Long id) {
		return entityManager.find(Visite.class, id);
	}

	@Override
	public void deleteVisiteById(Long id) {
		entityManager.getTransaction().begin();
		entityManager.createNamedQuery("Visite.deleteById")
				.setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
	public Visite createVisite(Visite Visite) {
		entityManager.getTransaction().begin();
		entityManager.persist(Visite);
		entityManager.getTransaction().commit();
		return Visite;
	}

	@Override
	public Visite updateVisite(Visite Visite) {
		entityManager.getTransaction().begin();
		Visite = entityManager.merge(Visite);
		entityManager.getTransaction().commit();
		return Visite;
	}

	@Override
	public List<Visite> findAll(int first, int size) {
		return entityManager.createNamedQuery("Visite.findAll", Visite.class)
				.setFirstResult(first).setMaxResults(size).getResultList();
	}

	@Override
	public List<Visite> findAllForMonumentId(Long idMonument, int first, int size) {
		return entityManager
				.createNamedQuery("Visite.findAllByMonumentId", Visite.class)
				.setParameter("id", idMonument).setFirstResult(first)
				.setMaxResults(size).getResultList();
	}

	@Override
	public Visite createVisiteForMonument(Visite visiteACreer, Long id) {
		Monument monument = entityManager.find(Monument.class, id);
		visiteACreer.setMonument(monument);
		visiteACreer = createVisite(visiteACreer);
		return visiteACreer;
	}
	
}
