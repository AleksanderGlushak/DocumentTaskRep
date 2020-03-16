import beans.*;
import dao.database.UserDatabaseDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

/*


    public void readToDocument(Document document) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        in.useDelimiter(Delimiters.SEPARATOR.toString());
        String type;
        while(in.hasNext()){
            type = in.next();
            try{
                switch (BeansToRead.valueOf(type.toUpperCase())) {
                    case COMMENT:
                        document.addComment(this.commentReader.readOne());
                        break;
                    case ANNOTATION:
                        document.addAnnotation(this.annotationReader.readOne());
                        break;
                    case ATTACHMENT:
                        document.addAttachment(this.attachmentReader.readOne());
                        break;
                    default:
                        System.out.println(UNKNOWN_TYPE_MESSAGE);
                        in.nextLine();
                }
            } catch (IllegalArgumentException e){
                System.out.println(UNKNOWN_TYPE_MESSAGE);
                in.nextLine();
            }
        }
    }
 */

public class Runner {
    private static final String FILE_TO_WRITE = "text.txt";
    private static final String UNKNOWN_TYPE_MESSAGE = "A line of unknown type is found!";

    private static Logger log = Logger.getLogger(String.valueOf(Runner.class));//logback

    public static void main(String[] args) throws IOException, SQLException {
        new UserDatabaseDAO().addUser(new User(45557576,"Eugen Dladislavovich","Eugen@gmail.com"));
//        User comUser = new User(343676,"Artemiy Vladimirovich","Artemiy@gmail.com");
//        print(comUser);
//        User attUser = new User(45557576,"Maxim Dladislavovich","Maxim@gmail.com");
//        print(attUser);
//        User annUser = new User(34235455,"Pavel Ananievich","Pavel@gmail.com");
//        print(annUser);
//
//        print(new Comment(1233312,comUser,"The best title ever, ist't it??","Here is some text"));
//        print(new Attachment(65423,attUser,"Not that good title","Here is a bit worse text",46354636));
//        print(new Annotation(564235, annUser, "Not that good title", 6436364,46354636,"Here is a bit worse text"));
//
//        final List<Comment> comments = new ArrayList<>();
//        comments.add(new Comment(1233312,comUser,"The best title ever, isn\'t it??","Here is some text"));
//        comments.add(new Comment(646312,comUser,"What title is?","Here is some text"));
//        comments.add(new Comment(452343312,comUser,"Who cares about titles","Here is some text"));
//        comments.add(new Comment(87632112,attUser,"No one does","Here is some text"));
//        comments.add(new Comment(9145312,attUser,"But I have to create some","Here is some text"));
//        comments.add(new Comment(6452121,attUser,"Don\'t I","Here is some text"));
//        comments.add(new Comment(223176,attUser,"So close to the end of comments","Here is some text"));
//        comments.add(new Comment(7212,annUser,"And here\'s the end","Here is some text"));
//
//        Collections.sort(comments, new Comparator<Comment>() {
//            @Override
//            public int compare(Comment comment, Comment t1) {
//                return comment.getTitle().compareTo(t1.getTitle());
//            }
//        });
//
//        for (Comment c :
//                comments) {
//            print(c);
//        }
//
//        List<Content> comUserContents = new ArrayList<>();
//
//        comUserContents.add(new Comment(1233312,comUser,"The best title ever, isn\'t it??","Here is some text"));
//        comUserContents.add(new Comment(646312,comUser,"What title is?","Here is some text"));
//        comUserContents.add(new Comment(452343312,comUser,"Who cares about titles","Here is some text"));
//
//        List<Content> attUserContents = new ArrayList<>();
//
//        attUserContents.add(new Comment(87632112,attUser,"No one does","Here is some text"));
//        attUserContents.add(new Comment(9145312,attUser,"But I have to create some","Here is some text"));
//        attUserContents.add(new Comment(6452121,attUser,"Don\'t I","Here is some text"));
//        attUserContents.add(new Comment(223176,attUser,"So close to the end of comments","Here is some text"));
//
//        List<Content> annUserContents = new ArrayList<>();
//
//        annUserContents.add(new Comment(7212,annUser,"And here\'s the end","Here is some text"));
//
//
//        Map<User, List<Content>> userContents = new HashMap<>();
//        userContents.put(comUser, comUserContents);
//        userContents.put(attUser, attUserContents);
//        userContents.put(annUser, annUserContents);
//
//        for (Map.Entry<User, List<Content> > entry:
//                    userContents.entrySet()){
//            print(entry.getKey() + " --- has ---" +entry.getValue().size() + " contents");
//        }
//
//        Set<Attachment> attachments = new TreeSet<>();
//        attachments.add(new Attachment(642452,attUser,"first attachment  title","first attachment content",9383));
//        attachments.add(new Attachment(642453,attUser,"second attachment  title","second attachment content",44523));
//        attachments.add(new Attachment(642454,attUser,"third attachment  title","third attachment content",94213));
//        attachments.add(new Attachment(642455,attUser,"fourth attachment  title","fourth attachment content",1123));
//        attachments.add(new Attachment(642456,attUser,"fifth attachment  title","fifth attachment content",6433));
//        attachments.add(new Attachment(642457,attUser,"sixth attachment  title","sixth attachment content",2383));
//
//        for (Attachment a :
//                attachments) {
//            print(a);
//        }
//
//        Document document = new Document(12345,"First document", "Document text : coffee is very important thing in our lives");//constructor
//        document.addAll(comments);
//        document.addAll(comUserContents);
//        document.addAll(attUserContents);
//        document.addAll(annUserContents);
//        document.addAll(userContents);
//        document.addAll(attachments);
//
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(new File("text.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();//print("File not found!", true);
//        }
//        List<Showable> showables = new ArrayList<>();
//        showables.addAll(document.getComments());
//        document.addAnnotation(new Annotation(564235, annUser, "Not that good title", 6436364,46354636,"Here is a bit worse text"));
//        document.addAnnotation(new Annotation(344235, annUser, "Not that good title", 6436364,46354636,"Worse text"));
//        document.addAnnotation(new Annotation(5631235, annUser, "Not that good title", 6436364,46354636,"This is a bit worse text"));
//        document.addAnnotation(new Annotation(67625, annUser, "Not that good title", 6436364,46354636,"This is her worst text"));
//
//        ContentCsvFileWriter csvFileWriter = new ContentCsvFileWriter("data.csv");
//        csvFileWriter.writeComments(document.getComments());
//        csvFileWriter.close();
//
//        showables.addAll(document.getAnnotations());
//        for (Showable s :
//                showables) {
//            try {
//                fw.append(s.show() + "\n");
//            } catch (IOException e) {
//                print("Error during writing happened!", true);
//            }
//        }
//        fw.close();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            mapper.writeValue(new File("document.json"),document);
//        } catch (IOException e) {
//            print("Error during JSON writing happened!",true);
//        }
//
//        Document readDoc = null;
//        try {
//            readDoc = mapper.readValue(new File("document.json"),Document.class);
//            print(readDoc);
//        } catch (IOException e) {
//            print("Error during deserialization happened!",true);
//        }

    }

    public static void print(Object text) {
        print(text.toString(),false);
    }

    public static void print(String text, boolean isError) {
        if(!isError)
            log.warning(text);//logging, git
        else
            log.info(text);
    }
}
