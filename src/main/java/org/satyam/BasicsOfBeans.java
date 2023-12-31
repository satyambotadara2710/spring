package org.satyam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *  what is spring and features of spring?
 */

/*
    spring core:

    spring core hase some different features like

    ioc ( inversion of control) -> In ioc we don't have control of the flow of the program it manages by spring itself.
    di (dependency injection) -> DI is the implementation of ioc. IOC is achieved with help of DI

*/
@ComponentScan("org.satyam")
public class BasicsOfBeans {

    static final Person person;

    static {
        person= new AnnotationConfigApplicationContext(ComponentScanExampleConfig.class)
                .getBean(Person.class);
    }
    public static void main(String[] args) {

        // retrieves beans from application context
        // beans are created using @Beans,so we need to retrieve using AnnotationConfigApplicationContext
        // pass the config class file to retrieves beans from it.
//        var context = new AnnotationConfigApplicationContext(Config.class);

        // get bean using context.getBean("bean_name",bean_type.class);
//        var vehical = context.getBean("audi",Vehical.class);

        /*
         generate NoUniqueBeanDefinitionException bcz we try to fetch bean using data type of bean
         but inside spring application context there aer multiple beans of same data type

            var noUniqueBeanFoundException = context.getBean(Vehical.class);
        */

        /*
            we use @Primary annotation, so it will not generate NoUniqueBeanDefinitionException
            var primaryAnnotationUseCase = context.getBean(Vehical.class);
         */
//        var primaryAnnotationUseCase = context.getBean(Vehical.class);
//
//        System.out.println(vehical.getName());
//        System.out.println("@Primary annotation use case:"+primaryAnnotationUseCase.getName());


        /*
           use of bean created by  @Component
         */
//        System.out.println("bean create using @Component ->> ");
//        var componentAnnotationContext = new AnnotationConfigApplicationContext(ComponentScanExampleConfig.class);
//        var vehicle = componentAnnotationContext.getBean(Vehical.class);
//
//        vehicle.printHello();
//        componentAnnotationContext.close();

        /*
                create beans programmatically
         */



//        var context = new AnnotationConfigApplicationContext(ComponentScanExampleConfig.class);
//        var v = context.getBean("getVehicle",Vehical.class);
//        var v2 = context.getBean("componentBean",Vehical.class);
//        v2.printHello();
//        v.printHello();

        /*
          below program will generate NoUniqueBeanDefinitionException bcz ComponentScanExampleConfig have @ComponentScan
          it will create bean of Vehicale using @Component of vehical
          and ComponentScanExampleConfig also have @Bean so it will have 2 beans of same data type
         */
        /*
        var contextProgram = new AnnotationConfigApplicationContext(Config.class);

        contextProgram.registerBean("audi",Vehical.class, () -> {
            var vehical = new Vehical();
            vehical.setName("audi");
            return vehical;
        });

        var name = contextProgram.getBean("audi",Vehical.class).getName();
        System.out.println(name);
        */


        /**
         *  autowired beans
         */

//        var context = new AnnotationConfigApplicationContext(ComponentScanExampleConfig.class);
//        var person = context.getBean(Person.class);
//        person.printVehicalName();

        person.printVehicalName();


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
//    @Bean(name = "tesla")
//    @Primary
//    Vehical getVehicle(){
//        var vehical = new Vehical();
//        vehical.setName("tesla");
//        return vehical;
//    }
    /*
        NoUniqueBeanDefinitionException is happened when try to get bean without bean name directly with bean data type
        ex : context.getBeans(Vehicle.class);

        if there is two or more method that return same type of object then there are more then one object inside
        spring application context.

        it will generate confusion between two same type of object, to overcome from this problem we need to give name to
        beans so application context won't be confused on same data type beans.

        ex : context.getBeans("bean_name",Vehicle.class);
     */
    @Bean(name = "audi")
    Vehical getVehicle2(){
        var vehical = new Vehical();
        vehical.setName("audi");
        return  vehical;
    }
    /**
     * use of  @Primary anotation
     */

    /*
        When we have multiple beans of same data type and if we try to get beans using data type of beans it generate
        NoUniqueBeanDefinitionException.
        In above example we see one way to solve this issue is that we can provide bean name to each bean to uniquely
        identify beans.
        But we have another approach we can  use @Primary annotation.
        when we define bean using @Primary annotation spring make bean default bean.
        Therefor if inside spring application context there are multiple beans of same data type, and we try to get bean
        using only data type of the bean spring not generate NoUniqueBeanDefinitionException instead of it will give
        default bean from list of same data type bean.

       ex : if i try to get bean of volvo using context.getBeans(Vehicle.class);

       here I am not passing any bean name. then it should generate NoUniqueBeanDefinitionException because there are
       3 beans of vehicle.
       but if I make volvo bean as @Primary then it will not generate any error it will return volvo bean as it is
       default bean for vehicle data type
     */
//    @Bean(name = "volvo")
//    @Primary
//    Vehical getVehicle3() {
//        var vehical = new Vehical();
//        vehical.setName("volvo");
//        return vehical;
//    }
}

/*
    @ComponentScan annotation take base package name.
    this annotation will scan all the class inside the base package and if class has @Component or any
    stereotype annotation it will create beans of it.
 */
@Configuration
@ComponentScan(basePackages = "org.satyam")
class ComponentScanExampleConfig {
//    @Bean
//    public Vehical getVehicle() {
//        var v = new Vehical();
//        v.setName("audi");
//        return v;
//    }
}

/**
 * @Component annotation
 */
/*
    there are two ways to create beans in spring
    1. using @Bean
    2. Using Stereotype annotations like @Component, @Service, @Repository,@Controller
 */
/*
  Stereotype annotation : all mention below Stereotype annotations are used to create beans of class
  these beans are managed by spring application.
  we use any of the annotations to create bean of the class.

            @Component (generic) it will use with any class.
            @Service -> mostly used for service class where business logic are implemented.
            @Repository -> used with the class that manage databases.
            @Controller -> used with the class that act as the controller.

  We can use any of the annotation with any class but for readability we use annotations according to the
  role of the class in application.
*/
/*
    @Component annotation is generic stereotype annotation.
    @Component annotation create bean of class.
    We need to add @ComponentScan annotation on top of configuration class to scan bean class.
 */
// simple java pojo class
//@Component("componentBean")
class Vehical {

