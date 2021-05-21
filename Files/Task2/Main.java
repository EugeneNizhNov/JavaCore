/* В данном решении объеденены 2 и 3 задача
   Задача 2: Сохранение
   Задача 3: Загрузка (со звездочкой *)
 */
package JavaCore.Files.Task2;

import JavaCore.Files.Task2.GameProgress;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        String parthSave = "D://Games/savegame/";
        String zipPath = "D://Games/savegame/save.zip";
        String unzipPath = "D://Games/src/test/";
        GameProgress[] saveGameProgresses = new GameProgress[]{(new GameProgress(50, 2, 1, 205.6)),
                (new GameProgress(78, 5, 6, 105.9)),
                (new GameProgress(95, 3, 1, 352.9))};
        for (int i = 0; i < saveGameProgresses.length; i++) {
            //создаются файлы сохранений
            saveGame(saveGameProgresses[i], createPath(i + 1));
        }
        //создадим списокт сохраненных файлов
        List<String> listFile = new ArrayList<>();
        File savedDir = new File(parthSave);
        if (savedDir.isDirectory()) {
            for (File item : savedDir.listFiles()) {
                if (item.isFile()) {
                    listFile.add(item.getName());
                }
            }
        }
        //создаем архив сохранений
        zipFiles(parthSave, listFile);
        //удалим файлы сохранений
        for (String item : listFile) {
            File deleteFile = new File(parthSave, item);
            System.out.println(item);
            if (deleteFile.delete()) {
                System.out.println("Файл " + deleteFile.getName() + " удален успешно");
            } else {
                System.out.println("Ошибка удаления файла");
            }
        }
        //распакуем zip-архив в требуемый каталог
        openZip(zipPath, unzipPath);
        //создадим список распакованных сохранений
        List<String> unzipListFile = new ArrayList<>();
        File unzipSavedDir = new File(unzipPath);
        if (unzipSavedDir.isDirectory()) {
            for (File item : unzipSavedDir.listFiles()) {
                if (item.isFile()) {
                    unzipListFile.add(item.getPath());
                }
            }
        }
        //прочитаем срисок сохраненных файлов
        for (String list : unzipListFile) {
            GameProgress unzipGameProgress = openProgress(list);
            System.out.println(unzipGameProgress);
        }
    }

    public static void zipFiles(String pathName, List<String> listFile) {
        File zipFile = new File(pathName, "save.zip");
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (String list : listFile) {
                File zipList = new File(pathName, list);
                FileInputStream fis = new FileInputStream(zipList);
                ZipEntry entry = new ZipEntry(list);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String createPath(int i) {
        return "D://Games/savegame/save" + Integer.toString(i) + ".dat";
    }

    public static void saveGame(GameProgress saveGameProgress, String name) {
        try (FileOutputStream fos = new FileOutputStream(name);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(saveGameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openZip(String zipPath, String unzipPath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(unzipPath + name);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String unzipSavedFile) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(unzipSavedFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
