package org.satyam.assignment1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ObjectInputFilter;

public class Assigment1 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var person  = context.getBean(Person.class);
        person.funInCar();
    }
}

// tyer hase two implementation 1. bridgestonetyers 2. michelintyers
// music system hase two implementation 1. sony 2. bose

class Tyer {
    private String name;
    public Tyer(String name){
        this.name = name;
    }
    public void move() {
        System.out.println("tyer is moving ->>"+name);
    }
}

class MusicSystem {
    private String name;
    public MusicSystem(String name) {
        this.name= name;
    }
    public void makeSound(){
        System.out.println("car is making sound from ->>"+name);
    }
}

// configuration tell spring to register all the bean mention inside class config
@Configuration
@ComponentScan(basePackages = "org.satyam.assignment1")
class Config {

    @Bean(name = "bridgeStone")
    public Tyer bridgeStoneTyer() {
        return new Tyer("bridgeStoneTyer");
    }

    @Bean (name = "michelin")
    public Tyer michelinTyer() {
        return new Tyer("michelinTyer");
    }

    @Bean(name = "boseMusic")
    public MusicSystem boseMusicSystem() {
        return new MusicSystem("boseMusicSystem");
    }

    @Bean(name = "sonyMusic")
    public MusicSystem sonyMusicSystem() {
        return new MusicSystem("sonyMusicSystem");
    }

}

@Service
class VehicleService {
    // depend on speakers and typers bean
    private final Tyer tyer;
    private final MusicSystem musicSystem;

    @Autowired
    public VehicleService(@Qualifier("bridgeStone") Tyer tyer,@Qualifier("boseMusic") MusicSystem musicSystem) {
        this.musicSystem = musicSystem;
        this.tyer = tyer;
    }

    public void makeSound(){
        musicSystem.makeSound();
    }
    public void runCar() {
        tyer.move();
    }
}
@Component
class Vehicle {
    // depend on VehicleService bean. to play music and move vehicle
    private final VehicleService vehicleService;
    private String name;

    @Autowired
    public Vehicle(VehicleService vehicleService ) {
        this.vehicleService = vehicleService;
    }

    public void startCar() {
        vehicleService.makeSound();
        vehicleService.runCar();
    }
}
@Component
class Person {

    // depends on vehicle bean
    private final Vehicle vehicle;
    public Person(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void funInCar(){
        vehicle.startCar();
    }
}