package ua.edu.ukma.systemsdesign.dataprocessor.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.edu.ukma.systemsdesign.dataprocessor.configs.SftpConnection;
import ua.edu.ukma.systemsdesign.dataprocessor.models.Post;
import ua.edu.ukma.systemsdesign.dataprocessor.models.Root;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
@Slf4j
public class FtpService {
    private final ObjectMapper objectMapper;

    @Autowired
    public FtpService(SftpConnection sftpConnection) {
        this.sftpConnection = sftpConnection;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    private final SftpConnection sftpConnection;
    @Synchronized
    public Root getPostUpdate(String path) {
        var val = sftpConnection.getFileBytesAndConvert(path,Root.class);
        return val;
    }
}
