/**
 * A TrieNode class representing the nodes in a trie data structure. Each
 * TrieNode object contains an array of child nodes, one for each character in
 * the alphabet, and a boolean flag indicating if the node is the end of a word.
 *
 * TrieNode objects are used by the Trie class to build and manage the trie data
 * structure, enabling efficient search, insertion, and deletion of strings, as
 * well as advanced functionalities such as prefix-based searches and
 * autocompletion.
 *
 * @authors Hayden Price, Kendra Jones, Nishant Athawale
 */
public class TrieNode {
	private final int ALPHABET_SIZE = 26;
	private TrieNode[] children;
	private boolean isEndOfWord;

	/**
	 * Initializes a new TrieNode object with an array of child nodes of size
	 * ALPHABET_SIZE (26) and sets the isEndOfWord flag to false.
	 */
	public TrieNode() {
		children = new TrieNode[ALPHABET_SIZE];
		isEndOfWord = false;
	}

	/**
	 * Returns the array of child TrieNode objects for this node.
	 *
	 * @return The array of TrieNode objects representing the children of this node.
	 */
	public TrieNode[] getChildren() {
		return children;
	}

	/**
	 * Checks if this TrieNode represents the end of a word in the Trie.
	 *
	 * @return True if this node is the end of a word, otherwise false.
	 */
	public boolean isEndOfWord() {
		return isEndOfWord;
	}

	/**
	 * Sets the isEndOfWord flag for this TrieNode, indicating whether the node
	 * represents the end of a word in the Trie.
	 *
	 * @param endOfWord A boolean value specifying if this node is the end of a
	 *                  word.
	 */
	public void setEndOfWord(boolean endOfWord) {
		isEndOfWord = endOfWord;
	}
}