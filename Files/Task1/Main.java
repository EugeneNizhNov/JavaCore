// Задача 1: Установка

package JavaCore.Files.Task1;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static StringBuilder logStringBuilder = new StringBuilder();

    public static void main(String[] args) {
        File directory = new File("D://Games/src");
        mkdr(directory);
        directory = new File("D://Games/res");
        mkdr(directory);
        directory = new File("D://Games/savegame");
        mkdr(directory);
        directory = new File("D://Games/temp");
        mkdr(directory);
        directory = new File("D://Games/src/main");
        mkdr(directory);
        directory = new File("D://Games/src/test");
        mkdr(directory);
        directory = new File("D://Games/res/drawables");
        mkdr(directory);
        directory = new File("D://Games/res/vectors");
        mkdr(directory);
        directory = new File("D://Games/res/icon");
        mkdr(directory);
        File newFile = new File("D://Games/src/main", "Main.java");
        createNewFile(newFile);
        newFile = new File("D://Games/src/main", "Utils.java");
        createNewFile(newFile);
        File newFileTemp = new File("D://Games/temp", "temp.txt");
        createNewFile(newFileTemp);
        try (FileWriter writer = new FileWriter(newFileTemp)) {
            writer.write(logStringBuilder.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Завершена работа программы)");
    }

    public static void mkdr(File directory) {
        if (directory.mkdir()) {
            logStringBuilder.append("Создана директория " + directory.getName() + " путь " + directory.getParent() + "\n");
        }
    }

    public static void createNewFile(File newFile) {
        try {
            if (newFile.createNewFile()) {
                logStringBuilder.append("Создан файл " + newFile.getName() + " в директории  " + newFile.getParent() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
