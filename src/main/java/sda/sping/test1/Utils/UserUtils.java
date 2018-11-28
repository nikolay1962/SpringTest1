package sda.sping.test1.Utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Service
public class UserUtils {

    public int age(Date DBbirthday) {
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(DBbirthday.getTime());
//        System.out.println("DOB:" + birthday +
//                ", year:" + birthDay.get(Calendar.YEAR)
//        + ", month:" + birthDay.get(Calendar.MONTH)
//        + ", day:" + birthDay.get(Calendar.DAY_OF_MONTH) ) ;

        LocalDate dob;
        dob = LocalDate.of( birthDay.get(Calendar.YEAR), birthDay.get(Calendar.MONTH) + 1, birthDay.get(Calendar.DAY_OF_MONTH));
        LocalDate today = LocalDate.now();
        return Period.between(dob, today).getYears();
    }
}
