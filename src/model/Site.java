package model;


import javax.persistence.*;
import java.util.List;
import javax.xml.bind.annotation.*;


@Entity
@NamedQueries(value= { 
		@NamedQuery(
			name="findAllSites", 
			query="SELECT s FROM Site s")}) //used to perform query function

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Site {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private double latitude;
	@XmlAttribute
	private double longitude;
	@XmlAttribute
	private String name;

	// many-to-one association to Tower
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="tower")
	private List<Tower> towers;

	public Site() {
		
		super();
	}
	public Site(int id, String name, double latitude, double longitude,
			List<Tower> towers) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.towers = towers;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	/*do not need constructor here */
	
}