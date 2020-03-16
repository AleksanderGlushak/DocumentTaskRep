package dao;

import beans.Attachment;
import beans.Comment;
import beans.User;

import java.sql.SQLException;
import java.util.Collection;

public interface CommentDAO {
    public void addComment(Comment comment) throws SQLException;
    public void updateComment(Long commentId, Comment comment) throws SQLException;
    public Comment getCommentById(Long commentId) throws SQLException;
    public Collection getAllComments() throws SQLException;
    public void deleteComment(Comment comment) throws SQLException;
}
