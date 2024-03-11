package BankApplication.RBAC.Controller;


import BankApplication.RBAC.Config.RBACUserDetailsService;
import BankApplication.RBAC.Model.Customer;
import BankApplication.RBAC.Model.CustomerRegistration;
import BankApplication.RBAC.Service.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    // by defualt with the csrf configuration post , put api's are disables with out authernication
    // to make them allow with out having any authentications we can use the follwoing command
    // http.csrf.disable()

    @Autowired
    private RBACUserDetailsService rbacUserDetailsService;


    @PostMapping("/register")
    public ResponseEntity registerNewCustomer(@RequestParam("userName")String userName,
                                      @RequestParam("password")String password) throws Exception{
        try{

            String res = customerRegistrationService.registerNewCustomer(userName,password);

            return new ResponseEntity(res, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/get")
    public List<CustomerRegistration> customers() {

        return customerRegistrationService.allCustomers();
    }

    @PostMapping("/adding")
    // if I want to add new user first i said need to check his login details so it is  authenticated api
    // both customer and admin will have acess to this api
    public ResponseEntity addNewCustomer(@RequestBody Customer customer) {
        String res = customerRegistrationService.addNewCustomer(customer);

        return new ResponseEntity(res , HttpStatus.OK);
    }

    @GetMapping("/getCustomerDetails")
    // For getting customer detials the details first should be authinicated
    // the both user and admin roles are allowed for this
    // and here we are directly get customer Details basing on the username and password given during log in time
    public ResponseEntity customerDetails() {

        CustomerRegistration customerRegistration = rbacUserDetailsService.getCustomerRegistrationForOuterClasses();

        return new ResponseEntity(customerRegistration.getCustomer(),HttpStatus.OK);
    }

    @PutMapping("/updateDetails")
    // updating details access will have both the user and admin

    public ResponseEntity updateDetails(@RequestParam("userName") String userName) {

        CustomerRegistration customerRegistration = rbacUserDetailsService.getCustomerRegistrationForOuterClasses();

        String res = customerRegistrationService.updateDetails(customerRegistration,userName);

        return new ResponseEntity(res,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteDetails() {

        CustomerRegistration customerRegistration = rbacUserDetailsService.getCustomerRegistrationForOuterClasses();

        String res = customerRegistrationService.deleteDetails(customerRegistration);

        return new ResponseEntity(res,HttpStatus.OK);
    }


}
