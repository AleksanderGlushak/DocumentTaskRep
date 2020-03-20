package dao.database;

import beans.Comment;

public class CommentDatabaseDAO extends AbstractDatabaseDAO<Comment> {
    public CommentDatabaseDAO(){
        super();
        super.type = Comment.class;
    }
}
