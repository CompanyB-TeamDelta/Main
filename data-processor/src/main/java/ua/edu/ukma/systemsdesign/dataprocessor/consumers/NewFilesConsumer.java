package ua.edu.ukma.systemsdesign.dataprocessor.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import ua.edu.ukma.systemsdesign.dataprocessor.models.NewFilesRequest;
import ua.edu.ukma.systemsdesign.dataprocessor.services.PostUpdatesService;

@Controller
public class NewFilesConsumer {

    private final PostUpdatesService postUpdatesService;

    @Autowired
    public NewFilesConsumer(PostUpdatesService postUpdatesService) {
        this.postUpdatesService = postUpdatesService;
    }

    @SqsListener(
            value = "${aws.sqs.new-files-queue}",
            deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeRequest(@Payload NewFilesRequest request) {

        postUpdatesService.savePostUpdates(request.getTimestamp(), request.getNewFiles());
    }
}
