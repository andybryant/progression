package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.TargetDao;
import org.dodgybits.progress.model.Target;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TargetHibernateDao extends GenericHibernateDao<Target, Long> implements TargetDao
{
  @SuppressWarnings("unchecked")
  @Override
  public List<Target> findAll()
  {
    return getHibernateTemplate().find("from Target t "
        + "order by t.name");
  }
}
