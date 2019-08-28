import  java.util.*;
import java.text.DecimalFormat;
public class Build {
	private List<Part> parts;
	public Build() {
		this.parts = new ArrayList<Part>();
	}
	public void clear() {
		this.parts.clear();
	}
	public void addPart(Part newPart) {
		this.parts.add(newPart);
	}
	public void removePart(int index) {
		this.parts.remove(index);
	}
	
	public String printCost() {
		double total = 0;
		for(int index = 0; index < this.parts.size(); index++) {
			total += this.parts.get(index).getPrice();
		}
		DecimalFormat f = new DecimalFormat("####0.00");
		return "$"+f.format(total);
	}
	public String getMissingParts() {
		String reqParts[] = {"motherboard","cpu","case","RAM","storage"};
		String out = "";
		boolean firstLine = true;
		for(int reqIndex = 0; reqIndex < reqParts.length; reqIndex++) { //Go through each of the required parts
			boolean found = false;
			String targetType = reqParts[reqIndex];
			
			for(int partIndex = 0; partIndex < this.parts.size(); partIndex++) { //Try to find the part type in the existing parts
				String thisType = this.parts.get(partIndex).getType();
				if(thisType == "memory") {
					thisType = "ram";
				}
				if(thisType.toLowerCase().equals(targetType.toLowerCase())) {
					found = true;
				}
			}
			if(!found) {
				if(!firstLine) {
					out += "\n";
				}
				else {
					firstLine = false;
				}
				String directive = (reqIndex <= 2) ? "a " : "";
				out += "The build is missing " + directive + targetType + ".";
			}
		}
		if(out == "") {
			out = "The build is functional.";
		}
		return out;
	}
	public boolean partExists(int index) {
		return (index < this.parts.size() && index >= 0);
	}
	@Override
	public String toString() {
		String out = "";
		boolean firstLine = true;
		for(int index = 0; index < this.parts.size(); index++) {
			Part prt = this.parts.get(index);
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
