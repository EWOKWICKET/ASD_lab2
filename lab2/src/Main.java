public class Main {
    private static Storage storage;
    private static MainMenu mainMenu;

    public static void main(String[] args) {
        storage = Storage.getInstance();
        storage.readFiles();
        mainMenu = new MainMenu();
    }
}