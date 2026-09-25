package DTO;


public class SleepQualit {

    public Duration calculateSleep(LocalTime hourSleep, LocalTime hourWake){
        Duration duration;
        if(hourWake.isBefore(hourSleep)){
            hourWake = hourSleep.plusHours(24);
        }
        return Duration.between(hourSleep, hourWake);
    }

    public Qualite calculateQualite(Duration duration){
        long hours = duration.toHours();

        if(hours >=0 && hours <=3)
            return Qualite.RUIM;
        if(hours >=4 && hours <=7)
            return Qualite.MEDIO;
        if(hours >8 && hours <=10)
            return Qualite.BOM;

            return Qualite.MARAVILHOSO;
    }

    public enum Qualite{
        RUIM, MEDIO, BOM, MARAVILHOSO;
    }
}
