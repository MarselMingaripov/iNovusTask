package iNovus.service;

import iNovus.dao.UserDao;
import iNovus.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        userDao.save(user);

    }

    @Override
    public User findByUsername(String username) {

        return userDao.findByUsername(username);
    }
}
