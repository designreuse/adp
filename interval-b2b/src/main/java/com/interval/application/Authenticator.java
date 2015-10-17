package com.interval.application;

import com.interval.rest.models.RESTUser;
import com.interval.service.Service;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;

public final class Authenticator {


    private static Service profileService;
    private static Authenticator authenticator = null;

    private Authenticator(){};

    public static Authenticator getInstance() {
        if ( authenticator == null ) {
            authenticator = new Authenticator(profileService);
        }
        return authenticator;
    }

    @Inject
    public Authenticator(Service profileService) {
        this.profileService = profileService;
    }

    public RESTUser login(String email, String password) throws LoginException {

        final StringBuilder query = new StringBuilder("from User u where ");
        query.append("u.email=").append("'").append(email).append("'").append(" ").append("and")
                .append(" ").append("u.password=").append("'").append(password).append("'");

        final List<RESTUser> userList = profileService.search(query.toString());

        if(!userList.isEmpty()) {
            RESTUser user = userList.get(0);

            if (user.getEmail().equals(email)) {
                String passwordMatch = user.getPassword();
                if (passwordMatch.equals(password)) {
                    String authToken = UUID.randomUUID().toString();
                    user.setToken(authToken);
                    profileService.update(user);
                    return user;
                }
            }
        }
        throw new LoginException( "Don't Come Here Again!" );
    }

    public void logout(String authToken) throws GeneralSecurityException {

        final StringBuilder query = new StringBuilder("from User u where ");
        query.append("u.token=").append(authToken);
        final List<RESTUser> userList = profileService.search(query.toString());
        if(!userList.isEmpty()) {
            RESTUser user = userList.get(0);
            if (user.getToken().equals(authToken)) {
                user.setToken(null);
                profileService.update(user);
            }
        }
        throw new GeneralSecurityException( "Invalid service key and authorization token match." );
    }

    public boolean isAuthTokenValid(String username, String authToken) {
        final StringBuilder query = new StringBuilder("from User u where ");
        query.append("u.email=").append("'").append(username).append("'");
        final List<RESTUser> userList = profileService.search(query.toString());
        if(!userList.isEmpty()) {
            RESTUser user = userList.get(0);
            if (user.getToken() != null && user.getToken().equals(authToken)) {
                return true;
            }
        }
    return false;
    }
}
