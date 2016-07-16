package helpers.database;

public class PostgresqlConnection implements DatabaseConnectionInterface {
    private String user;
    private String password;
    private String database;
    private int port;
    private String backupName;

    public PostgresqlConnection(String user, String  password, String database, int  port) {
        this.user = user;
        this.password = password;
        this.database = database;
        this.port= port;
        this.backupName= database + ".sql";
    }

    @Override
    public String getDumpCommand() {
        return "PGPASSWORD=\""+password+"\" pg_dump "+database+" -U "+user+" -h localhost > /tmp/"+backupName;
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
