package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.BuildDao;
import org.dodgybits.progress.model.Build;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BuildHibernateDao extends GenericHibernateDao<Build, Long> implements
    BuildDao
{
  
  @SuppressWarnings("unchecked")
  public List<Build> findPendingBuilds()
  {
    return getHibernateTemplate().find("from Build b "
        + "where b.scheduledDate > current_timestamp() "
        + "and b.performedDate is null");
  }

}
