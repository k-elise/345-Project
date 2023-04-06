/**
 * TrieTest class tests the basic functionality of the Trie class.
 *
 * @authors Hayden Price, Kendra Jones, Nishant Athawale
 */
public class TrieTest {
	public static void main(String[] args) {
		Trie trie = new Trie();

		// test insertion of words
		trie.insert("hello");
		trie.insert("world");
		trie.insert("trie");
		trie.insert("tree");

		// test searching for words
		System.out.println("Searching for 'hello': " + trie.search("hello"));
		System.out.println("Searching for 'world': " + trie.search("world"));
		System.out.println("Searching for 'trie': " + trie.search("trie"));
		System.out.println("Searching for 'tree': " + trie.search("tree"));

		// test deletion
		trie.delete("hello");
		System.out.println("Searching for 'hello' after deletion: " + trie.search("hello"));

		// test autocomplete
		System.out.println("Words with prefix 'tr':");
		StringArray suggestions = trie.autocomplete("tr");
		for (int i = 0; i < suggestions.size(); i++) {
			System.out.println(suggestions.get(i));
		}
	}
}