package sda.sping.test1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String username;

    private String password;

    private String role;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String nationality;

    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(unique = true)
    private Account account;

}
