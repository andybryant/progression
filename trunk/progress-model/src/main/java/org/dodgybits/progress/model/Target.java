package org.dodgybits.progress.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;


/**
 * Where an artifact can be deployed within a given environment. This will
 * usually be an application server, or it may simply a server it is standalone.
 */
@Entity
public class Target implements Serializable
{
  private static final long serialVersionUID = 1L;

  private Long aId;
  private Environment aEnvironment;
  private String aName;
  private String aServerName;
  private String aContainer;
  private String aDescription;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId()
  {
    return aId;
  }
  
  @ManyToOne
  public Environment getEnvironment()
  {
    return aEnvironment;
  }
  
  @Column(unique=true, nullable=false, length=50)
  public String getName()
  {
    return aName;
  }
  
  @Column(name="server_name", length=50)
  public String getServerName()
  {
    return aServerName;
  }
  
  @Basic
  public String getContainer()
  {
    return aContainer;
  }
  
  @Basic
  public String getDescription()
  {
    return aDescription;
  }

  public void setId(Long pId)
  {
    aId = pId;
  }
  
  public void setEnvironment(Environment pEnvironment)
  {
    aEnvironment = pEnvironment;
  }
  
  public void setName(String pName)
  {
    aName = pName;
  }

  public void setServerName(String pServerName)
  {
    aServerName = pServerName;
  }
  
  public void setContainer(String pContainer)
  {
    aContainer = pContainer;
  }
  
  public void setDescription(String pDescription)
  {
    aDescription = pDescription;
  }
  
  public boolean equals(Object pClient)
  {
    return StringUtils.equals(aName, ((Target)pClient).aName);
  }
  
  public int hashCode()
  {
    return aName.hashCode();
  }
  
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Target");
    b.append(" id=").append(aId);
    b.append(" environment=").append(aEnvironment.getName());
    b.append(" name=").append(aName);
    b.append(" serverName=").append(aServerName);
    b.append(" container=").append(aContainer);
    b.append(" description=").append(aDescription);
    b.append("]");
    return b.toString();
  }
}
