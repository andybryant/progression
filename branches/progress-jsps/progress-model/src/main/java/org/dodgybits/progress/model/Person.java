package org.dodgybits.progress.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;

@Entity
public class Person implements Serializable, Comparable<Person>
{
  private static final long serialVersionUID = 1L;

  private Long aId;
  private String aName;
  private String aEmailAddress;
  private String aLocation;
  private int aDisplayOrder;
  
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
  
  @Column(name="email_address", unique=true, nullable=false, length=30)
  public String getEmailAddress()
  {
    return aEmailAddress;
  }
  
  @Column(length=30)
  public String getLocation()
  {
    return aLocation;
  }
  
  @Column(name="display_order", unique=true)
  public int getDisplayOrder()
  {
    return aDisplayOrder;
  }

  public void setId(Long pId)
  {
    aId = pId;
  }
  
  public void setName(String pName)
  {
    aName = pName;
  }
  
  public void setEmailAddress(String pEmailAddress)
  {
    aEmailAddress = pEmailAddress;
  }
  
  public void setLocation(String pLocation)
  {
    aLocation = pLocation;
  }
  
  public void setDisplayOrder(int pDisplayOrder)
  {
    aDisplayOrder = pDisplayOrder;
  }
  
  public boolean equals(Object pClient)
  {
    return StringUtils.equals(aName, ((Person)pClient).aName);
  }
  
  public int hashCode()
  {
    return aName.hashCode();
  }
  
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Person");
    b.append(" id=").append(aId);
    b.append(" name=").append(aName);
    b.append(" emailAddress=").append(aEmailAddress);
    b.append(" aLocation=").append(aLocation);
    b.append(" displayOrder=").append(aDisplayOrder);
    b.append("]");
    return b.toString();
  }
  
  public int compareTo(Person pPerson)
  {
    return aDisplayOrder - pPerson.aDisplayOrder;
  }
  
}
