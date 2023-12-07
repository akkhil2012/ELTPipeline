package lld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InMemoryFileSystemExample {

    /*
    Design an In-memory file system
We are only required to write the class structure - either on whiteboard or on laptop whichever is comfortable.
Follow ups will be asked for including additional requirements later on, so make sure your design is easily extendable.
Follow ups
Efficiently searching file system
Handling user permissions, group permissions
Listing the files in folder similar to what "ls" command does in linux
Handle input options
Brushing up on design patterns really helps

:  https://leetcode.com/discuss/interview-experience/4205025/Salesforce-or-SMTS-or-Bengaluru-or-Oct-2023-Offer

https://medium.com/root-node/design-in-memory-file-system-96ee6c484616

https://leetcode.ca/2017-07-10-588-Design-In-Memory-File-System/x
     */



    public static  void main(String args[]){
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        fileSystem.ls("/");

        fileSystem.mkdir("/a");
        fileSystem.mkdir("/a/b/c");

        fileSystem.addContentToFile("/a/b/c/d","hello");

        fileSystem.pathToDirMapper
                .entrySet()
                .stream()
                .map(
                        entry ->{
                            String key = entry.getKey();
                            List<CustomFile> modList  = entry.getValue()
                                    .stream()
                                    .collect(Collectors.toList());
                            return Map.entry(key,modList);
                        })
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue))
                .forEach((k,v) -> System.out.println("Kay==> "+k  +" Value ===> "+v));



    }

}


class InMemoryFileSystem {

    Map<String, List<CustomFile>> pathToDirMapper;

    private void init() {
        pathToDirMapper = new HashMap<>();
        pathToDirMapper.put("/", new ArrayList<>());
    }

    public InMemoryFileSystem() {
        init();
    }

    public List<CustomFile> ls(String ls) {
        List<CustomFile> filesUnderDir = null;

        if (pathToDirMapper.get("/").size() == 0)
            return filesUnderDir;
        else {
            List<CustomFile> childFiles = pathToDirMapper.get("/");
            filesUnderDir = new LinkedList<>();
            for (CustomFile child : childFiles) {
                parse(child, filesUnderDir);

            }

        }
        return filesUnderDir;
    }


    private void parse(CustomFile file, List<CustomFile> filesUnderDir) {

        if (file.getFileType() == FileType.FILE) {
            filesUnderDir.add(file);
            return;
        }

        parse(file, filesUnderDir);

    }


    public void mkdir(String path) {
        String[] pathSplit = path.split("//");

        String parent = "/";
        for (String p : pathSplit) {
            if (!pathToDirMapper.containsKey(p)) {
                CustomFile missingDir = new CustomFile(p, FileType.DIR);
                pathToDirMapper.get(parent).add(missingDir);
                pathToDirMapper.put(missingDir.getFileName(), new ArrayList<>());
            }
            parent = p;
        }
        CustomFile newDir = new CustomFile(parent, FileType.DIR);
        pathToDirMapper.get(parent).add(newDir);


    }


    public void addContentToFile(String file, String content) {
        Pattern regex = Pattern.compile("//");
        List<String> dirs = Arrays.stream(file.split("//")).collect(Collectors.toList());

        String fileToWriteTo = "";
        boolean flag = false;
        for(String s : dirs){
            flag =!flag;
               if(!pathToDirMapper.containsKey(s)){
                   mkdir(s);
               }
            flag = true;
        }

        if(flag){

        }

        Optional<String> pathTillParentFile = Optional.of(Arrays.stream(file.split("//")).reduce((f, s) -> s).get());
       // String pathTillParentFile = file.substring("//");
        /*if(pathToDirMapper.containsKey(pathTillParentFile.)){

        }*/

    }


    public String readContentFromFile(String file, String content) {

        return "";
    }


}


class CustomFile {

    private String fileName = "";
    private FileType fileType;

    @Override
    public String toString() {
        return "CustomFile{" +
                "fileName='" + fileName + '\'' +
                ", fileType=" + fileType +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public FileType getFileType() {
        return fileType;
    }

    public CustomFile(String fileName, FileType fileType) {
        this.fileType = fileType;
        this.fileName = fileName;
    }
}


enum FileType {
    FILE, DIR
}













