package com.banking.springboot.controller;

import java.util.Date;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.banking.springboot.auth.AuthenticatedUserService;
import com.banking.springboot.auth.RegistrationDto;
import com.banking.springboot.auth.User;
import com.banking.springboot.auth.UserRepository;
import com.banking.springboot.auth.UserRole;
import com.banking.springboot.auth.UserRoleRepository;
import com.banking.springboot.model.Employee;
import com.banking.springboot.repository.EmployeeRepository;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUserService authService;

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public ModelAndView login() {

        ModelAndView mav = new ModelAndView();

        if (!authService.isAuthenticated()) {
            mav.setViewName("login");
            return mav;
        }

        mav.setViewName("redirect:/home");
        return mav;

    }

    @GetMapping("/register")
    public String register(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registrationDto", registrationDto);
        return "register";
    }

    @PostMapping("/register")
    public String addUser(Model model, @Valid @ModelAttribute("registrationDto") RegistrationDto registrationDto,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            User user = userRepository.findUserById(registrationDto.getId());
            if (user == null) {
                user = new User();
            }

            String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

            user.setFirstName(registrationDto.getFirstName());
            user.setLastName(registrationDto.getLastName());
            user.setEmail(registrationDto.getEmail());
            user.setPassword(encodedPassword);
            user.setAddress(registrationDto.getAddress());
            user.setCity(registrationDto.getCity());
            user.setState(registrationDto.getState());
            user.setZip(registrationDto.getZip());
            user.setPhone(registrationDto.getPhone());
            user.setCreateDate(new Date());
            user.setAvatar(registrationDto.getAvatar());

            userRepository.save(user);

            UserRole ur = new UserRole();
            ur.setRoleName("USER");
            ur.setUserId(user.getId());
            userRoleRepository.save(ur);

            Employee existingEmployee = employeeRepository.findEmployeeByEmail(user.getEmail());

            if (existingEmployee != null) {
                existingEmployee.setUser(user);
                employeeRepository.save(existingEmployee);
            } else {
                Employee newEmployee = new Employee();
                newEmployee.setFirstName(user.getFirstName());
                newEmployee.setLastName(user.getLastName());
                newEmployee.setEmail(user.getEmail());
                newEmployee.setUser(user);
                employeeRepository.save(newEmployee);
            }
            return "redirect:/register?success";
        } else {
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("registrationDto", registrationDto);
            return "register";
        }
    }

}
