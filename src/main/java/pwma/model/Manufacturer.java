package pwma.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.NamedQueries;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = Manufacturer.GET_ALL, query = "Select m from Manufacturer m"),
		@NamedQuery(name = Manufacturer.GET_BY_NAME, query = "Select m from Manufacturer m where m.name like '%name%'") })
@Entity
@XmlRootElement
public class Manufacturer implements Serializable {

	private static final long serialVersionUID = 3677149284423984436L;
	public static final String GET_ALL = "Manufacturer.getAll";
	public static final String GET_BY_NAME = "Manufacturer.getByName";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manufacturer_seq")
	@SequenceGenerator(name = "manufacturer_seq", sequenceName = "manufacturer_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = true)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "manufacturer")
	private Set<Item_Manufacturer> itemManufacturer;

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
		Manufacturer other = (Manufacturer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Manufacturer() {
		super();
	}

	public Manufacturer(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + "]";
	}

}