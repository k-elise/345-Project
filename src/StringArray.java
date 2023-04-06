/**
 * A custom StringArray class that implements a dynamic array of strings. This
 * class provides basic functionalities for adding strings, retrieving strings
 * by index, and getting the size of the array. It is used by the Trie class to
 * store and manage strings while performing operations such as autocompletion.
 *
 * The StringArray class manages the underlying storage using a primitive string
 * array and resizes it when needed to accommodate new elements.
 * 
 * Wait... we've seen this implementation before... many times! Funny how we're
 * using it to implement a basic String.
 * 
 * @authors Hayden Price, Kendra Jones, Nishant Athawale
 */
public class StringArray {
	private String[] data;
	private int size;

	/**
	 * Initializes a new StringArray object with an initial capacity. The
	 * constructor also initializes an empty array of strings of the specified
	 * initial capacity.
	 */
	public StringArray() {
		data = new String[10];
		size = 0;
	}

	/**
	 * Adds a string to the end of the array, resizing the underlying storage if
	 * necessary to accommodate the new element. The method checks if the array is
	 * full and, if so, creates a new array with double the capacity, copies the
	 * existing elements to the new array, and sets the new array as the underlying
	 * storage.
	 *
	 * @param value The string to be added to the array.
	 */
	public void add(String value) {
		if (size == data.length) {
			resize();
		}
		data[size++] = value;
	}

	/**
	 * Retrieves the string at the specified index in the array.
	 *
	 * @param index The index of the string to retrieve.
	 * @return The string at the specified index.
	 * @throws IndexOutOfBoundsException if the index is out of the bounds of the
	 *                                   array.
	 */
	public String get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		return data[index];
	}

	/**
	 * Returns the number of strings in the array.
	 *
	 * @return The size of the array.
	 */
	public int size() {
		return size;
	}

	/**
	 * Resizes the underlying string array to the specified new capacity. This
	 * method creates a new array with the given capacity, copies the existing
	 * elements from the old array to the new array, and sets the new array as the
	 * underlying storage. This method is called internally by the 'add' method when
	 * the array becomes full and needs to be resized to accommodate new elements.
	 *
	 * The typical use case for resizing is to double the capacity each time the
	 * array becomes full, providing an amortized constant-time complexity for
	 * adding elements to the array.
	 *
	 * @param newCapacity The new capacity for the resized string array.
	 */
	private void resize() {
		String[] newData = new String[data.length * 2];
		System.arraycopy(data, 0, newData, 0, data.length);
		data = newData;
	}
}