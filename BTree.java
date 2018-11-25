/*
Created by Arazoo Hoseyni on 11/24/2018
 */
package binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * Function: save values of a array into a binary tree, and traversal it in 3
 * ways
 * 
 * 
 * 
 */
public class BTree {

	private int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static List<Node> nodeList = null;

	/**
	 * Inner Class: Node
	 * 
	 * 
	 */
	private static class Node {
		Node leftChild;
		Node rightChild;
		Node father;

		int data;
		int preorder;
		int postorder;
		int inorder;

		Node(int newData) {
			leftChild = null;
			rightChild = null;
			data = newData;
			father = null;
		}

		public void setfather(Node node) {
			father = node;
		}

	}

	/**
	 * creat the binary tree
	 * 
	 * @param
	 * @return
	 */
	public void createBinTree() {
		nodeList = new LinkedList<Node>();
		for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
			nodeList.add(new Node(array[nodeIndex]));
		}
		for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
			nodeList.get(parentIndex).leftChild = nodeList
					.get(parentIndex * 2 + 1);
			nodeList.get(parentIndex).leftChild.setfather(nodeList
					.get(parentIndex));
			nodeList.get(parentIndex).rightChild = nodeList
					.get(parentIndex * 2 + 2);
			nodeList.get(parentIndex).rightChild.setfather(nodeList
					.get(parentIndex));
		}
		int lastParentIndex = array.length / 2 - 1;
		nodeList.get(lastParentIndex).leftChild = nodeList
				.get(lastParentIndex * 2 + 1);
		if (array.length % 2 == 1) {
			nodeList.get(lastParentIndex).rightChild = nodeList
					.get(lastParentIndex * 2 + 2);
		}
	}

	/**
	 * pre-orderTraverse
	 * 
	 * @param Node
	 *            node
	 * @return
	 */
	public static void preOrderTraverse(Node node) {
		if (node == null)
			return;
		System.out.print(node.data + " ");
		preOrderTraverse(node.leftChild);
		preOrderTraverse(node.rightChild);
	}

	/**
	 * in-orderTraverse
	 * 
	 * @param Node
	 *            node
	 * 
	 * @return
	 */
	public static void inOrderTraverse(Node node) {
		if (node == null)
			return;
		inOrderTraverse(node.leftChild);
		System.out.print(node.data + " ");
		inOrderTraverse(node.rightChild);
	}

	/**
	 * post-orderTraverse
	 * 
	 * @param Node
	 *            node
	 * @return
	 */
	public static void postOrderTraverse(Node node) {
		if (node == null)
			return;
		postOrderTraverse(node.leftChild);
		postOrderTraverse(node.rightChild);
		System.out.print(node.data + " ");
	}

	static int count = 0;

	/**
	 * Assign pre-order number
	 * 
	 * @param Node
	 *            node
	 * @return
	 */
	public static void preOrderNumber(Node node) {
		if (node == null)
			return;
		count++;
		node.preorder = count;
		System.out.print(node.preorder + " ");
		preOrderNumber(node.leftChild);
		preOrderNumber(node.rightChild);
	}

	/**
	 * Assign in-order number
	 * 
	 * @param Node
	 *            node
	 * @return
	 */
	public static void inOrderNumber(Node node) {
		if (node == null)
			return;
		inOrderNumber(node.leftChild);
		count++;
		node.inorder = count;
		System.out.print(node.inorder + " ");
		inOrderNumber(node.rightChild);
	}

	/**
	 * Assign post-order number
	 * 
	 * @param Node
	 *            node
	 * @return
	 */
	public static void postOrderNumber(Node node) {
		if (node == null)
			return;
		postOrderNumber(node.leftChild);
		postOrderNumber(node.rightChild);
		count++;
		node.postorder = count;
		System.out.print(node.postorder + " ");
	}

	/**
	 * return the node visited after node x in a pre-order traversal of BT
	 * 
	 * @param Node
	 *            node
	 * @return Node
	 */
	public static Node preorderNext(Node node) {
		if (null != node.leftChild)
			return node.leftChild;
		if (null != node.rightChild)
			return node.rightChild;
		if (null != node.father)
			if (node.father.leftChild == node && node.father.rightChild != null) {
				return node.father.rightChild;
			}
		if (null != node.father) {
			Node p = node.father;
			while (p.father != null) {
				if (p.father.leftChild == p && p.father.rightChild != null) {
					return p.father.rightChild;
				} else {
					p = p.father;
				}
			}
		}
		return null;
	}

	/**
	 * return the node visited after node x in an in-order traversal of BT
	 * 
	 * @param Node
	 *            node
	 * @return Node
	 */
	public static Node inorderNext(Node node) {
		if (null != node.rightChild) {
			Node p = node.rightChild;
			while(p.leftChild!=null){
				p = p.leftChild;
			}
			return p;
		}
		if (null != node.father) {
			if (node.father.leftChild == node) {
				return node.father;
			} else {
				Node p = node.father;
				while (p.father != null) {
					if (p.father.leftChild == node) {
						return p.father;
					} else {
						p = p.father;
					}
				}
			}
		}
		return null;
	}

	/**
	 * return the node visited after node x in an post-order traversal of BT
	 * 
	 * @param Node
	 *            node
	 * @return Node
	 */
	public static Node postorderNext(Node node) {
		if (null == node.father) {
			return null;
		}
		if (node.father.leftChild == node) {
			return node.father.rightChild;
		} else
			return node.father;

	}

	/**
	 * assistant for postorderNext()
	 * 
	 * @param Node
	 *            node
	 * @return
	 */

	/**
	 * main method for test
	 * 
	 * @param
	 * @return
	 */
	public static void main(String[] args) {
		BTree BT = new BTree();
		BT.createBinTree();
		Node root = nodeList.get(0);

		System.out.println("pre-order Traverse：");
		preOrderTraverse(root);
		System.out.println();
		count = 0;
		preOrderNumber(root);
		System.out.println();

		System.out.println("in-order Traverse：");
		inOrderTraverse(root);
		System.out.println();
		count = 0;
		inOrderNumber(root);
		System.out.println();

		System.out.println("post-order Traverse：");
		postOrderTraverse(root);
		count = 0;
		System.out.println();
		postOrderNumber(root);
		System.out.println();

		System.out.println(preorderNext(root).data);
		System.out.println(inorderNext(root).data);
		System.out.println(postorderNext(root.leftChild).data);

	}

}
