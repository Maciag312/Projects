package com.example.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CTaskRepository<CTask> extends CrudRepository<CTask, Long>, JpaRepository<CTask, Long> {
    List<CTask> findAll();
    Optional<CTask> findById(Long id);
}
