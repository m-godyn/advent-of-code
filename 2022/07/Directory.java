package Day7;

import java.util.ArrayList;
import java.util.List;

class Directory implements Comparable<Directory> {

    private final String path;
    private final String parent;
    private final List<File> files;
    private final List<Directory> directories;

    public Directory(String path, String parent) {
        this.path = path;
        this.parent = parent;
        files = new ArrayList<>();
        directories = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    public String getParent() {
        return parent;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    void list() {
        for (Directory directory : directories) {
            System.out.println(directory.getPath());
        }
        for (File file : files) {
            System.out.printf("%s size=%s\n", file.name(), file.size());
        }
    }

    int sumFilesSize() {
        int size = 0;
        size += files.stream().mapToInt(File::size).sum();
        if (!directories.isEmpty()) {
            for (Directory directory : directories) {
                size += directory.sumFilesSize();
            }
        }
        return size;
    }

    @Override
    public int compareTo(Directory other) {
        return this.path.compareTo(other.getPath());
    }
}
