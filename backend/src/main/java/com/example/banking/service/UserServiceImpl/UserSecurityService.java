package com.example.banking.service.UserServiceImpl;

import com.example.banking.model.User;
import com.example.banking.repository.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (null == user) {
            log.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return user;
    }
}
