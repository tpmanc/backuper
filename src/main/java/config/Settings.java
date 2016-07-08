package config;

import exceptions.InternalException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

@Component
public class Settings {
    public static final String dbProperties = "database.properties";

    private static final String dbDriver = "db.driverClassName";
    private static final String dbUrl = "db.url";
    private static final String dbUser = "db.user";
    private static final String dbPass = "db.password";
    private static final String dbPoolSize = "db.poolSize";

    public static String getAppHome() {
        String userHome = System.getProperty("user.home");
        String appHome = userHome + File.separator + getAppDir();
        File fileDir = new File(appHome);
        // Создаем основную директорию, если ее нет
        if (!fileDir.exists()) {
            boolean res = fileDir.mkdirs();
            if (!res) {
                throw new InternalException("Невозможно создать директорию " + fileDir);
            }
        }
        return appHome;
    }

    public static String getDbPath() {
        String fileDir = getAppHome();
        return fileDir + File.separator + Settings.dbProperties;
    }

    public static boolean isAllFilled() {
        boolean isFilled = true;

        String dbFilePath = getDbPath();
        File dbFile = new File(dbFilePath);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                throw new InternalException("Невозможно создать файл " + dbFilePath);
            }
            isFilled = false;
        }

        HashMap<String, String> dbProperties = getDbProperties();
        if (dbProperties.get(dbUrl) == null) {
            isFilled = false;
        }
        if (dbProperties.get(dbUser) == null) {
            isFilled = false;
        }
        if (dbProperties.get(dbPoolSize) == null) {
            isFilled = false;
        }

        return isFilled;
    }

    public static HashMap<String, String> getDbProperties() {
        String dbFilePath = getDbPath();

        HashMap<String, String> res = new HashMap<>();
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(dbFilePath);
            property.load(fis);
            fis.close();

            String driverName = property.getProperty(dbDriver);
            if (driverName != null) {
                res.put(dbDriver, driverName);
            }

            String url = property.getProperty(dbUrl);
            if (url != null) {
                res.put(dbUrl, url);
            }

            String user = property.getProperty(dbUser);
            if (user != null) {
                res.put(dbUser, user);
            }

            String password = property.getProperty(dbPass);
            if (password != null) {
                res.put(dbPass, password);
            }

            String poolSize = property.getProperty(dbPoolSize);
            if (poolSize != null) {
                res.put(dbPoolSize, poolSize);
            }
            return res;
        } catch (IOException e) {
            throw new InternalException("Файл "+Settings.getDbPath()+" не найден");
        }
    }

    public static void setDbProperties(String url, String user, String pass, Integer pool) {
        String dbFilePath = getDbPath();

        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(dbFilePath);
            property.load(fis);
            fis.close();

            if (url != null && url.length() > 0) {
                property.setProperty(dbUrl, url);
            }
            if (user != null && user.length() > 0) {
                property.setProperty(dbUser, user);
            }
            if (pass != null && pass.length() > 0) {
                property.setProperty(dbPass, pass);
            }
            if (pool != null && pool > 0) {
                property.setProperty(dbPoolSize, pool.toString());
            }

            FileOutputStream out = new FileOutputStream(dbFilePath);
            property.store(out, null);
            out.close();
        } catch (IOException e) {
            throw new InternalException("Файл "+Settings.dbProperties+" не найден");
        }
    }

    public static String getAppDir() {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AppProperties appProperties = (AppProperties) ctx.getBean("appProperties");
        return appProperties.getAppName();
    }
}
