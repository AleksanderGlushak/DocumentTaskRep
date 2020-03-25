package services;

import beans.Document;
import beans.User;
import dao.database.CommonDatabaseDAO;

public class UserService extends AbstractService<User> {
    public UserService() {
        this.dao = new CommonDatabaseDAO<>(User.class);
    }

}
