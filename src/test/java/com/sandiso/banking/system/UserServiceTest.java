package com.sandiso.banking.system;

import com.sandiso.banking.system.dao.UserRepository;
import com.sandiso.banking.system.model.User;
import com.sandiso.banking.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userServiceTest;
    private User user;

    @Test
    public void saveUserTest() {
        /*user = new User("Sandiso", "Mpithi", "sandisompithi@outlook.com", "0791735356", null, null, "luna12323");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userServiceTest.save(user));*/
    }
}
