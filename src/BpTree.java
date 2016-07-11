import java.util.ArrayList;

/**
* CMPT 454 programming assignment BpTree
 * @author Qiang Lai 301193801
 *
 */
public class BpTree {
	
	private final int N; // the number of search key values of B+ tree
	private Node root; // the root of the B+ tree
	
	/**
	 * Constructor of B+ Tree
	 * @param n search key values (where n is the value of the parameter passed to the tree's constructor) and n+1 pointers
	 */
	public BpTree(int n) {
		N = n; 
		this.root = new Leaf(); // create a root node
	}
	
	/**
	 *  Inserts the key (int) and value (string) into the tree
	 */
	public void  insert(int key, String value) {
		//check if the key exists
		if (findKey(key) == null) { // key exists then report error
			System.out.println("Cannot insert. Key: " + key + "alread exists");
			return;
		}
		
	}

	/**
	 * Searches the tree for the key (int), if found returns the matching value
	 * @return  the value if found, otherwise return null
	 */
	private String findKey(int key) {
		return findKey(root,key);
	}

	private String findKey(Node node, int key) {
		if (node.isLeaf()) {
			return node.getValue(key) ;
		}
		return null;
	}

	/**
	 *  Node structure
	 */
	abstract class Node{
		protected int n_keys; // the number of keys in the node
		protected ArrayList<Integer> keys; // the keys in the node
		protected Node parent; // the parent node of this node
		protected Node left; // the left sibling of this node
		protected Node right; // the right sibling of this node
		protected boolean leaf; // true if the node is a leaf node; false if the node is an interior node
		
		/**
		 *  Constructor for Node
		 */
		protected Node() {
			this.keys = new ArrayList<Integer>();
			this.n_keys = keys.size();
			this.parent = null;
			this.left = null;
			this.right = null;
		}

		/**
		 * Determine whether the node is a leaf or not
		 * @return true if is a leaf, false if not
		 */
		public boolean isLeaf() {
			return leaf;
		}

	}
	
	/**
	 *  Leaf node stores the values
	 */
	class Leaf extends Node{
		private ArrayList<String> values; // values stored in the leaf node
		private Leaf next; // pointer to next leaf
		
		/**
		 * Constructor  of Leaf node
		 */
		protected Leaf() {
			super(); // call Node constructor 
			this.values = new ArrayList<String>(); 
			this.next = null; // no next leaf node
			this.leaf = true; // set true for leaf node
		}
		
		
		
		
	}
	
}
