package ro.itschool.demospringdata.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Clock {

    @Scheduled(fixedRate = 1000) //1s = 1000ms
    public void tic(){
        System.out.println("Tic");
    }

}
