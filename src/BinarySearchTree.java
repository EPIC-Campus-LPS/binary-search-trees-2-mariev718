import java.security.InvalidParameterException;

public class BinarySearchTree {

    // PIVs
    private int numNodes = 0;
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

        if(numNodes == 0) return 0;

        if(numNodes == 1) return 1;

        return countLeafNodes(root);

    }

    public int countLeafNodes(TreeNode<Integer> node) {

        if(node == null) return 0;

        int left = countLeafNodes(node.getLeftChild());
        int right = countLeafNodes(node.getRightChild());

        if(left + right == 0) {

            return 1;

        }

        return left + right;

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

        if(numNodes == 0) {

            System.out.println("Tree is empty");

        } else

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

        if(numNodes == 0) {

            System.out.println("Tree is empty");

        } else printPreorder(root);

    }

    public void printPreorder(TreeNode<Integer> node) {

        if(node == null) return;

        System.out.println(node.getValue());
        printPreorder(node.getLeftChild());
        printPreorder(node.getRightChild());

    }

    public void printPostorder() {

        if(numNodes == 0) {

            System.out.println("Tree is empty");

        } else printPostorder(root);

    }

    public void printPostorder(TreeNode<Integer> node) {

        if(node == null) return;

        printPostorder(node.getLeftChild());
        printPostorder(node.getRightChild());
        System.out.println(node.getValue());

    }

    public int delete(int value) {

        if(delete(value, root)) {

            if(root.getLeftChild() != null && root.getRightChild() != null) {

                TreeNode<Integer> right = root.getRightChild();

                root = root.getLeftChild();

                TreeNode<Integer> temp = root;

                while(temp.getRightChild() != null) {

                    temp = temp.getRightChild();

                }

                temp.setRightChild(right);

            } else if(root.getLeftChild() == null && root.getRightChild() == null) {

                root = new TreeNode<Integer>(null, null, null);

            } else if(root.getLeftChild() != null) {

                root = root.getLeftChild();

            } else {

                root = root.getRightChild();

            }

        }

        numNodes--;

        return value;

    }

    public boolean delete(int value, TreeNode<Integer> node) {

        if(node == null) {

            throw new InvalidParameterException("Value " + value + " is not in tree.");

        }

        if(node.getValue() == value) {

            return true;

        }

        if(value < node.getValue()) {

            if (delete(value, node.getLeftChild())) {

                TreeNode<Integer> temp = node.getLeftChild();

                if (temp.getLeftChild() == null && temp.getRightChild() == null) {

                    node.setLeftChild(null);

                } else if (temp.getLeftChild() != null && temp.getRightChild() == null) {

                    node.setLeftChild(temp.getLeftChild());

                } else if (temp.getLeftChild() == null && temp.getRightChild() == null) {

                    node.setLeftChild(temp.getRightChild());

                } else {

                    node.setLeftChild(temp.getLeftChild());

                    while (node.getRightChild() != null) {

                        node = node.getRightChild();

                    }

                    node.setRightChild(temp.getRightChild());

                }

            }
        } else if(value > node.getValue()) {

            if (delete(value, node.getRightChild())) {

                TreeNode<Integer> temp = node.getRightChild();

                if (temp.getLeftChild() == null && temp.getRightChild() == null) {

                    node.setRightChild(null);

                } else if (temp.getLeftChild() != null && temp.getRightChild() == null) {

                    node.setRightChild(temp.getLeftChild());

                } else if (temp.getLeftChild() == null && temp.getRightChild() == null) {

                    node.setRightChild(temp.getRightChild());

                } else {

                    node.setRightChild(temp.getLeftChild());

                    while (node.getRightChild() != null) {

                        node = node.getRightChild();

                    }

                    node.setRightChild(temp.getRightChild());

                }

            }
        }

        return false;

    }

}