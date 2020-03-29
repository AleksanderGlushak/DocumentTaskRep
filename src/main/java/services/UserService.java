package services;

import beans.User;
import dao.database.CommonDatabaseDAO;
import dao.map.UserMapDAO;

import java.util.TreeMap;

public class UserService extends AbstractService<User> {
    public UserService() {
        this.dao = new CommonDatabaseDAO<>(User.class);
    }

}
