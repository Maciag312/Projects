package com.example.template.controller;

import com.example.template.ipo.CalInterval;
import com.example.template.ipo.EmployeeJSON;
import com.example.template.model.CTask;
import com.example.template.model.Employee;
import com.example.template.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController  {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


//    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public List<Employee> create(@RequestBody final Employee employee){
//        employeeService.create(employee);
//        return employeeService.getAll();
//    }
    @PostMapping(value = "/create",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody final EmployeeJSON eJs){
        Employee employeeElem = new Employee(eJs.lastname, eJs.surname, eJs.Adress, eJs.Nationality, eJs.Description, eJs.Position, eJs.contract);
        employeeService.create(employeeElem);
    }
    @PostMapping(value = "/getAll",  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAll(){
        return employeeService.getAll();
    }
    @PostMapping(value = "getById", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Employee getById(@RequestParam("id") Long id){
        return employeeService.getById(id);
    }
    @PostMapping(value = "getByNameAndSurname", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname){
        List<Employee> employeeArrayList = new ArrayList<Employee>();
        employeeArrayList.add
    }

    @PostMapping(value = "/getTasks",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CTask> getTask(@RequestBody final CalInterval calInterval){
        return employeeService.getTasksByTInterval(calInterval.getStart(),calInterval.getFinish());
    }


}
