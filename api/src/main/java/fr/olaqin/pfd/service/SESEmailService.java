package fr.olaqin.pfd.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SESEmailService {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Async
    public void sendEmail(String templateName, String fromAddress, List<String> toAddresses, String dataTemplate) {

        SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest()
                .withDestination(new Destination(toAddresses))
                .withTemplate(templateName)
                .withTemplateData(dataTemplate)
                .withSource(fromAddress);
        amazonSimpleEmailService.sendTemplatedEmail(templatedEmailRequest);

    }

}
