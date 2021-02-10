package pwma.service;

import pwma.model.Item;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ItemService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	// public void insert(Item item) throws Exception {
	// em.merge(item);
	// }

	public List<Item> getAllItems() {
		return em.createNamedQuery(Item.GET_ALL, Item.class).getResultList();
	}

	public List<Item> getByName(String nameP) {
		return em.createNamedQuery(Item.GET_BY_NAME, Item.class).setParameter("name", nameP).getResultList();
	}

	public Item findById(Long id) {
		return em.find(Item.class, id);
	}

}
