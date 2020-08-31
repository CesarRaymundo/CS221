import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Double-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, and
 * ListIterator is supported.
 * 
 * @author Cesar Raymundo
 * 
 * @param <T> type to store
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T>{
	
	private LinearNode<T> head;
	private LinearNode<T> tail;
	private int size;
	private int modCount;
	
	/**
	 * Constructor for a new Indexed Unsorted Double linked List.
	 */
	public IUDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
		modCount = 0;
	}
	
	@Override
	public void addToFront(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		
		newNode.setNext(head);
		
		if(head != null) {
			head.setPrevious(newNode);
		} else {
			tail = newNode;
		}
		head = newNode;
		
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		newNode.setPrevious(tail);
		
		if(tail != null) {
			tail.setNext(newNode);
		} else {
			head = newNode;
		}
		tail = newNode;
		
		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
		if(isEmpty())
			throw new NoSuchElementException();
		
		LinearNode<T> current = head;
		while(current != null && !current.getElement().equals(target)) {
			current = current.getNext();
		}
		/// If target is not found
		if(current == null) {
			throw new NoSuchElementException();
		}
		
		LinearNode<T> newNode = new LinearNode<T>(element);
		newNode.setNext(current.getNext());
		newNode.setPrevious(current);
		if(current == tail) {
			tail = newNode;
		} else {
			current.getNext().setPrevious(newNode);
		}
		
		current.setNext(newNode);
		
		size++;
		modCount++;
	}

	@Override
	public void add(int index, T element) {
		if(index < 0 || index > size) 
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> newNode = new LinearNode<T>(element);
		LinearNode<T> current = head;
		
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		newNode.setNext(current);
		
		if(current != null) {
			newNode.setPrevious(current.getPrevious());
			current.setPrevious(newNode);
		} else {
			newNode.setPrevious(tail);
			tail = newNode;
		}
		
		if(current != head) {
			newNode.getPrevious().setNext(newNode);
		} else {
			head = newNode;
		}
		
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if(isEmpty()) 
			throw new NoSuchElementException();
		
		T retVal = head.getElement();
		head = head.getNext();
		if(size > 1) {
			head.setPrevious(null);
		} else {
			tail = null;
		}
		
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T removeLast() {
		if(isEmpty()) 
			throw new NoSuchElementException();
		
		T retVal = tail.getElement();
		if(size > 1) {
			tail.getPrevious().setNext(null);
			tail = tail.getPrevious();
		} else {
			head = null;
			tail = null;
		}
		
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(T element) {
		if(isEmpty()) 
			throw new NoSuchElementException();
		
		LinearNode<T> current = head;
		while(current != null && !current.getElement().equals(element)) {
			current = current.getNext();
		}
		if(current == null) {
			throw new NoSuchElementException();
		}
		T retVal = current.getElement();
		if(size == 1) {
			head = null;
			tail = null;
		} else {
			//Current is head
			if(current == head) { 
				current.getNext().setPrevious(current.getPrevious());
				head = current.getNext();
			} 
			//Current is tail
			else if(current == tail) { 
				current.getPrevious().setNext(current.getNext());
				tail = current.getPrevious();
			} 
			//Current is middle
			else {
				current.getNext().setPrevious(current.getPrevious());
				current.getPrevious().setNext(current.getNext());
			}
		}
		
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= size) 
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		T retVal = current.getElement();
		if(size == 1) {
			head = null;
			tail = null;
		}  else {
			//Current is head
			if(current == head) { 
				current.getNext().setPrevious(current.getPrevious());
				head = current.getNext();
			} 
			//Current is tail
			else if(current == tail) { 
				current.getPrevious().setNext(current.getNext());
				tail = current.getPrevious();
			} 
			//Current is middle
			else {
				current.getNext().setPrevious(current.getPrevious());
				current.getPrevious().setNext(current.getNext());
			}
		}
		
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public void set(int index, T element) {
		if(index < 0 || index >= size) 
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		current.setElement(element);
		
		modCount++;
	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= size) 
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public int indexOf(T element) {
		int index = 0;
		LinearNode<T> current = head;
		
		while(current != null && !current.getElement().equals(element)) {
			index++;
			current = current.getNext();
		}
		
		if(current == null) {
			index = -1;
		}
		
		return index;
	}

	@Override
	public T first() {
		if(isEmpty()) 
			throw new NoSuchElementException();
		
		return head.getElement();
	}

	@Override
	public T last() {
		if(isEmpty()) 
			throw new NoSuchElementException();
		
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		LinearNode<T> current = head;
		boolean foundNode = false;
		if (current == null) {
			foundNode = false;
		}
		else if (current.getElement() == target) {
			foundNode = true;
		}
		while (current != null && !foundNode) {
			if (current.getElement() == target) {
				foundNode = true;
			}
			else {
				current = current.getNext();
			}
		}
		return foundNode;
	}


	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(T element : this) {
			if(element != null) {
				str.append(element);
				str.append(", ");
			}
		}
		if(!isEmpty()) {
			str.delete(str.length()-2, str.length());
		}
		str.append("]");
		return str.toString();
	}
	
	@Override
	public Iterator<T> iterator() {
		return new DLLiterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DLLiterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLiterator(startingIndex);
	}
	
	
	private class DLLiterator implements ListIterator<T> {

		private LinearNode<T> nextNode;
		private LinearNode<T> lastReturnedNode;
		private int nextIndex;
		private int iterModCount;
				
		/**
		 * Constructor for ListIterator for a IUDoubleLinkedList.
		 *
		 */
		public DLLiterator() {
			this(0);
		}
		
		public DLLiterator(int start) {
			if(start < 0 || start > size) 
				throw new IndexOutOfBoundsException();
			
			nextNode = head;
			nextIndex = start;
			lastReturnedNode = null;
			iterModCount = modCount;
			
			for(int i = 0; i < start; i++) {
				nextNode = nextNode.getNext();
			}
		}
		
		@Override
		public boolean hasNext() {
			if(iterModCount != modCount) 
				throw new ConcurrentModificationException();
			boolean hasNextNode;
			
			if(nextNode != null) {
				hasNextNode = true;
			}
			else {
				hasNextNode = false;
			}
			return hasNextNode;
		}

		@Override
		public T next() {
			if(!hasNext()) 
				throw new NoSuchElementException();
			
			lastReturnedNode = nextNode;
			nextNode = nextNode.getNext();
			nextIndex++;
			return lastReturnedNode.getElement();
		}

		@Override
		public boolean hasPrevious() {
			if(iterModCount != modCount) 
				throw new ConcurrentModificationException();
			
			return (nextNode != head);
		}

		@Override
		public T previous() {
			if(!hasPrevious()) 
				throw new NoSuchElementException();
			
			if(nextNode == null) {
				nextNode = tail;
			} else {
				nextNode = nextNode.getPrevious();
			}
			lastReturnedNode = nextNode;
			nextIndex--;
			return lastReturnedNode.getElement();
		}

		@Override
		public int nextIndex() {
			if(iterModCount != modCount) 
				throw new ConcurrentModificationException();
			
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex-1;
		}

		@Override
		public void remove() {
			if(iterModCount != modCount) 
				throw new ConcurrentModificationException();
			
			if(lastReturnedNode == null) 
				throw new IllegalStateException();
			
			//last move was previous
			if(lastReturnedNode == nextNode) { 
				nextNode = nextNode.getNext();
			}
			//last move was next
			else { 
				nextIndex--;
			}
			if(lastReturnedNode != head) {
				lastReturnedNode.getPrevious().setNext(nextNode);
			} 
			//lastReturned is head
			else { 
				head = nextNode;
				if(nextNode != null) {
					nextNode.setPrevious(null);
				}
			}
			if(lastReturnedNode != tail) {
				nextNode.setPrevious(lastReturnedNode.getPrevious());
			} 
			//last returned is tail
			else { 
				tail = lastReturnedNode.getPrevious();
				if(tail != null) {
					tail.setNext(null);
				}
			}
			
			lastReturnedNode = null;
			size--;
			modCount++;
			iterModCount++;
		}

		@Override
		public void set(T e) {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(lastReturnedNode == null) {
				throw new IllegalStateException();
			}
			
			lastReturnedNode.setElement(e);
			modCount++;
			iterModCount++;
		}

		@Override
		public void add(T e) {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			LinearNode<T> newNode = new LinearNode<T>(e);
			//empty list
			if(isEmpty()) { 
				head = tail = newNode;
			} 
			//If list is not empty
			else { 
				//iterator at head
				if(nextNode == head) { 
					newNode.setNext(nextNode);
					nextNode.setPrevious(newNode);
					head = newNode;
				} 
				//iterator at tail
				else if(nextNode == null) { 
					tail.setNext(newNode);
					newNode.setPrevious(tail);
					tail = newNode;
				} 
				 //iterator in middle of list
				else {
					nextNode.getPrevious().setNext(newNode);
					newNode.setNext(nextNode);
					newNode.setPrevious(nextNode.getPrevious());
					nextNode.setPrevious(newNode);
				}
			}
			
			size++;
			modCount++;
			iterModCount++;
		}
	}

}