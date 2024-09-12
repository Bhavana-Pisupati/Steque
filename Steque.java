/*
 *  File: Steque.java
 *  Author: 
 *  Date: 18th Nov, 2021
 *  ---------------------------------------
 *  Steque is stack-ended queue data structure which supports
 *  stack operations: pop and push, along with queue enqueue 
 *  operation.
 *  
 *  Salient features:
 *  1. Operations like push, pop, enqueue are supported.
 *  2. NullPointerException is thrown when null element is inserted.
 *  3. UnsupportedOperationException is thrown when using remove() method.
 *  4. The data structure is iterable and is implemented for generic type.
 *  
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * Steque is a stack-ended data structure which 
 * supports stack operations as well as queue's 
 * enqueue operation.
 * 
 * @author 
 * @version 1.0
 *
 */
public class Steque<Item> implements Iterable<Item> {
    private Node first,last;
    private int n;
    private class Node{
        Item item;
        Node next;
    }
    /**
     * constructs a steque object.
     */
    public Steque() {
           first=null;
           last=null;
           n=0;
    }
    
    
    /**
     * inserts an item in the steque in queue fashion. Time complexity=O(1), Space complexity= O(n)
     * @param item Item to be inserted.
     */
    public void enqueue(Item item) {
        if(item == null) throw new IllegalArgumentException();
        Node oldlast=last;
        last=new Node();
        last.item=item;
        n++;
        if(first == null) first=last;
        else oldlast.next = last;
    }
    
    
    /**
     * inserts an item in the steque in stack fashion. Time complexity=O(1), Space complexity= O(n)
     * @param item Item to be inserted.
     */
    public void push(Item item) {
        if(item == null) throw new IllegalArgumentException();
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        n++;
        if(last == null) last=first; 
    }
    
    /**
     * pops a least recent item in steque. Time complexity=O(1), Space complexity= O(1)
     * @return Item object from steque.
     */
    public Item pop() {
        if(isEmpty()) throw new NoSuchElementException();
        Item item=first.item;
        first=first.next;
        n--;
        return item;
    }
    
    /**
     * checks to see if steque is empty. Time complexity=O(1), Space complexity= O(1)
     * @return true if steque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return first==null;
    }
    
    /**
     * return the number of elements currently in the steque. Time complexity=O(1), Space complexity= O(1)
     * @return size as integer.
     */
    public int size() {
        return n;
    }
    
    /**
     * returns an iterator over the elements  Time complexity=O(n), Space complexity= O(n)
     * stored in steque.
     * 
     */
    public Iterator<Item> iterator() {
        return new StequeIterator(first);
    }
    private class StequeIterator implements Iterator<Item> {
        private Node current;

        public StequeIterator(Node first) {
            current = first;
        }


        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}