import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookUtils {

    private String addressBookName;

    public String getAddressBookName() {
        return addressBookName;
    }

    public void setAddressBookName(String addressBookName) {
        this.addressBookName = addressBookName;
    }

    public static ArrayList<Person> personData;
    public Map<String, Person> nameToContactDetailsMap;
    public Map<String, ArrayList<String>> cityToPersonsMap;
    private Map<String, ArrayList<String>> stateToPersonsMap;

    public AddressBookUtils() {
        personData = new ArrayList<Person>();
        nameToContactDetailsMap = new HashMap<>();
        cityToPersonsMap = new HashMap<>();
        stateToPersonsMap = new HashMap<>();
    }

    public void addContacts(Person contactPerson) {
        System.out.println("-------Adding a Contact---- " + contactPerson.firstName);
        personData.add(contactPerson);
        nameToContactDetailsMap.put(contactPerson.firstName, contactPerson);

        cityToPersonsMap.computeIfAbsent(contactPerson.city, k -> new ArrayList<>()).add(contactPerson.firstName);
        stateToPersonsMap.computeIfAbsent(contactPerson.state, k -> new ArrayList<>()).add(contactPerson.firstName);
    }

    public Person getContactInfo(Scanner input) {
        String fName = "", lName = "";

        boolean duplicateName = false;

        do {
            System.out.println("Enter first name:");
            input.nextLine();
            fName = input.nextLine();
            System.out.println("Enter last name:");
            lName = input.nextLine();
            duplicateName = checkDuplicateName(fName, lName);
        } while (duplicateName);

        System.out.println("Enter the address:");
        String address = input.nextLine();
        System.out.println("Enter the city");
        String city = input.nextLine();
        System.out.println("Enter the state:");
        String state = input.nextLine();
        System.out.println("zip");
        long zip = input.nextLong();
        System.out.println("Enter the phone no:");
        long phoneNo = input.nextLong();
        input.nextLine();
        System.out.println("Enter the email:");
        String email = input.nextLine();

        Person contact = new Person(fName, lName, address, city, state, zip, phoneNo, email);
        return contact;
    }

    public boolean checkDuplicateName(String fName, String lName) {

        boolean nameExists = personData.stream()
                .anyMatch(n -> n.firstName.equals(fName) && n.lastName.equals(lName));

        if (nameExists == true) {
            System.out.println("Contact with this name already exists. Try again.");
        }
        return nameExists;
    }

    public void viewContacts() {
        if (personData.size() == 0) {
            System.out.println("No contacts added.");
        } else {
            for (int i = 0; i < personData.size(); i++) {
                int num = i + 1;
                System.out.println("Showing contact details contact no. " + num);
                System.out.println(personData.get(i).firstName + " " + personData.get(i).lastName);
                System.out.println(personData.get(i).houseNo + " " + personData.get(i).city + " "
                        + personData.get(i).state + " " + personData.get(i).zip);
                System.out.println(personData.get(i).phoneNo);
                System.out.println(personData.get(i).email);
            }
        }
    }

    public void viewSortedByNames() {
        List<Person> sortedDetails = personData.stream().sorted(Comparator.comparing(n -> n.toString()))
                .peek(n -> System.out.println(n)).collect(Collectors.toList());
    }

    public void viewSortedByCity() {
        List<Person> sortedDetailsByCity = personData.stream()
                .sorted((ab1, ab2) -> ab1.getCity().compareTo(ab2.getCity()))
                .peek(addBook -> System.out.println(addBook)).collect(Collectors.toList());
    }

    public void viewSortedByState() {
        List<Person> sortedDetailsByCity = personData.stream()
                .sorted((ab1, ab2) -> ab1.getState().compareTo(ab2.getState()))
                .peek(addBook -> System.out.println(addBook)).collect(Collectors.toList());
    }

    public void editContact(Scanner input) {
        System.out.println("Enter first name of contact to edit it");
        String cName = input.nextLine();

        if (nameToContactDetailsMap.containsKey(cName)) {
            Person editContact = nameToContactDetailsMap.get(cName);
            System.out.println("Which details you would like to edit?");
            System.out.println("Press - 1 for first name \n2 for last name \n"
                    + "3 for address \n4 for city \n5 for state \n6 for zip \n" + "7 for phone no. \n8 for email");

            int num = input.nextInt();
            switch (num) {
                case 1:
                    System.out.println("Enter new first name");
                    input.nextLine();
                    String fName = input.nextLine();
                    editContact.setFirstName(fName);
                    break;
                case 2:
                    System.out.println("Enter new last name");
                    input.nextLine();
                    String lName = input.nextLine();
                    editContact.setLastName(lName);
                    break;
                case 3:
                    System.out.println("Enter new address");
                    input.nextLine();
                    String add = input.nextLine();
                    editContact.setLastName(add);
                    break;
                case 4:
                    System.out.println("Enter new city");
                    input.nextLine();
                    String city = input.nextLine();
                    editContact.setCity(city);
                    break;
                case 5:
                    System.out.println("Enter new state");
                    input.nextLine();
                    String state = input.nextLine();
                    editContact.setState(state);
                    break;
                case 6:
                    System.out.println("Enter new zip");
                    long zip = input.nextLong();
                    editContact.setZip(zip);
                    break;
                case 7:
                    System.out.println("Enter new zip");
                    long phNum = input.nextLong();
                    editContact.setPhoneNo(phNum);
                    break;

                case 8:
                    System.out.println("Enter new zip");
                    input.nextLine();
                    String email = input.nextLine();
                    editContact.setEmail(email);
                    break;
                default:
                    System.out.println("No edits");
                    return;
            }
        } else {
            System.out.println("No such contact");
        }

    }

    public void deleteContact(Scanner input) {
        System.out.println("Enter first name of contact to delete it");
        String cName = input.nextLine();

        if (nameToContactDetailsMap.containsKey(cName)) {
            Person conInfo = nameToContactDetailsMap.get(cName);
            nameToContactDetailsMap.remove(cName, conInfo);
            personData.remove(conInfo);
        } else {
            System.out.println("No such contact to delete");
        }
    }

    public AddressBookUtils addressBookOption() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to the address book system. Choose your option");
        AddressBookUtils addBook = new AddressBookUtils();
        ReaderWriter addFileIO = new ReaderWriter();
        boolean runLoop = true;
        while (runLoop) {
            System.out.println("Press 1 for adding contact \nPress 2 to view contacts "
                    + "\nPress 3 to edit a contact \nPress 4 to delete a contact"
                    + " \nPress 5 to view sorted address book \nPress 6 to write contact to file"
                    + "\nPress 7 to read from file \nPress 8 to exit");
            int ch = userInput.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("---- Add contact details ---- ");
                    Person contactPerson = addBook.getContactInfo(userInput);
                    addBook.addContacts(contactPerson);
                    System.out.println("Contact added for " + contactPerson.firstName + " " + contactPerson.lastName);
                    break;

                case 2:
                    System.out.println("----view contacts --- ");
                    addBook.viewContacts();

                    break;

                case 3:
                    System.out.println("---- Editing contacts---");
                    addBook.editContact(userInput);
                    break;

                case 4:
                    System.out.println("---- Delete a contact---");
                    addBook.deleteContact(userInput);
                    break;

                case 5:
                    System.out.println("------Sorted by name----");
                    addBook.viewSortedByNames();
                    System.out.println("------Sorted by city----");
                    addBook.viewSortedByCity();
                    break;

                case 6:
                    System.out.println(" --- Writing to file ----");
                    addFileIO.writeToFile(personData);
                    break;

                case 7:
                    System.out.println("---- Reading from file ----");
                    addFileIO.readFromFile();
                    break;

                case 8:
                    System.out.println("exit");
                    runLoop = false;
                    break;

                default:
                    System.out.println("No correct option chosen");

            }
        }
        return addBook;
    }

}
