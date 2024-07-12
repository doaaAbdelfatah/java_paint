import java.io.File;
import java.io.IOException;

public class DemoFile {
    public static void main(String[] args) {
        File file  =new File("C:\\java\\one\\two\\three");
//        System.out.println(file.exists());
//        System.out.println(file.isFile());
//        System.out.println(file.delete());

        try {
            if (!file.exists())
                file.mkdirs();
//                file.createNewFile();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
