public class MenuItem {

    private String name;
    private Runnable action;

    public MenuItem(String name, Runnable action) {

        this.name = name;
        this.action = action;

    }

    public String getName() {
        return this.name;
    }

    public void performAction() {
        if (action != null) {
            action.run();
        }
    }

}
