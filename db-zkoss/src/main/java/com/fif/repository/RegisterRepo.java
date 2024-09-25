package com.fif.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fif.entity.Registration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegisterRepo {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Registration> queryAll() {
        Query query = em.createQuery("SELECT l FROM Registration l");
        List<Registration> result = query.getResultList();
        System.out.println("result");
        System.out.println(result);
        return result;
    }

    @Transactional(readOnly = true)
    public Registration get(Integer id) {
        return em.find(Registration.class, id);
    }
    @Transactional(noRollbackFor = {})
    public Registration save(Registration registration) {
        System.out.println(registration.getId());
        System.out.println(registration.getName());
        System.out.println(registration.getGender());
        System.out.println(registration.getBirthdate());
        System.out.println(registration.getCountry());
        try {
            em.persist(registration);
            em.flush();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            return registration;
        }


    }

    @Transactional
    public void delete(Registration registration) {
        Registration r = get(registration.getId());
        if(r != null) {
            em.remove(r);
        }
    }

}
