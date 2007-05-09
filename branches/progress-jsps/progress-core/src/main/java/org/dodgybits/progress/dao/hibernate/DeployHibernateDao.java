package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.DeployDao;
import org.dodgybits.progress.model.Deploy;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DeployHibernateDao extends GenericHibernateDao<Deploy, Long> implements
    DeployDao
{

  @SuppressWarnings("unchecked")
  public List<Deploy> findLatestDeploysByEnvironment(Long pEnvironmentId)
  {
    return getHibernateTemplate().find("from Deploy d1 " +
        "where d1.environment.id = ? " +
        "and d1.performedDate = " +
        "(select max(performedDate) from Deploy d2 " +
        "where d2.environment.id = d1.environment.id)", pEnvironmentId); 
  }

  @SuppressWarnings("unchecked")
  public List<Deploy> findPendingDeploys()
  {
    return getHibernateTemplate().find("from Deploy d "
        + "where d.scheduledDate > current_timestamp() "
        + "and d.performedDate is null");
  }

}
