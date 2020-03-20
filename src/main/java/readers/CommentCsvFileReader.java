package readers;

import beans.Comment;

import java.io.FileNotFoundException;

public class CommentCsvFileReader extends AbstractContentScvFileReader {
    private static final String PATTERN = "";

    public CommentCsvFileReader(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter, PATTERN);
    }

    public CommentCsvFileReader(String filename) throws FileNotFoundException {
        super(filename, PATTERN);
    }

    @Override
    public Comment readOne(){
        Comment comment = new Comment();
        comment.setId(super.readLong());
        comment.setUser(super.readUser());
        comment.setTitle(super.readText());
        comment.setText(super.readText());
        IN.nextLine();

        return comment;
    }
}
