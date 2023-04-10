package task;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileData file1 = new FileData("file1.txt", 1000, "C:\\Folder1");
        FileData file2 = new FileData("file2.txt", 500, "C:\\Folder1\\Subfolder1");
        FileData file3 = new FileData("file3.txt", 2000, "C:\\Folder1\\Subfolder1");

        FileNavigator fileNavigator = new FileNavigator();

        fileNavigator.add(file1);
        fileNavigator.add(file2);
        fileNavigator.add(file3);

        List<FileData> filesInFolder1 = fileNavigator.find("C:\\Folder1");
        System.out.println("Files in C:\\Folder1:");
        for (FileData file : filesInFolder1) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }
        System.out.println();

        List<FileData> filesInSubfolder1 = fileNavigator.find("C:\\Folder1\\Subfolder1");
        System.out.println("Files in C:\\Folder1\\Subfolder1:");
        for (FileData file : filesInSubfolder1) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }
        System.out.println();

        List<FileData> smallFilesInSubfolder1 = fileNavigator.filterBySize("C:\\Folder1\\Subfolder1", 1000);
        System.out.println("Small files (less than 1000 bytes) in C:\\Folder1\\Subfolder1:");
        for (FileData file : smallFilesInSubfolder1) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }
        System.out.println();

        List<FileData> allFilesSortedBySize = fileNavigator.sortBySize();
        System.out.println("All files sorted by size:");
        for (FileData file : allFilesSortedBySize) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }
        System.out.println();

        fileNavigator.remove("C:\\Folder1\\Subfolder1");
        System.out.println("Files in C:\\Folder1 after removing C:\\Folder1\\Subfolder1:");
        List<FileData> filesInFolder1AfterRemove = fileNavigator.find("C:\\Folder1");
        for (FileData file : filesInFolder1AfterRemove) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }
    }
}
