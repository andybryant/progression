package org.dodgybits.progress.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang.StringUtils;

@Entity
public class Client implements Serializable
{
  private static final long serialVersionUID = 1L;

  private Long aId;
  private String aName;
  private List<Project> aProjects;
  
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

  @OneToMany(mappedBy="client")
  public List<Project> getProjects()
  {
    return aProjects;
  }

  public void setId(Long pId)
  {
    aId = pId;
  }
  
  public void setName(String pName)
  {
    aName = pName;
  }
  
  public void setProjects(List<Project> pProjects)
  {
    aProjects = pProjects;
  }
  
  public boolean equals(Object pClient)
  {
    return StringUtils.equals(aName, ((Client)pClient).aName);
  }
  
  public int hashCode()
  {
    return aName.hashCode();
  }
  
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Client");
    b.append(" id=").append(aId);
    b.append(" name=").append(aName);
    b.append("]");
    return b.toString();
  }
  
}
