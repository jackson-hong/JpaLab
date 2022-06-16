package com.lab.community.service.user;

import com.lab.community.common.code.ResultCode;
import com.lab.community.common.exception.LabException;
import com.lab.community.domain.user.User;
import com.lab.community.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long userId = Long.parseLong(username);
        User user = userRepository.findById(userId).orElseThrow(() -> new LabException(ResultCode.RESULT_4001));
        return new org.springframework.security.core.userdetails.User(user.getAccountId(), "", Collections.singletonList(user.getAccountType()));
    }

    public User findByAccountId(String accountId){
        return userRepository.findByAccountId(accountId).orElseThrow(() -> new LabException(ResultCode.RESULT_4001));
    }
}
