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
        return result;
    }

    @Transactional(readOnly = true)
    public Registration get(Integer id) {
        return em.find(Registration.class, id);
    }
    @Transactional(noRollbackFor = {})
    public Registration save(Registration registration) {
        System.out.println(registration.getId());
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

    @Transactional
    public Registration editRegis(Registration registration) {
        System.out.println(registration.getId());
        Registration selectedUser = get(registration.getId());
        if(selectedUser != null) {
            selectedUser.setName(registration.getName());
            selectedUser.setGender(registration.getGender());
            selectedUser.setBirthdate(registration.getBirthdate());
            selectedUser.setCountry(registration.getCountry());
            em.merge(selectedUser);
        }

        return selectedUser;
    }

}
