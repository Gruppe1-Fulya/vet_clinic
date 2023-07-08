package package1;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class inventory implements List<inventory> {

    
	private String itemName;
	private String description;
	private int quantity;
	private float price;
	private Date expirationDate;
	private int supplierID;
	
	public inventory (String itemName, String description, int quantity, float price, Date expirationDate, int supplierID) {
		
		this.itemName=itemName;
		this.description=description;
		this.quantity=quantity;
		this.price=price;
		this.expirationDate=expirationDate;
		this.supplierID=supplierID;
	}
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName=itemName;
	}
	public String getDescription(){
		return description;
	}	
	public void setDescription(String description) {
		this.description=description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price=price;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate=expirationDate;
	}
	public int getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(int supplierID) {
		this.supplierID=supplierID;
	}


	public boolean isEmpty() {
		return false;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Iterator<inventory> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean add(inventory e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addAll(Collection<? extends inventory> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addAll(int index, Collection<? extends inventory> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public inventory get(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public inventory set(int index, inventory element) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void add(int index, inventory element) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public inventory remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ListIterator<inventory> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ListIterator<inventory> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<inventory> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
