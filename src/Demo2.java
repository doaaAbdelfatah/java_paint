import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Demo2 {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\dode3\\OneDrive\\Desktop\\Mystro");

//        if (!file.exists()){
//            try {
////                System.out.println(file.createNewFile());
//                System.out.println(file.mkdirs());
////                System.out.println(file.delete());
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }

        System.out.println(  Arrays.toString( file.list()));

        for (File f : file.listFiles()){
            System.out.println(f.getName()  +"  : " + (f.length()/1024) + " KB");
//            f.delete();
        }
//        file.delete();

    }
}
