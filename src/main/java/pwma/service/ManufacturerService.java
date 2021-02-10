package pwma.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pwma.model.Manufacturer;

@Stateless
public class ManufacturerService {

	@Inject
	private EntityManager em;

	public List<Manufacturer> getAllManufacturers() {
		return em.createNamedQuery(Manufacturer.GET_ALL, Manufacturer.class).getResultList();
	}

	public List<Manufacturer> getByName(String nameP) {
		return em.createNamedQuery(Manufacturer.GET_BY_NAME, Manufacturer.class).setParameter("name", nameP).getResultList();
	}

	public Manufacturer findById(Long id) {
		return em.find(Manufacturer.class, id);
	}

}
