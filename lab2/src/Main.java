public class Main {
    private static Storage storage;
    private static MainMenu mainMenu;

    public static void main(String[] args) {
        storage = Storage.getInstance();
        mainMenu = new MainMenu();
        System.out.println(storage.findGood("q"));
        System.out.println();
        System.out.println(storage.findGood("qwerty"));
    }
}