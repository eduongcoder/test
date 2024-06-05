package com.example.Configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Entity.Employee;
import com.example.Entity.EmployeeDTO;
import com.example.Entity.Relative;
import com.example.Entity.RelativeDTO;

@Configuration
public class ComponentConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Define specific mappings
        modelMapper.addMappings(new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setIdDepartment(source.getIdDepartment().getId()); 
            }
        });
        modelMapper.addMappings(new PropertyMap<Relative, RelativeDTO>() {
            @Override
            protected void configure() {
                map().setIdEmployee(source.getIdEmployee().getId());
            }
        });

        return modelMapper;
    }
}
