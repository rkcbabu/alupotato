package web.app.alupotato.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ram Chaulagai <ram.chaulagai.np@gmail.com>
 * @param <Entity>
 * @param <ID>
 */
@Transactional
@EnableTransactionManagement
public abstract class GenericDaoImpl<Entity, ID extends Serializable> implements GenericDAO<Entity, ID> {

    @Autowired
    protected SessionFactory sessionFactory;

    private String entityName;
    Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

    private Class<Entity> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        persistentClass = (Class<Entity>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        entityName = persistentClass.getName();
    }

    public Class<Entity> getPersistentClass() {
        return persistentClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Entity get(ID id) {
        Entity t = (Entity) sessionFactory.getCurrentSession().get(getPersistentClass(),
                id);
        return t;
    }

    @Override
    public List<Entity> getAll() {
        return findByCriteria();
    }

    @SuppressWarnings("unchecked")
    public Entity findById(ID id, boolean lock) {
        Entity entity;
        if (lock) {
            entity = (Entity) sessionFactory.getCurrentSession().load(
                    getPersistentClass(), id, LockOptions.UPGRADE);
        } else {
            entity = (Entity) sessionFactory.getCurrentSession().load(
                    getPersistentClass(), id);
        }

        return entity;
    }

    @Override
    public void create(Entity entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        if (logger.isDebugEnabled()) {
            logger.debug(entityName + " saved successfully," + entityName
                    + " Details=" + entity);
        }
        sessionFactory.getCurrentSession().flush();

    }

    @Override
    public void update(Entity entity) {
        sessionFactory.getCurrentSession().update(entity);
        sessionFactory.getCurrentSession().flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Entity merge(Entity entity) {
        Entity t = (Entity) sessionFactory.getCurrentSession().merge(entity);
        return t;

    }

    @Override
    public void delete(Entity entity) {
        sessionFactory.getCurrentSession().delete(entity);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteById(ID id) {
        Entity entity = (Entity) sessionFactory.getCurrentSession().load(
                getPersistentClass(), id);
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    protected List<Entity> findByCriteria(Criterion... criterion) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(
                getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
