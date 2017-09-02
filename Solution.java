package com.javarush.task.task31.task31501;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/*
Проход по дереву файлов
*/
public class Solution {
    //public static TreeSet<File> treeSet = new TreeSet<>(new FileComparator());
    public static ArrayList<File> treeSet = new ArrayList<>();
    public static void main(String[] args) throws Exception{

        String resultFileAbsolutePathStr = args[1];
        String pathStr = args[0];
//        String resultFileAbsolutePathStr = "C:\\Users\\Osn\\IdeaProjects\\SandBox\\result.txt"; // testing
//        String pathStr = "C:\\Users\\Osn\\IdeaProjects\\SandBox\\232"; // testing
        File path = new File(pathStr);
        File resultFileAbsolutePath = new File(resultFileAbsolutePathStr);
        File resultFileRen = new File(resultFileAbsolutePath.getParent()+"/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, resultFileRen);
        try (FileOutputStream outputStreamResult = new FileOutputStream(resultFileRen)) {
            goDir(path);
            Collections.sort(treeSet, new FileComparator());
            for (File file: treeSet) {

                //System.out.println(file + " from treeset"); //testing
                FileInputStream inputStream = new FileInputStream(file);
                byte[] b = new byte[(int)file.length()];
                inputStream.read(b);
                outputStreamResult.write(b);
                outputStreamResult.write(System.lineSeparator().getBytes());
                inputStream.close();
            }
            outputStreamResult.close();
        }
    }

    public static void goDir(File path) {
        for (File file: path.listFiles()) {
            if (file.isFile()) {
                if (file.length()>50) {
                    FileUtils.deleteFile(file);
                } else {
                    treeSet.add(file);
                    //System.out.println(file + " in treeSet");  //testing
                }
            }
            if (file.isDirectory()) goDir(file);
            //System.out.println(file.length() + " " + file);
        }
    }

    public static void deleteFile(File file) {

        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }

    public static class FileComparator implements Comparator<File> {
        @Override
        public int compare(File o1, File o2) {
            //return o1.getName().compareTo(o2.getName()) == 0? 1:o1.getName().compareTo(o2.getName());
            return o1.getName().compareTo(o2.getName());
        }

    }
}
