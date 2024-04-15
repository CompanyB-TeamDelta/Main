package ua.edu.ukma.systemsdesign.dataprocessor.configs;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
@Slf4j
public class DataSourceConfig {
    @Value("${ftp.port}") private Integer port;
    @Value("${ftp.host}") private String server;
    @Value("${ftp.username}") private String username;
    @Value("${ftp.password}") private String password;

    @Bean
    public SftpConnection ftpClient() {
        SftpConnection sftpConnection = new SftpConnection(port,server,username,password);
        return sftpConnection;
    }
}
