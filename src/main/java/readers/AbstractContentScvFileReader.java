package readers;

import beans.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AbstractContentScvFileReader {
    private final String delimiter;
    private final String FILENAME;//not caps
    private final String PATTERN;
    protected final Scanner IN;

    public abstract Object readOne();

    public boolean hasNext(){
        return IN.hasNext();
    } // abstract getPattern !!!

    protected long readLong(){
        return IN.nextLong();
    }

    protected String readText(){
        IN.useDelimiter(Delimiters.COMPLEX_TEXT_FRAME.toString());
        IN.next();
        String text = IN.next();
        IN.useDelimiter(delimiter);

        return text;
    }

    protected User readUser(){
        User user = new User();
        user.setId(readLong());
        user.setName(readText());
        IN.next();
        user.setEmail(IN.next());
        return user;
    }

    protected AbstractContentScvFileReader(String filename, String delimiter, String pattern) throws FileNotFoundException {
        this.delimiter = delimiter;
        this.FILENAME = filename;
        this.PATTERN = pattern;
        this.IN = new Scanner(new File(filename));
    }

    protected AbstractContentScvFileReader(String filename, String pattern) throws FileNotFoundException {
        this(filename,Delimiters.SEPARATOR.toString(),pattern);
    }
}
