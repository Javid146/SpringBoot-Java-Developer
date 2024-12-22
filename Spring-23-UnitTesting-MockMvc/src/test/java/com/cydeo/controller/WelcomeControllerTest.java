package com.cydeo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*MockMvc is often used for web layer testing, which involves testing the controller's behavior and interactions with HTTP requests and responses.
This is sometimes part of an integration test but is technically more focused on controller-level testing.
In other words, MockMvc doesn't perform full-blown integration tests where all layers (e.g., service, repository, database) are tested together.
Instead, it isolates the web layer (controller) and tests it in conjunction with a mocked or simulated request and response cycle.*/
@WebMvcTest(WelcomeController.class) //give all beans related tot this class
class WelcomeControllerTest {

/*In Spring, @Autowired is an annotation used for dependency injection. It tells Spring to automatically inject a dependent bean into the class where the annotation is applied.
Why @Autowired for MockMvc?
MockMvc is a Spring bean that gets automatically configured when you use @WebMvcTest. Spring takes care of creating the necessary beans (like MockMvc) for testing the controller layer.
By using @Autowired, you don't need to manually instantiate MockMvc. Spring will automatically provide it for you when the test runs, ensuring that the necessary setup for web-layer testing is done.
So, in summary, @Autowired in this context is used to inject the MockMvc instance into your test so you can simulate HTTP requests and perform assertions on the responses.*/
    @Autowired
    private MockMvc mvc;

    @Test
    void welcome() throws Exception {

        //create mock test for integration test
        RequestBuilder request = MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON);

        //verify above mock API request with MvcResult
        //mvc.perform(request): This sends the mock HTTP request. andReturn(): This method returns the result of the request, which is an instance of MvcResult.
        MvcResult result = mvc.perform(request).andReturn();
        //You can then use MvcResult to access the response details, like this:
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("welcome",result.getResponse().getContentAsString());
    }

    //test above and this are same, just different style
    @Test
    void welcome2() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/welcome")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("welcome"))
                .andReturn();
    }
}