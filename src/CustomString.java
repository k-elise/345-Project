/**
 * A custom CustomString class that provides basic string manipulation
 * functionalities, such as appending characters, retrieving characters by
 * index, and converting the CustomString object to a Java String. This class is
 * used by the Trie class to build and manage strings while performing
 * operations such as autocompletion and traversal.
 *
 * The CustomString class manages the underlying storage using a primitive char
 * array and resizes it when needed to accommodate new characters.
 * 
 * My only question is, how does StringBuilder or the likes do it? Is it much
 * faster? I didn't write tests to test the run times or analyze the
 * complexities.
 * 
 * @authors Hayden Price, Kendra Jones, Nishant Athawale
 */
public class CustomString {
	private char[] chars;
	private int length;

	/**
	 * Initializes a new CustomString object with an initial capacity. The
	 * constructor also initializes an empty char array of the specified initial
	 * capacity.
	 */
	public CustomString(String string) {
		chars = string.toCharArray();
		length = chars.length;
	}

	/**
	 * Appends a character to the end of the CustomString, resizing the underlying
	 * storage if necessary to accommodate the new character. The method checks if
	 * the char array is full and, if so, creates a new array with double the
	 * capacity, copies the existing characters to the new array, and sets the new
	 * array as the underlying storage.
	 *
	 * @param c The character to be appended to the CustomString.
	 */
	public void append(char c) {
		if (length == chars.length) {
			resize();
		}
		chars[length++] = c;
	}

	/**
	 * Retrieves the character at the specified index in the CustomString.
	 *
	 * @param index The index of the character to retrieve.
	 * @return The character at the specified index.
	 * @throws IndexOutOfBoundsException if the index is out of the bounds of the
	 *                                   CustomString.
	 */
	public void deleteLastChar() {
		if (length > 0) {
			length--;
		}
	}

	/**
	 * Converts the CustomString object to a Java String.
	 *
	 * @return A Java String representation of the CustomString.
	 */
	public String toString() {
		return new String(chars, 0, length);
	}

	/**
	 * Resizes the underlying char array to the specified new capacity. This method
	 * creates a new array with the given capacity, copies the existing characters
	 * from the old array to the new array, and sets the new array as the underlying
	 * storage. This method is called internally by the 'append' method when the
	 * array becomes full and needs to be resized to accommodate new characters.
	 *
	 * The typical use case for resizing is to double the capacity each time the
	 * array becomes full, providing an amortized constant-time complexity for
	 * appending characters to the CustomString.
	 */
	private void resize() {
		char[] newChars = new char[chars.length * 2];
		System.arraycopy(chars, 0, newChars, 0, chars.length);
		chars = newChars;
	}
}