package ch.dulce;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import io.quarkus.funqy.Funq;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishResponse;

public class GreetingFunction {

    private static final Logger LOGGER = Logger.getLogger(GreetingFunction.class);
    private static final ObjectWriter EMAIL_WRITER = new ObjectMapper().writerFor(Email.class);
    
    @Inject
    SnsClient sns;
    
    @ConfigProperty(name = "topicArn")
    String topicArn;
    
    @Funq
    public String emails(Email email) throws JsonProcessingException {
        String message = EMAIL_WRITER.writeValueAsString(email);
        PublishResponse response = sns.publish(p -> p.topicArn(topicArn).message(message));
        LOGGER.infov("Received email from {0}.", email.getFrom());
        return "hello from: " + email.getFrom();
    }
}
