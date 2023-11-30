package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

class NoSpaceLeftOnDevice {

    public static void main(String[] args) {
        executeCmdLines();
        sumTotalSizeOfDirsUnder100k();
        findLeastDirectorySizeToFreeSpaceForUpdate();
    }

    static void findLeastDirectorySizeToFreeSpaceForUpdate() {
        Set<Directory> directories = Directories.getInstance().getDirectories();
        final int DISK_SPACE = 70000000;
        final int NEEDED_SPACE_4_UPDATE = 30000000;
        int usedSpace = directories.stream()
                .sorted()
                .findFirst()
                .orElseThrow()
                .sumFilesSize();
        int asd = directories.stream()
                .filter(x -> usedSpace - x.sumFilesSize() < DISK_SPACE - NEEDED_SPACE_4_UPDATE)
                .mapToInt(Directory::sumFilesSize)
                .min()
                .orElseThrow();
        System.out.println("=== Dir size to delete: " + asd + " ===");
    }

    static void sumTotalSizeOfDirsUnder100k() {
        int fileSizeSum = Directories.getInstance().getDirectories().stream()
                .mapToInt(Directory::sumFilesSize)
                .filter(size -> size <= 100000)
                .sum();
        System.out.println("\n\n=== SUM: " + fileSizeSum + " ===");
    }

    static void executeCmdLines() {
        CmdHandler cmdHandler = CmdHandler.getInstance();
        for (String cmdLine : readCmdLines()) {
            cmdHandler.execute(cmdLine);
        }
    }

    static List<String> readCmdLines() {
        Path path = Paths.get(".", "src/Day7/input.txt");
        if (Files.notExists(path)) {
            System.err.println("File does not exist!");
            return List.of();
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return List.of();
        }
    }
}
