package com.example.restapi.security.services;

import com.kurepin.lab4.entities.Comment;
import com.kurepin.lab4.entities.Employee;
import com.kurepin.lab4.entities.Task;
import com.kurepin.lab4.repositories.CommentRepository;
import com.kurepin.lab4.repositories.EmployeeRepository;
import com.kurepin.lab4.repositories.TaskRepository;
import com.kurepin.lab4.security.models.Role;
import com.kurepin.lab4.security.models.User;
import com.kurepin.lab4.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessControlSecurity {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    public boolean commentIsAccessedByTaskId(String username, Long taskId) {
        return isAdmin(username) || getTask(taskId).getEmployeeId().getId().equals(getUser(username).getEmployee().getId());
    }

    public boolean commentIsAllowed(String username, Long commentId) {
        return isAdmin(username) || getComment(commentId).getTaskId().getEmployeeId().getId().equals(getUser(username).getEmployee().getId());
    }

    public boolean taskIsAccessedByEmployeeId(String username, Long employeeId) {
        return isAdmin(username) || employeeId.equals(getEmployee(username).getId());
    }

    public boolean taskIsAllowed(String username, Long taskId) {
        return isAdmin(username) || getTask(taskId).getEmployeeId().getId().equals(getUser(username).getEmployee().getId());
    }

    public boolean employeeIsAllowed(String username, Long employeeId) {
        if (isAdmin(username)) {
            return true;
        }
        return getEmployee(username).getId().equals(employeeId);
    }

    public Employee getEmployee(String username) {
        return employeeRepository.getReferenceById(getUser(username).getEmployee().getId());
    }

    public boolean isAdmin(String username) {
        return getUser(username).getRole().equals(Role.ADMIN);
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    private Task getTask(Long taskId) {
        return taskRepository.getReferenceById(taskId);
    }

    private Comment getComment(Long commentId) {
        return commentRepository.getReferenceById(commentId);
    }
}
