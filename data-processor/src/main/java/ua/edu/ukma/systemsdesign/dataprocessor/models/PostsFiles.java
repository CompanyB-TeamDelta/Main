package ua.edu.ukma.systemsdesign.dataprocessor.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "posts_files")
@NoArgsConstructor
public class PostsFiles {
    @Id
    private String filePath;
    private Timestamp fetchedAt;
    @OneToMany(mappedBy = "filePath")
    private List<PostUpdate> postsFilesList;
}
