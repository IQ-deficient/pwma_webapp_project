package pwma.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

//@SuppressWarnings("serial")
// an entity represents a table in a relational database
// each entity instance corresponds to a row in that table
@Entity
// create an XML representation of this instance
@XmlRootElement
// static queries, also known as named queries
@NamedQueries({ @NamedQuery(name = Member.GET_ALL, query = "Select m from Member m"),
		@NamedQuery(name = Member.GET_BY_NAME, query = "Select m from Member m where m.name = :name") })
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	// named queries are defined statically on entities
	// common practice is to prefix the query name with the entity name like Member.findAll
	public static final String GET_ALL = "Member.getAll";
	public static final String GET_BY_NAME = "Member.getByName";

	public static final int DAY_OF_YEAR = 6;
	public static final int YEAR = 1;

	// tell the persistence provider that the id attribute has to be mapped to a primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
	// @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
	@Basic(optional = false)
	// attribute names are mapped to a specific column name
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "name", nullable = true)
	private String name;

	// this indicates to the validation runtime that the value cannot be null
	@NotNull
	@NotEmpty
	@Email
	@Column(name = "email", nullable = false)
	private String email;

	@NotNull
	// @Size(min = 10, max = 12)
	// @Digits(fraction = 0, integer = 12)
	// (?=.*[@#$%])
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[^-\\s].[^-\\s]{6,20})", message = "Must contain: 1x Digit; 1x lower+upper case; 6-20 char; no spaces")
	@Column(name = "password", nullable = false)
	private String password;

	// if there is no need to map an attribute to database
	@Transient
	private Integer age;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date creationDate;

	// annotated such that it will get called before inserting data into or updating data in the database
	@PrePersist
	@PreUpdate
	private void validateEmail() {
		if (email == null || "".equals(email))
			throw new IllegalArgumentException("Invalid email");
	}

	@PostLoad
	@PostPersist
	@PostUpdate
	public void calculateAge() {
		if (dateOfBirth == null) {
			age = null;
			return;
		}
		Calendar birth = new GregorianCalendar();
		birth.setTime(dateOfBirth);
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		int adjust = 0;
		// https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html#DAY_OF_YEAR
		if (now.get(DAY_OF_YEAR) - birth.get(DAY_OF_YEAR) < 0) {
			adjust = -1;
		}
		age = now.get(YEAR) - birth.get(YEAR) + adjust;
	}

	// @OneToOne(cascade = CascadeType.ALL)
	// @PrimaryKeyJoinColumn
	// private Account accounts;

	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy =
	// "member")
	// // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	// private Set<Item> items;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "member")
	private Set<Item> items;

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", age=" + age
				+ "]";
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
		Member other = (Member) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
