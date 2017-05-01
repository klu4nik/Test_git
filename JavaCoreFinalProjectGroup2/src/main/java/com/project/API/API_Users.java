package com.project.API;

import com.project.Entity.User;
import com.project.Util.PasswordHashGenerator;
import com.project.Controller.UserController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by MYKOLA.GOROKHOV on 27.04.2017.
 */
public class API_Users {
    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";
    private final static String ITEM_3 = "3";
    private final static String EXIT = "q";

    private UserController userController = new UserController();

    public void run() throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
        String choice;
        do {
            drawMainMenu();
            //-----------------
            Scanner scanner = new Scanner(System.in);
            choice = String.valueOf(scanner.next().toLowerCase().charAt(0));
            //-----------------


            switch (choice) {
                case ITEM_1:
                    AddUserMenu();
                    break;
                case ITEM_2:
                    editUserMenu();
                    break;
                case ITEM_3:
                    deleteUserMenu();
                    break;
                case EXIT:
                    System.out.println("Exiting . . .");
                    userController.flush();
                    break;
                default:
                    System.out.println("Wrong Choice");
                    break;
            }

        } while (!choice.equals(EXIT));


    }

    private void deleteUserMenu() throws NoSuchAlgorithmException {
        cls();
        System.out.println("+-----------------------------------------+");
        System.out.println("|          DELETE USER MENU               |");
        System.out.println("+-----------------------------------------+");
        System.out.println();
        System.out.println("Введите Login, удаляемого  пользователя: ");

        //-----------------
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        while (userController.findUserByLogin(login) == null && !login.equals("")) {

            System.out.println("Такой пользователь не зарегистрирован ...");
            System.out.println("Попробуйте еще раз.\n");
            System.out.println("Введите Login, для удаляемого пользователя: ");
            login = scanner.nextLine();
        }
        if (!login.equals("")) {
            System.out.println();
            System.out.println("Пользователь удаляется . . . ");
            userController.deleteUserByLogin(login);
        }

    }

    private void editUserMenu() throws NoSuchAlgorithmException {
        cls();
        System.out.println("+-----------------------------------------+");
        System.out.println("|            EDIT USER MENU               |");
        System.out.println("+-----------------------------------------+");

        System.out.println();
        System.out.println();
        System.out.println("Введите Login пользователя: ");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        while (userController.findUserByLogin(login) == null && !login.equals("")) {
            System.out.println("Такой пользователь не зарегистрирован в системе ...");
            System.out.println("Попробуйте еще раз.\n");
            System.out.println("Введите Login пользователя: ");
            login = scanner.nextLine();
        }
        if (!login.equals("")) {
            User oldUser = userController.findUserByLogin(login);
            System.out.println("Вы редактируете данные : " + oldUser);

            System.out.println();
            System.out.println("Введите Новую Фамилию (Enter оставить старую): ");
            String lastName = scanner.nextLine();
            if (lastName.equals("")) {
                lastName = oldUser.getLastName();
            }
            System.out.println("Новая Фамилия :" + lastName);
            System.out.println("");
            System.out.println("Введите Новое Имя (Enter оставить старую): ");
            String firstName = scanner.nextLine();
            if (firstName.equals("")) {
                firstName = oldUser.getFirsName();
            }
            System.out.println("Новое Имя :" + firstName);
            System.out.println();
            System.out.println("Введите Новый пароль: ");
            String password = scanner.nextLine();

            System.out.println();
            System.out.println("Пользователь Редактируется . . . ");
            userController.deleteUserByLogin(oldUser.getLogin());
            userController.addUser(new User(firstName, lastName, login, PasswordHashGenerator.generate(password)));
        }
    }

    private void AddUserMenu() throws NoSuchAlgorithmException {
        cls();
        System.out.println("+-----------------------------------------+");
        System.out.println("|             ADD USER MENU               |");
        System.out.println("+-----------------------------------------+");
        System.out.println();
        System.out.println("Введите Login, для нового пользователя: ");

        //-----------------
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        while (userController.findUserByLogin(login) != null && !login.equals("")) {
            System.out.println("Такой пользователь уже зарегистрирован ...");
            System.out.println("Попробуйте еще раз.\n");
            System.out.println("Введите Login, для нового пользователя: ");
            login = scanner.nextLine();

        }
        if (!login.equals("")) {
            System.out.println("Login: " + login);

            System.out.println();
            System.out.println("Введите Фамилию, нового пользователя: ");
            String lastName = scanner.nextLine();
            System.out.println("Фамилия :" + lastName);
            System.out.println("");
            System.out.println("Введите Имя, нового пользователя: ");
            String firstName = scanner.nextLine();
            System.out.println("Имя :" + firstName);
            System.out.println();
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();

            System.out.println();
            System.out.println("Пользователь добавляется . . . ");
            userController.addUser(new User(firstName, lastName, login, PasswordHashGenerator.generate(password)));
        }

    }


    private void drawMainMenu() {
        cls();
        System.out.println("+-----------------------------------------+");
        System.out.println("|                USER MENU                |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|" + ITEM_1 + ". Зарегистрировать пользователя         |");
        System.out.println("|" + ITEM_2 + ". Редактировать данные пользователя     |");
        System.out.println("|" + ITEM_3 + ". Удалить пользователя                  |");
        System.out.println("|                                         |");
        System.out.println("|" + EXIT + ". В предыдущее меню                     |");
        System.out.println("+-----------------------------------------+");
    }

    private void cls() {
        for (int i = 1; i <= 300; i++) {
            System.out.println();
        }
    }


}