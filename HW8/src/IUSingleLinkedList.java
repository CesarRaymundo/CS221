import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author Cesar Raymundo
 * 
 * @param <T> type to store
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	private LinearNode<T> head;
	private LinearNode<T> tail;
	private int size;
	private int modCount;
	
	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = null;
		tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		
		if(size > 0) {
			tail.setNext(newNode);
			tail = newNode;
		} else {
			head = newNode; 
			tail = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);		
	}

	@Override
	public void addAfter(T element, T target) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		boolean found = false;
		LinearNode<T> current = head;

		while (current != null && !found) {
			if (current.getElement().equals(target)) {
				found = true;
			} else {
				current = current.getNext();
			}
		}

		if (found == false) {
			throw new NoSuchElementException("LinkedUnorderedList");
		} else {
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}

		if (current == tail) {
			tail = newNode;
		}
		size++;
		modCount++;
	}
	@Override
	public void add(int index, T element) {
		if(index > size || index < 0) 
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> newNode = new LinearNode<T>(element);
        if(index == 0) {
                newNode.setNext(head);
                head = newNode;
        } else {
                LinearNode<T> targetNode = head;
                for(int i = 0; i < index-1; i++) {
                	targetNode = targetNode.getNext();
                }

                newNode.setNext(targetNode.getNext());
                targetNode.setNext(newNode);
        }
        if(newNode.getNext() == null) {
                tail = newNode;
        }
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if(isEmpty()) 
			throw new NoSuchElementException();
		T retVal = head.getElement();
		if(size == 1) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
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
        	LinearNode<T> current = head;
            while(current.getNext() != tail) {
                current = current.getNext();
            }
            tail = current;
            current.setNext(null);
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
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current != null && !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		if (!found) {
			throw new NoSuchElementException();
		}

		if (size() == 1) { // only node
			head = tail = null;
		} else if (current == head) { // first node
			head = current.getNext();
		} else if (current == tail) { // last node
			tail = previous;
			tail.setNext(null);
		} else { // somewhere in the middle
			previous.setNext(current.getNext());
		}

		size--;
		modCount++;

		return current.getElement();
	}
	@Override
	public T remove(int index) {
		if (index >= size || index < 0) 
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> current = head;
		LinearNode<T> prev = null;
		for (int i = 0; i < index; i++) {
			prev = current;
			current = current.getNext();
		}

		T retVal = current.getElement();

		if (size == 1) {
			head = null;
			tail = null;
		} else if (current == head) {
			head = current.getNext();
		} else if (current == tail) {
			prev.setNext(null);
			tail = prev;
		} else {
			prev.setNext(current.getNext());
		}
		size--;
		modCount++;
		return retVal;
	}
	@Override
	public void set(int index, T element) {
		if (index >= size || index < 0) 
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		current.setElement(element);
		modCount++;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		LinearNode<T> current = head;

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current.getElement();
	}

	@Override
	public int indexOf(T element) {
		LinearNode<T> current = head;
		for (int i = 0; i < size; i++) {
			if (current.getElement().equals(element)) {
				return i;
			}
			current = current.getNext();
		}
		return -1;
	}


	@Override
	public T first() {
		if(isEmpty())  
			throw new NoSuchElementException();
		
		return head.getElement();
	}

	@Override
	public T last() {
		if(isEmpty())  {
			throw new NoSuchElementException();
		}
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		LinearNode<T> current = head;
		while (current != null) {
			if (current.getElement().equals(target)) {
				return true;
			} else {
				current = current.getNext();
			}
		}
		return false;
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
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	/**
	 * Overriding toString method to print meaningful output
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		LinearNode<T> curr = head;
		for (int i = 0; i < size; i++) {
			str.append(curr.getElement());
			str.append(", ");
			curr = curr.getNext();
		}
		if (!isEmpty()) {
			str.delete(str.length() - 2, str.length());
		}
		str.append("]");
		return str.toString();
	}


	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private LinearNode<T> nextNode;
		private int iterModCount;

		private LinearNode<T> previous;
		private boolean canRemove;

		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			previous = null;
			iterModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			return (nextNode != null);

		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();

			T retVal = nextNode.getElement();
			previous = nextNode;
			nextNode = nextNode.getNext();

			canRemove = true;
			return retVal;
		}

		
		@Override
		public void remove() {

			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			if (!canRemove)
				throw new IllegalStateException();

			LinearNode<T> current = head;
			LinearNode<T> temp = null;

			while (current != previous) {
				temp = current;
				current = current.getNext();
			}

			if (size() == 1) {
				head = null;
				tail = null;
			} else if (previous == head) {
				head = current.getNext();
			} else if (previous == tail) {
				tail = temp;
				tail.setNext(null);
			} else
				temp.setNext(current.getNext());

			canRemove = false;
			size--;
			modCount++;
			iterModCount = modCount;
		}
	}
}