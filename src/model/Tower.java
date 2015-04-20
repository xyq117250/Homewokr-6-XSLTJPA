package model;
import java.util.List;
import javax.persistence.*;

import javax.xml.bind.annotation.*;


@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)

//Do not need write for function for find all Tower
//@NamedQuery(name="Tower.findAll", query="SELECT t FROM Tower t")

public class Tower{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private double height;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private int sides;

	//many-to-one relationship to Equipment
	@OneToMany(mappedBy="tower",cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="equipment")
	private List<Equipment> equipments;

	//bi-directional many-to-one association to Site
	@ManyToOne
	@JoinColumn(name="siteId")
	@XmlTransient
	private Site site;

	public Tower() {
		super();
	}

	public Tower(int id, String name, double height, int sides,List<Equipment> equipments, Site site) 
	{
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.sides = sides;
		this.equipments = equipments;
		this.site = site;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSides() {
		return this.sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}

	public List<Equipment> getEquipments() {
		return this.equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}