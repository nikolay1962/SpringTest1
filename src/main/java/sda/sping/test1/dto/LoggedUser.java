package sda.sping.test1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUser {

    @Id
    private int id;

    private int userid;

    private int accountid;

    private String status;

}
