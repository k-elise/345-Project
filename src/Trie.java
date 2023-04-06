/**
 * A Trie class implementing a trie data structure, which is an efficient way to
 * store a dynamic set of strings. Tries enable fast search, insertion, and
 * deletion operations, and support advanced functionalities such as
 * prefix-based searches and autocompletion.
 *
 * The Trie class represents the trie using TrieNode objects, which store a
 * reference to an array of child nodes, one for each character in the alphabet,
 * and a boolean flag indicating if the node is the end of a word.
 *
 * @authors Hayden Price, Kendra Jones, Nishant Athawale
 */
public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	/**
	 * Inserts a word into the Trie, iteratively traversing the TrieNode objects and
	 * creating new child nodes if necessary for each character in the word. When
	 * the end of the word is reached, the TrieNode's isEndOfWord flag is set to
	 * true.
	 *
	 * @param word The word to be inserted.
	 */
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

	/**
	 * Searches for a word in the Trie, iteratively traversing the TrieNode objects
	 * using the characters of the word as indices. If a node is not found for a
	 * given character, the word does not exist in the Trie, and the method returns
	 * false. If the end of the word is reached and the TrieNode's isEndOfWord flag
	 * is set, the method returns true.
	 *
	 * @param word The word to be searched for.
	 * @return True if the word exists in the Trie, otherwise false.
	 */
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

	/**
	 * Deletes a word from the Trie by calling the private delete method with the
	 * root node, the word to delete, and an initial index of 0.
	 *
	 * @param word The word to be deleted.
	 */
	public void delete(String word) {
		word = word.toLowerCase();
		delete(root, word, 0);
	}

	/**
	 * A private helper method that recursively traverses the TrieNodes to delete
	 * the specified word. If the end of the word is reached, the TrieNode's
	 * isEndOfWord flag is set to false. The method then checks if the current node
	 * is empty and returns the result. During traversal back up the tree, the
	 * method determines if the current node should be deleted based on the return
	 * value from the previous call and the current node's state.
	 *
	 * @param node  The current TrieNode being processed.
	 * @param word  The word to delete.
	 * @param index The current index in the word.
	 * @return True if the current TrieNode should be deleted, otherwise false.
	 */
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

	/**
	 * A private helper method that checks if a TrieNode is empty, i.e., has no
	 * children.
	 *
	 * @param node The TrieNode to check.
	 * @return True if the TrieNode is empty, otherwise false.
	 */
	private boolean isEmpty(TrieNode node) {
		for (TrieNode child : node.getChildren()) {
			if (child != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the Trie contains any words that start with the given prefix, by
	 * iteratively traversing the TrieNode objects using the characters of the
	 * prefix as indices. If a node is not found for a given character, the method
	 * returns false. If the end of the prefix is reached, the method returns true,
	 * since there is at least one word in the Trie that starts with the given
	 * prefix.
	 *
	 * @param prefix The prefix to search for.
	 * @return True if any words start with the given prefix, otherwise false.
	 */
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

	/**
	 * Returns an array of words in the Trie that start with the given prefix. The
	 * method first traverses the TrieNode objects using the characters of the
	 * prefix as indices. Once the end of the prefix is reached, the method calls
	 * the private collectWords helper method, which performs a depth-first
	 * traversal of the Trie, building and storing words that start with the given
	 * prefix.
	 *
	 * @param prefix The prefix to search for.
	 * @return A StringArray containing words that start with the given prefix.
	 */
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

	/**
	 * A private helper method that performs a depth-first traversal of the Trie
	 * starting from the given TrieNode, building and storing words in a StringArray
	 * using a CustomString to accumulate characters. When a TrieNode with the
	 * isEndOfWord flag set is encountered, the accumulated characters form a word
	 * that starts with the given prefix, and it is added to the results.
	 *
	 * @param node      The current TrieNode being processed.
	 * @param results   A StringArray to store words that start with the prefix.
	 * @param wordSoFar A CustomString to accumulate characters during traversal.
	 */
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