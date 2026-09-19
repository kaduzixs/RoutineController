package DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalTime;
@Getter
@Setter
public class SleepDto{

    private LocalTime hourSleep;
    private LocalTime hourWake;

    public SleepDto(){}

    public SleepDto(LocalTime hourSleep, LocalTime hourWake){
        this.hourSleep = hourSleep;
        this.hourWake = hourWake;
    }


}
