package org.satyam.aop;

/*
    what is aop?

    aop means aspect oriented programming.

    aop can be implemented using spring.

    AOP provides the way to dynamically add the cross-cutting concerns before after or around the actual method
    logic.

    ex: if we want to execute some logic like logging,security before , after or around the method logic implement we can
    achieve this by aop.

    terms:

    Aspect : (what) aspect is basic foundational program in aop. it defines what logic need to implement.

    advice : (when) when aspect need to execute. for example before , after , around the method execution.

    pointcut : (which) which method inside app that framework need to intercept and execute the given aspect.

    joinPoint: which defines the event that triggers the execution of an aspect. in spring only support method call.
                when program try to execute particular method our aspect logic will be implemented.

    Target object : Is the bean that declares the method/pointcut which is intercepted by an aspect.

    example

        developer want some logic (Aspect) to be executed before(Advice) each execution(joinPoint) of
        method playMusic() (point cut) present inside the bean VehicleServices (target object).
 */
public class Aop {
    public static void main(String[] args) {

    }
}


