import java.util.Arrays;

public class Group {
    private Good[] goods;
    private String name;

    /**
     * Creates a new group
     * @param name of a group
     */
    public Group(String name) {
        this.name = name;
    }

    /**
     * Creates a new group when program starts(for initially created groups)
     * @param name of a group
     * @param goods array of goods
     */
    public Group(String name, Good[] goods) {
        this.name = name;
        this.goods = goods;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Good[] getGoods() {
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
        goods = Arrays.copyOf(goods, goods.length + 1);
        goods[goods.length - 1] = good;
    }
    public void deleteGood() {

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

    @Override
    public String toString() {
        return name + " containing " + goods.length + " goods";
    }
}
