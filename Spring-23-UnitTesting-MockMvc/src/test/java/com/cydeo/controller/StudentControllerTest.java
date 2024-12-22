package com.cydeo.controller;

import com.cydeo.entity.Student;
import com.cydeo.service.StudentService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*Purpose: The @WebMvcTest annotation is used to test the web layer of your Spring Boot application, specifically focusing on controllers.
It sets up a Spring context with just the components needed to test the web layer (i.e., controllers, filters, and view resolvers)
without involving the full application context (such as service beans, repository beans, etc.).
It will configure Spring MVC features for testing, like MockMvc, which allows you to simulate HTTP requests.
Only web-related beans like Controller, @RequestMapping, @GetMapping, @PostMapping, etc., are initialized.*/
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    /*MockMvc is often used for web layer testing, which involves testing the controller's behavior and interactions with HTTP requests and responses.
    This is sometimes part of an integration test but is technically more focused on controller-level testing.
    In other words, MockMvc doesn't perform full-blown integration tests where all layers (e.g., service, repository, database) are tested together.
    Instead, it isolates the web layer (controller) and tests it in conjunction with a mocked or simulated request and response cycle.*/
    @Autowired
    private MockMvc mvc;

    /*This is used because, in web layer testing, you usually want to mock out the service layer (in this case, StudentService) to isolate and focus on testing the controller layer.
      Without @MockBean, the real StudentService bean would be injected into your StudentController, potentially involving real database calls or complex logic, which is not ideal for unit testing the controller.
      How it works:
      @MockBean creates a mock instance of StudentService, and it automatically replaces any existing StudentService bean in the application context with this mock.
      You can then configure the mock using Mockito (when(...).thenReturn(...)) to simulate the behavior of the service for specific test scenarios.*/
    @MockBean
    StudentService studentService;

    /*this test verifies StudentController getStudent() method
    * it verifies hard-coded student data in controller
    * there is no real interaction with db because student data directly comes from StudentController
    * so this test is Mock test*/
    @Test
    void getStudent() throws Exception {
        //mock test via MockMvcRequestBuilders
        mvc.perform(MockMvcRequestBuilders
                .get("/student")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 0,\"firstName\":\"Mike\",\"lastName\": \"Smith\",\"age\": 21}"))
                .andReturn();
    }

    @Test
    void jsonAssert() throws JSONException {
        String expected = "{\"id\": 0,\"firstName\":\"Mike\",\"lastName\": \"Smith\"}";
        String actual = "{\"id\": 0,\"firstName\":\"Mike\",\"lastName\": \"Smith\",\"age\": 20}";

        //"strict:true" means all parameters and values should match, "strict:false" checks partially and if comparison matches test passes
        JSONAssert.assertEquals(expected,actual,false);
    }

    @Test
    void getStudent_service() throws Exception {

        //expected response. Mocks studentService.getStudent() but returns predefined mock data: John Doe and Tom Hanks
        when(studentService.getStudent()).thenReturn(Arrays.asList(
                new Student("John","Doe",20),
                new Student("Tom","Hanks",50)
        ));

        /*this test verifies StudentController getStudent_Service() method. It calls studentService.getStudent(), but since it has mock data above, it fetches that instead of real db data*/
        mvc.perform(MockMvcRequestBuilders
                .get("/service/student") //mocks api call, but not really interacts with db
                .accept(MediaType.APPLICATION_JSON))
                //verify that json below matches with expected response above
                .andExpect(content().json("[{\"id\": 0,\"firstName\": \"John\",\"lastName\": \"Doe\",\"age\": 20}," +
                                                     "{\"id\": 0,\"firstName\": \"Tom\",\"lastName\": \"Hanks\",\"age\": 50}]"))
                .andReturn();
    }
}