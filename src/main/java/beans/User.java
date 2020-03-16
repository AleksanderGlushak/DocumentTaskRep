package beans;

import readers.Delimiters;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String email;

    public User() {
    }

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
