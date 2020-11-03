package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    EmployeeRepository employees = new EmployeeRepository();

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/all")
    public String allEmployees(Model model){
        //Data fra databasen
        List<Employee> allEmps = employees.findAllEmployees();

        //Data til viewet
        model.addAttribute("allEmps",allEmps);

        //returnerer viewet
        return "all-employees";
    }

    @GetMapping("/single")
    public String singleEmployee(WebRequest wr, Model model){
        Employee emp = employees.findSingleEmployeeByEmpno(Integer.parseInt(wr.getParameter("empno")));
        model.addAttribute("employee",emp);
        return "index";
    }
}
