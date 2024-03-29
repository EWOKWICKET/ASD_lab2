import java.util.ArrayList;
import java.util.Arrays;

public class Group {
    private ArrayList<Good> goods;
    private String name;

    /**
     * Creates a new group
     * @param name of a group
     */
    public Group(String name) {
        this.name = name;
        goods = new ArrayList<>(2);
    }

    /**
     * Creates a new group when program starts(for initially created groups)
     * @param name of a group
     * @param goods array of goods
     */
    public Group(String name, ArrayList<Good> goods) {
        this.name = name;
        this.goods = goods;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Good> getGoods() {
        return goods;
    }

    /**Prints all goods of group*/
    public void getAllGroupGoods() {
        System.out.println("Group " + name + ":");      //Change to print in UI
        for (Good good : goods) {
            System.out.println(good);                   //Change to print in UI
        }
    }

    public void addGood(Good good) {
        goods.add(good);
    }
    public void deleteGood(Good good) {
        goods.remove(good);
    }
    public void changeGood() {

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

    public ArrayList<Good> findGood(String name) {
        ArrayList<Good> found = new ArrayList<>();
        for (Good good: goods) {
            if (good.getName().equals(name)) {
                found.add(good);
            }
        }
        return found;
    }

    @Override
    public String toString() {
        return name + " containing " + goods.size() + " goods";
    }
}
