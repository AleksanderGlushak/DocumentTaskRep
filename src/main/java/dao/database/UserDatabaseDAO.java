package dao.database;

import beans.User;

public class UserDatabaseDAO extends AbstractDatabaseDAO<User>{
    public UserDatabaseDAO(){
        super();
        super.type = User.class;
    }
}
