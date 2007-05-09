package org.dodgybits.progress.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Deploy extends Service
{
  private static final long serialVersionUID = 1L;
  
  protected Build aBuild;
  protected Environment aEnvironment;
  protected List<DeployTarget> aDeployTargets;
  
  @ManyToOne
  public Build getBuild()
  {
    return aBuild;
  }
  
  @ManyToOne
  public Environment getEnvironment()
  {
    return aEnvironment;
  }

  @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="deploy")
  public List<DeployTarget> getDeployTargets()
  {
    return aDeployTargets;
  }
  
  public void setBuild(Build pBuild)
  {
    aBuild = pBuild;
  }
  
  public void setEnvironment(Environment pEnvironment)
  {
    aEnvironment = pEnvironment;
  }
  
  public void setDeployTargets(List<DeployTarget> pDeployTargets)
  {
    aDeployTargets = pDeployTargets;
  }
  
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Deploy");
    b.append(" id=").append(aId);
    b.append(" build_id=").append(aBuild.getId());
    b.append(" artifact=").append(aBuild.getArtifact().getName());
    b.append(" environment=").append(aEnvironment.getName());
    b.append(" contact=").append(aContact.getName());
    b.append(" comments=").append(aComments);
    b.append(" status=").append(aStatus);
    b.append(" scheduledDate=").append(aScheduledDate);
    b.append(" performedDate=").append(aPerformedDate);
    b.append("]");
    return b.toString();
  }

}
