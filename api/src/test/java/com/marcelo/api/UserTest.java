package com.marcelo.api;

import com.marcelo.api.domain.User;
import com.marcelo.api.exceptions.AppException;
import com.marcelo.api.repository.UserRepository;
import com.marcelo.api.services.UserService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void saveUserWithEmailAlreadyExistsFailure() {
        var user = new User();
        var email = "teste@email.com";
        var password = "pwteste";
        user.setPassword(password);
        user.setEmail(email);
        Mockito.when(repository.findByEmail(email)).thenReturn(user);
        var newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        expectedException.expect(AppException.class);
        expectedException.expectMessage("Email already exists");
        userService.save(newUser);
    }

    @Test
    public void saveUserWithLoginAlreadyExistsFailure() {
        var user = new User();
        var login = "login";
        var password = "pwteste";
        user.setPassword(password);
        user.setLogin(login);
        Mockito.when(repository.userFindByLogin(login)).thenReturn(user);
        var newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        expectedException.expect(AppException.class);
        expectedException.expectMessage("Login already exists");
        userService.save(newUser);
    }
}
