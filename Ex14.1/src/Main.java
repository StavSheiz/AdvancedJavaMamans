import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<Integer>(ENUMPriority.Four);
		
		System.out.println("Integer queue:");
		
		queue.add(1, 4);
		queue.add(3, 3);
		queue.add(1, 1);
		queue.add(2, 2);
		queue.add(10, 20);
		
		printItems(queue);
		
		queue.remove(1);
		queue.remove(2);
		
		printItems(queue);
		
		queue.add(5, 1);
		queue.add(6, 2);
		
		printItems(queue);
		
		System.out.println();
		System.out.println("CustomerTicket queue:");
		
		MyPriorityQueue<CustomerTicket> customerTicketQueue = new MyPriorityQueue<CustomerTicket>(ENUMPriority.Three);
		
		customerTicketQueue.add(new CustomerTicket("1", "Stav", "There is a problem."), 2);
		customerTicketQueue.add(new CustomerTicket("1", "Stav", "This is not working."), 2);
		customerTicketQueue.add(new CustomerTicket("2", "Not Stav", "I can't read"), 3);
		customerTicketQueue.add(new CustomerTicket("3", "David", "Hello?"), 1);
		customerTicketQueue.add(new CustomerTicket("1", "Stav", "There is a problem."), 3);
		
		printItems(customerTicketQueue);
		
		System.out.println("Poll: " + customerTicketQueue.poll());
		
		printItems(customerTicketQueue);
		
		
		CustomerTicket ticket = new CustomerTicket("1", "Stav", "There is a problem.");
		System.out.println("Remove: " + ticket);
		customerTicketQueue.remove(ticket);
		System.out.println("Contains: " + ticket + ": " + customerTicketQueue.contains(ticket));
		
		printItems(customerTicketQueue);
		
		System.out.println("Remove: " + ticket);
		customerTicketQueue.remove(ticket);
		System.out.println("Contains: " + ticket + ": " + customerTicketQueue.contains(ticket));

		printItems(customerTicketQueue);

	}
	
	private static void printItems(MyPriorityQueue queue) {
		Iterator iterator = queue.iterator();
		
		System.out.println("Items in queue:");
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
