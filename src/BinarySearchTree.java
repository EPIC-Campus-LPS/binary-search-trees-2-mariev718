import java.security.InvalidParameterException;

public class BinarySearchTree {

    // PIVs
    private int numNodes = 0;
    private int numLeafNodes = 0;
    private TreeNode<Integer> root = new TreeNode<>(0, null, null);


    // Constructor
    public BinarySearchTree() {
    }

    public void add(int value) {

        // if there are no nodes yet, current becomes the root
        if (numNodes == 0) {

            TreeNode<Integer> temp = new TreeNode<>(value, null, null);
            root = temp;
            numNodes++;
            numLeafNodes++;

        } else {

            TreeNode<Integer> current = root;
            TreeNode<Integer> temp = new TreeNode<>(value, null, null);

            // checks to make sure neither child is missing
            // if one child is missing, it will check if the current value can be the remaining child's child
            while ((current.getLeftChild() != null || value > current.getValue())
                    && (current.getRightChild() != null || value <= current.getValue())) {

                if (value <= current.getValue()) {

                    current = current.getLeftChild();

                } else {

                    current = current.getRightChild();

                }

            }

            if (value <= current.getValue()) {

                current.setLeftChild(temp);

            } else {

                current.setRightChild(temp);

            }

            numNodes++;

            // If the current node has both children after adding the new node as a child, num leaf node increases by 1
            // If it only has one child after adding the new node,
            // leaf node num stays the same because current is not a leaf anymore but the child is
            if (current.getLeftChild() != null & current.getRightChild() != null) {

                numLeafNodes++;

            }

        }

    }

    public boolean contains(int value) {

        return contains(value, root);

    }

    public boolean contains(int value, TreeNode<Integer> node) {

        if(node == null) return false;

        if(node.getValue() == value) return true;

        if(value < node.getValue()) return contains(value, node.getLeftChild());

        return contains(value, node.getRightChild());

    }

    public int countNodes() {

        return numNodes;

    }

    public int countLeafNodes() {

        return numLeafNodes;

    }

    public int getHeight() {

        return getHeight(root, 0) - 1;

    }

    public int getHeight(TreeNode<Integer> node, int count) {

        if(node == null) return count;

        count++;

        int left = getHeight(node.getLeftChild(), count);
        int right = getHeight(node.getRightChild(), count);

        if(left > right) return left;
        return right;

    }

    public void printInorder() {

        // calls to same method with constructor
        printInorder(root);

    }

    public void printInorder(TreeNode<Integer> node) {

        if (node == null) return;

        printInorder(node.getLeftChild());
        System.out.println(node.getValue());
        printInorder(node.getRightChild());

    }

    public void printPreorder() {

        printPreorder(root);

    }

    public void printPreorder(TreeNode<Integer> node) {

        if(node == null) return;

        System.out.println(node.getValue());
        printPreorder(node.getLeftChild());
        printPreorder(node.getRightChild());

    }

    public void printPostorder() {

        printPostorder(root);

    }

    public void printPostorder(TreeNode<Integer> node) {

        if(node == null) return;

        printPostorder(node.getLeftChild());
        printPostorder(node.getRightChild());
        System.out.println(node.getValue());

    }

    public int delete(int value) {

        TreeNode<Integer> temp = delete(value, root);

//        if(temp.getLeftChild() == null && temp.getRightChild() == null) {
//
//            temp = null;
//
//        } else if(temp.getLeftChild() != null) {
//
//            temp = temp.getLeftChild();
//
//        } else if(temp.getRightChild() != null) {
//
//            temp = temp.getRightChild();
//
//        } else {
//
//            TreeNode<Integer> rightTemp = temp.getRightChild();
//
//            temp = temp.getLeftChild();
//
//            TreeNode<Integer> right = temp.getRightChild();
//
//            while(right.getRightChild() != null) {
//
//                right = right.getRightChild();
//
//            }
//
//            right.setRightChild(rightTemp);
//
//        }

        return value;

    }

    public TreeNode<Integer> delete(int value, TreeNode<Integer> node) {

        if(node == null) {

            throw new InvalidParameterException("Value " + value + " is not in tree.");

        }

        if(node.getValue() == value) {

            return node;

        } else if(value < node.getValue()) {

            return delete(value, node.getLeftChild());

        } else {

            return delete(value, node.getRightChild());

        }

    }

}