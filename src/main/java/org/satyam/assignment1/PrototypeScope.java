package org.satyam.assignment1;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
    prototype scope:
    in prototype scope bean every time bean is called spring create new bean of data type.
    using prototype scope we can avoid race condition.
 */
@Configuration
@ComponentScan(basePackages = "org.satyam.assignment1")
public class PrototypeScope {
    public static int number = 10;
    public static void main(String[] args) {
        /*
            here first bean is create with data as 10
            after create first bean we change the data value as 2
            and then create second bean. And second bean contain value as 2.
         */
        var context = new AnnotationConfigApplicationContext(PrototypeScope.class);
        var manageData = context.getBean(ManageData.class);
        System.out.println(manageData.getData());
        manageData.transaction(2);
        var manageData2 = context.getBean(ManageData.class);
        System.out.println(manageData2.getData());

    }
}
/*

    prototype scope bean inside singleton scope bean.

    when we autowired prototype scope bean inside the singleton scope bean then each time when we try to access
    method or constructor it will not create bean of prototype scope bean.

 */
@Component("manageData")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ManageData {
    private int data;
    @PostConstruct
    public void fun() {
        this.data = PrototypeScope.number;
        System.out.println("manage data instance created");
    }
    public int getData(){
        return data;
    }
    public void setData(int data){
        this.data = data;
    }
    public void transaction(int data){
        this.data = data;
        PrototypeScope.number = data;
    }
}