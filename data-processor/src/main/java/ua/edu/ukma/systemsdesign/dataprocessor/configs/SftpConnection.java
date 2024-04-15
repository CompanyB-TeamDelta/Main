package ua.edu.ukma.systemsdesign.dataprocessor.configs;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
@NoArgsConstructor
public class SftpConnection {
    private Integer port;
    private String server;
    private String username;
    private String password;

    public SftpConnection(Integer port, String server, String username, String password) {
        this.port = port;
        this.server = server;
        this.username = username;
        this.password = password;
        renewConnection();
    }

    private final JSch jsch = new JSch();
    private Session session;
    private ChannelSftp channelSftp;
    private void renewConnection(){
        if(channelSftp != null) {
            channelSftp.disconnect();
            channelSftp = null;
        }
        if (session != null) {
            session.disconnect();
            session = null;
        }
        try {
            session = jsch.getSession(username, server);
            var config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(password);
            session.setPort(port);
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
        }
        catch (Exception e){
            log.error("[SftpConnection] FAILED TO INSTANTIATE CONNECTION: " + e.getMessage());
        }
    }

    public byte[] getFileBytes(String src){
        try {
            renewConnection();
            channelSftp.connect();
            return channelSftp.get(src).readAllBytes();
        }
        catch (Exception e){
            log.error("failed to get input stream: " + e.getMessage());
            throw new NullPointerException();
        }
        finally {
            channelSftp.disconnect();
        }
    }
}
