package com.cydeo;

import com.cydeo.model.Comment;
import com.cydeo.service.CommentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CydeoApplication {

    public static void main(String[] args) {

        Comment comment = new Comment();
        comment.setAuthor("Javid");
        comment.setText("Spring Framework is amazing");

        ApplicationContext context = SpringApplication.run(CydeoApplication.class, args);

        CommentService commentService = context.getBean(CommentService.class);
        commentService.publishComment(comment);
    }

}




