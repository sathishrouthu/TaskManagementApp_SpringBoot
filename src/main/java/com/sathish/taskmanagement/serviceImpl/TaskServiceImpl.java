package com.sathish.taskmanagement.serviceImpl;

import com.sathish.taskmanagement.entity.Task;
import com.sathish.taskmanagement.entity.User;
import com.sathish.taskmanagement.exception.TaskNotFound;
import com.sathish.taskmanagement.exception.UserNotFound;
import com.sathish.taskmanagement.exception.UserNotMatch;
import com.sathish.taskmanagement.payload.TaskDto;
import com.sathish.taskmanagement.repository.TaskRepository;
import com.sathish.taskmanagement.repository.UserRepository;
import com.sathish.taskmanagement.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public TaskDto saveTask(long userid, TaskDto taskDto) {

        User user = userRepository.findById(userid).orElseThrow(
                () -> new UserNotFound( String.format("User Id %d Not found ",userid) )
        );
        Task task = modelMapper.map(taskDto,Task.class);
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask,TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks(long userid) {
        userRepository.findById(userid).orElseThrow(
                ()-> new UserNotFound(String.format("User Id %d Not found",userid))
        );

        List<Task> tasks = taskRepository.findAllByUserId(userid);
        return tasks.stream().map(task -> modelMapper.map(task,TaskDto.class)).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTask(long userid,long taskid){
        User user = userRepository.findById(userid).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d Not found",userid))
        );
        Task task = taskRepository.findById(taskid).orElseThrow(
                ()->new TaskNotFound(String.format("Task Id %d Not found",taskid))
        );
        if(user.getId()!=task.getUser().getId()){
            throw new UserNotMatch(String.format("User Id %d not matched with Task Id %d",userid,taskid));
        }
        return modelMapper.map(task,TaskDto.class);
    }

    @Override
    public void deleteTask(long userid,long taskid){
        User user = userRepository.findById(userid).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d Not found",userid))
        );
        Task task = taskRepository.findById(taskid).orElseThrow(
                ()->new TaskNotFound(String.format("Task Id %d Not found",taskid))
        );
        if(user.getId()!=task.getUser().getId()){
            throw new UserNotMatch(String.format("User Id %d not matched with Task Id %d",userid,taskid));
        }
        taskRepository.deleteById(taskid);

    }
}
