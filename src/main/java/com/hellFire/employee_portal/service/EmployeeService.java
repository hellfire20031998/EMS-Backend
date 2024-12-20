package com.hellFire.employee_portal.service;


import com.hellFire.employee_portal.entity.Employee;
import com.hellFire.employee_portal.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee postEmployee(Employee employee){

        return  employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return  employeeRepository.findAll();
    }

    public  void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee with id "+id +" not found");
        }
        employeeRepository.deleteById(id);
    }

    public  Employee getEmployeeById(Long id){
        return  employeeRepository.findById(id).orElse(null);
    }

    public  Employee updateEmployee(Long id,Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            Employee existingEmployee = employeeOptional.get();

            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setName(employee.getName());
            existingEmployee.setPhone(employee.getPhone());

            return employeeRepository.save(existingEmployee);
        }
        return  null;
    }

}
