package BankApplication.RBAC.Service;

import BankApplication.RBAC.Enum.RoleType;
import BankApplication.RBAC.Excetpions.UserAlreadyRegisterd;
import BankApplication.RBAC.Model.Customer;
import BankApplication.RBAC.Model.CustomerRegistration;
import BankApplication.RBAC.Model.Role;
import BankApplication.RBAC.Repository.CustomerRepository;
import BankApplication.RBAC.Repository.RegisterationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerRegistrationService {

    @Autowired
    private RegisterationRepository registerationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    public String registerNewCustomer(String userName , String password) throws Exception{

        Optional<CustomerRegistration> optionalCustomerRegistration = registerationRepository.findCustomerRegistrationByusername(userName);

        if(optionalCustomerRegistration.isEmpty()) {

            CustomerRegistration customerRegistration = new CustomerRegistration();

            customerRegistration.setUsername(userName);

            String pwd = passwordEncoder.encode(password);

            customerRegistration.setPassword(pwd);

            customerRegistration.setRole("user");

            registerationRepository.save(customerRegistration);

            return "Registered successfully";
        }

        throw new UserAlreadyRegisterd( userName + " this email is already used please do login with your credentials");

    }

    public List<CustomerRegistration> allCustomers() {

        return registerationRepository.findAll();
    }

    public String addNewCustomer(Customer customer) {

        // Assume the customer is able to login

        Role role = new Role();

        role.setRoleType(RoleType.user);

        customer.setRole(role);

        // find my registration details to attach with my login credentials

        String userName = customer.getEmail();

       CustomerRegistration customerRegistration = registerationRepository.findCustomerRegistrationByusername(userName).get();

       customer.setCustomerRegistration(customerRegistration);

       customerRepository.save(customer);

       customerRegistration.setCustomer(customer);

       // customer Registration should also  update the role now beacuse the customer detials are added now

        String roleType = role.getRoleType().toString();

        customerRegistration.setRole(roleType);

        role.setCustomer(customer);

        // afther this i need to add this into repo

        registerationRepository.save(customerRegistration);

       return "customer added successfully";
    }

    public String updateDetails(CustomerRegistration customerRegistration,String username) {

        // find the customer

        Customer customer = new Customer();

        customer.setName(username);

        customerRepository.save(customer);

        return "customer details are updated";
    }

    public String deleteDetails(CustomerRegistration customerRegistration) {

        Customer customer = customerRegistration.getCustomer();

        registerationRepository.delete(customerRegistration);
        customerRepository.delete(customer);

        return "delete successfully";
    }
}
