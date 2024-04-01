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
    private static BufferedWriter bw;

    private Storage() {
//        groups.add(new Group("Крупи"));
//        groups.add(new Group("T2"));
//        groups.add(new Group("T3"));
//        groups.get(0).addGood(new Good("Крупи", "qwerty", "fwe", "me", 10, 25));
//        groups.get(0).addGood(new Good("Крупи", "qwertyqefw", "fwe", "me", 10, 25));
//        groups.get(0).addGood(new Good("Крупи", "qwery", "fwe", "me", 10, 25));
//        groups.get(1).addGood(new Good("T2", "quarry", "fwe", "me", 10, 25));
//        groups.get(1).addGood(new Good("T2", "quabrry", "fwe", "me", 10, 25));
//        groups.get(1).addGood(new Good("T2", "qq", "fwe", "me", 10, 25));
//        groups.get(2).addGood(new Good("T3", "jrtwre", "fwe", "me", 10, 25));
//        groups.get(2).addGood(new Good("T3", "ewgew", "fwe", "me", 10, 25));

    }

    public static Storage getInstance() {
        // Якщо екземпляр ще не існує, створюємо його
        if (instance == null) {
            instance = new Storage();
        }
        // Повертаємо єдиний екземпляр класу
        return instance;
    }

    public void addGroup(Group group) {
        groups.add(group);
        sortGroups();
    }

    public void deleteGroup(Group group) {
        groups.remove(group);
    }

    public void changeGroup(Group group) {

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
     * Prints all gods on storage
     */
    public String getAllStorageGoods() {
        StringBuilder text = new StringBuilder();
        for (Group group : groups) {
            text.append(group.getAllGroupGoods());
        }
        return text.toString();
    }

    /**
     * Searches for goods with correct pattern
     *
     * @param name pattern to use for search
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
     * Opens and updates each file
     *
     * @param name of a group and a file
     * @throws IOException
     */
    private static void updateFile(String name) throws IOException {
        try {
            bw = new BufferedWriter(new FileWriter("lab2/Groups/" + name+".txt"));
        } catch (IOException e) {
            System.out.println("File not found");
        }
        for (Good good : (groups.get(findGroup(name))).getGoods()) {
            bw.write(good.writeInFile()+ "\n");
        }
        bw.close();
    }

    public void readFiles(){
        File folder=new File("lab2/Groups");
        File[] fileList=folder.listFiles();
        for(File file:fileList){
            readFile(file);
        }
        sortGroups();
    }

    private void readFile(File file) {
        try {
            BufferedReader br=new BufferedReader(new FileReader(file.getPath()));
            String groupName=file.getName();
            groupName=groupName.replaceAll(".txt", "");
            ArrayList<Good> goods=new ArrayList<>();
            String goodString;
            do{
                goodString= br.readLine();
                if(goodString!=null && !goodString.isBlank()){
                    createNewGood(goodString, goods, groupName);
                }

            }while(goodString!=null);
            groups.add(new Group(groupName,goods));
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e){
            System.out.println("Error in reading");
        }
    }

    private void createNewGood(String goodString, ArrayList<Good> goods, String groupName) {
        StringTokenizer st=new StringTokenizer(goodString, "|");

        String name=st.nextToken();

        StringTokenizer desSt=new StringTokenizer(st.nextToken(), ":");
        String descr=desSt.nextToken();
        descr=desSt.nextToken();

        StringTokenizer mnfSt=new StringTokenizer(st.nextToken(), ":");
        String mnf=mnfSt.nextToken();
        mnf=mnfSt.nextToken();

        StringTokenizer amountSt=new StringTokenizer(st.nextToken(), ":");
        String amountStr=amountSt.nextToken();
        amountStr=amountSt.nextToken();
        int amount=Integer.parseInt(amountStr);

        StringTokenizer priceSt=new StringTokenizer(st.nextToken(), ":");
        String priceStr=priceSt.nextToken();
        priceStr=priceSt.nextToken();
        float price=Float.parseFloat(priceStr);

        goods.add(new Good(groupName, name,descr,mnf,amount,price));
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

    private void sortGroups() {
        groups.sort(comparing(Group::getName));
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

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
