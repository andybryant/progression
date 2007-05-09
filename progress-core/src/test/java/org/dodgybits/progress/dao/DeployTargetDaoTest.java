package org.dodgybits.progress.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dodgybits.progress.model.DeployTarget;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class DeployTargetDaoTest extends AbstractTransactionalDataSourceSpringContextTests
{
  private DeployTargetDao aDeployTargetDao;
  
  public String[] getConfigLocations()
  {
    return new String[] {"classpath:progressContext.xml"};
  }

  public void setDeployTargetDao(DeployTargetDao pDeployTargetDao)
  {
    aDeployTargetDao = pDeployTargetDao;
  }
  
  public void testGetArtifactTopology() throws Exception
  {
    DeployTarget lDeployTarget = aDeployTargetDao.findById(new Long(27), false);
    System.out.println(lDeployTarget);
    assertEquals(new Long(1), lDeployTarget.getArtifact().getId());
  }

  public void testFindByEnvironment() throws Exception
  {
    List<DeployTarget> lMappings = aDeployTargetDao.findByEnvironment(new Long(1));
    assertTrue(CollectionUtils.isNotEmpty(lMappings));
    System.out.println(lMappings);
  }
  
  public void testFindCurrentDeploysByEnvironment() throws Exception
  {
    List<DeployTarget> lMappings = aDeployTargetDao.findCurrentDeploysByEnvironment(new Long(4));
    assertTrue(CollectionUtils.isNotEmpty(lMappings));
    System.out.println(lMappings);
  }

  
}
