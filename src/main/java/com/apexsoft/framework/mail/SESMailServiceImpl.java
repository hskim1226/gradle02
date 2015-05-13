package com.apexsoft.framework.mail;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by hanmomhanda on 15. 5. 11.
 */
@Service
public class SESMailServiceImpl implements SESMailService{

    private static final Logger logger = LoggerFactory.getLogger(SESMailServiceImpl.class);

    private Region sesRegion;

    @Value("#{app['ses.smtp.mailer']}")
    private String FROM;
//    private String TO = "hanmomhanda@naver.com"; // Replace with a "To" address. If your account is still in the
//    // sandbox, this address must be verified.
//    private String BODY = "This email was sent through Amazon SES by using the AWS SDK for Java.";
//    private String SUBJECT = "Amazon SES 테스트 (AWS SDK for Java)\n<img src='http://tenasia.hankyung.com/webwp_kr/wp-content/uploads/2015/01/2015011517284126778.jpg'/>";

    public SESMailServiceImpl(String sesRegion) {
        this.sesRegion = Region.getRegion(Regions.fromName(sesRegion));
    }

    @Override
    public void sendMail(Mail mail) throws Exception {

        try {
            // Construct an object to contain the recipient address.
            Destination destination = new Destination().withToAddresses(mail.getTo());
            if (mail.getBcc() != null)
                destination.withBccAddresses(mail.getBcc());
            if (mail.getCc() != null)
                destination.withCcAddresses(mail.getCc());

            // Create the subject and body of the message.
            Content subject = new Content().withData(mail.getSubject());
            Content textBody = new Content().withData(mail.getContents());
            Body body = new Body().withText(textBody);

            // Create a message with the specified subject and body.
            Message message = new Message().withSubject(subject).withBody(body);

            // Assemble the email.
            SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);


//            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            // Instantiate an Amazon SES client, which will make the service call. The service call requires your AWS credentials.
            // Because we're not providing an argument when instantiating the client, the SDK will attempt to find your AWS credentials
            // using the default credential provider chain. The first place the chain looks for the credentials is in environment variables
            // AWS_ACCESS_KEY_ID and AWS_SECRET_KEY.
            // For more information, see http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/credentials.html
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient();

            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox
            // status, sending limits, and Amazon SES identity-related settings are specific to a given AWS
            // region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
            // the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1
            // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
//            Region REGION = Region.getRegion(Regions.US_WEST_2);
//            client.setRegion(REGION);
            client.setRegion(this.sesRegion);

            // Send the email.
            client.sendEmail(request);
        } catch (Exception e) {
            logger.error("SES Mail sending error : " + e.getMessage());
            // TODO DB에 메일 발송 오류 테이블을 두고, 오류 시 그 테이블에 저장 하여 나중에 오류난 대상에게만 재발송
        }
    }
}
