import java.io.FileWriter;
import java.io.IOException;

public class Printer {

    public static void clearFile(){
        try(FileWriter writer = new FileWriter("io/output.txt", false))
        {
            writer.write("");
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void print(String s, boolean fileMode){
        if (fileMode){
            try(FileWriter writer = new FileWriter("io/output.txt", true))
            {
                writer.write(s);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.print(s);
        }
    }

    public static void println(String s, boolean fileMode){
        if (fileMode){
            s+="\n";
            try(FileWriter writer = new FileWriter("io/output.txt", true))
            {
                writer.write(s);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        } else{
            System.out.println(s);
        }
    }


}
