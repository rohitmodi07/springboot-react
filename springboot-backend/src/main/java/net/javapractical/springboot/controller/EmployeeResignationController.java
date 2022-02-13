package net.javapractical.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javapractical.springboot.model.Employee;
import net.javapractical.springboot.service.EmployeeResignationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeResignationController {

    @Autowired
    private EmployeeResignationService employeeService;

    // build get employee by id REST API
    @GetMapping("{employeeId}/{employeeEmailId}")
    public Employee getEmployeeById(@PathVariable  long employeeId, @PathVariable String employeeEmailId) throws Exception{
        Employee employee = employeeService.getEmployeeById(employeeId, employeeEmailId);
        return employee;
    }

    // build update resignation REST API
    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) throws Exception {
        System.out.println(" Put request with employeedetails : "+employeeDetails);
    	Employee empoloyee = employeeService.updateResignationDetails(employeeDetails);

        return empoloyee;
    }
}
