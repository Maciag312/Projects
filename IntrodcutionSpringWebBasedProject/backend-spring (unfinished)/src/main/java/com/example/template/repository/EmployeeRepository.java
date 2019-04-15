package com.example.template.repository;

import com.example.template.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository<Employee> extends CrudRepository<Employee, Long>, JpaRepository<Employee, Long> {
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    List<Employee> findByNameAndSurname(String name, String surname);

}
