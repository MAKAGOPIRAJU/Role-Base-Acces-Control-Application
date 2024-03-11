package BankApplication.RBAC.Config;

import BankApplication.RBAC.Model.CustomerRegistration;
import BankApplication.RBAC.Repository.RegisterationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// This class is like Authenication page
// This is the class where we are develop the business logic how to autenication will allowed.


@Service
public class RBACUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisterationRepository registerationRepository;

    public static CustomerRegistration customerRegistrationForOuterClasses;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<CustomerRegistration> optionalUser = registerationRepository.findCustomerRegistrationByusername(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("user is not found in our data base with the email" + username);
        }

        CustomerRegistration customerRegistration = optionalUser.get();

        customerRegistrationForOuterClasses = customerRegistration;

        List<GrantedAuthority> authorites = new ArrayList<>();

        authorites.add(new SimpleGrantedAuthority(customerRegistration.getRole()));

        return new User(customerRegistration.getUsername(), customerRegistration.getPassword(),authorites);
    }

    public CustomerRegistration getCustomerRegistrationForOuterClasses() {
        return customerRegistrationForOuterClasses;
    }

}
