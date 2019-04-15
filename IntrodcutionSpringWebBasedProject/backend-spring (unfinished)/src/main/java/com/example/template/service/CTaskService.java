package com.example.template.service;

import com.example.template.model.CTask;
import com.example.template.model.Employee;
import com.example.template.repository.CTaskRepository;
import com.example.template.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CTaskService {
    private CTaskRepository repository;

    @Autowired
    public CTaskService(CTaskRepository repository){
        this.repository = repository;
    }

    public void create(CTask cTask){
        repository.save(cTask);
    }

    public List<CTask> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
