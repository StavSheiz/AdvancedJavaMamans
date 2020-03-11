
public enum ENUMPriority {
	One,
	Two,
	Three,
	Four,
	Five,
	Six,
	Seven,
	Eight,
	Nine,
	Ten;
	
	public Integer getValue() {
		return this.ordinal() + 1;
	}
}
