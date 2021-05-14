package ch.dulce;

import io.quarkus.funqy.Funq;

public class GreetingFunction {

    @Funq
    public String emails(Email email) {
        return "hello from: " + email.getFrom();
    }
}
