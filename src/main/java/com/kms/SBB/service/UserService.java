package com.kms.SBB.service;


import com.kms.SBB.entitiy.user.SiteUser;
import com.kms.SBB.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final  BCryptPasswordEncoder bCryptPasswordEncoder;

    public SiteUser create(String username,String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
}
