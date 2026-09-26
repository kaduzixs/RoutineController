package Service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service

public class SleepService {
    public Duration calculateSleep(LocalDateTime hourSleep, LocalDateTime hourWake){
        if (hourSleep.isAfter(hourWake)){
            LocalDateTime wake = hourWake.plusDays(1);
            return Duration.between(hourSleep, wake);
        }
        return Duration.between(hourSleep, hourWake);

    }

    public Qualite calculateQualite(Duration duration){
        long hours = duration.toHours();

        if(hours >=0 && hours <=3)
            return Qualite.RUIM;
        if(hours >3 && hours <=7)
            return Qualite.MEDIO;
        if(hours >=8 && hours <=10)
            return Qualite.BOM;

        return Qualite.MARAVILHOSO;
    }

    public enum Qualite{
        RUIM, MEDIO, BOM, MARAVILHOSO;

    }





}
