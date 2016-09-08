package com.diegoalves.bugweb.sessionbeans;

import com.diegoalves.bugweb.model.Bug;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego Alves
 */
public class BugRepository {
    
    @PersistenceContext
    private EntityManager manager;
    
    public void add(Bug bug) {
        manager.persist(bug);
    }
    
    public void edit(Bug bug) {
        manager.merge(bug);
    }
    
    public void removeById(Long id) {
        Bug bug = manager.find(Bug.class, id);
        manager.remove(bug);
    }
    
    public List<Bug> findAll() {
        TypedQuery<Bug> query = manager.createQuery("select b from Bug b", Bug.class);
        return query.getResultList();
    }
    
    public Bug findById(Long id) {
        return manager.find(Bug.class, id);
    }

}
