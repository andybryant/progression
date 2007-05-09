package org.dodgybits.progress.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang.StringUtils;

@Entity
public class Artifact implements Serializable
{
  private static final long serialVersionUID = 1L;

  private Long aId;
  private String aName;
  private String aDescription;

  @Id() 
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

  public void setDescription(String pDescription)
  {
    aDescription = pDescription;
  }


  public void setId(Long pId)
  {
    aId = pId;
  }

  public void setName(String pName)
  {
    aName = pName;
  }

  public boolean equals(Object pClient)
  {
    return StringUtils.equals(aName, ((Artifact)pClient).aName);
  }
  
  public int hashCode()
  {
    return aName.hashCode();
  }
  
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Artifact");
    b.append(" id=").append(aId);
    b.append(" name=").append(aName);
    b.append(" description=").append(aDescription);
    b.append("]");
    return b.toString();
  }

}
