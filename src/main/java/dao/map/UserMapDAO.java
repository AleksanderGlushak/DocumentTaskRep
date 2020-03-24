package dao.map;

import beans.User;

import java.util.TreeMap;

public class UserMapDAO extends AbstractMapDAO<User>{
    private static final TreeMap<Long, User> map = new TreeMap<>();
    @Override
    protected TreeMap<Long, User> getMap() {
        return map;
    }
}
