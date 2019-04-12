package com.Programming3.Controller;

import com.Programming3.Model.Calculations;
import com.Programming3.UserRepository;
import com.Programming3.Model.Employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    // Message to Marker: There are two Index() method, the first one represents Iteration 1, and the next represents Iteration 2 and 3.
    //                    Please comment one method to be able to do the corresponding iteration
    //                    For now, the first Index() method is commented out for Iteration 2 and 3 to be functioning

//    @RequestMapping("/")
//    public String index()
//    {
//        return "index";
//    }

    @RequestMapping("/")
    public String index() {
        return "sqlindex";
    }

    @PostMapping("/sqlSubmit")
    public String getAllSql(Model model,
                            @RequestParam("sqlName") String name,
                            @RequestParam("sqlSalary") float salary)
    {
        Employee newEmp = new Employee(name, salary);
        userRepository.save(newEmp);

        Iterable<Employee> employeeListBefore = userRepository.findAll();
        List<Employee> employeeListAfter = new ArrayList();
        float sum = 0f;
        float average;

        for (Employee e : employeeListBefore)
        {
            employeeListAfter.add(e);
            sum += e.getSalary();
        }

        if (employeeListAfter.size() == 0)
        {
            average = 0f;
        }
        else
        {
            average = sum / employeeListAfter.size();
        }

        model.addAttribute("empl",employeeListAfter);
        model.addAttribute("average", average);
        model.addAttribute("sum", sum);

        return "sqlSubmit";
    }

    @GetMapping("/sqlSubmit")
    public String getListSql(Model model)
    {
        Iterable<Employee> employeeListBefore = userRepository.findAll();
        List<Employee> employeeListAfter = new ArrayList();
        float sum = 0f;
        float average;

        for (Employee e : employeeListBefore)
        {
            employeeListAfter.add(e);
            sum += e.getSalary();
        }

        if (employeeListAfter.size() == 0)
        {
            average = 0f;
        }
        else
        {
            average = sum / employeeListAfter.size();
        }

        model.addAttribute("empl",employeeListAfter);
        model.addAttribute("average", average);
        model.addAttribute("sum", sum);

        return "sqlSubmit";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam(name="employeeId") Integer employeeId)
    {
        Iterable<Employee> list = userRepository.findAll();

        for (Employee e : list)
        {
            if (e.getId() == employeeId)
            {
                userRepository.delete(e);
            }
        }

        return "redirect:sqlSubmit";
    }

    @GetMapping(value = "/edit")
    public String edit(@RequestParam(name="employeeId") Integer employeeId, Model model)
    {
        Iterable<Employee> list = userRepository.findAll();
        Employee editEmp = new Employee();

        for (Employee e : list)
        {
            if (e.getId() == employeeId)
            {
                editEmp = e;
            }
        }

        model.addAttribute("editEmp" , editEmp);

        return "edit";
    }

    @PostMapping(value = "/edit")
    public String edited(@RequestParam(name="id") Integer id,
                         @RequestParam(name="name") String name,
                         @RequestParam(name="salary") float salary)
    {
        Iterable<Employee> currentList = userRepository.findAll();
        Employee editEmp = new Employee();

        for (Employee e : currentList)
        {
            if (e.getId() == id)
            {
                editEmp = e;
            }
        }

        editEmp.setName(name);
        editEmp.setSalary(salary);
        userRepository.save(editEmp);

        return "redirect:sqlSubmit";
    }

    @PostMapping("/Submit")
    public String getAllEmployee(Model model,
                                 @RequestParam("nameOne") String nameFormOne,
                                 @RequestParam("salaryOne") float salaryFormOne,
                                 @RequestParam("nameTwo") String nameFormTwo,
                                 @RequestParam("salaryTwo") float salaryFormTwo)
    {
        Calculations cal = new Calculations();
        Employee emp = new Employee(nameFormOne,salaryFormOne);
        Employee empl = new Employee(nameFormTwo,salaryFormTwo);

        List<Employee> employeeList= new ArrayList();
        employeeList.add(emp);
        employeeList.add(empl);

        float mean = cal.mean(salaryFormOne, salaryFormTwo);

        String compare = cal.compare(nameFormOne, salaryFormOne, nameFormTwo, salaryFormTwo);

        model.addAttribute("empl",employeeList);
        model.addAttribute("mean",mean);
        model.addAttribute("compare", compare);

        return "Submit";
    }
}