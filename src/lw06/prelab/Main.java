import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class Main {

    // Insert secara rekursif; duplikat diabaikan
    static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    // Search secara rekursif
    static boolean search(Node root, int target) {
        if (root == null) {
            return false;
        }
        if (target == root.data) {
            return true;
        }
        if (target < root.data) {
            return search(root.left, target);
        }
        return search(root.right, target);
    }

    // Root -> Left -> Right
    static void preorder(Node root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.data).append(" ");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Left -> Root -> Right
    static void inorder(Node root, StringBuilder sb) {
        if (root == null) return;
        inorder(root.left, sb);
        sb.append(root.data).append(" ");
        inorder(root.right, sb);
    }

    // Left -> Right -> Root
    static void postorder(Node root, StringBuilder sb) {
        if (root == null) return;
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(root.data).append(" ");
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("treenumbers.txt"));
        String[] values = sc.nextLine().trim().split("\\s+");
        int target = Integer.parseInt(sc.nextLine().trim());
        sc.close();

        Node root = null;
        for (String v : values) {
            root = insert(root, Integer.parseInt(v));
        }

        StringBuilder pre = new StringBuilder();
        StringBuilder in = new StringBuilder();
        StringBuilder post = new StringBuilder();
        preorder(root, pre);
        inorder(root, in);
        postorder(root, post);

        System.out.println("Preorder : " + pre.toString().trim());
        System.out.println("Inorder : " + in.toString().trim());
        System.out.println("Postorder : " + post.toString().trim());
        System.out.println("Search " + target + " : " + search(root, target));
    }
}
