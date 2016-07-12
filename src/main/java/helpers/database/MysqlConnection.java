package helpers.database;

public class MysqlConnection implements DatabaseConnectionInterface {
    private String user;
    private String password;
    private String database;
    private int port;
    private String backupName;

    public MysqlConnection(String user, String  password, String database, int  port) {
        this.user = user;
        this.password = password;
        this.database = database;
        this.port= port;
        this.backupName= database + ".sql";
    }

    @Override
    public String getDumpCommand() {
        return "mysqldump -u "+user+" -p'"+password+"' "+database+" > /tmp/"+backupName;
    }

    @Override
    public String getTablesCountCommand() {
        return "mysql -u "+user+" -p'"+password+"' -N -e \"SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = '"+database+"'\"";
    }

    @Override
    public String getDbName() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getTempBackupName() {
        return backupName;
    }
}
