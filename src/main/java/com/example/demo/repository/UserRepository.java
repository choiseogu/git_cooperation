package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public List<UserEntity> findAll() {
        return em.createQuery("select i from UserEntity i", UserEntity.class).getResultList();
    }

    public UserEntity findOne(String id) {
        return em.createQuery("select u from UserEntity u where u.id =:id", UserEntity.class)
                .setParameter("id", id).getSingleResult();
    }

    public UserEntity save(UserEntity user) {
        if(user.getId()==null) {
            em.persist(user);
            return user;
        }
        else{
            em.merge(user);
            return user;

        }
    }

    public void delete(String id) {
        em.remove(findOne((id)));
    }
}
