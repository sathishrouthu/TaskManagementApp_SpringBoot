package com.sathish.taskmanagement.repository;

import com.sathish.taskmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface UserRepository extends JpaRepository<User, Long> {

}
