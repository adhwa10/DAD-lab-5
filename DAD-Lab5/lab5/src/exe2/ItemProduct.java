package exe2;

import java.io.Serializable;

public class ItemProduct implements Serializable{
	
	private String name;
	private int itemProductID;
	private float price;
	
	//setter
	public void setname(String name) {
		this.name = name;	
	}

	public void setitemProductID(int itemProductID)
	{
		this.itemProductID = itemProductID;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}
	
	//getter
	public String getname()
	{
		return name;
	}
	
	public int getitemProductID()
	{
		return itemProductID;
	}
	
	public float getPrice()
	{
		return price;
	}

}

