package co.simplon.PoleEmploi.patrimoine.endpoint;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import co.simplon.PoleEmploi.patrimoine.dao.MonumentDao;
import co.simplon.PoleEmploi.patrimoine.endpoint.MonumentResource;
import co.simplon.PoleEmploi.patrimoine.modele.Monument;

public class MonumentResourceTest {

	@Test(expected=IllegalArgumentException.class)
	public void deleteMonumentById_should_throw_IllegalArgumentException_when_id_is_null () {
		// Given
		MonumentResource monumentRJ = new MonumentResource();
		Long id = null;
		
		// When
		monumentRJ.deleteMonumentById(id);
		
		// Then
		// Inutile, une exception doit être retournée
	}
	@Test
	public void with_Mockito_getMonuments_should_return_DEFAULT_PAGE_SIZE_items_when_from_and_limit_are_both_null(){
		// Given
		MonumentResource resource = new MonumentResource();
		MonumentDao dao = Mockito.mock(MonumentDao.class);
		resource.setMonumentDao(dao);
		Integer from = null;
		Integer limit = null;
		
		// When
		Mockito.when(dao.findAll(0, MonumentResource.DEFAULT_PAGE_SIZE))
			.thenReturn(getMockedList(MonumentResource.DEFAULT_PAGE_SIZE));
		List<Monument> listeMonuments;
		listeMonuments = (ArrayList<Monument>) resource.getMonuments(from, limit);
		
		// Then
		assertEquals(MonumentResource.DEFAULT_PAGE_SIZE, listeMonuments.size());
		Mockito.verify(dao);
	}
	
	private List<Monument> getMockedList(int expectedSize) {
		// résultat du mock
		List<Monument> monumentsFromDao = new ArrayList<>();
		for (int i = 0; i < expectedSize; i++) {
			monumentsFromDao.add(new Monument("Touou"));
		}
		return monumentsFromDao;
	}
	
	@Test
	public void getMonuments_should_return_DEFAULT_PAGE_SIZE_items_when_from_and_limit_are_both_null(){
		// Given
		MonumentResource monumentRJ = new MonumentResource();
		List<Monument> liste;
		MonumentMockDao monumentMockDao = new MonumentMockDao();
		monumentRJ.setMonumentDao(monumentMockDao);
		int DEFAULT_PAGE_SIZE = 10;
		
		// When
		liste = (ArrayList<Monument>) monumentRJ.getMonuments(null, null);
		
		// Then
		assertEquals(DEFAULT_PAGE_SIZE, liste.size());
		
	}
}
