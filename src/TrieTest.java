public class TrieTest {
	public static void main(String[] args) {
		Trie trie = new Trie();

		// Insert words
		trie.insert("hello");
		trie.insert("world");
		trie.insert("trie");
		trie.insert("tree");

		// Search for words
		System.out.println("Searching for 'hello': " + trie.search("hello"));
		System.out.println("Searching for 'world': " + trie.search("world"));
		System.out.println("Searching for 'trie': " + trie.search("trie"));
		System.out.println("Searching for 'tree': " + trie.search("tree"));
		System.out.println("Searching for 'missing': " + trie.search("missing"));

		// Delete words
		trie.delete("hello");
		System.out.println("Searching for 'hello' after deletion: " + trie.search("hello"));
	}
}