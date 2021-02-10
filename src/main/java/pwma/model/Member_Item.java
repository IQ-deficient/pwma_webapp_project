package pwma.model;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

//@Entity
public class Member_Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberItem_seq")
	@SequenceGenerator(name = "memberItem_seq", sequenceName = "memberItem_seq", allocationSize = 1)
	private Long id;
	
//	@ManyToOne
//	private Member member;
//
//	@OneToMany
//	private Set<Item> items;

	public Member_Item() {
		super();
	}

}
