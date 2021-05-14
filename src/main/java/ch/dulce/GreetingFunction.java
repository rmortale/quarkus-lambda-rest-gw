package ch.dulce;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.funqy.Funq;
import software.amazon.awssdk.services.sns.SnsClient;

public class GreetingFunction {

    @Inject
    SnsClient sns;
    
    @ConfigProperty(name = "topicArn")
    String topicArn;
    
    @Funq
    public String emails(Email email) {
        return "hello from: " + email.getFrom();
    }
}
