package controllers;

import services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "answer", value = "/answer")
public class AnswerController extends HttpServlet {
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
    }

        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = "";
        answer += "Comments : " + cs.getAll().toString();
        answer += "Attachments : " + ats.getAll().toString();
        answer += "Annotations : " + ans.getAll().toString();
        resp.getWriter().print(answer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
