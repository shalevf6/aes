import Interfaces.Break;
import Interfaces.Decrypt;
import Interfaces.Encrypt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        if(args.length == 7) {
            try {
                byte[] keys = readBytes(args[2]);
                byte[] input = readBytes(args[4]);
                String output = args[6];

                switch (args[0]){
                    case "-e":
                        Encrypt encrypt = new Encrypt(keys, input);
                        byte[] eResult = encrypt.encrypt();
                        writeBytes(output, eResult);
                        break;
                    case "-d":
                        Decrypt decrypt = new Decrypt(keys, input);
                        byte[] dResult = decrypt.decrypt();
                        writeBytes(output, dResult);
                        break;
                    case "-b":
                        Break breaker = new Break(keys, input);
                        byte[] bResult = breaker.getKeys2();
                        writeBytes(output, bResult);
                        break;
                    default:
                        throw new RuntimeException("Invalid arguments given. Please insert them properly");
                }
            }
            catch (Exception e){
                System.out.println("Some error occurred. Info: {\n" + e.getMessage() +"\n}");
                System.out.println("Exiting program now...");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        // If there was no input inserted
        else {
            System.out.println("Bad input: " + Arrays.toString(args));
        }
    }


    public static byte[] readBytes(String file) throws IOException, InvalidPathException {
        Path path = Paths.get(file);
        return Files.readAllBytes(path);
    }


    public static void writeBytes(String file, byte[] toWrite) throws IOException{
        FileOutputStream writer = new FileOutputStream(file);
        writer.write(toWrite);
    }


}
