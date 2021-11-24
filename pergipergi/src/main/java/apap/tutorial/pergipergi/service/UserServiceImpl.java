package apap.tutorial.pergipergi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tutorial.pergipergi.model.UserModel;
import apap.tutorial.pergipergi.repository.UserDb;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);

        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode(password);

        return passwordHash;
    }

    @Override
    public List<UserModel> seeAllUsers() {
        return userDb.findAll();
    }

    @Override
    public void removeUser(String uname) {
        userDb.delete(userDb.findByUsername(uname));
    }

    @Override
    public boolean checkPassword(String uname, String pw) {
        BCryptPasswordEncoder PE = new BCryptPasswordEncoder();
        UserModel usr = userDb.findByUsername(uname);
        
        return PE.matches(pw, usr.getPassword());
    }

    @Override
    public UserModel updatePassword(String uname, String pass) {
        UserModel temp = userDb.findByUsername(uname);
        String newPass = encrypt(pass);

        temp.setPassword(newPass);
        userDb.save(temp);

        return temp;
    }
    
}
