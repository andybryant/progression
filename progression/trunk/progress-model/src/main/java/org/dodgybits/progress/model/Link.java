package org.dodgybits.progress.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

@Entity
public class Link implements Serializable, Comparable<Link>
{
  private static final long serialVersionUID = 1L;

  private Long aId;
  private String aName;
  private String aUrl;
  private String aTopic;
  private int aDisplayOrder;
  private Environment aEnvironment;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId()
  {
    return aId;
  }

  @Column(unique = true, nullable = false, length = 30)
  public String getName()
  {
    return aName;
  }
  
  @Column(length=100)
  public String getUrl()
  {
    return aUrl;
  }

  @Column(length=50)
  public String getTopic()
  {
    return aTopic;
  }
  
  @ManyToOne()
  @JoinTable(
      name="Environment_Link",
      joinColumns=@JoinColumn(name="link_id"))
  public Environment getEnvironment()
  {
    return aEnvironment;
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

  public void setUrl(String pUrl)
  {
    aUrl = pUrl;
  }
  
  public void setTopic(String pTopic)
  {
    aTopic = pTopic;
  }
 
  public void setEnvironment(Environment pEnvironment)
  {
    aEnvironment = pEnvironment;
  }
  
  public void setDisplayOrder(int pDisplayOrder)
  {
    aDisplayOrder = pDisplayOrder;
  }
  
  public boolean equals(Object pClient)
  {
    return StringUtils.equals(aUrl, ((Link) pClient).aUrl);
  }

  public int hashCode()
  {
    return aUrl.hashCode();
  }

  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Link");
    b.append(" id=").append(aId);
    b.append(" name=").append(aName);
    if (aEnvironment != null)
    {
      b.append(" environment=" + aEnvironment.getName());
    }
    b.append(" topic=").append(aTopic);      
    b.append(" url=").append(aUrl);
    b.append(" displayOrder=").append(aDisplayOrder);
    b.append("]");
    return b.toString();
  }
  
  public int compareTo(Link pLink)
  {
    return aDisplayOrder - pLink.aDisplayOrder;
  }
  
}

  