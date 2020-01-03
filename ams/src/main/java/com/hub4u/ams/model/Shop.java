package com.hub4u.ams.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Shop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "{shop.reference.required}")
	private String reference;
	private String dimensions;
	@Min(message = "{shop.price.minimum}", value = 1000)
	private int price;
	private String description;
	private boolean occupied;
	private String pictureFileName;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "shop", fetch = FetchType.EAGER)
	private List<ShopTenantRegistration> tenants  = new ArrayList<ShopTenantRegistration>();
	
	public Shop() {
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDateModified() {
		return dateModified;
	}

	public void setDateModified(LocalDateTime dateModified) {
		this.dateModified = dateModified;
	}
	
	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public List<ShopTenantRegistration> getTenants() {
		return tenants;
	}

	public void setTenants(List<ShopTenantRegistration> tenants) {
		this.tenants = tenants;
	}
	
	/**
	 * */
	public void assignNewTenant(Tenant tenant) {
		
//		tenant.setActive(true);
//		tenant.setDateCreated(LocalDateTime.now());
//		tenant.set
//		
//		servant.setAssigned(true);
//		servant.setCurrentDeptArea(this.getName());
//		
//		ServantZone servantZone = new ServantZone();
//		servantZone.setAssignmentStartDate(LocalDate.now());
//		servantZone.setZone(this);
//		servantZone.setServant(servant);
//		servantZone.setZoneId(this.getId());
//		servantZone.setServantId(servant.getId());
//		
//		servant.getZones().add(servantZone);
//		this.getServants().add(servantZone);
		
	}


	@Override
	public String toString() {
		return "Shop [reference=" + reference + ", dimensions=" + dimensions + ", price=" + price + ", description="
				+ description + ", occupied=" + occupied + ", pictureFileName=" + pictureFileName + ", dateCreated="
				+ dateCreated + ", dateModified=" + dateModified + "]";
	}

}
