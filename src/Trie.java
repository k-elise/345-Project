public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(String word) {
		word = word.toLowerCase();
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

	public boolean search(String word) {
		word = word.toLowerCase();
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

	public void delete(String word) {
		word = word.toLowerCase();
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

	public boolean startsWith(String prefix) {
		prefix = prefix.toLowerCase();
		TrieNode node = root;
		for (char c : prefix.toCharArray()) {
			int index = c - 'a';
			if (node.getChildren()[index] == null) {
				return false;
			}
			node = node.getChildren()[index];
		}
		return true;
	}

	public StringArray autocomplete(String prefix) {
		prefix = prefix.toLowerCase();
		StringArray suggestions = new StringArray();
		TrieNode node = root;
		for (char c : prefix.toCharArray()) {
			int index = c - 'a';
			if (node.getChildren()[index] == null) {
				return suggestions;
			}
			node = node.getChildren()[index];
		}
		findAllWords(node, suggestions, new CustomString(prefix));
		return suggestions;
	}

	private void findAllWords(TrieNode node, StringArray words, CustomString currentWord) {
		if (node.isEndOfWord()) {
			words.add(currentWord.toString());
		}
		for (int i = 0; i < node.getChildren().length; i++) {
			TrieNode child = node.getChildren()[i];
			if (child != null) {
				currentWord.append((char) (i + 'a'));
				findAllWords(child, words, currentWord);
				currentWord.deleteLastChar();
			}
		}
	}
}