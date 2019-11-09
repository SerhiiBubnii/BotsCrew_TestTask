package com.bubnii.view;

import com.bubnii.model.dao.DepartmentDaoImpl;
import com.bubnii.model.dao.LectorDaoImpl;
import com.bubnii.model.dao.interfaces.DepartmentDto;
import com.bubnii.model.dao.interfaces.LectorDao;
import com.bubnii.model.entity.Lector;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class View {

    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private Scanner input;
    private DepartmentDto departmentDto;
    private LectorDao lectorDao;

    public View() {
        departmentDto = new DepartmentDaoImpl();
        lectorDao = new LectorDaoImpl();
        input = new Scanner(System.in);
        menu = new LinkedHashMap<>();
        menu.put("1", "\t1 - Show who is head of department");
        menu.put("2", "\t2 - Show department statistic");
        menu.put("3", "\t3 - Show the average salary for department");
        menu.put("4", "\t4 - Show count of employee for department");
        menu.put("5", "\t5 - Show global search by substring");
        menu.put("Q", "\tQ - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::getHeadOfDepartment);
        methodsMenu.put("2", this::getDepartmentStatistic);
        methodsMenu.put("3", this::getAverageSalaryForDepartment);
        methodsMenu.put("4", this::getCountOfEmployeeOnDepartment);
        methodsMenu.put("5", this::getGlobalSearchBySubstring);
    }

    private void getGlobalSearchBySubstring() {
        System.out.println("Enter the substring to look for");
        String substring = input.next();
        lectorDao
                .findAllBySubstring(substring)
                .forEach(lector -> System.out.println("Answer: " + lector.getFirstName() + " " + lector.getLastName()));
    }

    private void getCountOfEmployeeOnDepartment() {
        System.out.println("Enter the department name");
        String departmentName = input.next();
        departmentDto
                .findByHeadOfDepartmentName(departmentName)
                .ifPresent(department -> System.out.println("Answer: " + department.getLectors().size()));
    }

    private void getAverageSalaryForDepartment() {
        System.out.println("Enter the department name");
        String departmentName = input.next();
        departmentDto
                .findByHeadOfDepartmentName(departmentName)
                .ifPresent(department -> department
                        .getLectors()
                        .stream()
                        .mapToDouble(Lector::getSalary)
                        .average()
                        .ifPresent(value -> System.out.println("Answer: The average salary of "
                                + departmentName + " department is " + value)));
    }

    private void getDepartmentStatistic() {
        System.out.println("Enter the department name");
        String departmentName = input.next();
        departmentDto
                .findByHeadOfDepartmentName(departmentName)
                .ifPresent(department -> department
                        .getLectors()
                        .stream()
                        .collect(Collectors
                                .groupingBy(Lector::getDegree, Collectors.counting()))
                        .forEach((degree, aLong) -> System.out.println("Answer: " + degree + " - " + aLong)));
    }

    private void getHeadOfDepartment() {
        System.out.println("Enter the department name");
        String departmentName = input.next();
        departmentDto
                .findByHeadOfDepartmentName(departmentName)
                .ifPresent(department ->
                        System.out.println("Answer: Head of " + departmentName
                                + " department is " + department.getHeadOfDepartment()));
    }

    public void show() {
        String keyMenu;
        do {
            System.out.println("------------------------------------------------------------------------------------------");
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.next().toUpperCase();
            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!keyMenu.equals("Q"));
    }

    private void outputMenu() {
        System.out.println("MENU:");
        for (String str : menu.values()) {
            System.out.println(str);
        }
    }
}
