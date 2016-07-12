package helpers.database;

public interface DatabaseConnectionInterface {
    public String getDumpCommand();
    public String getTablesCountCommand();
    public String getDbName();
    public String getTempBackupName();
}
