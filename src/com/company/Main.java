package com.company;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("555-555-5555");

    public static void main(String[] args) {
    boolean quit = false;
    startPhone();
    printActions();
    while(!quit) {
        System.out.println("\n Enter action: (6 to show available actions)");
        int action = scanner.nextInt();
        scanner.nextLine();

        switch(action) {
            case 0:
                System.out.println("\n Shutting down...");
                quit = true;
                break;
            case 1:
                mobilePhone.printContactList();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                modifyContact();
                break;
            case 4:
                removeContact();
                break;
            case 5:
                searchForContact();
                break;
            case 6:
                printActions();
                break;
            default:
                quit = true;
                break;
        }
    }
    }
    private static void startPhone() {
        System.out.println("Starting phone...");
    }
    private static void printActions() {
        System.out.println("\n Available phone actions: \n press");
        System.out.println("0 - to shutdown \n" +
                           "1 - to print contacts \n" +
                           "2 - to add new contact \n" +
                           "3 - to update existing contact \n" +
                           "4 - to remove contact \n" +
                           "5 - to search for contact"
                           );
    }
    private static void addNewContact() {
        System.out.println("Enter contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter contact phone number: ");
        String number = scanner.nextLine();
        Contact contact = Contact.createContact(name, number);
        if (mobilePhone.addNewContact(contact)) {
            System.out.println("New contact added! " + "\n" +
                               "Name: " + name + "\n" +
                               "Phone: " + number);
        } else {
            System.out.println("Contact " + name + " already exists...");
        }
    }
    private static void modifyContact() {
        System.out.println("Enter name of contact you want to update: ");
        String existingContactName = scanner.nextLine();
        Contact oldContact = mobilePhone.queryContact(existingContactName);
        if (oldContact != null) {
            System.out.println("Enter updated name for selected contact: ");
            String updatedContactName = scanner.nextLine();
            System.out.println("Enter updated number for selected contact");
            String updatedContactNumber = scanner.nextLine();
            Contact updatedContact = Contact.createContact(updatedContactName, updatedContactNumber);
            mobilePhone.updateContact(oldContact, updatedContact);
        } else {
            System.out.println("Contact " + existingContactName + " could not be found to update...");
        }
    }
    private static void removeContact() {
        System.out.println("Enter name of contact you want to remove: ");
        String contactToRemove = scanner.nextLine();
        Contact unwantedContact = mobilePhone.queryContact(contactToRemove);
        if (unwantedContact != null) {
            mobilePhone.removeContact(unwantedContact);
            System.out.println(contactToRemove + " has been removed from Contact List");
        } else {
            System.out.println(contactToRemove + " could not be found to remove...");
        }
    }
    private static void searchForContact() {
        System.out.println("Enter name of contact you are searching for: ");
        String contactSearch = scanner.nextLine();
        Contact contactToSearch = mobilePhone.queryContact(contactSearch);
        if (contactToSearch != null) {
            System.out.println(mobilePhone.searchForContact(contactToSearch));
        } else {
            System.out.println("Could not find searched contact in Contact List...");
        }
    }
}
