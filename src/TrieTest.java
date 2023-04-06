public class TrieTest {
	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.insert("hello");
		trie.insert("world");
		trie.insert("trie");
		trie.insert("tree");

		System.out.println("Searching for 'hello': " + trie.search("hello"));
		System.out.println("Searching for 'world': " + trie.search("world"));
		System.out.println("Searching for 'trie': " + trie.search("trie"));
		System.out.println("Searching for 'tree': " + trie.search("tree"));

		trie.delete("hello");
		System.out.println("Searching for 'hello' after deletion: " + trie.search("hello"));

		System.out.println("Words with prefix 'tr':");
		StringArray suggestions = trie.autocomplete("tr");
		for (int i = 0; i < suggestions.size(); i++) {
			System.out.println(suggestions.get(i));
		}
	}
}