package com.example.demo.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Employee;
import com.example.demo.jparepository.JpaRepositoryTest;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/employeeManagement")
public class ControllerTest {
	
	@Autowired
	private JpaRepositoryTest jpaRepositoryTest;
	
	@RequestMapping("/home")
	public String welcome()	{
		return "welcome";
	}
	@RequestMapping("/add")
	
	public String addEmp()
	{
		return "addEmployee";
	}
	
	
    @RequestMapping("/saveEmployee")
    public String saveEmpInfo(
        @RequestParam String firstName,
        @RequestParam String lastName,
        @RequestParam String email,
        @RequestParam String departmentId
    ) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setDepartmentId(departmentId);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = timeFormat.format(new Date());
        Time currentSqlTime = Time.valueOf(currentTime);
        
        employee.setCreatedAt(String.valueOf(currentSqlTime));
        employee.setUpdatedAt(String.valueOf(currentSqlTime));
        jpaRepositoryTest.save(employee);
        
        return "confirmationpage";
    }
    
    @RequestMapping("/allEmployee")
    public String getAllEmployee(Model model) {
        List<Employee> employees = jpaRepositoryTest.findAll();
        model.addAttribute("employees", employees);
        return "allEmployeesDetails"; 
    }

    @RequestMapping("remove")
    public String removeEmp()
    {
    	return "removeEmployee";
    }
    
    @RequestMapping("/removeEmployee")
    public String removeEmployee(@RequestParam Long employeeId, Model model) {
        Employee employee = jpaRepositoryTest.findById(employeeId).orElse(null);

        if (employee != null) {
            jpaRepositoryTest.delete(employee);
            model.addAttribute("message", "Employee removed successfully.");
        } else {
            model.addAttribute("message", "Employee not found.");
        }

        return "employeeresult";
    }
    
    @RequestMapping("/update")
    public String updateEmp()
    {
    	return "updateEmpDetails";
    }
    
    @RequestMapping("/updateEmployee")
    public String updateEmployee(
        @RequestParam Long employeeId,
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String departmentId
    ) {
        // Retrieve the employee by ID
        Employee employee = jpaRepositoryTest.findById(employeeId).orElse(null);

        if (employee != null) {
            // Update employee details if new values are provided
            if (firstName != null) {
                employee.setFirstName(firstName);
            }
            if (lastName != null) {
                employee.setLastName(lastName);
            }
            if (email != null) {
                employee.setEmail(email);
            }
            if (departmentId != null) {
                employee.setDepartmentId(departmentId);
            }
            
            // Update the updatedAt field with the current timestamp
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            String currentTime = timeFormat.format(new Date());
            Time currentSqlTime = Time.valueOf(currentTime);
            
            employee.setUpdatedAt(String.valueOf(currentSqlTime));
            
            jpaRepositoryTest.save(employee);
            return "updateEmployeeResult"; // Display the update result
        } else {
            return "updateEmployeeResultError"; // Display an error message
        }
    }
	
}
