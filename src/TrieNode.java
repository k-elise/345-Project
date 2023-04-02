public class TrieNode {
	private final int ALPHABET_SIZE = 26;
	private TrieNode[] children;
	private boolean isEndOfWord;

	public TrieNode() {
		children = new TrieNode[ALPHABET_SIZE];
		isEndOfWord = false;
	}

	public TrieNode[] getChildren() {
		return children;
	}

	public boolean isEndOfWord() {
		return isEndOfWord;
	}

	public void setEndOfWord(boolean endOfWord) {
		isEndOfWord = endOfWord;
	}
}