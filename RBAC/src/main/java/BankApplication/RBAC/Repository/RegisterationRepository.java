package BankApplication.RBAC.Repository;

import BankApplication.RBAC.Model.CustomerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterationRepository extends JpaRepository<CustomerRegistration, Integer> {

    Optional<CustomerRegistration> findCustomerRegistrationByusername(String username);

}
