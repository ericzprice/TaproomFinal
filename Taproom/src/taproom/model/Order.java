    package taproom.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.NONE)
//nothing is serialized
public class Order {
	
	//@XmlElement
	private String beerStyle;
	//@XmlElement
	private int quantity;
	//@XmlElement
	private int size;
	//@XmlElement
	private String location;
	public Order(String beerStyle, String location,int quantity, int size) {
		super();
		this.beerStyle = beerStyle;
                         this.location=location;
		this.size = size;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBeerStyle() {
		return beerStyle;
	}
	public void setBeerStyle(String beerStyle) {
		this.beerStyle = beerStyle;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Order [beerStyle=" + beerStyle + ", quantity=" + quantity + ", size=" + size + ", location=" + location
				+ "]";
	}
	
	
	
	
	

}