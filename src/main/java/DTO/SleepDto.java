package DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class SleepDto{

    private LocalDateTime hourSleep;
    private LocalDateTime hourWake;

    public SleepDto(){}

    public SleepDto(LocalDateTime hourSleep, LocalDateTime hourWake){
        this.hourSleep = hourSleep;
        this.hourWake = hourWake;
    }


}
