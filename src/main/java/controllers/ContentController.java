package controllers;

import beans.*;
import services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(name = "content", value = "/content")
public class ContentController extends HttpServlet {
    private static DocumentService ds;
    private static UserService us;
    private static AttachmentService ats;
    private static AnnotationService ans;
    private static CommentService cs;

    @Override
    public void init() throws ServletException {
        ds = new DocumentService();
        us = new UserService();
        ats = new AttachmentService();
        ans = new AnnotationService();
        cs = new CommentService();

        Document document = new Document("First document", "Document text : coffee is very important thing in our lives");
        User user = new User("Artemiy Vladimirovich","Artemiy@gmail.com");
        document.addAnnotation(new Annotation(document,user,"First annotation title",5,12,"First annotation text"));
        user = new User("Kirill Ahmetovich","Kirya@gmail.com");
        document.addComment(new Comment(document,user,"Second comment title","Second comment content"));
        user = new User("3 Vasilievich","Petia@gmail.com");
        document.addComment(new Comment(document,user,"First comment title","First comment content"));
        user = new User("4 Vasilievich","Petia@gmail.com");
        document.addComment(new Comment(document,user,"First comment title","First comment content"));
        user = new User("5 Vasilievich","Petia@gmail.com");
        document.addComment(new Comment(document,user,"First comment title","First comment content"));
        ds.addUsersIfAbsent(document);
        ds.add(document);


        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.print("Print in URL " +
                "?operation=\" and append \"create\",\"update\" or \"delete\".\n" +
                "Then print \"&contentType=\" and append \"comment\",\"annotation\" or \"attachment\".\n" +
                "Then print \"&userId=\" and the id of user this content belongs to.\n" +
                "Then print \"&title=\", \"&text=\", \"&filesize\", \"&content\", \"&startPos=\", \"&endPos=\" according to chosen content type and " +
                "\"contentId\" for updating or deleting.\n ");
        Properties properties = new Properties();
        try {
            ServletContext sc = req.getServletContext();
            properties.load(new FileInputStream(sc.getRealPath("/WEB-INF/permission.properties")));
        } catch (IOException e) {
            pw.print("Properties file was not found!");
        }

        try {
            String contentType = req.getParameter("contentType");
            String operation = req.getParameter("operation");
            String sId = req.getParameter("userId");
            if (properties.getProperty(sId).contains(contentType)) {
                Document document = ds.getById((long) 1);
                long id = Long.parseLong(sId);
                User user = new UserService().getById(id);
                String title = req.getParameter("title");
                long contentId;
                switch (contentType) {
                    case "comment":
                        Comment c;
                        String textC = req.getParameter("text");
                        c = new Comment(document, user, textC, title);
                        switch (operation) {
                            case "create":
                                cs.add(c);
                                break;
                            case "update":
                                contentId = Long.parseLong(req.getParameter("contentId"));
                                c.setId(contentId);
                                cs.update(c);
                                break;
                            case "delete":
                                contentId = Long.parseLong(req.getParameter("contentId"));
                                c.setId(contentId);
                                cs.delete(c);
                                break;
                        }
                        break;
                    case "annotation":
                        Annotation a;
                        String textA = req.getParameter("text");
                        long startPos = Long.parseLong(req.getParameter("startPos"));
                        long endPos = Long.parseLong(req.getParameter("endPos"));
                        a = new Annotation(document, user, title, startPos, endPos, textA);
                        switch (operation) {
                            case "create":
                                ans.add(a);
                                break;
                            case "update":
                                contentId = Long.parseLong(req.getParameter("contentId"));
                                a.setId(contentId);
                                ans.update(a);
                                break;
                            case "delete":
                                contentId = Long.parseLong(req.getParameter("contentId"));
                                a.setId(contentId);
                                ans.delete(a);
                                break;
                        }
                        break;
                    case "attachment":
                        Attachment at;
                        String content = req.getParameter("content");
                        long fileSize = Long.parseLong(req.getParameter("filesize"));
                        at = new Attachment(document, user, title, content, fileSize);
                        switch (operation) {
                            case "create":
                                ats.add(at);
                                break;
                            case "update":
                                contentId = Long.parseLong(req.getParameter("contentId"));
                                at.setId(contentId);
                                ats.update(at);
                                break;
                            case "delete":
                                contentId = Long.parseLong(req.getParameter("contentId"));
                                at.setId(contentId);
                                ats.delete(at);
                                break;
                        }
                        break;


                }


            } else throw new RuntimeException("Access denied !");
            resp.sendRedirect("/Exadel_Web_exploded/answer");
        } catch (NullPointerException | NumberFormatException e){
            pw.print("Check entered parameters!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}