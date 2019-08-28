import  java.util.*;
public class Catalogue {
	private List<Part> parts;
	public Catalogue() {
		this.parts = new ArrayList<Part>();
		this.addPart(new Part("evo 860","storage", 155.00));
		this.addPart(new Part("daskeyboard", "keyboard", 239.00));
		this.addPart(new Part("i5", "cpu", 365.00));
		this.addPart(new Part("Corsair 16G", "memory", 299.00));
		this.addPart(new Part("ASUS ROG", "motherboard", 159.00));
		this.addPart(new Part("sheetmetal box", "case", 39.00));
		this.addPart(new Part("Ryzen 7", "cpu", 299.00));

	}
	public void addPart(Part newPart) {
		this.parts.add(newPart);
	}
	
	public String listItems(String type, double minPrice, double maxPrice) {
		String out = "";
		boolean firstLine = true;
		for(int index = 0; index < this.parts.size(); index++) {
			Part prt = this.parts.get(index);
			if((prt.getType().equals(type) || type.equals("all")) && ((prt.getPrice() >= minPrice && prt.getPrice() <= maxPrice)||minPrice == -1)) {
				if(!firstLine) {
					out += "\n";
				}
				else {
					firstLine = false;
				}
				out += Integer.toString(index+1) +". " + 
						prt.getType().toUpperCase() + ": " +
						prt.getName() + " @ " +
						prt.printPrice();
			}
					
		}
		return out;
	}
	public Part getPart(int index) {
		return this.parts.get(index);
	}
	public boolean removePart(int index) {
		if(index < this.parts.size() && index >= 0) {
			this.parts.remove(index);
			return true;
		}
		return false;
	}
	public boolean partExists(int index) {
		return (index < this.parts.size() && index >= 0);
	}
	@Override
	public String toString() {
		String out = "";
		boolean firstLine = true;
		for(int index = 0; index < this.parts.size(); index++) {
			Part prt = parts.get(index);
			if(!firstLine) {
				out += "\n";
			}
			else {
				firstLine = false;
			}
			out += Integer.toString(index+1)+". "+prt.toString();
					
		}
		return out;
	}
}
