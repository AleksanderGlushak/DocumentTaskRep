package beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;
@Entity
@Table(name = "documents")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document extends Identity{
    @Column
    private String name;
    @Column
    private String text;
    @OneToMany(mappedBy = "document")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Comment> comments;
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "document")
    private List<Annotation> annotations;
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "document")
    private List<Attachment> attachments;

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void addAnnotation(Annotation annotation){
        this.annotations.add(annotation);
    }

    public void addAttachment(Attachment attachment){
        this.attachments.add(attachment);
    }

    public void addContent(Comment comment, Annotation annotation, Attachment attachment){
        addComment(comment);
        addAnnotation(annotation);
        addAttachment(attachment);
    }

    public Document(String name, String text, List<Comment> comments, List<Annotation> annotations, List<Attachment> attachments) {
        this.name = name;
        this.text = text;
        this.comments = comments;
        this.annotations = annotations;
        this.attachments = attachments;
    }

    public Document(String name, String text) {
        this.name = name;
        this.text = text;
        this.comments = new ArrayList<Comment>();
        this.annotations = new ArrayList<Annotation>();
        this.attachments = new ArrayList<Attachment>();
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", comments=" + comments +
                ", annotations=" + annotations +
                ", attachments=" + attachments +
                '}';
    }

    public Document() {
        this.comments = new ArrayList<Comment>();
        this.annotations = new ArrayList<Annotation>();
        this.attachments = new ArrayList<Attachment>();
    }

    public void addAll(List<? extends Content> content){
        for (Content c :
                content) {
            if (c instanceof Attachment) {
                this.attachments.add((Attachment) c);
            } else if (c instanceof Annotation) {
                this.annotations.add((Annotation) c);
            } else if (c instanceof Comment) {
                this.comments.add((Comment) c);
            } else {
                throw new ClassCastException();
            }
        }
    }

    public void addAll(Map<User,List<Content>> content){
        for (Map.Entry<User, List<Content> > c:
                content.entrySet()){
            List<? extends Content> v = c.getValue();
            addAll(v);
        }
    }

    public void addAll(Set<Attachment> attachments){
        for (Attachment c:
                attachments){
            this.attachments.add(c);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getSerializeDate(){
        Date date = new Date();
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }
}
