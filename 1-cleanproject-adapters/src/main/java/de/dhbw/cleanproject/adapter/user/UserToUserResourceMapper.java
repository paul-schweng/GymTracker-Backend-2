package de.dhbw.cleanproject.adapter.user;

import de.dhbw.cleanproject.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserResourceMapper implements Function<User, UserResource> {

    @Override
    public UserResource apply(final User user) {
        return map(user);
    }

    private UserResource map(final User user) {
        return new UserResource(user.getId(), user.getUsername(), user.getName());
    }

}
