package BankApplication.RBAC.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String name;

    private String email; // this will be the username for login

    private String phoneNumber;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    private CustomerRegistration customerRegistration; // child


    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Role role; // parent

}