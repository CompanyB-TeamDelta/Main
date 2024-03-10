package ua.edu.ukma.systemsdesign.dataprocessor.models;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class Post {
    LocalDateTime datetime;
    String postText;
    List<Reaction> reactions;
    List<Media> mediaInPost;
    List<Comment> comments;
}
