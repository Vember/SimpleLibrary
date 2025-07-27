import java.util.LinkedHashMap;
import java.util.Map;

public class MenuUI {

    private LinkedHashMap<String, MenuItem> menuItems;

    public MenuUI() {
        this.menuItems = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, MenuItem> getHashMap() {
        return this.menuItems;
    }

    public void addMenuItem(String key, String name, Runnable action) {
        menuItems.put(key, new MenuItem(name, action));
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("--- Menu ---");
        for (Map.Entry<String, MenuItem> entry : menuItems.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }
        System.out.println("------------");
        System.out.println();
    }

    public void selectMenuItem(String key) {
        MenuItem item = menuItems.get(key);
        if (item != null) {
            item.performAction();
        } else {
            System.out.println("Invalid menu selection.");
        }
    }

}
