import java.util.Arrays;

public class Storage {
    private Group[] groups;

    public Storage() {
        groups = new Group[4];
    }
    {
        //SET INITIAL GROUPS AND GOODS
    }
    public void addGroup(Group group) {
        groups = Arrays.copyOf(groups, groups.length + 1);
        groups[groups.length - 1] = group;
    }
    public void deleteGroup() {

    }
    public void changeGroup() {

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
}
