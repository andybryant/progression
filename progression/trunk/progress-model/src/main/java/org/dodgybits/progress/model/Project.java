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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

@Entity
public class Project implements Serializable
{
  private static final long serialVersionUID = 1L;

  private Long aId;
  private String aName;
  private String aDescription;
  private List<Artifact> aArtifacts;
  private Client aClient;
  
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

  @ManyToMany(fetch=FetchType.LAZY)
  @JoinTable(inverseJoinColumns=@JoinColumn(name="artifact_id"))
  public List<Artifact>getArtifacts()
  {
    return aArtifacts;
  }
  
  @ManyToOne()
  public Client getClient()
  {
    return aClient;
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
  
  public void setArtifacts(List<Artifact> pArtifacts)
  {
    aArtifacts = pArtifacts;
  }
  
  public void setClient(Client pClient)
  {
    aClient = pClient;
  }

  public boolean equals(Object pClient)
  {
    return StringUtils.equals(aName, ((Project)pClient).aName);
  }
  
  public int hashCode()
  {
    return aName.hashCode();
  }
  
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Project");
    b.append(" id=").append(aId);
    b.append(" name=").append(aName);
    b.append(" client=").append(aClient);
    b.append(" description=").append(aDescription);
    b.append("]");
    return b.toString();
  }
}
