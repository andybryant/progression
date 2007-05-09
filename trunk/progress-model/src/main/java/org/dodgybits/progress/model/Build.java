package org.dodgybits.progress.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Build extends Service
{
  private static final long serialVersionUID = 1L;

  protected String aVersion;
  protected Project aProject;
  protected Artifact aArtifact;

  @Column(length=30)
  public String getVersion()
  {
    return aVersion;
  }
  
  @ManyToOne
  public Project getProject()
  {
    return aProject;
  }
  
  @ManyToOne
  public Artifact getArtifact()
  {
    return aArtifact;
  }
  
  public void setVersion(String pVersion)
  {
    aVersion = pVersion;
  }
  
  public void setArtifact(Artifact pArtifact)
  {
    aArtifact = pArtifact;
  } 

  public void setProject(Project pProject)
  {
    aProject = pProject;
  }  

  public String toString()
  {
    StringBuffer b = new StringBuffer();
    b.append("[Build");
    b.append(" id=").append(aId);
    b.append(" artifact=").append(aArtifact);
    b.append(" project=").append(aProject.getName());
    b.append(" contact=").append(aContact);
    b.append(" comments=").append(aComments);
    b.append(" version=").append(aVersion);
    b.append(" status=").append(aStatus);
    b.append(" scheduledDate=").append(aScheduledDate);
    b.append(" performedDate=").append(aPerformedDate);
    b.append("]");
    return b.toString();
  }

}
