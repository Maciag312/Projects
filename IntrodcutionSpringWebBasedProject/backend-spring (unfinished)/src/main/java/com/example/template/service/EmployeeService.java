package com.example.template.service;

import com.example.template.ipo.CTime;
import com.example.template.model.CTask;
import com.example.template.model.Employee;
import com.example.template.repository.EmployeeRepository;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository){
        this.repository = repository;
    }

    public void create(Employee employee){
        repository.save(employee);
    }

    public List<Employee> getAll(){
        return repository.findAll();
    }

    public Employee getById(Long id){return (Employee)repository.findById(id).orElse(new Employee());}

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Employee> findByNameOrSurname(String name, String surname){

        return repository.findByNameAndSurname(name, surname);
    }

    public List<CTask> getTasksByTIntervalAndId(Calendar begin, Calendar finish, Long id){
        Employee emp = (Employee) repository.findById(id).orElse(null);
        List<CTask> cTasksList = emp.getcTaskList();
        List<CTask> cTasksToReturn = new ArrayList<CTask>();
        for(CTask ctas: cTasksList){
            if(ctas.getStart().getTimeInMillis()>finish.getTimeInMillis()||ctas.getFinish().getTimeInMillis()>begin.getTimeInMillis()) {
                cTasksToReturn.add(ctas);
            }
        }
        return cTasksToReturn;
    }
    public List<CTask> getTasksByTInterval(Calendar begin, Calendar finish){
        List<Employee> emp =  repository.findAll();
        List<CTask> cTasksToReturn = new ArrayList<CTask>();
        for(Employee emp2: emp){
            for(CTask ctas: emp2.getcTaskList()){
                if(ctas.getStart().getTimeInMillis()>finish.getTimeInMillis()||ctas.getFinish().getTimeInMillis()>begin.getTimeInMillis()) {
                    cTasksToReturn.add(ctas);
                }

            }
        }
        return cTasksToReturn;
    }

}
