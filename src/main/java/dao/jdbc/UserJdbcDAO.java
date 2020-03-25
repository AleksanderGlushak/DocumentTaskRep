package dao.jdbc;

import beans.User;
import dao.CommonDao;
import dao.database.CommonDatabaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConnectionPool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class UserJdbcDAO implements CommonDao<User> {

    private ConnectionPool connectionPool;
    private static Logger log = LoggerFactory.getLogger(CommonDatabaseDAO.class);

    private static final String UPDATE_USER = "UPDATE users SET email = ?, name = ? WHERE id = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String ADD_USER = "INSERT INTO users (email, name) VALUES (?,?)";
    private static final String GET_ID = "SELECT id FROM users WHERE email = ? AND name = ?";
    private static final String GET_BY_ID = "SELECT email, name FROM users WHERE id = ?";

    public UserJdbcDAO(){
        connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public User add(User user){
        Connection c = null;
        try {
            c = connectionPool.getConnection();
            PreparedStatement st = null;
            try {
                st = c.prepareStatement(ADD_USER);
                st.setString(1,user.getEmail());
                st.setString(2,user.getName());
                st.execute();
                st = c.prepareStatement(GET_ID);
                st.setString(1,user.getEmail());
                st.setString(2,user.getName());
                ResultSet rs = st.executeQuery();
                long id;
                if(rs.next()){
                    id = rs.getLong(1);
                    user.setId(id);
                }
            } catch (SQLException e) {
                log.error("Error during user adding");
            } finally {
                connectionPool.putBack(c);
            }
        } catch (TimeoutException e) {
            log.error("Could not get free connection from pool while adding!");
        }
        return user;
    }

    @Override
    public User update(User user) {
        try {
            Connection c = connectionPool.getConnection();
            PreparedStatement st = null;
            try {
                st = c.prepareStatement(UPDATE_USER);
                st.setString(1,user.getEmail());
                st.setString(2,user.getName());
                st.setLong(3,user.getId());
                st.execute();
            } catch (SQLException e) {
                log.info("Error during user updating");
            } finally {
                connectionPool.putBack(c);
            }
        } catch (TimeoutException e) {
            log.error("Could not get free connection from pool while updating!");
        }
        return user;
    }

    @Override
    public User getById(Long id) {
        User user = null;
        try {
            Connection c = connectionPool.getConnection();
            PreparedStatement st = null;
            user = new User();
            try {
                st = c.prepareStatement(GET_BY_ID);
                st.setLong(1,id);
                ResultSet rs = st.executeQuery();
                if(rs.next()) {
                    user.setId(id);
                    user.setEmail(rs.getString(1));
                    user.setName(rs.getString(2));
                }
            } catch (SQLException e) {
                log.info("Error during getting user by id");
            } finally {
                connectionPool.putBack(c);
            }
        } catch (TimeoutException e) {
            log.error("Could not get free connection from pool while getting by id!");
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = null;
        try {
            Connection c = connectionPool.getConnection();
            PreparedStatement st = null;
            users = new LinkedList<>();
            try {
                st = c.prepareStatement(GET_ALL_USERS);
                ResultSet rs = st.executeQuery();
                User user;
                long id;
                String email;
                String name;
                while(rs.next()){
                    id = rs.getLong(1);
                    email = rs.getString(2);
                    name = rs.getString(3);
                    user = new User(email,name);
                    user.setId(id);
                    users.add(user);
                }
            } catch (SQLException e) {
                log.info("Error during getting all users");
            } finally {
                connectionPool.putBack(c);
            }
        } catch (TimeoutException e) {
            log.error("Could not get free connection from pool while getting all!");
        }
        return users;
    }

    @Override
    public void delete(User user) {
        try {
            Connection c = connectionPool.getConnection();
            PreparedStatement st = null;
            try {
                st = c.prepareStatement(DELETE_USER);
                st.setLong(1,user.getId());
                st.execute();
            } catch (SQLException e) {
                log.error("Error during user delete");
            } finally {
                connectionPool.putBack(c);
            }
        } catch (TimeoutException e) {
            log.error("Could not get free connection from pool while deleting!");
        }

    }

}