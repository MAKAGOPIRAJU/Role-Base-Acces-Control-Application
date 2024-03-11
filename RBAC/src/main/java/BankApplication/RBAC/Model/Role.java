package BankApplication.RBAC.Model;

import BankApplication.RBAC.Enum.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private RoleType roleType;


    @OneToOne
    @JoinColumn
    @JsonIgnore
    private Customer customer; // this class is child



}
