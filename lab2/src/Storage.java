import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private ArrayList<Group> groups;

    public Storage() {
        groups = new ArrayList<>(3);
    }
//    {
//        //SET INITIAL GROUPS AND GOODS
//        groups.add(new Group("T1"));
//        groups.add(new Group("T2"));
//        groups.add(new Group("T3"));
//        groups.get(0).addGood(new Good("G1", "fwe", "me", 10, 25));
//        groups.get(0).addGood(new Good("G2", "fwe", "me", 10, 25));
//        groups.get(1).addGood(new Good("R1", "fwe", "me", 10, 25));
//        groups.get(1).addGood(new Good("R2", "fwe", "me", 10, 25));
//        groups.get(2).addGood(new Good("K1", "fwe", "me", 10, 25));
//        groups.get(2).addGood(new Good("K2", "fwe", "me", 10, 25));
//    }
    public void addGroup(Group group) {
        groups.add(group);
    }
    public void deleteGroup(Group group) {
        groups.remove(group);
    }
    public void changeGroup(Group group) {

    }

    /**
     * @return price of all goods on storage
     */
    public float getStoragePrice() {
        float sum = 0;
        for (Group group: groups) {
            sum += group.getGroupPrice();
        }
        return sum;
    }

    /**Prints all gods on storage*/
    public void getAllStorageGoods() {
        for (Group group: groups) {
            for (Good good: group.getGoods()) {
                System.out.println(good);           //Change to print in UI
            }
        }
    }

    public void findGood(String name) {
        Good found = null;
        for (Group group: groups) {
            found = group.findGood(name);
        }
        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("No goods were found");
        }
    }

//    public void updateFiles() {
//        StringBuilder text = new StringBuilder();
//        for (Group group: groups) {
//            for (Good good: group.getGoods()) {
//
//            }
//        }
//    }
}
