package io.github.zemise.security;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    @Secured("ADMIN")
    void updateUser(User user);

    @Secured({"USER", "ADMIN"})
    void deleteUser();

}
