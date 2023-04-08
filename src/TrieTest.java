import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * TrieTest class tests the basic functionality of the Trie class.
 *
 * @authors Hayden Price, Kendra Jones, Nishant Athawale
 */
public class TrieTest {

	/*
	 * testInsertAutoComplete tests the functionality of the Trie class by inserting
	 * strings from a dictionary, querying the autocomplete method with a prefix and
	 * printing all suggested words that start with said prefix.
	 */
	@Test
	public void testInsertAutoComplete() {
		Trie instance = new Trie();
		getWords("src/dict.txt", instance);
		
		StringArray suggest = instance.autocomplete("th");
		System.out.println("Words with prefix 'th': ");
		for (int i = 0; i < suggest.size(); i++) {
			System.out.println(suggest.get(i));
		}
	}

	/*
	 * testSearch tests the functionality of the Trie class by querying the Trie for
	 * the words beginning with 'p' and then searching through that specific
	 * StringArray to narrow down search results to words that begin with 'pre' and
	 * prints out the result.
	 */
	@Test
	public void testSearch() {
		Trie instance = new Trie();
		getWords("src/common.txt", instance);

		StringArray check = instance.autocomplete("p");
		int count = 0;
		System.out.println("Checking for how many common words begin with prefix 'pre': ");
		for (int i = 0; i < check.size(); i++) {
			if (instance.search("pre")) {
				count++;
			}
		}
		System.out.println("A total of " + count + " common words being with prefix 'pre'.");

	}

	/*
	 * testDelete tests the functionality of the Trie class by querying the Trie for
	 * words beginning with 'p' and then deleting words that begin with 'pre' - this
	 * relies on the previous test to see if 1501 words can be successfully deleted.
	 */
	@Test
	public void testDelete() {
		Trie instance = new Trie();
		getWords("src/common.txt", instance);

		StringArray del = instance.autocomplete("p");
		for (int i = 0; i < del.size(); i++) {
			if (instance.startsWith("pre")) {
				instance.delete(del.get(i));
			}
		}
		int count = 0; // shouldn't ever be used; initialized just in case.
		for (int i = 0; i < del.size(); i++) {
			if (instance.startsWith("pre")) {
				count++;
			}
		}
		assertFalse(instance.startsWith("pre"));
		System.out.println("Successfully deleted all words beginning with prefix 'pre'.");

	}

	/*
	 * Another test for autocomplete. This test populates the Trie with strings from
	 * dict.txt and queries words with the prefix 'no'. It then narrows down search
	 * for words with prefix 'nov'.
	 */
	@Test
	public void testAutoComplete2() {
		Trie instance = new Trie();
		getWords("src/dict.txt", instance);

		StringArray suggest = instance.autocomplete("no");
		System.out.println("Did you mean... ");
		for (int i = 0; i < suggest.size(); i++) {
			System.out.println(suggest.get(i));
		}

		StringArray narrowDown = instance.autocomplete("nov");
		System.out.println("\nOkay, how about...");
		for (int i = 0; i < narrowDown.size(); i++) {
			System.out.println(narrowDown.get(i));
		}
	}

	/*
	 * Tests the functionality of the method startswith. Builds Trie from textfile,
	 * queries it for words with prefix 'pr'. Uses startsWith to confirm certains
	 * words either are/are not in the Trie.
	 */
	@Test
	public void testStartsWith() {
		Trie instance = new Trie();
		getWords("src/common.txt", instance);

		StringArray check = instance.autocomplete("pr");
		System.out.println("Checking for words that begin with prefix 'pr': ");

		for (int i = 0; i < check.size(); i++) {
			System.out.println(check.get(i));
		}

		assertTrue(instance.startsWith("pri"));
		assertTrue(instance.startsWith("pro"));
		assertFalse(instance.startsWith("pz"));

		instance.insert("pza");
		assertTrue(instance.startsWith("pz"));
	}

	/*
	 * Minor test to ensure startsWith and search methods in the Trie function
	 * properly on an empty Trie.
	 */
	@Test
	public void EmptyTrie() {
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		Trie full = new Trie();
		Trie empty = new Trie();

		getWords("src/dict.txt", full);
		StringArray check = full.autocomplete(String.valueOf(c));
		for (int i = 0; i < check.size(); i++) {
			assertFalse(empty.startsWith(check.get(i)));
			assertFalse(empty.search(check.get(i)));
		}

	}

	/**
	 * Private function that reads a file, parses it and takes each word to insert
	 * into the Trie.
	 * 
	 * @param fn   - the file to open.
	 * @param trie - the trie to insert into
	 */
	private static void getWords(String fn, Trie trie) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fn));
			String line = reader.readLine();
			while (line != null) {
				String[] split = line.split(System.lineSeparator());
				if (split.length < 2) {
					trie.insert(split[0]);
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
