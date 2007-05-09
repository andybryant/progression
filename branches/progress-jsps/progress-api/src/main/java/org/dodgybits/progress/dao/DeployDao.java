package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Deploy;

public interface DeployDao extends GenericDao<Deploy, Long>
{
  /**
   * Find the latest deployment for each artifact on the given environment.
   */
  public List<Deploy> findLatestDeploysByEnvironment(Long pEnvironmentId);

  /**
   * Find all scheduled deploys that have not been performed.
   */
  public List<Deploy> findPendingDeploys();

}
