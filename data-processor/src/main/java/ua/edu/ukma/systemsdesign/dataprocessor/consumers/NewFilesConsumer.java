package ua.edu.ukma.systemsdesign.dataprocessor.consumers;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import ua.edu.ukma.systemsdesign.dataprocessor.models.NewFilesRequest;
import ua.edu.ukma.systemsdesign.dataprocessor.services.PostUpdatesService;

@Controller
@RequiredArgsConstructor
@EnableSqs
@Slf4j
public class NewFilesConsumer {

    private final PostUpdatesService postUpdatesService;
    private boolean logged = false;

    @SqsListener(
            value = "${aws.sqs.new-files-queue}",
            deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeRequest( final String input
            /**
             * create converter
             */
           // @Payload NewFilesRequest request
    ) {
        if(!logged) {
            log.info("[consumeRequest]: " + input);
            logged = true;
        }
    }
}
