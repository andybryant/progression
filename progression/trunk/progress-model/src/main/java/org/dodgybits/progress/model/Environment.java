package org.dodgybits.progress.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.commons.lang.StringUtils;

@Entity
public class Environment implements Serializable, Comparable<Environment> {
  private static final long serialVersionUID = 1L;

  private Long aId;
  private String aName;
  private String aDescription;
  private List<Target> aTargets;
  private int aDisplayOrder;
  private List<Link> aLinks;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId()
  {
	  return aId;
  }
  
  @Column(unique=true, nullable=false, length=30)
  public String getName()
  {
    return aName;
  }

  @Basic
  public String getDescription()
  {
    return aDescription;
  }
  
  @OneToMany(fetch=FetchType.LAZY,mappedBy="environment")
  public List<Target> getTargets()
  {
    return aTargets;
  }
  
  @Column(name="display_order", unique=true)
  public int getDisplayOrder()
  {
    return aDisplayOrder;
  }
  
  @OneToMany(fetch=FetchType.LAZY, mappedBy="environment")
  @OrderBy("displayOrder")
  public List<Link> getLinks()
  {
    return aLinks;
  }
  
  public void setId(Long pId)
  {
    aId = pId;
  }
  
  public void setName(String pName)
  {
    aName = pName;
  }
  
  public void setDescription(String pDescription)
  {
    aDescription = pDescription;
  }
  
  public void setTargets(List<Target> pTargets)
  {
    aTargets = pTargets;
  }
  
  public void setDisplayOrder(int pDisplayOrder)
  {
    aDisplayOrder = pDisplayOrder;
  }
  
  public void setLinks(List<Link> pLinks)
  {
    aLinks = pLinks;
  }
  
  public boolean equals(Object pClient)
  {
    return StringUtils.equals(aName, ((Environment)pClient).aName);
  }
  
  public int hashCode()
  {
    return aName.hashCode();
  }
  
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Environment");
    b.append(" id=").append(aId);
    b.append(" name=").append(aName);
    b.append(" description=").append(aDescription);
    b.append(" displayOrder=").append(aDisplayOrder);
    b.append("]");
    return b.toString();
  }
  
  public int compareTo(Environment pEnvironment)
  {
    return aDisplayOrder - pEnvironment.aDisplayOrder;
  }
  
}
