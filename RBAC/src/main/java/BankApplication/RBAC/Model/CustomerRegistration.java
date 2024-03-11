package BankApplication.RBAC.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customerregistration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String password;

    private String username;

    private String role = "user";

    @OneToOne(mappedBy = "customerRegistration") // parent
    @JsonIgnore
    private Customer customer;

}
