package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Artifact;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class ArtifactDaoTest extends AbstractTransactionalDataSourceSpringContextTests
{
  private ArtifactDao aArtifactDao;
  
  public String[] getConfigLocations()
  {
    return new String[] {"classpath:progressContext.xml"};
  }

  public void setArtifactDao(ArtifactDao pArtifactDao)
  {
    aArtifactDao = pArtifactDao;
  }
  
  public void testFindById() throws Exception
  {
    Artifact lArtifact = aArtifactDao.findById(new Long(1), false);
    System.out.println(lArtifact);
    assertEquals(new Long(1), lArtifact.getId());
  }
  
  public void testFindAll() throws Exception
  {
    List<Artifact> lArtifacts = aArtifactDao.findAll();
    System.out.println(lArtifacts);
    assertTrue(lArtifacts.size() > 1);
  }

}
