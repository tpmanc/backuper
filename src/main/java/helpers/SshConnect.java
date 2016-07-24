package helpers;

import com.jcraft.jsch.*;
import exceptions.BashExecuteException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SshConnect {
    protected String address;
    protected int port;
    protected String user;
    protected String password;

    protected Session session = null;
    protected Channel execChannel = null;

    protected String archiveNameDate;
    protected String archiveExtension = ".tar.gz";

    protected String tempDir = "/tmp";

    public SshConnect(String address, int port, String user, String password) {
        this.address = address;
        this.port = port;
        this.user = user;
        this.password = password;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyy_MM_dd");
        archiveNameDate = df.format(date);
    }

    public void connect() throws JSchException {
        JSch ssh = new JSch();
        session = ssh.getSession(user, address, port);
        session.setPassword(password);

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
    }

    public void disconnect() {
        session.disconnect();
    }

    public String executeBash(String command) throws JSchException, IOException, BashExecuteException {
        execChannel = session.openChannel("exec");
        ((ChannelExec) execChannel).setCommand(command);
        InputStream commandOutput = execChannel.getInputStream();
        InputStream errorOutput = ((ChannelExec) execChannel).getErrStream();
        execChannel.connect();

        int readByte = commandOutput.read();
        StringBuilder outputBuffer = new StringBuilder();
        while(readByte != 0xffffffff) {
            outputBuffer.append((char)readByte);
            readByte = commandOutput.read();
        }

        readByte = errorOutput.read();
        StringBuilder errorBuffer = new StringBuilder();
        while(readByte != 0xffffffff) {
            errorBuffer.append((char)readByte);
            readByte = errorOutput.read();
        }

        if (errorBuffer.length() > 0) {
            System.out.println(errorBuffer);
            throw new BashExecuteException(errorBuffer.toString());
        }

        execChannel.disconnect();
        return outputBuffer.toString();
    }

    protected String getArchiveFullName(String name) {
        return name+"-"+archiveNameDate+archiveExtension;
    }

    protected String createArchive(String name, String tempName) throws IOException, JSchException, BashExecuteException {
        String archivePath = tempDir+"/"+getArchiveFullName(name);
        String command = "tar -zcf \""+archivePath+"\" -C "+tempDir+" "+tempName;
        executeBash(command);
        return archivePath;
    }

    public String downloadBackup(String from, String to) throws JSchException, SftpException {
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        channel.get(from, to);
        channel.disconnect();
        return to;
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
