public class Good {
    private String name;
    private String description;
    private String manufacturer;
    private int amount;
    private float price;

    public Good(String name, String description, String manufacturer, int amount, float price) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.amount = amount;
        this.price = price;
    }

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

    public void addAmount(int amount) {
        this.amount += amount;
    }
    public void reduceAmount(int amount) {
        this.amount -= amount;
    }

    @Override
    public String toString() {
        return name + " | опис: " + description + " | виробник: " + manufacturer + " | кількість на складі: " + amount + " | ціна: " + price;
    }
}
