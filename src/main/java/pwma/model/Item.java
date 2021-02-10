package pwma.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQueries({ @NamedQuery(name = Item.GET_ALL, query = "Select i from Item i"),
		@NamedQuery(name = Item.GET_BY_NAME, query = "Select i from Item i where i.name like '%name%'") })
@Entity
@XmlRootElement
public class Item implements Serializable {

	//TODO read up on serials
	private static final long serialVersionUID = 1630193878032342300L;
	public static final String GET_ALL = "Item.getAll";
	public static final String GET_BY_NAME = "Item.getByName";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
	@SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "price", nullable = true)
	private Double price;

	// @ManyToOne
	// private Member member;

	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "item")
	// private Set<Member_Item> memberItem;

	// @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ManyToOne
	// src: https://www.baeldung.com/jackson-annotations#2-jsonignore (avoiding infinite recursion)
	@JsonIgnore
	private Member member;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "item")
	private Set<Item_Manufacturer> itemManufacturer;

	public Item() {
		super();
	}

	public Item(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
