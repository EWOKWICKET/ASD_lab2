/*
Group class. Contains all necessary fields and methods
 */

import java.util.ArrayList;

import static java.util.Comparator.comparing;

public class Group {
    private ArrayList<Good> goods;
    private String name;

    /**
     * Creates a new group
     *
     * @param name of a group
     */
    public Group(String name) {
        this.name = name;
        goods = new ArrayList<>(1);
    }

    /**
     * Creates a new group when program starts(for initially created groups)
     *
     * @param name  of a group
     * @param goods array of goods
     */
    public Group(String name, ArrayList<Good> goods) {
        this.name = name;
        this.goods = goods;
        //sortGoods();
    }
    //Setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        for (Good good : goods) {
            good.setGroup(name);
        }
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    /**
     * @return all goods in group
     */
    public String getAllGroupGoods() {
        StringBuilder text = new StringBuilder();
        text.append("Група " + name + ":\n");
        for (Good good : goods) {
            text.append(good + "\n");
        }
        return text.toString();
    }

    /** Adds a new good
     * @param good - good to be added
     */
    public void addGood(Good good) {
        goods.add(good);
        sortGoods();
    }

    /** Deletes a good
     * @param good - good to be deleted
     */
    public void deleteGood(Good good) {
        goods.remove(good);
    }

    /**
     * @return price of all goods in group
     */
    public float getGroupPrice() {
        float sum = 0;
        for (Good good : goods) {
            sum += good.getAmount() * good.getPrice();
        }
        return sum;
    }

    /**
     *
     * @param name - pattern for search
     * @return ArrayList of found goods
     */
    public ArrayList<Good> findGood(String name) {
        ArrayList<Good> found = new ArrayList<>();
        for (Good good : goods) {
//            if (good.getName().matches(name + "([0-9_A-Za-zА-Яа-я]*\\s*){3}")) {
//                found.add(good);
//            }
            if (good.getName().startsWith(name)) {
                found.add(good);
            }
        }
        return found;
    }

    /**
     * sorts goods by name
     */
    private void sortGoods() {
        goods.sort(comparing(Good::getName));
    }

    @Override
    public String toString() {
        return name + " containing " + goods.size() + " goods";
    }
}
