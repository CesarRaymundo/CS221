import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new IUDoubleLinkedList<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		if (list.size() < 2) {
			return;
		}
		IUDoubleLinkedList<T> left = new IUDoubleLinkedList<T>();
		IUDoubleLinkedList<T> right = new IUDoubleLinkedList<T>();
		

		int middle = list.size() / 2;
		int last = list.size();

		//Splitting the list into two parts
		for (int i = 0; i < middle; i++)
			left.add(list.removeFirst());
		for (int i = middle; i < last; i++)
			right.add(list.removeFirst());
		
		// calling method for recursion
		mergesort(left);
		mergesort(right);
		
		// Comparing and swapping elements according to increasing order
		while (left.size() != 0 && right.size() != 0) {
			T leftElement = left.removeFirst();
			T rightElement = right.removeFirst();

			// Comparing the left single element to right single element and sorting accordingly 
			if (leftElement.compareTo(rightElement) > 0) {
				list.add(rightElement);
				left.addToFront(leftElement);
			} else {
				list.add(leftElement);
				right.addToFront(rightElement);
			}
		
		}
		
		 // Removing elements from the left and right divided list after 
		  //comparing and putting elements in the sorted list 
		while (left.size() != 0)
			list.add(left.removeFirst());
		while (right.size() != 0)
			list.add(right.removeFirst());
		
	}
		
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		if (list.size() < 2)
			return;
		IUDoubleLinkedList<T> left = new IUDoubleLinkedList<T>();
		IUDoubleLinkedList<T> right = new IUDoubleLinkedList<T>();
		
		int middle = list.size() / 2;
		int last = list.size();

		//Splitting the list into two parts
		for (int i = 0; i < middle; i++)
			left.add(list.removeFirst());
		for (int i = middle; i < last; i++)
			right.add(list.removeFirst());

		// calling method for recursion
		mergesort(left, c);
		mergesort(right, c);

		while (left.size() != 0 && right.size() != 0) {
			T leftElement = left.removeFirst();
			T rightElement = right.removeFirst();

			// Comparing and swapping elements according to increasing order
			if (c.compare(leftElement, rightElement) > 0) {
				list.add(rightElement);
				left.addToFront(leftElement);
			} else {
				list.add(leftElement);
				right.addToFront(rightElement);
			}
		}
		 // Removing elements from the left and right divided list after 
		  //comparing and putting elements in the sorted list 
		while (left.size() != 0)
			list.add(left.removeFirst());
		while (right.size() != 0)
			list.add(right.removeFirst());

	}
	
}