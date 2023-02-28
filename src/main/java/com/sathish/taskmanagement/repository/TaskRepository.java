package com.sathish.taskmanagement.repository;

import com.sathish.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
//    List<Task> findAllByUsersId(long userid);

    List<Task> findAllByUserId(long userid);
}
