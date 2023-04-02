public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Insert a new word into the Trie
	public void insert(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			int index = c - 'a';
			if (node.getChildren()[index] == null) {
				node.getChildren()[index] = new TrieNode();
			}
			node = node.getChildren()[index];
		}
		node.setEndOfWord(true);
	}

	// Search for a word in the Trie
	public boolean search(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			int index = c - 'a';
			if (node.getChildren()[index] == null) {
				return false;
			}
			node = node.getChildren()[index];
		}
		return node.isEndOfWord();
	}

	// Delete a word from the Trie
	public void delete(String word) {
		delete(root, word, 0);
	}

	private boolean delete(TrieNode node, String word, int index) {
		if (index == word.length()) {
			if (!node.isEndOfWord()) {
				return false;
			}
			node.setEndOfWord(false);
			return isEmpty(node);
		}

		int charIndex = word.charAt(index) - 'a';
		if (node.getChildren()[charIndex] == null) {
			return false;
		}

		boolean shouldDeleteCurrentNode = delete(node.getChildren()[charIndex], word, index + 1);

		if (shouldDeleteCurrentNode) {
			node.getChildren()[charIndex] = null;
			return isEmpty(node) && !node.isEndOfWord();
		}

		return false;
	}

	private boolean isEmpty(TrieNode node) {
		for (TrieNode child : node.getChildren()) {
			if (child != null) {
				return false;
			}
		}
		return true;
	}
}
