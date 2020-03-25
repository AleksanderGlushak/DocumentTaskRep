package dao.map;

import beans.Comment;

import java.util.TreeMap;

public class CommentMapDAO extends AbstractMapDAO<Comment> {
    public CommentMapDAO(TreeMap<Long, Comment> map) {
        super(map);
    }
}
