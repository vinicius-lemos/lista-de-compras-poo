import java.util.ArrayList;
import java.util.Scanner;

public class Lists {
    Scanner keyboard = new Scanner(System.in);

    private ArrayList<List> lists = new ArrayList<List>();

    public Lists() {
        this.lists = new ArrayList<List>();
    }

    private List getListById(int id) {
        for (List list : this.lists)
            if (list.getId() == id)
                return list;

        return null;
    }

    public void addList(List list) {
        this.lists.add(list);
    }

    public void editListById(int id) {
        List list = this.getListById(id);

        if (list == null) {
            System.out.println("\nLista não existente.");
            return;
        }

        System.out.println("\nDeseja modificar o nome " + list.getName() + "?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        int choice = keyboard.nextInt();

        if (choice == 1) {
            System.out.println("\nNovo nome da lista: ");
            list.setName(keyboard.next());
        }

        System.out.println("\nDeseja modificar a categoria " + list.getCategory().toString() + "?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        choice = keyboard.nextInt();

        if (choice == 1) {
            System.out.println("Novo nome da categoria: ");
            String newCategoryName = keyboard.next();

            Category newCategory = new Category(newCategoryName);

            list.setCategory(newCategory);
        }

        System.out.println("\nLista editada com sucesso!");
    }

    public void removeListById(int id) {
        List deletedList = this.getListById(id);

        if (deletedList == null) {
            System.out.println("\nLista não existente.");
            return;
        }

        this.lists.remove(deletedList);
        System.out.println("\nLista removida.");
    }

    public void selectListById(int id) {
        List list = this.getListById(id);

        if (list == null) {
            System.out.println("\nLista não existente.");
            return;
        }

        int choice;

        do {
            if (list.getCategory() != null) {
                System.out.println("\n=*=*=*= " + list.getName() + " - " + list.getCategory().toString() + " =*=*=*=");
            } else {
                System.out.println("\n=*=*=*= " + list.getName() + "+ =*=*=*=");
            }

            System.out.println("1. Adicionar um item");
            System.out.println("2. Remover um item");
            System.out.println("3. Editar um item");
            System.out.println("4. Listar os items");
            System.out.println("5. Adicionar/editar categoria");
            System.out.println("6. Remover categoria");
            System.out.println("7. Sair da categoria");
            System.out.print("Digite sua escolha: ");
            choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nNome do item: ");
                    String itemName = keyboard.next();

                    System.out.println("Quantidade do item: ");
                    double itemAmount = Double.parseDouble(keyboard.next());

                    ListItem item = new ListItem(itemName, itemAmount);

                    list.addListItem(item);
                    break;
                case 2:
                    System.out.println("\nId do item que deseja remover: ");
                    int deletedId = Integer.parseInt(keyboard.next());

                    list.removeListItemById(deletedId);
                    break;
                case 3:
                    System.out.println("\nId do item que deseja editar: ");
                    int editedId = Integer.parseInt(keyboard.next());

                    list.editListItemById(editedId);
                    break;
                case 4:
                    System.out.println(list.toString());
                    break;
                case 5:
                    System.out.println("\nNome da categoria: ");
                    String categoryName = keyboard.next();

                    Category category = new Category(categoryName);

                    list.setCategory(category);
                    break;
                case 6:
                    list.removeCategory();
                    break;
                case 7:
                    break;
                default:
                    System.out.print("\nPor favor, selecione um número de 1-7.\n");
            }
        } while (choice != 7);
    }

    public String toString() {
        if (this.lists.size() == 0)
            return "\nNão há listas cadastradas.\n";

        String lists = "";

        for (List list : this.lists) {
            String listCategory = list.getCategory().getName();
            lists += list.getId() + " - " + list.getName() + " - " + listCategory + "\n";
        }

        return lists;
    }
}
