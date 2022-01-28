import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class List implements Comparable<List> {
    Scanner keyboard = new Scanner(System.in);

    private static int nextId = 0;
    private int id = 0;
    private String name;
    private Category category;
    private ArrayList<ListItem> items = new ArrayList<ListItem>();

    public List(String name, Category category) {
        this.setId(++this.nextId);
        this.setName(name);
        this.setCategory(category);
    }

    public int compareTo(List otherList) {
        if (this.id < otherList.id)
            return -1;
        if (this.id > otherList.id)
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }

    private ListItem getListItem(int id) {
        for (ListItem item : items)
            if (item.getId() == id)
                return item;

        return null;
    }

    public void addListItem(ListItem item) {
        this.items.add(item);
    }

    public void editListItemById(int id) {
        ListItem item = this.getListItem(id);

        if (item == null) {
            System.out.println("\nItem não existente.");
            return;
        }

        System.out.println("\nDeseja modificar o nome " + item.getName() + "?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        int choice = keyboard.nextInt();

        if (choice == '1') {
            System.out.println("\nNovo nome do item: ");
            item.setName(keyboard.next());
        }

        System.out.println("\nDeseja modificar a quantidade " + item.getAmount() + "?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        choice = keyboard.nextInt();

        if (choice == '1') {
            System.out.println("Quantidade do item: ");
            item.setAmount(keyboard.nextDouble());
        }

        System.out.println("\nItem editado com sucesso!");
    }

    public void removeListItemById(int id) {
        ListItem deletedItem = this.getListItem(id);

        if (deletedItem == null) {
            System.out.println("\nItem não existente.");
            return;
        }

        items.remove(deletedItem);
        System.out.println("\nItem removida.");
    }

    public void transferListItem() {
        System.out.println("\n=*=*=*= Items =*=*=*=");
        System.out.println(this.toString());

        System.out.println("\nId do item da lista que deseja transferir: ");
        int transferId = keyboard.nextInt();
    }

    public void removeCategory() {
        this.setCategory(null);
    }

    public void setNewCategoryName(String newName) {
        this.getCategory().setName(newName);
    }

    public String toString() {
        if (this.items.size() == 0)
            return "\nNão há itens cadastrados.\n";

        Collections.sort(this.items);

        String list = "";

        for (ListItem item : items)
            list += item + "\n";

        return list;
    }
}