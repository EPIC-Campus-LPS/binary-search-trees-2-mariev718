public class BinarySearchTree {

    // PIVs
    private int height = 0;
    private int numNodes = 0;
    private int numLeafNodes = 0;
    private TreeNode<Integer> root = new TreeNode<>(0, null, null);


    // Constructor
    public BinarySearchTree() {}

    public void add(int value) {

        // if there are no nodes yet, current becomes the root
        if(numNodes == 0) {

            TreeNode<Integer> temp = new TreeNode<>(value, null, null);
            root = temp;
            numNodes++;
            numLeafNodes++;

        } else {

            TreeNode<Integer> current = root;
            TreeNode<Integer> temp = new TreeNode<>(value, null, null);
            int count = 0;

            // checks to make sure neither child is missing
            // if one child is missing, it will check if the current value can be the remaining child's child
            while((current.getLeftChild() != null || value > current.getValue())
                    && (current.getRightChild() != null || value <= current.getValue())) {

                if(value <= current.getValue()) {

                    current = current.getLeftChild();

                } else {

                    current = current.getRightChild();

                }

                count++;

            }

            if(value <= current.getValue()) {

                current.setLeftChild(temp);

            } else {

                current.setRightChild(temp);

            }

            numNodes++;
            count ++;

            if(count > height) height = count;

            // If the current node has both children after adding the new node as a child, num leaf node increases by 1
            // If it only has one child after adding the new node,
            // leaf node num stays the same because current is not a leaf anymore but the child is
            if(current.getLeftChild() != null & current.getRightChild() != null) {

                numLeafNodes++;

            }

        }

    }

    public boolean contains(int value) {

        if(numNodes == 0) {

            return false;

        }

        TreeNode<Integer> current = root;

        while(value != current.getValue() &&
                ((current.getLeftChild() != null || value > current.getValue())
                && (current.getRightChild() != null || value <= current.getValue()))) {

            if(value <= current.getValue()) {

                current = current.getLeftChild();

            } else {

                current = current.getRightChild();

            }

        }

        if(value == current.getValue()) return true;
        if(value <= current.getValue()) {
            if(current.getLeftChild() != null) {

                current = current.getLeftChild();

            }

        } else {
            if(current.getRightChild() != null) {

                current = current.getRightChild();

            }

        }

        if(value == current.getValue()) return true;

        return false;

    }

    public int countNodes() {

        return numNodes;

    }

    public int countLeafNodes() {

        return numLeafNodes;

    }

    public int getHeight() {

        return height;

    }

    // WIP
    public void printInorder() {

        printInorder(root);

    }

    public void printInorder(TreeNode<Integer> node) {



    }

    // WIP
    public void printPreorder() {

        TreeNode<Integer> current = root;

        int count = 0;

        while(count < numNodes) {

            while(current.getLeftChild().getLeftChild() != null) {

                System.out.println(current.getValue());
                current = current.getLeftChild();

                count += 1;

            }

            System.out.println(current.getLeftChild());
            count += 1;

            if(current.getRightChild() != null) {

                current = current.getRightChild();

            }

        }

    }

    public void printPostorder() {



    }

    public int delete(int value) {

        return 0;

    }

}
