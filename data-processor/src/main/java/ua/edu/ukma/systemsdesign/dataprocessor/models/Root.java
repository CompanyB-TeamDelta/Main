package ua.edu.ukma.systemsdesign.dataprocessor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Root {
    private Long id;
    private long subscribers;
    private List<Post> posts;
}
