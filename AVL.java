class BPlusNode {
    boolean isLeaf;
    int[] keys;
    int keyCount;              // actual number of keys
    BPlusNode[] children;
    int childCount;            // optional clarity
    BPlusNode next;

    static final int ORDER = 4; // you can change degree

    // Constructor for leaf/internal node
    BPlusNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new int[ORDER];
        this.children = new BPlusNode[ORDER + 1];
        this.keyCount = 0;
        this.childCount = 0;
        this.next = null;
    }
}

public class BPlusTree {

    // Find correct leaf
    static BPlusNode findLeaf(BPlusNode root, int key) {

        BPlusNode node = root;

        while (!node.isLeaf) {

            int i = 0;

            while (i < node.keyCount && key >= node.keys[i]) {
                i++;
            }

            node = node.children[i];
        }

        return node;
    }

    // Range count query
    static int rangeCount(BPlusNode root, int lo, int hi) {

        if (root == null) return 0;

        BPlusNode leaf = findLeaf(root, lo);

        int count = 0;

        while (leaf != null) {

            for (int i = 0; i < leaf.keyCount; i++) {

                int k = leaf.keys[i];

                if (k > hi) {
                    return count;
                }

                if (k >= lo) {
                    count++;
                }
            }

            leaf = leaf.next;
        }

        return count;
    }

    // Dummy main so it runs (optional but useful for testing)
    public static void main(String[] args) {

        BPlusNode root = new BPlusNode(true);

        root.keys[0] = 10;
        root.keys[1] = 20;
        root.keys[2] = 30;
        root.keyCount = 3;

        System.out.println(rangeCount(root, 15, 30));
    }
}