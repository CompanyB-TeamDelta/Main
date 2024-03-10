package ua.edu.ukma.systemsdesign.dataprocessor.models;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Comment {
    Integer userId;
    LocalDateTime datetime;
    String text;
}
