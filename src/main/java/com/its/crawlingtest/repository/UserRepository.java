package com.its.crawlingtest.repository;

import com.its.crawlingtest.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;




public interface UserRepository  extends JpaRepository<User, Long> {

 Optional<User> findByEmail(String email);


}
