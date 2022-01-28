import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("5. Transferir um item");
            System.out.println("6. Adicionar/editar categoria");
            System.out.println("7. Remover categoria");
            System.out.println("8. Sair da categoria");
            System.out.print("Digite sua escolha: ");
            choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nNome do item: ");
                    String itemName = keyboard.next();

                    System.out.println("Quantidade do item: ");
                    double itemAmount = Double.parseDouble(keyboard.next());

                    System.out.println("Unidade de medida do item: ");
                    System.out.println("1. Mililitro (mL)");
                    System.out.println("2. Litro (L)");
                    System.out.println("3. Grama (g)");
                    System.out.println("4. Quilograma (Kg)");
                    System.out.println("5. Centimetros (cm)");
                    System.out.println("6. Metro (m)");
                    System.out.println("7. Quilometro (Km)");
                    System.out.println("8. Outro");
                    int unitChoice = keyboard.nextInt();

                    Unit itemUnit = null;

                    if (unitChoice == 1)
                        itemUnit = Unit.mililitros;
                    else if (unitChoice == 2)
                        itemUnit = Unit.litros;
                    else if (unitChoice == 3)
                        itemUnit = Unit.gramas;
                    else if (unitChoice == 4)
                        itemUnit = Unit.quilogramas;
                    else if (unitChoice == 5)
                        itemUnit = Unit.centimetros;
                    else if (unitChoice == 6)
                        itemUnit = Unit.metros;
                    else if (unitChoice == 7)
                        itemUnit = Unit.quilometros;
                    else {
                        System.out.println("Digite a unidade de medida: ");
                        String customUserUnit = keyboard.next();

                        ListItem item = new ListItem(itemName, itemAmount, customUserUnit);
                        list.addListItem(item);
                        break;
                    }

                    ListItem item = new ListItem(itemName, itemAmount, itemUnit);
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
                    list.transferListItem();
                case 6:
                    System.out.println("\nNome da categoria: ");
                    String categoryName = keyboard.next();

                    Category category = new Category(categoryName);

                    list.setCategory(category);
                    break;
                case 7:
                    list.removeCategory();
                    break;
                case 8:
                    break;
                default:
                    System.out.print("\nPor favor, selecione um número de 1-8.\n");
            }
        } while (choice != 8);
    }

    public String toString() {
        if (this.lists.size() == 0)
            return "\nNão há listas cadastradas.\n";

        Collections.sort(this.lists);

        String lists = "";

        for (List list : this.lists) {
            String listCategory = list.getCategory().getName();
            lists += list.getId() + " - " + list.getName() + " - " + listCategory + "\n";
        }

        return lists;
    }
}
