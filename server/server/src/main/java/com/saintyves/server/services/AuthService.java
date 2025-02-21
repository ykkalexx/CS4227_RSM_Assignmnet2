package com.saintyves.server.services;

import com.saintyves.server.model.User;
import com.saintyves.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> handleOAuthSuccess() {
        OAuth2User oauth2User = (OAuth2User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = saveOrUpdateUser(oauth2User);

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);

        return response;
    }

    private User saveOrUpdateUser(OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElse(new User());

        user.setEmail(email);
        user.setName(oauth2User.getAttribute("name"));
        user.setProvider("google");
        user.setProviderId(oauth2User.getAttribute("sub"));

        return userRepository.save(user);
    }
}