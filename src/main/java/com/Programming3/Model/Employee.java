package com.Programming3.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private float salary;

    public Employee(String name, float salary)
    {
        this.name = name;
        this.salary = salary;
    }

    public Employee()
    {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public float getSalary() {
        return this.salary;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setSalary(float salary) {
        this.salary = salary;
    }
}
