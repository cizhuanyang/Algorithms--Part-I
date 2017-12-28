package RandomizedQueuesAndDeques;

import java.util.Iterator;

/**
 * @author FrouFrou
 * A double-ended queue or deque (pronounced "deck") is a generalization of a stack 
 * and a queue that supports inserting and removing items from either the 
 * front or the back of the data structure. 
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
	private InternalItem<Item> first;
	public Deque() {// construct an empty deque
		// TODO Auto-generated constructor stub
	}
	public boolean isEmpty() {// return true if the queue is empty, false otherwise
		
	}
	public void addFirst(Item items) {// insert the item at the front of the queue
		
	}
	public void addLast(Item items) {
		
	}
	public class InternalItem<Item>{
		private Item item;
		private InternalItem<Item> nextItem;
		private InternalItem<Item> previousItem;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
