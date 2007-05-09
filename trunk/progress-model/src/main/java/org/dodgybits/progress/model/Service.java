package org.dodgybits.progress.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.apache.commons.lang.ObjectUtils;

@MappedSuperclass
public abstract class Service implements Serializable
{

  public enum Status { scheduled, pending, abandoned, complete }

  protected Long aId;
  protected Person aContact;
  protected String aComments;
  protected Status aStatus;
  protected Date aCreatedDate;
  protected Date aScheduledDate;
  protected Date aPerformedDate;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId()
  {
    return aId;
  }
  
  @ManyToOne 
  public Person getContact()
  {
    return aContact;
  }
  
  @Basic
  public String getComments()
  {
    return aComments;
  }

  @Enumerated(STRING)
  public Status getStatus()
  {
    return aStatus;
  }
  
  @Temporal(TIMESTAMP) 
  @Column(name="created_timestamp")
  public Date getCreatedDate()
  {
    return aCreatedDate;
  }
  
  @Temporal(TIMESTAMP) 
  @Column(name="scheduled_timestamp")
  public Date getScheduledDate()
  {
    return aScheduledDate;
  }
  
  @Temporal(TIMESTAMP) 
  @Column(name="performed_timestamp")
  public Date getPerformedDate()
  {
    return aPerformedDate;
  }
  
  public void setId(Long pId)
  {
    aId = pId;
  }

  
  public void setContact(Person pContact)
  {
    aContact = pContact;
  }

  public void setComments(String pComments)
  {
    aComments = pComments;
  }
  
  
  public void setStatus(Status pStatus)
  {
    aStatus = pStatus;
  }
  
  public void setCreatedDate(Date pCreatedDate)
  {
    aCreatedDate = pCreatedDate;
  }
  
  public void setScheduledDate(Date pScheduledDate)
  {
    aScheduledDate = pScheduledDate;
  }
  
  public void setPerformedDate(Date pPerformedDate)
  {
    aPerformedDate = pPerformedDate;
  }
  
  public boolean equals(Object pObject)
  {
    return ObjectUtils.equals(aId, ((Service)pObject).aId);
  }
  
  public int hashCode()
  {
    return aId.hashCode();
  }
}
