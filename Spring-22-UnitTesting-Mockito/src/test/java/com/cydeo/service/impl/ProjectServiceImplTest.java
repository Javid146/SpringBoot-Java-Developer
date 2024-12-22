package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith allows to run Mockito methods in a class.Provided by Junit
@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

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
    ProjectRepository projectRepository;
    @Mock
    ProjectMapper projectMapper;

    //instead of real repo. mock repo is injected to be used by projectServiceImpl, we don't want real projectRepository db to run
    //@InjectMocks injects the mocked projectRepository into the projectServiceImpl instance.
    @InjectMocks
    ProjectServiceImpl projectServiceImpl;

    @Test
    void getByProjectCode_test(){

        Project project = new Project();
        ProjectDTO projectDTO = new ProjectDTO();
        //define behaviour of mock projectRepository
        when(projectRepository.findByProjectCode(Mockito.anyString())).thenReturn(project);
        //define behaviour of mock projectMapper
        when(projectMapper.convertToDto(project)).thenReturn(projectDTO);

        //real code execution with mock repo (projectRepository)
        ProjectDTO projectDTO1 = projectServiceImpl.getByProjectCode(Mockito.anyString());

        //verify projectServiceImpl.getByProjectCode() indeed interacted with mock projectRepository and had same argument
        verify(projectRepository).findByProjectCode(Mockito.anyString());
        //verify projectServiceImpl.getByProjectCode() indeed interacted with mock projectMapper and converted to same Object        verify(projectMapper).convertToDto(any(Project.class));

        //assert result is not or nut
        assertNotNull(projectDTO1);
    }

    @Test
    void getByProjectCode_exception_test() {
        // Defining behavior of the mock projectRepository: throw an exception when an empty string is passed as the project code
        when(projectRepository.findByProjectCode("")).thenThrow(new RuntimeException("Project Not Found"));

        // Calling the real method projectServiceImpl.getByProjectCode("") and asserting that it throws a RuntimeException
        // The exception is captured and assigned to the 'exception' variable
        Throwable exception = assertThrows(RuntimeException.class, () -> projectServiceImpl.getByProjectCode(""));

        // Verifying that projectRepository.findByProjectCode() was called with any string argument
        // This confirms the interaction between the service and the repository during the method call
        verify(projectRepository).findByProjectCode(Mockito.anyString());

        // Asserting that the exception message is as expected ("Project Not Found") to ensure the correct exception was thrown
        assertEquals("Project Not Found", exception.getMessage());
    }

    @Test
    void save_test() {
        // Creating a ProjectDTO object that will be passed as input to the service method
        ProjectDTO projectDTO = new ProjectDTO();

        // Creating a Project object that the mock mapper will return when converting the DTO
        Project project = new Project();

        // Stubbing the behavior of the mock projectMapper: When convertToEntity() is called with projectDTO, return the mock Project object
        when(projectMapper.convertToEntity(projectDTO)).thenReturn(project);

        // Stubbing the behavior of the mock projectRepository: When save() is called with the project, return the same project object
        when(projectRepository.save(project)).thenReturn(project);

        // Calling the real method in projectServiceImpl, which should use the mocked dependencies (projectMapper and projectRepository)
        projectServiceImpl.save(projectDTO);

        // Verifying that projectRepository.save() was called exactly once with the mock project object as an argument
        verify(projectRepository).save(project);

        // Verifying that projectMapper.convertToEntity() was called exactly once with any ProjectDTO argument
        verify(projectMapper).convertToEntity(any(ProjectDTO.class));
    }
}