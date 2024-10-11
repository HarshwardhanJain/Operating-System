package OS_LAB_11_21_24;

import java.util.*;

class LRUCache {
    private final int capacity;
    private final Map<Integer, Node> cache;
    private final DoublyLinkedList pageList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.pageList = new DoublyLinkedList();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        pageList.moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            pageList.moveToHead(node);
        } else {
            if (cache.size() == capacity) {
                Node tail = pageList.removeTail();
                cache.remove(tail.key);
            }
            Node newNode = new Node(key, value);
            pageList.addToHead(newNode);
            cache.put(key, newNode);
        }
    }

    public void display() {
        pageList.display();
    }

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DoublyLinkedList {
        private final Node head;
        private final Node tail;

        public DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        public Node removeTail() {
            Node tailNode = tail.prev;
            removeNode(tailNode);
            return tailNode;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void display() {
            Node current = head.next;
            while (current != tail) {
                System.out.print(current.key + " ");
                current = current.next;
            }
            System.out.println();
        }
    }
}

public class LRUPageReplacement {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        System.out.println("Cache after adding 1, 2, 3:");
        lruCache.display();

        lruCache.get(1);
        System.out.println("Cache after accessing 1:");
        lruCache.display();

        lruCache.put(4, 4);
        System.out.println("Cache after adding 4:");
        lruCache.display();

        lruCache.get(3);
        System.out.println("Cache after accessing 3:");
        lruCache.display();

        lruCache.put(5, 5);
        System.out.println("Cache after adding 5:");
        lruCache.display();
    }
}