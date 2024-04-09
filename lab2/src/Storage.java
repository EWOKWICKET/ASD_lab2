/*
Storage class wit all necessary methods for work with groups and statistacs methods
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.Comparator.comparing;

public class Storage {
    private static Storage instance;
    private static ArrayList<Group> groups = new ArrayList<>();

    private Storage() {
    }

    /** Uses constructor if instance is null, else returns reference on it
     * @return class reference
     */
    public static Storage getInstance() {
        // Якщо екземпляр ще не існує, створюємо його
        if (instance == null) {
            instance = new Storage();
        }
        // Повертаємо єдиний екземпляр класу
        return instance;
    }

    /**
     * @return groups
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * Adds a group and sorts
     * @param group
     */
    public void addGroup(Group group) {
        groups.add(group);
        sortGroups();
    }

    /**
     * Deletes a group
     * @param group
     */
    public void deleteGroup(Group group) {
        groups.remove(group);
    }

    /**
     * @return price of all goods on storage
     */
    public String getStoragePrice() {
        StringBuilder text = new StringBuilder();
        float sum = 0;
        int amount = 0;
        for (Group group : groups) {
            sum += group.getGroupPrice();
            amount += group.getGoods().size();
        }
        text.append("Кількість товарів на складі: " + amount + "\nЦіна товарів: " + sum);
        return text.toString();
    }

    /**
     * @return all storage goods in String
     */
    public String getAllStorageGoods() {
        StringBuilder text = new StringBuilder();
        for (Group group : groups) {
            text.append(group.getAllGroupGoods());
        }
        return text.toString();
    }

    /**
     * @param name - pattern to search
     * @return ArrayList of found goods
     */
    public ArrayList<Good> findGood(String name) {
        ArrayList<Good> found = new ArrayList<>();
        for (Group group : groups) {
            found.addAll(group.findGood(name));
        }
        return found;
    }

    /**
     * Updates information in group files
     */
    public static void updateFiles() {
        File folder = new File("lab2/Groups");
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
        folder.delete();
        String folderPathStr = "lab2/Groups";

        // Create a Path object representing the folder
        Path folderPath = Paths.get(folderPathStr);
        try {
            // Create the folder/directory
            Files.createDirectory(folderPath);
        } catch (IOException e) {
            System.err.println("Failed to create folder: " + e.getMessage());
        }
        for (Group group : groups) {
            try {
                updateFile(group.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Opens and updates a file
     *
     * @param name of a group and a file
     * @throws IOException
     */
    private static void updateFile(String name) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("lab2/Groups/" + name + ".txt"))) {
            for (Good good : (groups.get(findGroup(name))).getGoods()) {
                bw.write(good.toWrite() + "\n");
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /**
     * reads info from files
     */
    public void readFiles() {
        File folder = new File("lab2/Groups");
        File[] fileList = folder.listFiles();
        for (File file : fileList) {
            readFile(file);
        }
        sortGroups();
    }

    /**
     * reads info from each file
     * @param file
     */
    private void readFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {
            String groupName = file.getName();
            groupName = groupName.replaceAll(".txt", "");
            ArrayList<Good> goods = new ArrayList<>();
            String goodString;
            do {
                goodString = br.readLine();
                if (goodString != null && !goodString.isBlank()) {
                    createNewGood(goodString, goods, groupName);
                }

            } while (goodString != null);
            groups.add(new Group(groupName, goods));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error in reading");
        }
    }

    /**
     * Adds a good according to read info
     * @param goodString
     * @param goods
     * @param groupName
     */
    private void createNewGood(String goodString, ArrayList<Good> goods, String groupName) {
        StringTokenizer st = new StringTokenizer(goodString, "|");

        String name = st.nextToken();

        StringTokenizer desSt = new StringTokenizer(st.nextToken(), ":");
        String descr = desSt.nextToken();
        descr = desSt.nextToken();

        StringTokenizer mnfSt = new StringTokenizer(st.nextToken(), ":");
        String mnf = mnfSt.nextToken();
        mnf = mnfSt.nextToken();

        StringTokenizer amountSt = new StringTokenizer(st.nextToken(), ":");
        String amountStr = amountSt.nextToken();
        amountStr = amountSt.nextToken();
        int amount = Integer.parseInt(amountStr);

        StringTokenizer priceSt = new StringTokenizer(st.nextToken(), ":");
        String priceStr = priceSt.nextToken();
        priceStr = priceSt.nextToken();
        float price = Float.parseFloat(priceStr);

        goods.add(new Good(groupName, name, descr, mnf, amount, price));
    }

    /**
     * Finds group index
     *
     * @param name name of a group
     * @return index of a group in list of groups
     */
    public static int findGroup(String name) {
        for (Group group : groups) {
            if (group.getName().equals(name)) return groups.indexOf(group);
        }
        return -1;
    }

    /**
     * sorts groups by names
     */
    private void sortGroups() {
        groups.sort(comparing(Group::getName));
    }

    /**
     * Searches and deletes a good
     * @param name of a good
     */
    public void deleteGood(String name) {
        for (Group group : groups) {
            for (Good good : group.getGoods()) {
                if (good.getName().equals(name)) {
                    group.deleteGood(good);
                    return;
                }
            }
        }
    }
}
