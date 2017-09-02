package com.javarush.task.task31.task3101;

import java.io.File;

public class FileUtils {

    public static void deleteFile(File file) {
        //System.out.println(file + "delete");
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
        //file.delete();
        //System.out.println(file + " delete");
    }

    public static void renameFile(File source, File destination) {
        if (!source.renameTo(destination)) System.out.println("Can not rename file with name " + source.getName());
        //destination.renameTo(source);
    }
}
