package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.EnvironmentDao;
import org.dodgybits.progress.model.Environment;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business DAO operations related to the <tt>Environment</tt> entity.
 *
 * @see Environment
 */
@Transactional
public class EnvironmentHibernateDao extends GenericHibernateDao<Environment, Long>
   implements EnvironmentDao
{
  
  @SuppressWarnings("unchecked")
  @Override
  public List<Environment> findAll()
  {
    return getHibernateTemplate().find("from Environment e "
        + "order by e.displayOrder");
  }
}