    private String name;

    public String getName() {
        return name;
    }
    public void printHello() {
        System.out.println("hello world");
    }
    public void setName(String name) {
        this.name = name;
    }
    /*
    @PostConstruct -> execute this method when bean is created. Mostly the initialization logic is implement in this block

     */
//    @PostConstruct
//    public void initialize(){
//        this.name = "audi";
//    }
    /*
        @PreDestroy -> execute this method when spring destroy the bean. Mostly use to release resources like db connection
        files etc.
     */
//    @PreDestroy
//    void Destroy(){
//        System.out.println("destroy vehicle bean");
//    }
}


/**
        @Autowired annotation
 */

@Component("person")
class Person {
    // property base annotation
    // in property base annotation we simply mention @Autowired on top of the class property if spring find required dependency
    // it will inject object of vehicle class.
//    @Autowired(required = false)
//    @Autowired
    @Autowired
    private Vehical vehical;

    /* construction base auto-wire
        in construction base autowired there is no need to mention @Autowired on top of the constructor method
        we can mention it or not it works fine in both scenario.
        when we autowired bean spring will search for required bean in spring context if  spring find any
        relevant bean it will inject that bean in constructor.
     */

// @Autowired (optional)
//    @Autowired
//    Person(@Qualifier("audi") Vehical audi){
//        this.vehical = audi;
//    }

    /* setter method auto-wire
        in setter method autowired there is compulsory to mention @Autowired on top of the setter method.
        when we autowired bean spring will search for required bean in spring context if  spring find any
        relevant bean it will inject that bean in setter method parameter.
     */
//    @Autowired (*required)
//    public void setPerson(Vehical vehical){
//        this.vehical= vehical;
//    }

    public void printVehicalName() {
        if(vehical!=null)
        System.out.println("vehical name ->>"+this.vehical.getName());
        else
            System.out.println("vehical name ->> default");
    }

}

/**
 *  Note : How @Autowired work?
 */

/*
   When we mention @Autowired on top of the constructor or parameter spring will look for the bean according to the
   data type of the parameter where we mention @Autowired, And if spring find any bean of the data type it will inject
   the bean.
 */

/**
 * NOTE:
 * what if in ioc container there are multiple beans of same data type then how spring know which bean need to inject
 *  in @Autowired ?
 */

/*
    Spring try to perform some steps to identify correct bean from multiple beans of same data type:

    Consider scenario where there are three beans of Vehicle data type : vehicle1,vehicle2,vehicle3.

    Step 1: Spring try to match the parameter name and bean name if it matches then spring will inject matched bean.
             ex :
                @Autowired
                Vehicle vehicle1; it will inject vehicle1 bean

                @Autowired
                Vehicle vehicle2; it will inject vehicle2 bean

    Step 2: if no any bean name match with  parameter name then spring look for which bean is mention with @Primary
            and inject primary bean into parameter

    step 3: if there is no any @Primary bean then spring will look for @Qualifier() annotation in @Autowired annotation.
        ex:
        @Autowired
        public Person(@Qualifier("vehicle2") Vehicle vehicle){
            this.vehicle = vehicle;
        }
        it will inject bean of vehicle2.

 */