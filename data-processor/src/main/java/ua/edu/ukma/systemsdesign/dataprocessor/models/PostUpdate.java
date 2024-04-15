package ua.edu.ukma.systemsdesign.dataprocessor.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "post_updates", indexes = {
    @Index(name = "channel_subscription_id", columnList = "channel_subscription_id")
})
@NoArgsConstructor
public class PostUpdate {
    public PostUpdate(Long telegramChannelId, long telegramPostId, Timestamp fetchedAt, int commentsCount, int reactionsCount, boolean media) {
        this.telegramChannelId = telegramChannelId;
        this.telegramPostId = telegramPostId;
        this.fetchedAt = fetchedAt;
        this.commentsCount = commentsCount;
        this.reactionsCount = reactionsCount;
        this.media = media;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_channel_id", nullable = false)
    private Long telegramChannelId;

    @Column(name = "telegram_post_id", nullable = false)
    private long telegramPostId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fetched_at", nullable = false)
    private Timestamp fetchedAt;

    @Column(name = "comments_count", nullable = false)
    private int commentsCount;

    @Column(name = "reactions_count", nullable = false)
    private int reactionsCount;

    @Column(name = "media", nullable = false)
    private boolean media;
    private long views;

    public static PostUpdate of(Post post){
        var pu = new PostUpdate();
        pu.setMedia(post.getMedia_in_post());
        pu.setCommentsCount(post.getComments().size());
        pu.setTelegramPostId(post.getPost_id());
        pu.setReactionsCount(post.getReactions().size());
        pu.setViews(post.getViews());
        return pu;
    }
}
