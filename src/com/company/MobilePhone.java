package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;

    // Constructor
    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contact>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            System.out.println("Contact already exists");
            return false;
        }
        this.myContacts.add(contact);
        return true;
    }
    public boolean removeContact(Contact contact) {
        int foundPosition = findContact(contact);
        if (foundPosition >= 0) {
            this.myContacts.remove(foundPosition);
            System.out.println(contact.getName() + " was removed from contacts...");
            return true;
        }
        System.out.println("Couldn't find contact to remove...");
        return false;
    }

    public boolean updateContact(Contact contactToUpdate, Contact updatedContact) {
        int foundPosition = findContact(contactToUpdate);
        if (foundPosition < 0) {
            System.out.println(contactToUpdate.getName() + " was not found - cannot update...");
            return false;
        } else if (findContact(updatedContact.getName()) != -1) {
            System.out.println(updatedContact.getName() + " already exists in Contact List - cannot update...");
            return false;
        }
        this.myContacts.set(foundPosition, updatedContact);
        System.out.println(contactToUpdate.getName() + " " + contactToUpdate.getPhoneNumber() + " was updated " +
                           "to:" + "\n" +
                           updatedContact.getName() + " " + updatedContact.getPhoneNumber());
        return true;
    }
    public Contact queryContact(String name) {
        int foundPosition = findContact(name);
        if (foundPosition >= 0) {
            return this.myContacts.get(foundPosition);
        } else {
            return null;
        }
    }
    private int findContact(Contact contact) {
        return this.myContacts.indexOf(contact);
    }
    private int findContact(String contactName) {
        for (int i = 0; i < this.myContacts.size(); i++) {
            Contact contact = this.myContacts.get(i);
            if (contact.getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }
    public String searchForContact(Contact contact) {
        if (findContact(contact) >= 0) {
            return "Name: " + contact.getName() + "\n"
                    + "Number: " + contact.getPhoneNumber();
        }
        return null;
    }

    public void printContactList() {
        System.out.println("****Contact List****");
        for (int i = 0; i < this.myContacts.size(); i++) {
            System.out.println((i+1) + "." +
                               this.myContacts.get(i).getName() + " -> " +
                               this.myContacts.get(i).getPhoneNumber()
                               );
        }
    }
}
