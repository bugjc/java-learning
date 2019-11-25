package com.bugjc.java.basics.container.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author qingyang
 * @date 2018/9/17 16:34
 */
@Slf4j
public class PriorityQueueUserDefinedObjectExample {

    public static void main(String[] args) {
        /*
           The requirement for a PriorityQueue of user defined objects is that

           1. Either the class should implement the Comparable interface and provide
              the implementation for the compareTo() function.
           2. Or you should provide a custom Comparator while creating the PriorityQueue.
        */

        // Create a PriorityQueue
        PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<>();

        // Add items to the Priority Queue
        employeePriorityQueue.add(new Employee("Rajeev", 100000.00));
        employeePriorityQueue.add(new Employee("Chris", 145000.00));
        employeePriorityQueue.add(new Employee("Andrea", 115000.00));
        employeePriorityQueue.add(new Employee("Jack", 167000.00));

        /*
            The compareTo() method implemented in the Employee class is used to determine
            in what order the objects should be dequeued.
        */
        while (!employeePriorityQueue.isEmpty()) {
            log.info(employeePriorityQueue.remove().toString());
        }
    }

    /**
     * @author qingyang
     * @date 2018/9/17 16:30
     */
    @AllArgsConstructor
    @Data
    public static class Employee implements Comparable<Employee>, Serializable {

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
}
