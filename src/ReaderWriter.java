import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReaderWriter {
	public static String FILE_ADDRESS_BOOK = "address-book-file";

	// function to write to the file
	public void writeToFile(ArrayList<Person> contactList) throws IOException {
		System.out.println("Here");

		Path filePath = Paths
				.get("C:\\Users\\Gopinath Das\\eclipse-workspace\\addresssbbookk\\src\\addresssbbookk\\Add.txt");
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
		StringBuffer addressBookBuffer = new StringBuffer();
		contactList.forEach(book -> {
			String bookDataString = book.toString().concat("\n");
			addressBookBuffer.append(bookDataString);
		});

		FileWriter write = new FileWriter("src/addresssbbookk/new.csv");
		contactList.forEach(book -> {
			String bookDataString = book.toString().concat("\n");
			try {
				write.write(bookDataString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		write.close();
		try {
			Files.write(filePath, addressBookBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// function to read from the file
	public void readFromFile() throws IOException {
		Path filePath = Paths.get("AddressBook.txt");
		try {
			System.out.println("The contact details in the address book file are : ");
			Files.lines(filePath).map(line -> line.trim()).forEach(line -> System.out.println(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

