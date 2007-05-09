package org.dodgybits.progress.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;

@Entity
@Table(name="Deploy_Target")
public class DeployTarget implements Serializable
{
  private static final long serialVersionUID = 1L;

  public enum Status { inactive, running }

  private Long aId;
  private Target aTarget;
  private Artifact aArtifact;
  private Deploy aDeploy;
  private Status aStatus;
  private String aComments;
  
  @Id() 
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId()
  {
    return aId;
  }

  @ManyToOne
  public Deploy getDeploy()
  {
    return aDeploy;
  }
  
  @ManyToOne
  public Target getTarget()
  {
    return aTarget;
  }
  
  @ManyToOne
  public Artifact getArtifact()
  {
    return aArtifact;
  }
  
  @Enumerated(EnumType.STRING)
  public Status getStatus()
  {
    return aStatus;
  }
  
  @Basic
  public String getComments()
  {
    return aComments;
  }
  
  public void setId(Long pId)
  {
    aId = pId;
  }

  public void setDeploy(Deploy pDeploy)
  {
    aDeploy = pDeploy;
  }
  
  public void setTarget(Target pTarget)
  {
    aTarget = pTarget;
  }
  
  public void setArtifact(Artifact pArtifact)
  {
    aArtifact = pArtifact;
  }
  
  public void setStatus(Status pStatus)
  {
    aStatus = pStatus;
  }
  
  public void setComments(String pComments)
  {
    aComments = pComments;
  }
  
  public boolean equals(Object pTop)
  {
    DeployTarget lTop = (DeployTarget) pTop;
    return ObjectUtils.equals(aDeploy, lTop.aDeploy) &&
    ObjectUtils.equals(aTarget, lTop.aTarget) &&
    ObjectUtils.equals(aArtifact, lTop.aArtifact);
  }
  
  public int hashCode()
  {
    return aId.hashCode();
  }
  
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[ArtifactTopology");
    b.append(" id=").append(aId);
    b.append(" target=").append(aTarget.getName());
    b.append(" artifact=").append(aArtifact.getName());
    b.append(" deploy=").append(aDeploy);
    b.append(" status=").append(aStatus);
    b.append(" comments=").append(aComments);
    b.append("]");
    return b.toString();
  }

}
