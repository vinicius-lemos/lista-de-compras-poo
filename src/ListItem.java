import java.io.Serializable;

public class ListItem implements Comparable<ListItem>, Serializable {
    public static int nextId = 0;
    private int id;
    private String name;
    private double amount;
    private String unit;

    public ListItem(String name, double amount, String unit) {
        this.setId(++this.nextId);
        this.setName(name);
        this.setAmount(amount);
        this.setUnit(unit);
    }

    public ListItem(String name, double amount, Unit unit) {
        this.setId(++this.nextId);
        this.setName(name);
        this.setAmount(amount);
        this.setUnit(unit.getName());
    }

    public int compareTo(ListItem otherListItem) {
        if (this.id < otherListItem.id)
            return -1;
        if (this.id > otherListItem.id)
            return 1;
        return 0;
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

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String toString() {
        String formattedUnit = UnitOfMeasurement.format(this.getUnit());

        return this.getId() + " - " + this.getName() + " - " + this.getAmount() + " " + formattedUnit;
    }
}
