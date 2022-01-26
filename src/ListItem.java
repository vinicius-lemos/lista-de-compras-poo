public class ListItem {
    private static int nextId = 0;
    private int id;
    private String name;
    private double amount;

    public ListItem(String name, double amount) {
        this.setId(++this.nextId);
        this.setName(name);
        this.setAmount(amount);
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public String toString() {
        return getName() + " - " + getAmount();
    }
}
