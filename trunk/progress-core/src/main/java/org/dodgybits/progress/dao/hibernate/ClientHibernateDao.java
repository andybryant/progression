package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.ClientDao;
import org.dodgybits.progress.model.Client;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ClientHibernateDao extends GenericHibernateDao<Client, Long> implements
    ClientDao
{
  @SuppressWarnings("unchecked")
  @Override
  public List<Client> findAll()
  {
    return getHibernateTemplate().find("from Client c "
        + "order by c.name");
  }
}
