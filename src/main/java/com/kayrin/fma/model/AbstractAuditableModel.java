package com.kayrin.fma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractAuditableModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id; 
	private Integer version;
	private Integer lastUpdatedBy; 
	private Date lastUpdatedDate;
	private Boolean deleted = false; 
		
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 10)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Version
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Column(name = "last_updated_by")
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	@Column(name = "last_updated_date")
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	@Column(name = "deleted")
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	} 
	
	/**
	 * override this method if your domain object has child
	 * AbstractAuditableModel.
	 * @return
	 */
	public List<AbstractAuditableModel> listChildren() {
		return new ArrayList<AbstractAuditableModel>();
	}

	protected void addIfNotNull(List<AbstractAuditableModel> list, AbstractAuditableModel toAdd) {
		if (toAdd != null) {
			list.add(toAdd);
		}
	}

	protected void addAllIfNotNull(List<AbstractAuditableModel> list, List<? extends AbstractAuditableModel> toAdd) {
		if (toAdd != null) {
			list.addAll(toAdd);
		}
	}
	
}
