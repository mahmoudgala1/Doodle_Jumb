package DoodleJump.Pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileIO {
    private static String data;

    public static String Read(String fileName) {
        try {
            Scanner input = new Scanner(new File("src\\Files\\" + fileName));
            data = "";
            while (input.hasNext()) {
                data = input.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Can not find file with the given path ");
        }
        return data;
    }

    public static void Write(String path, String fileName) {
        try {
            Scanner input = new Scanner(path);
            PrintWriter output = new PrintWriter(new File("src\\Files\\" + fileName));
            while (input.hasNext()) {
                output.println(input.nextLine());
            }
            output.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Can not find file with the given path ");
        }
    }

    public static String SettingsRead(int index) {

        try {
            data = Files.readAllLines(Paths.get("src\\Files\\Settings.txt")).get(index);
        } catch (IOException e) {
            System.out.println("Can not find file with the given path ");
        }

        return data;
    }

    public static void SettingsWrite(String condition, int index) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src\\Files\\Settings.txt"));
            lines.set(index, condition.toUpperCase());
            Files.write(Paths.get("src\\Files\\Settings.txt"), lines);
        } catch (IOException e) {
            System.out.println("Can not find file with the given path ");
        }
    }

}
