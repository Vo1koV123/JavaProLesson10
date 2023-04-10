package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileNavigator {
    private Map<String, List<FileData>> filesByPath;

    public FileNavigator() {
        filesByPath = new HashMap<>();
    }

    public void add(FileData file) {
        String path = file.getPath();
        if (!filesByPath.containsKey(path)) {
            filesByPath.put(path, new ArrayList<>());
        }
        List<FileData> files = filesByPath.get(path);
        if (files.stream().anyMatch(f -> f.getName().equals(file.getName()) )) {
            System.out.println("File with name " + file.getName() + " already exists at path " + path);
            return;
        }
        files.add(file);
    }

    public List<FileData> find(String path) {
        if (!filesByPath.containsKey(path)) {
            return Collections.emptyList();
        }
        return filesByPath.get(path);
    }

    public List<FileData> filterBySize(String path, long sizeLimit) {
        if (!filesByPath.containsKey(path)) {
            return Collections.emptyList();
        }
        return filesByPath.get(path).stream()
                .filter(file -> file.getSize() <= sizeLimit)
                .toList();
    }

    public void remove(String path) {
        filesByPath.remove(path);
    }

    public List<FileData> sortBySize() {
        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> files : filesByPath.values()) {
            allFiles.addAll(files);
        }
        Collections.sort(allFiles, (f1, f2) -> Long.compare(f1.getSize(), f2.getSize()));
        return allFiles;
    }
}