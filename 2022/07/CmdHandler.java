package Day7;

class CmdHandler {

    private static CmdHandler instance;
    private String currentDirectory;

    private CmdHandler() {
        currentDirectory = "";
        Directories.getInstance().getDirectories().add(new Directory("/", null));
    }

    static CmdHandler getInstance() {
        if (instance == null) {
            instance = new CmdHandler();
        }
        return instance;
    }

    void execute(String cmdLine) {
        if (cmdLine.isBlank()) {
            System.err.println("Empty command");
            return;
        }
        System.out.println(cmdLine);
        String[] arguments = cmdLine.split(" ");
        if ("$".equals(arguments[0])) {
            switch (arguments[1]) {
                case "cd" -> cd(arguments[2]);
                case "ls" -> ls();
            }
        } else if ("dir".equals(arguments[0])) {
            mkdir(arguments[1]);
        } else {
            touch(arguments);
        }
    }

    private void touch(String[] arguments) {
        findCurrentDirectory().getFiles().add(new File(arguments[1], Integer.parseInt(arguments[0])));
    }

    private void mkdir(String name) {
        Directory directory = "/".equals(currentDirectory) ?
                new Directory(currentDirectory + name, currentDirectory) :
                new Directory(currentDirectory + "/" + name, currentDirectory);
        Directories.getInstance().getDirectories().add(directory);
        findCurrentDirectory().getDirectories().add(directory);
    }

    private void ls() {
        findCurrentDirectory().list();
    }

    private void cd(String path) {
        if ("..".equals(path)) {
            currentDirectory = findCurrentDirectory().getParent();
        } else if ("".equals(currentDirectory)) {
            currentDirectory = "/";
        } else if ("/".equals(currentDirectory)) {
            currentDirectory += path;
        } else {
            currentDirectory = currentDirectory + "/" + path;
        }
    }

    private Directory findCurrentDirectory() {
        return Directories.getInstance().getDirectories()
                .stream()
                .filter(directory -> currentDirectory.equals(directory.getPath()))
                .findFirst()
                .orElseThrow();
    }


}
