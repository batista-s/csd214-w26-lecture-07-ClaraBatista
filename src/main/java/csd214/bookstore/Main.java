package csd214.bookstore;

import csd214.bookstore.repositories.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Persistence Strategy:");
        System.out.println("1. ArrayList (Memory - Sequential)");
        System.out.println("2. HashMap (Memory - Indexed)");
        System.out.println("3. H2 Database (In-Memory SQL)");
        System.out.println("4. MySQL (Production)");
        System.out.print("Choice: ");

        int choice = sc.nextInt();
        IRepository repository;

        // WIRING PHASE
        switch (choice) {
            case 2: repository = new InMemoryMapRepository(); break;
            case 3: repository = new H2Repository(); break;
            case 4: repository = new MySqlRepository(); break;
            default: repository = new InMemoryListRepository(); break;
        }

        // INJECTION PHASE
        App app = new App(repository);
        try {
            app.run();
        } finally {
            repository.close();
        }
    }
}