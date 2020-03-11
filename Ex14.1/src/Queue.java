import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Queue<T> {

	public Queue(ENUMPriority maxPriority) {
		this.maxPriority = maxPriority.getValue();
		this.queue = new PriorityQueue<QueueItem<T>>(new QueueItemComperator());
	}
	
	private int maxPriority;
	private PriorityQueue<QueueItem<T>> queue;

	public T poll() {
		return this.queue.poll().value;
	}
	
	public void add(T item, int priority) { // If not for the requirement of letting the user enter an invalid priority here
											// and use maxPriority as default I would've used the ENUMPriority everywhere instead of int/Integer, 
											// that way the user knows it's invalid on compilation and not on run.
		priority = priority > this.maxPriority ? this.maxPriority : priority;
		QueueItem<T> newItem = new QueueItem<T>(item, priority);
		this.queue.add(newItem);
	} 
	
	public boolean contains(T item) {
		return this.queue.contains(new QueueItem<T>(item, 0));
	}
	
	public boolean remove(T item) {
		return this.queue.remove(item);
	}
	
	public int size() {
		return this.queue.size();
	}
	
	public Iterator<T> iterator() {
		return new QueueIterator<T>(this.queue);
	}	
	
	private class QueueItem<T>  {
		public QueueItem(T item, int priority) {
			this.value = item;
			this.priority = priority;
		}
		
		private T value;
		private Integer priority;
		
		@Override
		public boolean equals(Object other) {
			return other.getClass().equals(QueueItem.class) &&
					this.value.equals(((QueueItem)other).value);
		}
	}
	
	private class QueueItemComperator implements Comparator<QueueItem<T>> { 
	    public int compare(QueueItem<T> item1, QueueItem<T> item2) 
	    { 
	        switch(item1.priority.compareTo(item2.priority)) {
		        case 1:
		        	return -1;
		        case -1:
		        	return 1;
		        default:
		        	return 0;
	        } 
	    } 
	} 
	
	private class QueueIterator<T> implements Iterator<T> {
		
		public QueueIterator(PriorityQueue<QueueItem<T>> queue) {
			this.queue = new PriorityQueue<QueueItem<T>>(queue);
		}
		
		private PriorityQueue<QueueItem<T>> queue;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !this.queue.isEmpty();
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return this.queue.poll().value;
		}
	}

}
