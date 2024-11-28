package com.metacoding.authblog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

//    public Optional<User> findByUsername(String username) {
//        Query q = em.createQuery("SELECT u FROM User u WHERE u.username = :username",User.class);
//        q.setParameter("username", username);
//        try {
//            User userPS = (User) q.getSingleResult(); // no result면
//            return Optional.ofNullable(userPS);
//        } catch(RuntimeException e) {
////            return Optional.ofNullable(null);
//            throw new Exception401();
//        }
        public User findByUsername(String username) {
        Query q = em.createQuery("SELECT u FROM User u WHERE u.username = :username",User.class);
        q.setParameter("username", username);
        try {
            return (User) q.getSingleResult(); // no result면
        } catch(RuntimeException e) {
            throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
        }

    }
}
