package RandomizedQueuesAndDeques;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author FrouFrou
 * A randomized queue is similar to a stack or queue, except that the 
 * item removed is chosen uniformly at random from items in the data structure.
 * @param <Item>
 */
public class RandomizedQueue<Item>implements Iterable<Item> {
	private static final int MINIMUM_STORAGE_SIZE = 2;
	private Item[] items;
	private int size;
	public RandomizedQueue() {// construct an empty randomized queue
		items=(Item[])new Object[MINIMUM_STORAGE_SIZE];
		size=0;
	}
	public boolean isEmpty() {// return true if the queue is empty, false otherwise
		return size==0;
	}
	public void add(Item item) { // insert the item into the queue
		if (item==null) {
			throw new NullPointerException("It is not allowed to enqueue null values");
		}
		if (isStorageFull()) {
			doubleStorage();
		}
		items[size++]=item;
		
	}
	public Item remove() {// delete and return an item from the queue, uniformly at random
		if (size==0) {
			throw new NoSuchElementException("try to deque a null queue");
		}
		int indexOfQueue=StdRandom.uniform(size);
		Item returnValue=items[indexOfQueue];
		size--;
		items[indexOfQueue]=items[size];
		items[size]=null;
		if (isStoragedOversized()) {
			halveStorage();
		}
		return returnValue;
	}
	private boolean isStorageFull() {
		return size==items.length;
	}
	private boolean isStoragedOversized() {
		return items.length>MINIMUM_STORAGE_SIZE&&items.length<=size/4;
	}
	private void resizeStorage(int newsize) {
		Item [] resizeItems=(Item [])new Object[newsize];
		for(int i=0;i<items.length;i++) {
			resizeItems[i]=items[i];
		}
		items=resizeItems;
	}
	private void halveStorage() {
		resizeStorage(items.length/2);
	}
	private void doubleStorage() {
		resizeStorage(items.length*2);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		int k=Integer.parseInt(args[0]);
		RandomizedQueue<String> randomizedQueue=new RandomizedQueue<String>();
		if (!StdIn.isEmpty()) {
			String []strings=StdIn.readAllStrings();
			for(String string:strings) {
				randomizedQueue.add(string);
			}
		}
		for(int i=1;i<=k;i++) {
			StdOut.println(randomizedQueue.remove());
		}
		
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public class RandomIterator implements Iterator<Item>{
		private Item[] itemsIterator;
		private int index;
		public RandomIterator() {
			// TODO Auto-generated constructor stub
			itemsIterator=copyRandomQueueItems();
			StdRandom.shuffle(itemsIterator);
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index<itemsIterator.length;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			
			return itemsIterator[index++];
		}
		
		private Item[] copyRandomQueueItems() {
			Item[] copyItems=(Item[])new Object[size];
			for(int i=0;i<items.length;i++) {
				copyItems[i]=items[i];
			}
			return copyItems;
		}
		
	
		
	}
}
