package org.satyam;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  what is spring and features of spring?
 */

/*
    spring core:

    spring core hase some different features like

    ioc ( inversion of control) -> In ioc we don't have control of the flow of the program it manages by spring itself.
    di (dependency injection) -> DI is the implementation of ioc. IOC is achieved with help of DI

*/

public class BasicsOfBeans {
    public static void main(String[] args) {
        // retrieves beans from application context
        // beans are created using @Beans,so we need to retrieve using AnnotationConfigApplicationContext
        // pass the config class file to retrieves beans from it.
        var context = new AnnotationConfigApplicationContext(Config.class);

        // get bean using context.getBean("bean_name",bean_type.class);
        var vehical = context.getBean(Vehical.class);

        System.out.println(vehical.getName());
    }
}

/**
 *  what is beans and how to create beans ?
 */

/*
    Beans are normal java pojo class which is managed by spring.
    Beans are simple java class that are managed by spring.
    In normal class we need to create object of the class and manage by our-self
    but in bean no need to manage the object spring automatically manage the lifecycle of class object.
 */

/*
    Add annotation @Configuration in class. This configuration allow us to create bean.
    inside class that beans are register in application context
 */

@Configuration
class Config {

    // getVehicle method create bean of the Vehicle object that object's life cycle is managed by spring
    // this bean is register inside spring application context that can be retrieved using application Context
    // bean is register with name in application context
    // by default the method name is consider as the bean name in spring
    // we can give custom bean name using  @Bean(name = "custom_bean_name")
    @Bean(name = "tesla")
    Vehical getVehicle(){
        var vehical = new Vehical();
        vehical.setName("tesla");
        return vehical;
    }
    @Bean(name = "audi")
    Vehical getVehicle2(){
        var vehical = new Vehical();
        vehical.setName("audi");
        return  vehical;
    }

}

// simple java pojo class
class Vehical {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}