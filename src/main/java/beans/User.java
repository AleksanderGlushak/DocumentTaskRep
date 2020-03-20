package beans;

import readers.Delimiters;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Identity{
    @Column
    private String name;
    @Column
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Annotation> annotations = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<Attachment> attachments = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new LinkedList<>();

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    private String frameText(String text){
        return Delimiters.COMPLEX_TEXT_FRAME + text + Delimiters.COMPLEX_TEXT_FRAME;
    }

    @Override
    public String toString() {
        return id + Delimiters.SEPARATOR.toString()
                + frameText(name) + Delimiters.SEPARATOR.toString()
                + email;
    }
}
