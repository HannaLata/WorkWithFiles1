package com.mainacad.service;

import com.mainacad.model.ConnectionInfo;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileService {

    public static final String MAIN_DIR = System.getProperty("user.dir");
    public static final String SEP = System.getProperty("file.separator");
    public static final String FILES_DIR = MAIN_DIR + SEP + "files";
    private static final Logger LOGGER = Logger.getLogger(FileService.class.getName());

    // work with text
    public  static void writeTextToFile(String text, String fileName, boolean append) {
        checkTargetDir();
        try (FileWriter fileWriter = new FileWriter(FILES_DIR + SEP + fileName, append)) {
            fileWriter.write(text + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkTargetDir() {
        File file = new File(FILES_DIR);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    private static void checkTargetDir(File file) {
        if(!file.exists()) {
            file.mkdir();
        }
    }

    public  static String readTextFromFile(String fileName) {
        String out = "";

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(FILES_DIR + SEP + fileName))) {
               String line;
               while ( ( line = bufferedReader.readLine()) != null ) {
                   out += line + "\n";
               }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }

    public  static List<ConnectionInfo> readConnectionsFromFile(String fileName) {
        List<ConnectionInfo> connectionInfoList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(FILES_DIR + SEP + fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ( ( line = bufferedReader.readLine()) != null ) {
                ConnectionInfo connectionInfo = new ConnectionInfo();

                String[] words = line.split(" ");
                connectionInfo.setSessionId(Integer.valueOf(words[0]));
                connectionInfo.setConnectionTime(Long.valueOf(words[1]));
                connectionInfo.setIp(words[2]);

                connectionInfoList.add(connectionInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connectionInfoList;
    }

    public static void writeBytesToFile(byte[] bytes, String fileName) {
        checkTargetDir();
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILES_DIR + SEP + fileName)){
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getBytesFromFile(String fileName) {
        File file = new File(FILES_DIR + SEP + fileName);
        try {
             return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static void copyFile(String sourceName, String targetName) {
        byte[] bytes = getBytesFromFile(sourceName);
        writeBytesToFile(bytes, targetName);
    }

    public static void writeBytesToFile(byte[] bytes, File file) {
        checkTargetDir();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath())){
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveFileToFolder(String sourceName, String folderTarget) {
        byte[] bytes = getBytesFromFile(sourceName);

        File fileDir = new File(MAIN_DIR + SEP + folderTarget);
        checkTargetDir(fileDir);
        fileDir = new File(MAIN_DIR + SEP + folderTarget + SEP + sourceName);

        writeBytesToFile(bytes, fileDir);
        File sourceFile = new File(FILES_DIR + SEP + sourceName);
        sourceFile.delete();

    }

    public static void writeObjectToFile(Object object, String fileName) {
        checkTargetDir();
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(FILES_DIR + SEP + fileName))){
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getObjectFromFile(String fileName) {
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(FILES_DIR + SEP + fileName))) {
            return objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
