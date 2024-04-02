/*
Good class. Contains all necessary fields
 */

public class Good {
    private String name;
    private String description;
    private String manufacturer;
    private int amount;
    private float price;
    private String group;

    /**
     * Full constructor
     * @param group
     * @param name
     * @param description
     * @param manufacturer
     * @param amount
     * @param price
     */
    public Good(String group, String name, String description, String manufacturer, int amount, float price) {
        this.group = group;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.amount = amount;
        this.price = price;
    }
    //Getters and setters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public int getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @param amount to add to a good
     */
    public void addAmount(int amount) {
        this.amount += amount;
    }

    /**
     * @param amount of goods to reduce
     */
    public void reduceAmount(int amount) {
        this.amount -= amount;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return name + " | опис: " + description + " | виробник: " + manufacturer + " | кількість на складі: " + amount + " | ціна: " + price;
    }
}
