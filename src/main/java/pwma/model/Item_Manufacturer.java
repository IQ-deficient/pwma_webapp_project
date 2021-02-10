package pwma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Item_Manufacturer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberItem_seq")
	@SequenceGenerator(name = "memberItem_seq", sequenceName = "memberItem_seq", allocationSize = 1)
	private Long id;

	@Column(name = "temp", nullable = true)
	private String temp;

	@ManyToOne
	private Item item;

	@ManyToOne
	private Manufacturer manufacturer;

	public Item_Manufacturer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

}
