package com.example.demo;

// this class is for Shared resources
public class SharedDataModel {
    private static String path;
    private static String customFileContent;
    private static boolean isShared;
    private static String ParsedXMLText;
    private static String ParsedJsonText;
    private static String ParsedCurrentXMLFile;
    public static String getPath() {
        return path;
    }

    public static void setPath(String newPath) {
        path = newPath;
    }
    public static void setIsShared(boolean status) {
        isShared = status;
    }
    public static boolean getIsShared() {
        return isShared;
    }

    public static void setCustomFileContent(String text) {
        customFileContent = text;
    }
    public static String getCustomFileContent() {
        return customFileContent;
    }

    public static String getParsedXMLText() {
        return ParsedXMLText;
    }

    public static void setParsedXMLText(String parsedXMLText) {
        ParsedXMLText = parsedXMLText;
    }

    public static String getParsedJsonText() {
        return ParsedJsonText;
    }

    public static void setParsedJsonText(String parsedJsonText) {
        ParsedJsonText = parsedJsonText;
    }

    public static String getParsedCurrentXMLFile() {
        return ParsedCurrentXMLFile;
    }

    public static void setParsedCurrentXMLFile(String parsedCurrentXMLFile) {
        ParsedCurrentXMLFile = parsedCurrentXMLFile;
    }
}
