import java.util.ArrayList;

/**
 * CMPT 454 programming assignment BpTree
 * 
 * @author Qiang Lai 301193801
 *
 */
public class BpTree {

	private final int N; // the number of search key values of B+ tree
	private Node root; // the root of the B+ tree

	/**
	 * Constructor of B+ Tree
	 * 
	 * @param n
	 *            search key values (where n is the value of the parameter
	 *            passed to the tree's constructor) and n+1 pointers
	 */
	public BpTree(int n) {
		N = n;
		this.root = new Leaf(); // create a root node
	}

	/**
	 * Inserts the key (int) and value (string) into the tree
	 */
	public void insert(int key, String value) {
		// check if the key exists
		if (findKey(key) == null) { // key exists then report error
			System.out.println("Cannot insert. Key: " + key + "alread exists");
			return;
		}
		insert(root,key,value);

	}
	
	/**
	 * Recursion method that search for the leaf for the key
	 * and then insert the key and value
	 * @param node the node that should used to insert the key and value
	 * @param key the key wanted to insert
	 * @param value the value wanted to insert
	 */
	public void insert(Node node, int key, String value){
		if(node.isLeaf() == true){
			
		}
	}

	/**
	 * Searches the tree for the key (int), if found returns the matching value
	 * 
	 * @return the value if found, otherwise return null
	 */
	private String findKey(int key) {
		return findKey(root, key);
	}

	/**
	 * Recursion method that search for the leaf node that contains the key
	 * 
	 * @param node
	 *            find the key from this node
	 * @param key
	 *            find this key number
	 * @return the value that is match for the key
	 */
	private String findKey(Node node, int key) {
		/*
		 * If the node is a leaf, then find the value for the key Else then find
		 * the child node from the interior node until the node is a leaf
		 */
		if (node.isLeaf()) {// if the node is a leaf node
			return ((Leaf) node).getValue(key);
		} else {// interior node
			return findKey(((Interior) node).findChild(key), key);
		}
	}

	/**
	 * Node structure
	 */
	abstract class Node {
		protected int n_keys; // the number of keys in the node
		protected ArrayList<Integer> keys; // the keys in the node
		protected Node parent; // the parent node of this node
		protected Node left; // the left sibling of this node
		protected Node right; // the right sibling of this node
		protected boolean leaf; // true if the node is a leaf node; false if the
								// node is an interior node

		/**
		 * Constructor for Node
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
		 * 
		 * @return true if is a leaf, false if not
		 */
		public boolean isLeaf() {
			return leaf;
		}

	}

	/**
	 * Leaf node stores the values
	 */
	class Leaf extends Node {
		private ArrayList<String> values; // values stored in the leaf node
		private Leaf next; // pointer to next leaf

		/**
		 * Constructor of Leaf node
		 */
		protected Leaf() {
			super(); // call Node constructor
			this.values = new ArrayList<String>();
			this.next = null; // no next leaf node
			this.leaf = true; // set true for leaf node
		}

		/**
		 * Find the value for the key
		 * 
		 * @param key
		 *            the key of the value
		 * @return the value if found, return null if not found
		 */
		public String getValue(int key) {
			// find key from keys array
			for (int i = 0; i < keys.size(); i++) {
				if (keys.get(i) == key) {// the key is found
					return values.get(i);// return the value
				}
			}
			return null;// not found, return null
		}

	}

	/**
	 * Interior Node
	 */
	class Interior extends Node {
		private ArrayList<Node> children;

		/**
		 * Constructor of Interior Node
		 */
		public Interior() {
			super();
			this.children = new ArrayList<Node>();
			this.leaf = false;
		}

		/**
		 * Find the pointer depends on the key
		 * 
		 * @param key
		 *            the key number that wanted to find
		 * @return the pointer of child node
		 */
		public Node findChild(int key) {

			/*
			 * for each key(k) in the keys that node contains if key <= k then
			 * return the pointer before the k
			 */
			for (int i = 0; i < n_keys; i++) {
				if (key <= keys.get(i)) {
					return children.get(i);
				}
			}
			// the key > every k in the node then return the last pointer
			return children.get(n_keys);
		}

	}

}
