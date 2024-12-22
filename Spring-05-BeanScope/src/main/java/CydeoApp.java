import com.cydeo.config.ProjectConfig;
import com.cydeo.model.Comment;
import com.cydeo.service.CommentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CydeoApp {

    public static void main(String[] args) {

        Comment comment = new Comment();
        comment.setAuthor("Javid");
        comment.setText("Spring Framework is amazing");

        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        CommentService commentService1 = context.getBean(CommentService.class);
        CommentService commentService2 = context.getBean(CommentService.class);

        //memory locations are different when CommentService class has @Scope(@Prototype)
        System.out.println("commentService1 = " + commentService1);
        System.out.println("commentService2 = " + commentService2);
        System.out.println(commentService1==commentService2); //true when memory locations are same, and vice versa


    }
}
