package dao.map;

import beans.Comment;

import java.util.TreeMap;

public class CommentMapDAO extends AbstractMapDAO<Comment> {
    private static final TreeMap<Long, Comment> map = new TreeMap<>();
    @Override
    protected TreeMap<Long, Comment> getMap() {
        return map;
    }
}
