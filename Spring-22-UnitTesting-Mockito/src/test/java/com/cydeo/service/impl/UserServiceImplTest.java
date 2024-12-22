package com.cydeo.service.impl;

import com.cydeo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

//@ExtendWith allows to run Mockito methods in a class.Provided by Junit
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    /*The Purpose of Mocks:
     Mocks allow you to simulate the behavior of external dependencies in a controlled way. You're not testing whether
     the taskRepository really accesses the database or whether taskMapper accurately converts data; instead, you're testing that your service (taskServiceImpl)
     interacts with those dependencies correctly and produces the right behavior.

     How Do You Know It Works? Test Logic, Not Real Data:
     When using mocks, you're verifying that the code logic inside taskServiceImpl is functioning correctly
     by ensuring it calls the right methods on its dependencies with the correct arguments.
     For example, in your test, you’re checking whether the taskServiceImpl correctly calls taskRepository.findById(anyLong()) and
     taskMapper.convertToDTO(task) and whether those calls happen with the expected parameters.*/
    @Mock
    UserRepository userRepository;

    //@InjectMocks injects the mocked userRepository into the userService instance.
    @InjectMocks
    UserServiceImpl userServiceImpl;


    /*Steps:
    * 1. Inject your class/repo method to mock:userRepository,userServiceImpl;
    * 2. Add @Mock, @InjectMocks. @Mock means userRepository repo will be mocked. @InjectMocks mean userRepository is injected in userServiceImpl for mock. because they depend on each other in userServiceImpl class.
    * 3. class real method userServiceImpl.deleteByUserName("mikesmith@cydeo.com"); But as we have annotations above, real db interaction will not happen.
    * Instead, Mockito will create fake behaviour as if real method ran
    * 4. verify result of mock: verify(userRepository).deleteByUserName("mikesmith@cydeo.com");*/
    @Test
    void deleteByUserName_test(){

        //invoke real userServiceImpl deleteByUserName code, but as deleteByUserName is part of userRepository anf userRepository
        //is mock, then it will not be real test
        userServiceImpl.deleteByUserName("mikesmith@cydeo.com");

        //mockito makes sure this deleteByUserName() runs, but mock test, real data in db stays intact.
        //verify that real deleteByUserName method above indeed interacted with mock userRepository and had the same argument: mikesmith@cydeo.com
        verify(userRepository).deleteByUserName("mikesmith@cydeo.com");
        //verify(userRepository,times(2)).deleteByUserName("mikesmith@cydeo.com");
        verify(userRepository,atLeastOnce()).deleteByUserName("mikesmith@cydeo.com");
//      run al least 1 time
        verify(userRepository,atLeast(1)).deleteByUserName("mikesmith@cydeo.com");
        verify(userRepository,atMostOnce()).deleteByUserName("mikesmith@cydeo.com");
        verify(userRepository,atMost(5)).deleteByUserName("mikesmith@cydeo.com");

        //InOrder verifies if real methods used for mock are in order. If they are not, test fails
        //example: userService.deleteByUserName() method has 1st deleteByUserName(), 2nd findAll() method.
        //here if line 45 is switched with 46, test fails
        InOrder inOrder = inOrder(userRepository);
        inOrder.verify(userRepository).deleteByUserName("mikesmith@cydeo.com");
        inOrder.verify(userRepository).findAll();
    }
}