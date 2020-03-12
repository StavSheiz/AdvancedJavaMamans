
public class CustomerTicket {

	public CustomerTicket(String id, String name, String content) {
		this.id = id;
		this.name = name;
		this.content = content;
	}
	
	private String id;
	private String name;
	private String content;

	@Override
	public boolean equals(Object other) {
		return (other instanceof CustomerTicket) && 
				this.id.equals(((CustomerTicket)other).id) && 
				this.content.equals(((CustomerTicket)other).content);
	}
	
	@Override
	public String toString() {
		return "id: " + this.id + ", name: " + this.name + ", content: " + this.content; 
	}
}
