package com.pages;

import java.io.*;
import java.util.Properties;

public class Common {

    // Method to read a property value from a properties file
    public static String readProperty(String filePath, String key) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    // Method to write a property value to a properties file
    public static void writeProperty(String filePath, String key, String value) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            prop.load(fis);
        } catch (IOException e) {
            // If file doesn't exist, we'll create a new one
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            prop.setProperty(key, value);
            prop.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the current timestamp in "yyyy-MM-dd HH:mm:ss" format
    public static String getCurrentTimestamp() {
        return java.time.LocalDateTime.now().toString();
    }

    // Method to create directories if they don't exist
    public static void createDirectories(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Method to delete files or directories (recursive for directories)
    public static boolean deleteFileOrDirectory(String path) {
        File fileOrDir = new File(path);
        if (!fileOrDir.exists()) {
            return false;
        }

        if (fileOrDir.isDirectory()) {
            String[] contents = fileOrDir.list();
            if (contents != null) {
                for (String file : contents) {
                    deleteFileOrDirectory(path + "/" + file);
                }
            }
        }
        return fileOrDir.delete();
    }

    // Method to log messages to a log file (optional)
    public static void logToFile(String filePath, String message) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(getCurrentTimestamp() + " - " + message);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write a list of strings to a file (for large logs, results, etc.)
    public static void writeListToFile(String filePath, java.util.List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all lines from a file and return them as a List of Strings
    public static java.util.List<String> readFileLines(String filePath) {
        java.util.List<String> lines = new java.util.ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Method to get a unique identifier for logging purposes (e.g., UUID)
    public static String generateUniqueId() {
        return java.util.UUID.randomUUID().toString();
    }

    // Method to format JSON response string (you can improve this as per your needs)
    public static String prettyPrintJson(String json) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            Object jsonObj = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
        } catch (IOException e) {
            e.printStackTrace();
            return json; // Return unformatted JSON if exception occurs
        }
    }
}
