package com.diegoalves.bugweb.sessionbeans;

import com.diegoalves.bugweb.model.Bug;
import com.diegoalves.bugweb.model.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego Alves
 */
@Stateless
public class ProjectRepository {
    
    @PersistenceContext
    private EntityManager manager;
    
    public void addProject(Project project) {
        manager.persist(project);
    }
    
    public void edit(Project project) {
        manager.merge(project);
    }
    
    public void removeById(Long id) {
        Project project = manager.find(Project.class, id);
        
        TypedQuery<Bug> query = manager.createQuery("select b from Bug b where b.project = :project", Bug.class);
        query.setParameter("project", project);
        
        List<Bug> bugs = query.getResultList();
        for(Bug b : bugs) {
            manager.remove(b);
        }
        manager.remove(project);
    }
    
    public List<Project> findAll() {
        TypedQuery<Project> query = manager.createQuery("select p from Project p", Project.class);
        return query.getResultList();
    }
    
    public Project findById(Long id) {
        return manager.find(Project.class, id);
    }
    
}
