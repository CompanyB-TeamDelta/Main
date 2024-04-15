package ua.edu.ukma.systemsdesign.dataprocessor.consumers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Controller;
import ua.edu.ukma.systemsdesign.dataprocessor.models.NewFilesRequest;
import ua.edu.ukma.systemsdesign.dataprocessor.services.FtpService;
import ua.edu.ukma.systemsdesign.dataprocessor.services.PostUpdatesService;

@Controller
@RequiredArgsConstructor
@EnableSqs
@Slf4j
public class NewFilesConsumer {

    private final PostUpdatesService postUpdatesService;
    private final FtpService ftpService;
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    private boolean logged = false;

    @SqsListener(
            value = "${aws.sqs.new-files-queue}",
            deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeRequest( final String input) {
        try {
            var files = objectMapper.readValue(input, NewFilesRequest.class);
            postUpdatesService.savePostUpdates(files.getFetched_at(),files.getFiles());
        }
        catch (Exception e){
            log.error("error: " + e.getMessage());
        }
    }
}
