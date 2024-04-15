package ua.edu.ukma.systemsdesign.dataprocessor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    public long post_id;
    public Date datetime;
    public String text;
    public boolean pinned;
    public int views;
    List<Reaction> reactions;
    Boolean media_in_post;
    public boolean is_reply;
    List<Comment> comments;
}
