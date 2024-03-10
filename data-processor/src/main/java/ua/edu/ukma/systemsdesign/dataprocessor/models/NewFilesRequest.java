package ua.edu.ukma.systemsdesign.dataprocessor.models;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class NewFilesRequest {
    LocalDateTime timestamp;
    List<String> newFiles;
}
