package taproom.model;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Tap {
	
	int totalOuncesAvailable;
	int totalOuncesPoured;
	boolean active;
	Beer beer;
	String description;
	public Tap(boolean active, Beer beer) {
		super();
		this.active = active;
		this.beer = beer;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Beer getBeer() {
		return beer;
	}
	public void setBeer(Beer beer) {
		this.beer = beer;
	}
	public int getTotalOuncesAvailable() {
		return totalOuncesAvailable;
	}
	public void setTotalOuncesAvailable(int totalOuncesAvailable) {
		this.totalOuncesAvailable = totalOuncesAvailable;
	}
	public int getTotalOuncesPoured() {
		return totalOuncesPoured;
	}
	public void setTotalOuncesPoured(int totalOuncesPoured) {
		this.totalOuncesPoured = totalOuncesPoured;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
