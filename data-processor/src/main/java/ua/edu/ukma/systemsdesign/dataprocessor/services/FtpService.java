package ua.edu.ukma.systemsdesign.dataprocessor.services;

import com.jcraft.jsch.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.edu.ukma.systemsdesign.dataprocessor.configs.SftpConnection;
import ua.edu.ukma.systemsdesign.dataprocessor.models.Post;

import java.io.IOException;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class FtpService {
    private final SftpConnection sftpConnection;
    public Post getPostUpdate(String path) {
        //todo continue
        var fileBytes = sftpConnection.getFileBytes(path);
        return null;
    }
}
