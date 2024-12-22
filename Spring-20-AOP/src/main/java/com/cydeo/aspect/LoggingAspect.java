package com.cydeo.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/*
AOP (Aspect-Oriented Programming) helps manage cross-cutting concerns like logging, security, and transactions, which affect multiple parts of an application.

In simple terms:
- AOP allows you to separate concerns that affect many parts of your application (like logging) from the core business logic.
- This means you can add behaviors (like logging) to multiple parts of your application without modifying the actual business logic.

### Key Concepts in AOP:

1. **Concern**:
   - A concern is a specific functionality or behavior within an application. Examples include:
     - **Logging**: Tracking method calls for debugging or monitoring.
     - **Transaction Management**: Ensuring database operations are executed atomically.
     - **Security**: Checking if users have proper permissions before accessing resources.
     - **Error Handling**: Managing exceptions across the application.

2. **Cross-Cutting Concerns**:
   - A cross-cutting concern is a concern that affects multiple parts of the application, such as:
     - **Logging**: Logging is needed in different layers of the application (service layer, controller layer, etc.), but it’s not part of the core logic.
     - **Security**: Security checks might be required in many places, like authentication or authorization checks.
     - **Transaction Management**: Database transactions that apply across different methods or classes.

3. **Aspect**:
   - An **aspect** is a module in AOP that encapsulates a cross-cutting concern, such as logging or security.
   - An aspect **is not** a business object or bean; it's a special class used to apply a specific behavior (like logging) across multiple parts of your application.
   - For example, a `LoggingAspect` is an aspect that contains the logic for logging method calls.

### Example with AOP:
Imagine you want to log every method call in your application. Without AOP, you would need to manually add logging in every method, which can make the code messy and redundant. With AOP, you can define a **logging aspect** that automatically applies the logging behavior to multiple methods without modifying the actual code.

### How AOP Works:
- **Aspect**: A module that handles a cross-cutting concern (like logging or security).
- **Join Point**: A specific point in the execution of the program (e.g., before or after a method is called).
- **Advice**: The action taken at a join point (e.g., logging a message).
- **Pointcut**: A specification that determines where the advice should be applied (e.g., all methods in a class).

### Aspect Annotations:

1. **@Aspect**:
   - This annotation defines a class as an **aspect**. It marks a class that contains advice methods (code to be executed at specific join points).
   - It is used to declare that the class is an aspect in AOP.

2. **@Before**:
   - This annotation defines **before advice**. It runs before the target method executes. It's commonly used for tasks like logging or validation before the method's execution.

3. **@After**:
   - This annotation defines **after advice**. It runs after the target method completes, regardless of whether the method finishes successfully or throws an exception. It is typically used for cleanup or final logging.

4. **@AfterReturning**:
   - This annotation defines **after-returning advice**. It runs after the target method completes successfully (without throwing any exceptions). It allows access to the method's return value and is commonly used to log successful executions.

5. **@AfterThrowing**:
   - This annotation defines **after-throwing advice**. It runs if the target method throws an exception. You can capture the thrown exception and log it or take other actions like notifying administrators.

6. **@Around**:
   - This annotation defines **around advice**, which surrounds the execution of the target method. It allows you to modify the method's behavior by controlling whether it should execute or not, and it also allows you to modify its arguments and return value.
   - It's the most powerful type of advice as it gives you complete control over the method execution.

7. **@Pointcut**:
   - This annotation defines a **pointcut**, which specifies where in the program the advice should be applied. It defines join points (specific points of execution) and allows you to reuse them in multiple advice methods.
   - Pointcuts are typically used to specify the methods where advice should be executed (e.g., all methods in a service class).

### Summary:
- **Concern**: A specific functionality or behavior in the application (e.g., logging, security).
- **Cross-Cutting Concern**: A concern that affects multiple parts of the system (e.g., logging across different methods).
- **Aspect**: A module that handles a cross-cutting concern (e.g., `LoggingAspect`).
- **Join Point**: A specific point in the method execution (e.g., before, after a method).
- **Advice**: Code that runs at a join point (e.g., logging, error handling).
- **Pointcut**: Specifies where advice should be applied (e.g., all methods in a class).

AOP helps you modularize common behaviors like logging and security, keeping your core business logic focused and uncluttered, while maintaining separation of concerns across different parts of the application.
*/
@Aspect //aspect is not a bean, spring does not create bean of it
@Configuration
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /*
   @Pointcut("execution(* com.cydeo.controller.CourseController.*(..))")
     - **1st star** (`*`): Matches any return type of methods.
     - **com.cydeo.controller**: Specifies that the execution should be within this package.
     - **CourseController**: Restricts the execution to the `CourseController` class within the specified package.
     - **\*.(..)**: The asterisk (`*`) indicates all methods within the `CourseController` class. The `(..)` specifies that the methods can have any number or type of parameters (including none).

   This pointcut matches all methods in the `CourseController` class, regardless of their return type or the number and type of their parameters.

   In the example below:
   - The `@Pointcut` annotation defines the pointcut expression (a condition that selects methods to advise).
   - The `log` method is a **before advice** that is triggered before the matched methods in `CourseController` are executed.

   The pointcut is used here with the `@Before` annotation, which ensures that the `log` method will run **before** any method in `CourseController` is executed.
   If you only used `@AfterReturning` directly without a `@Pointcut` method, you could specify the pointcut inline, but separating them improves clarity and reusability.
 */
    @Pointcut("execution(* com.cydeo.controller.CourseController.*(..))")
    private void pointcut(){}
    @Before("pointcut()")
    public void log(){ logger.info("Logger info --------"); }

    @Before("execution(* com.cydeo.controller.CourseController.*(..))")
    public void beforeAdvice(){ logger.info("Logger info --------"); }

    //any return type, package: repository, class: CourseRepository, (*): any parameter in findById
    @Pointcut("execution(* com.cydeo.repository.CourseRepository.findById(*))")
    private void anyProductRepositoryFindById(){}
    @Before("anyProductRepositoryFindById()")
    public void beforeCourseRepoOperation(JoinPoint joinPoint){
        logger.info("Before (findById()) : -> Method: {} - Arguments : {} - Target: {}", joinPoint,joinPoint.getArgs(),joinPoint.getTarget());
    }

    /*
      @Pointcut("within(com.cydeo.controller..*)")
        - **within** is used to define a pointcut at the **class level**. It specifies that the advice should be applied to methods **within the specified classes**.
        - **com.cydeo.controller..***: This matches **all classes** under the `com.cydeo.controller` package and any of its sub-packages. It applies the pointcut to all methods within these classes, regardless of the method name or parameters.

      @Pointcut("@within(org.springframework.stereotype.Service)")
        - **@within** is used to apply a pointcut to classes that are annotated with a specific annotation.
        - **org.springframework.stereotype.Service**: This pointcut applies to **all classes** that are annotated with `@Service`, typically used for service layer classes in Spring.

      In the example below:
      - The `beforeControllerAdvice` method uses a **combined pointcut** (`anyControllerOperation() || anyServiceOperation()`), meaning it applies before the execution of any method in the `com.cydeo.controller` package or any method in a `@Service`-annotated class.
      - This combined pointcut will trigger the advice before methods in both the `CourseController` class and any class with `@Service` annotation.

      **Note**:
      - If the `findById` method is executed in a class like `CourseServiceImpl`, which is annotated with `@Service`, and the controller method is executed in `CourseController`, then the `beforeControllerAdvice` will be executed twice (once for the controller and once for the service).
      - For example, if an API call like `GET http://localhost:8080/courses/api/v1/1` is made, the log info will be displayed twice: once for the controller method and once for the service method.

      The log message in the console will look like this:
      - **Before ()** : -> Method : execution(CourseDTO com.cydeo.controller.CourseController.getCourseById(Long)) - Arguments : [1] - Target: com.cydeo.controller.CourseController@4e02f17d
    */
    @Pointcut("within(com.cydeo.controller..*)")
    private void anyControllerOperation(){}
    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void anyServiceOperation(){}
    @Before("anyControllerOperation() || anyServiceOperation()")
    public void beforeControllerAdviceWithCombinedPointcuts(JoinPoint joinPoint){
        logger.info("Before () : -> Method : {} - Arguments : {} - Target: {}",joinPoint, joinPoint.getArgs(),joinPoint.getTarget());
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)") //classes with @DeleteMapping annotation on top
    private void anyDeleteCourseOperation(){}
    @Before("anyDeleteCourseOperation()")
    public void beforeControllerAdvice(JoinPoint joinPoint){
        logger.info("Before () : -> Method : {} - Arguments : {} - Target: {}",joinPoint, joinPoint.getArgs(),joinPoint.getTarget());
    }

    // This pointcut targets all methods annotated with @GetMapping, typically for handling GET requests in a controller.
    // It is reused in multiple advice methods below.
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetCourseOperation(){}

    // @AfterReturning: This advice runs after the method completes successfully (i.e., without throwing an exception).
    // The "result" parameter will hold the value returned by the method, typically a DTO or a list of DTOs.
    // In this case, we assume the controller returns a single CourseDTO object.
    @AfterReturning(pointcut = "anyGetCourseOperation()", returning = "result")
    public void afterReturningControllerAdvice(JoinPoint joinPoint,Object result){
        // Log the method signature and the result returned from the method (the CourseDTO object) via JoinPoint.
        logger.info("After returning -> Method: {} - result: {}",joinPoint.getSignature().toShortString(),result.toString());
    }

    // @AfterReturning: This advice also executes after a successful method execution but handles the case where the method returns a list of objects.
    // Here, "result" is a list of CourseDTO objects.
    @AfterReturning(pointcut = "anyGetCourseOperation()", returning = "result")
    public void afterReturningControllerAdvice(JoinPoint joinPoint, List<Object> result){
        // Log the method signature and the list of DTOs returned from the method.
        // This log will show the details of multiple CourseDTO objects returned by the controller.
        logger.info("After returning(List) -> Method: {} - result: {}",joinPoint.getSignature().toShortString(),result.toString());
    }

    // @AfterThrowing: This advice is executed if the target method throws an exception.
    // In this case, it catches any RuntimeException thrown by the controller method.
    //For example GET with wrong id: http://localhost:8080/courses/api/v1/1000
    @AfterThrowing(pointcut = "anyGetCourseOperation()",throwing = "exception")
    public void afterThrowingControllerAdvice(JoinPoint joinPoint, RuntimeException exception){
        // Log the method signature and the exception message that was thrown by the method.
        // This is useful for logging error scenarios, such as invalid input or database errors.
        logger.info("After Throwing -> Method: {} - Exception: {}",joinPoint.getSignature().toShortString(),exception.getMessage());
    }

    // @After: This advice runs after the target method finishes executing, regardless of whether it completes successfully or throws an exception.
    // It doesn't concern itself with the result or exception, but simply confirms that the method was executed.
    @After("anyGetCourseOperation()")
    public void afterControllerAdvice(JoinPoint joinPoint){
        logger.info("After finally -> Method : {}",joinPoint.getSignature().toShortString());
    }

    /* How @Around works:
    - The @Around advice combines the behavior of both @Before and @After annotations.
    - First, the @Before is executed, logging information before the target method runs.
    - Then, the `proceed()` method is called, which continues the execution of the target method and allows for the method’s return value or exception to be captured.
    - The `proceed()` method can receive data from the @Before advice (such as parameters or context), and it also passes the method’s result or any exception that occurs to @After advice.
    - After the `proceed()` call, the @After is executed, where the result or exception is processed and logged.
    - If the `proceed()` method is **not** called (e.g., if the method execution is skipped, or if an exception is thrown before proceeding), the @After-like behavior will **not** be executed*/
    @Pointcut("@annotation(com.cydeo.annotation.Loggable)") //any class methods with @Loggable
    private void anyLoggableMethodOperation(){}
    @Around("anyLoggableMethodOperation()")
    public Object anyLoggableMethodOperationAdvice(ProceedingJoinPoint proceedingJoinPoint)  {
        logger.info("Before () -> Method: {} - Parameters: {}",proceedingJoinPoint.getSignature().toShortString(),proceedingJoinPoint.getArgs());
        Object results;
        try {
            results = proceedingJoinPoint.proceed(); //proceed has Throwable exception, that's why use try-catch
        } catch (Throwable e) { throw new RuntimeException(e); }

        logger.info("After -> Method: {} - Results: {}",proceedingJoinPoint.getSignature().toShortString(),results.toString());
        return results;
    }
}
