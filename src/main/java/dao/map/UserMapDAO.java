package dao.map;

import beans.User;

import java.util.TreeMap;

public class UserMapDAO extends AbstractMapDAO<User>{
    public UserMapDAO(TreeMap<Long, User> map) {
        super(map);
    }
}
