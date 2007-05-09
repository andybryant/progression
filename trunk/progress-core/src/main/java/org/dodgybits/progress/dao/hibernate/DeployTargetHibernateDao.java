package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.DeployTargetDao;
import org.dodgybits.progress.model.DeployTarget;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DeployTargetHibernateDao extends GenericHibernateDao<DeployTarget,Long> implements DeployTargetDao
{

  @SuppressWarnings("unchecked")
  public List<DeployTarget> findByEnvironment(Long pEnvironmentId)
  {
    return getHibernateTemplate().find(
        "from DeployTarget a "
        + "where a.deploy.environment.id = ?", pEnvironmentId);
  }

  @SuppressWarnings("unchecked")
  public List<DeployTarget> findCurrentDeploysByEnvironment(Long pEnvironmentId)
  {
    return getHibernateTemplate().find(
        "from DeployTarget a1 " +
        "where a1.deploy.environment.id = ? " +
        "and a1.deploy.performedDate = " +
        "(select max(a2.deploy.performedDate) from DeployTarget a2 " +
        "where a1.target.id = a2.target.id " +
        "and a1.artifact.id = a2.artifact.id)", pEnvironmentId); 
  }
}
