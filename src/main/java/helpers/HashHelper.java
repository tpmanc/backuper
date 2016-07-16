package helpers;

import config.Settings;
import exceptions.InternalException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper {
    public static String getHash(String filePath){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(filePath);
            byte[] dataBytes = new byte[1024];
            int nread = 0;
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            byte[] mdbytes = md.digest();

            //
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            fis.close();
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getHashDir(String hash) {
        String firstDir = hash.substring(0, 2);
        String secondDir = hash.substring(2, 4);
        String path = Settings.getUploadDir() + File.separator + firstDir + File.separator + secondDir + File.separator;
        File dir = new File(path);
        if (!dir.exists()) {
            boolean res = dir.mkdirs();
            if (!res) {
                throw new InternalException("Невозможно создать директорию " + dir);
            }
        }
        return path;
    }
}
