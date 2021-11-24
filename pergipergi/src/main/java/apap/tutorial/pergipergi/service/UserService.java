package apap.tutorial.pergipergi.service;

import java.util.List;

import apap.tutorial.pergipergi.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> seeAllUsers();
    void removeUser(String uname);
    boolean checkPassword(String uname, String pw);
    UserModel updatePassword(String uname, String pass);
}
