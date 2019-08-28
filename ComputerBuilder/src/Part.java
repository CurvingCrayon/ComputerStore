import java.text.DecimalFormat;
public class Part {
	private String name;
	private String type;
	private double price;
	public Part(String name, String type, double price) {
		this.name = name;
		this.type = type.toLowerCase();
		this.price = price;
	}
	public String printPrice() {
		DecimalFormat f = new DecimalFormat("####0.00");
		return "$" + f.format(this.price);
	}
	public double getPrice() {
		return this.price;
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
	@Override
	public String toString() {
		return this.type.toUpperCase() + ": " + this.name + " @ " + this.printPrice();
	}
}
