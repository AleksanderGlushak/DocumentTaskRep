package readers;

import beans.Comment;

import java.io.FileNotFoundException;

public class CommentReader extends AbstractContentFileReader {
    private static final String PATTERN = "";

    public CommentReader(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter, PATTERN);
    }

    public CommentReader(String filename) throws FileNotFoundException {
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

    @Override
    public boolean hasNext() {
        return IN.hasNext("");
    }
}
