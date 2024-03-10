package ua.edu.ukma.systems_design.data_processor.models;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Comment {
    Integer userId;
    LocalDateTime datetime;
    String text;
}
