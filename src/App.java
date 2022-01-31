import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner keyboard = new Scanner(System.in);

        int choice;
        Lists lists = new Lists();

        do {
            System.out.println("\n=*=*=*= Listas =*=*=*=");
            System.out.println("1. Adicionar uma lista");
            System.out.println("2. Selecionar uma lista");
            System.out.println("3. Listar todas as listas");
            System.out.println("4. Editar uma lista");
            System.out.println("5. Remover uma lista");
            System.out.println("6. Sair do sistema");
            System.out.print("Digite sua escolha: ");
            choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nNome da lista: ");
                    String listName = keyboard.next();

                    System.out.println("\nNome da categoria: ");
                    String categoryName = keyboard.next();

                    Category category = new Category(categoryName);

                    List newList = new List(listName, category);

                    lists.addList(newList);
                    break;
                case 2:
                    System.out.println("\nId da lista que deseja editar: ");
                    int selectedId = keyboard.nextInt();

                    lists.selectListById(selectedId);
                    break;
                case 3:
                    System.out.println(lists.toString());
                    break;
                case 4:
                    System.out.println("\nId da lista que deseja editar: ");
                    int editedId = keyboard.nextInt();

                    lists.editListById(editedId);

                    break;
                case 5:
                    System.out.println("\nId da lista que deseja remover: ");
                    int deletedId = keyboard.nextInt();

                    lists.removeListById(deletedId);
                    break;
                case 6:
                    System.out.println("\nAté a próxima :)\n");
                    break;
                default:
                    System.out.print("\nPor favor, selecione um número de 1-6.\n");
            }
        } while (choice != 6);

        keyboard.close();
    }
}
