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

@WebMvcTest(StudentController.class) //give all beans related tot this class
class StudentControllerTest {

    //MockMvc is used for Integration testing
    @Autowired
    private MockMvc mvc;

    @MockBean
    StudentService studentService;

    @Test
    void getStudent() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/student")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 0,\"firstName\":\"Mike\",\"lastName\": \"Smith\",\"age\": 20}"))
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

        //expected response
        when(studentService.getStudent()).thenReturn(Arrays.asList(
                new Student("John","Doe",20),
                new Student("Tom","Hanks",50)
        ));

        mvc.perform(MockMvcRequestBuilders
                .get("/service/student")
                .accept(MediaType.APPLICATION_JSON))
                //verify that json below matches with expected response above
                .andExpect(content().json("[{\"id\": 0,\"firstName\": \"John\",\"lastName\": \"Doe\",\"age\": 20}," +
                                                     "{\"id\": 0,\"firstName\": \"Tom\",\"lastName\": \"Hanks\",\"age\": 50}]"))
                .andReturn();
    }
}