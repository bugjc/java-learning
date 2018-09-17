package com.bugjc.java.basics.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author qingyang
 * @date 2018/9/17 16:30
 */
@AllArgsConstructor
@Data
public class Employee implements Comparable<Employee>, Serializable {

    private String name;
    private double salary;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }

    @Override
    public int compareTo(Employee employee) {
        return Double.compare(this.getSalary(), employee.getSalary());
    }

}
