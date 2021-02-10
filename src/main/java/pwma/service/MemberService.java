package pwma.service;

import pwma.model.Item;
import pwma.model.Member;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.logging.Logger;

// turn a java class into a transactional and secure session component
@Stateless
public class MemberService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Member> memberEventSrc;

	public void register(Member member) throws Exception {
		log.info("Registering " + member.getName());
		// em.persist(member); // makes an instance managed and persistent
		em.merge(member); // merges the state of the given entity into the current persistence context
		memberEventSrc.fire(member);
	}

	public List<Member> getAllMembers() {
		return em.createNamedQuery(Member.GET_ALL, Member.class).getResultList();
	}

	public List<Member> getByName(String nameP) {
		return em.createNamedQuery(Member.GET_BY_NAME, Member.class).setParameter("name", nameP).getResultList();
	}

	public Member findById(Long id) {
		return em.find(Member.class, id);
	}

	// public List<Item> getAllItems() {
	// return em.createNamedQuery(Item.GET_ALL, Item.class).getResultList();
	// }
	//
	// public List<Item> getItemByName(String nameP) {
	// return em.createNamedQuery(Item.GET_BY_NAME, Item.class).setParameter("name", nameP).getResultList();
	// }

}
