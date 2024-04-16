package ua.edu.ukma.systemsdesign.dataprocessor.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.edu.ukma.systemsdesign.dataprocessor.models.PostUpdate;
import ua.edu.ukma.systemsdesign.dataprocessor.models.PostsFiles;
import ua.edu.ukma.systemsdesign.dataprocessor.repositories.PostFilesRepository;
import ua.edu.ukma.systemsdesign.dataprocessor.repositories.PostUpdateRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostUpdatesService {

    private final FtpService ftpService;
    private final PostUpdateRepository postUpdatesRepository;
    private final PostFilesRepository postFilesRepository;

    public void savePostUpdates(Date fetchedAt, List<String> newFiles) {

        for (String path: newFiles) {

            var posts = ftpService.getPostUpdate(path);
            var pf = new PostsFiles();
            pf.setFetchedAt(Timestamp.from(fetchedAt.toInstant()));
            pf.setFilePath(path);

            var id = "" + posts.getId();
            var idL = Long.parseLong("-100" + id);
            postFilesRepository.save(pf);
            posts.getPosts().forEach(p -> {
                try {
                    var pu = PostUpdate.of(p);
                    pu.setFetchedAt(Timestamp.from(fetchedAt.toInstant()));
                    pu.setTelegramChannelId(idL);
                    pu.setFilePath(pf);
                    postUpdatesRepository.save(pu);
                }
                catch (Exception e){
                    log.error("failed to save postUpdate: " + e.getMessage());
                }
            });
        }
    }
}
