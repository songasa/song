package com.zut.service_parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author song
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zut"})
//@MapperScan("com.zut.service_parking.mapper")
public class ParkingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class,args);
    }

}
