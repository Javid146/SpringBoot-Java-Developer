package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    TaskRepository taskRepository;
    @Mock
    TaskMapper taskMapper;
    @InjectMocks
    TaskServiceImpl taskServiceImpl;

    @ParameterizedTest
    @ValueSource(longs = {1L,2L,3L,-5L})
    void findById_test(long id){

        Task task = new Task();

        //define behaviour of mock taskRepository: return random Task when taskRepository.findById() called
        //id get @ValueSource array values 1 by 1
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        //define behaviour of mock taskMapper: return random TaskDTO when taskMapper.convertToDTO() called
        when(taskMapper.convertToDTO(task)).thenReturn(new TaskDTO());

        //todo call real taskServiceImpl that interacts with mock repo. This is main part of Unit test
        taskServiceImpl.findById(id);

        //verify taskServiceImpl.findById() method really interacted with mock repo.
        //point of unit test is that method interact correctly with dependencies (mappers,repos), not data verification
        verify(taskRepository).findById(id);
        verify(taskRepository).findById(anyLong()); //anyLong is for -5L to accept minus digit
        verify(taskMapper).convertToDTO(any(Task.class)); //same with line below
//        verify(taskMapper).convertToDTO(task);

//        verify(taskRepository,never()).findById(-5L); //never means don't accept that minus number
    }

    @Test
    void findById_bdd_test(){

        Task task = new Task();
        //taskRepository.findById(anyLong()) is mocked using given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));, which means when taskServiceImpl.findById() calls taskRepository.findById(),
        // it will return the mocked value (Optional.of(task)) instead of querying a real database.
//      when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task)); //same as line below
        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));
        //Similarly, taskMapper.convertToDTO(task) is mocked using given(taskMapper.convertToDTO(task)).willReturn(new TaskDTO());,
        // so it will return a new TaskDTO object without actually performing any real object conversion logic.
        given(taskMapper.convertToDTO(task)).willReturn(new TaskDTO());

        /*The taskServiceImpl.findById(anyLong()) call is executed in real time in the sense that it is invoking the actual method in the taskServiceImpl class.
        The method findById is executed as part of the test, meaning the business logic in the taskServiceImpl is executed as normal.
        However, since taskRepository and taskMapper are mocked, their behavior is controlled by Mockito, so no real database access or real mapping of objects occurs.*/
        /*This is the actual method call you are testing in your unit test. It invokes the findById method of the taskServiceImpl object,
         which is the service class that interacts with your repositories and mappers. This is where the business logic of your service is executed.
        Purpose: You call this to trigger the logic inside the taskServiceImpl's findById method and test how it interacts with
       the mocked dependencies (like taskRepository and taskMapper).*/
        taskServiceImpl.findById(anyLong());
//      verify(taskRepository).findById(anyLong()); //same as line below
        then(taskRepository).should().findById(anyLong());

//      verify(taskRepository,never()).findById(-5L); //never means don't accept that number //same as line below
        then(taskRepository).should(never()).findById(-5L);

        then(taskMapper).should().convertToDTO(task);
    }
}