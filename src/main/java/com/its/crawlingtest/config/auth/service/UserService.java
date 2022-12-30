package com.its.crawlingtest.config.auth.service;

import com.its.crawlingtest.domain.user.User;
import com.its.crawlingtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

        public List<User> findAllDesc() {
            return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
    }

