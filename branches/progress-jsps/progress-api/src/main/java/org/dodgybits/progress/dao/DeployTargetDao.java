package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.DeployTarget;

public interface DeployTargetDao extends GenericDao<DeployTarget, Long>
{
  /**
   * Return the whole history of artifact deploys for a given environment.
   */
  public List<DeployTarget> findByEnvironment(Long pEnvironmentId);

  /**
   * Return currently deployed artifacts for a given environment.
   */
  public List<DeployTarget> findCurrentDeploysByEnvironment(Long pEnvironmentId);

}
