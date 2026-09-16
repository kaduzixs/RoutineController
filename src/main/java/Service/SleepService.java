package Service;

import java.time.Duration;
import java.time.LocalTime;

public class SleepService {
    public Duration calculateSleep(LocalTime hourSleep, LocalTime hourWake){
        Duration duration;
        if(hourWake.isBefore(hourSleep)){
            hourWake = hourSleep.plusHours(24);
        }
        return Duration.between(hourSleep, hourWake);
    }

    public Qualite calculateQualite(Duration duration){
        long hours = duration.toHours();

        if(hours <=1 && hours >=3){
            return Qualite.RUIM;
        }
        else if(hours >5 && hours <=7){
            return Qualite.MEDIO;
        }
        else if(hours >7 && hours <=10){
            return Qualite.BOM;
        }
        else
            return Qualite.MARAVILHOSO;
    }

    public String durationSleep(Duration duration){
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();

        return "Horas: "+hours+ "Minutes: "+minutes;
    }

    public enum Qualite{
        RUIM, MEDIO, BOM, MARAVILHOSO;
    }



}
