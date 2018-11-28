package sda.sping.test1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllowedRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String role;
}
