package ua.edu.ukma.systemsdesign.dataprocessor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.systemsdesign.dataprocessor.models.PostsFiles;

@Repository
public interface PostFilesRepository extends JpaRepository<PostsFiles,String> {
}
