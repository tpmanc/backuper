package helpers;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class SshConnect {
    protected String address;
    protected int port;
    protected String user;
    protected String password;

    protected Session session = null;
    protected Channel shellChannel = null;
    protected ChannelShell sftpChannel = null;
    protected PrintStream commander;

    public SshConnect(String address, int port, String user, String password) {
        this.address = address;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    protected void sshConnect() throws JSchException, IOException {
        JSch ssh = new JSch();
        session = ssh.getSession(user, address, port);
        session.setPassword(password);

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        shellChannel = session.openChannel("shell");
        OutputStream inputstream_for_the_channel = shellChannel.getOutputStream();
        commander = new PrintStream(inputstream_for_the_channel, true);
        shellChannel.setOutputStream(null);
        shellChannel.connect(100);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
