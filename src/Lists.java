import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Lists {
    transient Scanner keyboard = new Scanner(System.in);

    private ArrayList<List> lists = new ArrayList<List>();

    public Lists() {
        this.lists = new ArrayList<List>();

        if (this.getLists().size() == 0)
            this.readList();
    }

    private List getListById(int id) {
        for (List list : this.lists)
            if (list.getId() == id)
                return list;

        return null;
    }

    public ArrayList<List> getLists() {
        return this.lists;
    }

    public void addList(List list) {
        this.lists.add(list);
        this.writeList();
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

        Category category = list.getCategory();
        if (category == null)
            System.out.println("\nDeseja adicionar a categoria?");
        else
            System.out.println("\nDeseja modificar a categoria " + category.toString() + "?");

        System.out.println("1. Sim");
        System.out.println("2. Não");
        choice = keyboard.nextInt();

        if (choice == 1) {
            System.out.println("Novo nome da categoria: ");
            String newCategoryName = keyboard.next();

            Category newCategory = new Category(newCategoryName);

            list.setCategory(newCategory);
        }

        this.writeList();

        System.out.println("\nLista editada com sucesso!");
    }

    public void removeListById(int id) {
        List deletedList = this.getListById(id);

        if (deletedList == null) {
            System.out.println("\nLista não existente.");
            return;
        }

        this.lists.remove(deletedList);

        this.writeList();

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
            if (list.getCategory() != null)
                System.out.println("\n=*=*=*= " + list.getName() + " - " + list.getCategory().toString() + " =*=*=*=");
            else
                System.out.println("\n=*=*=*= " + list.getName() + " =*=*=*=");

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

                    this.writeList();

                    break;
                case 2:
                    System.out.println("\nId do item que deseja remover: ");
                    int deletedId = Integer.parseInt(keyboard.next());

                    list.removeListItemById(deletedId);

                    this.writeList();

                    break;
                case 3:
                    System.out.println("\nId do item que deseja editar: ");
                    int editedId = Integer.parseInt(keyboard.next());

                    ListItem itemToEdit = list.getListItem(editedId);

                    if (itemToEdit == null) {
                        System.out.println("\nItem não existente.");
                        return;
                    }

                    System.out.println("\nDeseja modificar o nome " + itemToEdit.getName() + "?");
                    System.out.println("1. Sim");
                    System.out.println("2. Não");
                    int userChoice = keyboard.nextInt();

                    if (userChoice == 1) {
                        System.out.println("\nNovo nome do item: ");
                        itemToEdit.setName(keyboard.next());
                    }

                    System.out.println("\nDeseja modificar a quantidade " + itemToEdit.getAmount() + "?");
                    System.out.println("1. Sim");
                    System.out.println("2. Não");
                    userChoice = keyboard.nextInt();

                    if (userChoice == 1) {
                        System.out.println("Quantidade do item: ");
                        itemToEdit.setAmount(keyboard.nextDouble());
                    }

                    System.out.println("\nItem editado com sucesso!");

                    this.writeList();

                    break;
                case 4:
                    System.out.println(list.toString());
                    break;
                case 5:
                    System.out.println("\nNome da categoria: ");
                    String categoryName = keyboard.next();

                    Category category = new Category(categoryName);

                    list.setCategory(category);

                    this.writeList();

                    break;
                case 6:
                    list.removeCategory();

                    System.out.println("\nCategoria removida!");

                    this.writeList();

                    break;
                case 7:
                    break;
                default:
                    System.out.print("\nPor favor, selecione um número de 1-7.\n");
            }
        } while (choice != 7);
    }

    public String toString() {
        Collections.sort(this.lists);

        String lists = "";

        for (List list : this.lists) {
            if (list.getCategory() != null) {
                String listCategory = list.getCategory().getName();
                lists += list.getId() + " - " + list.getName() + " - " + listCategory + "\n";
            } else {
                lists += list.getId() + " - " + list.getName() + "\n";
            }
        }

        return lists;
    }

    public void writeList() {
        try (
                FileOutputStream listFile = new FileOutputStream("Lists.ser");
                ObjectOutputStream listStream = new ObjectOutputStream(listFile);) {
            for (List list : this.lists)
                listStream.writeObject(list);
        } catch (IOException e) {
            System.out.println("There was a problem writing the file");
            System.out.println(e);
        }
    }

    public void readList() {
        List tempList;
        boolean endOfFile = false;

        try (
                FileInputStream listFile = new FileInputStream("Lists.ser");
                ObjectInputStream listStream = new ObjectInputStream(listFile);) {
            while (endOfFile == false) {
                try {
                    tempList = (List) listStream.readObject();

                    List.nextId++;

                    for (ListItem item : tempList.getItems())
                        item.nextId++;

                    this.lists.add(tempList);
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nNo previous file was read");
        } catch (ClassNotFoundException e) {
            System.out.println("\nTrying to read an object of an unknown class");
        } catch (StreamCorruptedException e) {
            System.out.println("\nUnreadable file format");
        } catch (IOException e) {
            System.out.println("\nerror: There was a problem reading the file");
            System.out.println(e);
        }
    }
}