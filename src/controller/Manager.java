package controller;

import common.Library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Menu;

public class Manager extends Menu{

    Library l;
    
    public Manager (String td, String[] mc, String exit) {
        super (td, mc, exit);
        l = new Library();
    }
    
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
            {
                try {
                    createNewAccount();
                } catch (Exception ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            case 2:
            {
                try {
                    loginSystem();
                } catch (Exception ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            case 3:
                System.exit(0);
        }
    }
//    public int menu() {
//        System.out.println("1. Create a new account.");
//        System.out.println("2. Login system.");
//        System.out.println("3. Exit.");
//        System.out.print("Enter your choice: ");
//        int choice = Library.checkInputIntLimit(1, 3);
//        return choice;
//    }

    //create a new account
    public void createNewAccount() throws Exception {
        //check file data exist or not
        if (!Library.checkFileExist()) {
            return;
        }
        String username = Library.checkInputUsername();
        String password = Library.checkInputPassword();
        //check username exist or not
        if (!Library.checkUsernameExist(username)) {
            System.err.println("Username exist.");
            return;
        }
        addAccountData(username, password);
    }

    //login system
    public void loginSystem() throws Exception {
        //check file data exist or not
        if (!Library.checkFileExist()) {
            return;
        }
        String username = Library.checkInputUsername();
        String password = Library.checkInputPassword();
        //check username exist or not
        if (!Library.checkUsernameExist(username)) {
            if (!password.equalsIgnoreCase(passwordByUsername(username))) {
                System.err.println("Password incorrect.");
            }
            System.err.println("Login success.");
        }
    }

    //write new account to data
    public void addAccountData(String username, String password) throws Exception {
        File file = new File("D:/user.dat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(username + ";" + password + "\n");
            fileWriter.close();
            System.err.println("Create successly.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //get password by username
    public String passwordByUsername(String username) throws Exception {
        File file = new File("D:/user.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
