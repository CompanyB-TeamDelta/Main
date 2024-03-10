package ua.edu.ukma.systemsdesign.dataprocessor.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.JSON;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.systemsdesign.dataprocessor.models.Post;
import ua.edu.ukma.systemsdesign.dataprocessor.models.Reaction;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Repository
public class PostUpdatesRepository {

    private final DSLContext dslContext;
    private final ObjectMapper objectMapper;

    @Autowired
    public PostUpdatesRepository(DSLContext dslContext, ObjectMapper objectMapper) {
        this.dslContext = dslContext;
        this.objectMapper = objectMapper;
    }

    public void savePostUpdate(LocalDateTime fetchedAt, Post post) {

        dslContext.insertInto(DSL.table(DSL.name("post_updates")))
                .columns(
                        DSL.field("channel_subscription_id", Integer.class),
                        DSL.field("telegram_post_id", Integer.class),
                        DSL.field("fetched_at", Timestamp.class),
                        DSL.field("comments_count", Integer.class),
                        DSL.field("reactions_count", Integer.class),
                        DSL.field("reactions", JSON.class),
                        DSL.field("media_count", Integer.class),
                        DSL.field("media", JSON.class)
                )
                .values(
                        -1,
                        -1,
                        Timestamp.from(fetchedAt.toInstant(ZoneOffset.UTC)),
                        post.getComments().size(),
                        post.getReactions().stream().mapToInt(Reaction::getNumberOfReactions).sum(),
                        JSON.valueOf(toJson(post.getReactions())),
                        post.getMediaInPost().size(),
                        JSON.valueOf(toJson(post.getMediaInPost()))
                )
                .execute();
    }

    @SneakyThrows
    private String toJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
