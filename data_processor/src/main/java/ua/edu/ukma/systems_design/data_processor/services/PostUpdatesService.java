package ua.edu.ukma.systems_design.data_processor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.systems_design.data_processor.repositories.PostUpdatesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostUpdatesService {

    private final FtpService ftpService;
    private final PostUpdatesRepository postUpdatesRepository;

    @Autowired
    public PostUpdatesService(FtpService ftpService, PostUpdatesRepository postUpdatesRepository) {
        this.ftpService = ftpService;
        this.postUpdatesRepository = postUpdatesRepository;
    }

    public void savePostUpdates(LocalDateTime fetchedAt, List<String> newFiles) {

        for (String path: newFiles) {

            var post = ftpService.getPostUpdate(path);

            postUpdatesRepository.savePostUpdate(fetchedAt, post);
        }
    }
}
