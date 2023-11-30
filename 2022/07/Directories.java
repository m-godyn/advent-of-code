package Day7;

import java.util.Set;
import java.util.TreeSet;

class Directories {

    private static Directories instance;
    private final Set<Directory> directories = new TreeSet<>();

    private Directories() {
    }

    static Directories getInstance() {
        if (instance == null) {
            instance = new Directories();
        }
        return instance;
    }

    Set<Directory> getDirectories() {
        return directories;
    }
}
