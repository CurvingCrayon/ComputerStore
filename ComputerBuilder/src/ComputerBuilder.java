import java.util.*;
public class ComputerBuilder {
	private Catalogue catalogue;
	private Build currentBuild;

	public static void main(String args[]) {
		boolean run = true;
		System.out.println("Welcome to Jaime's Computer Store\n" +
							"Quality Parts at the Best Prices\n" +
							"=================================");
		
		ComputerBuilder store = new ComputerBuilder();
		char context;
		while(run) {
			System.out.println("1. Catalogue Menu\n" +
					"2. Build Menu\n" +
					"3. Exit");
			System.out.print("Select option: ");
			context = In.nextChar();
			switch(context) {
			case '1':
				store.runCatalogue();
				break;
				
			case '2':
				store.runBuild();
				break;
				
			case '3':
				run = false;
				break;
				
			case '?':
				store.helpContext();
				break;
				
			default:
				//Assume correct input
				break;
			}
		}
		
	}
	public ComputerBuilder() {
		this.catalogue = new Catalogue();
		this.currentBuild = new Build();
	}
	private void runCatalogue() {
		
		System.out.println("Welcome to the parts catalogue.");
		char choice;
		boolean run = true;
		while(run) {
			System.out.print("Enter choice (a/r/s/f/x): ");
			choice = In.nextChar();
			switch(choice) {
			case 'a':
				System.out.print("Enter part name: ");
				String name = In.nextLine();
				System.out.print("Enter part type: ");
				String type = In.nextLine();
				System.out.print("Enter part price: ");
				double price = In.nextDouble();
				
				Part newPart = new Part(name, type, price);
				this.catalogue.addPart(newPart);
				break;
				
			case 'r':
				System.out.print("Enter catalogue number of part to remove: ");
				if(!this.catalogue.removePart(In.nextInt() - 1)) {
					System.out.println("The catalogue has no part with that number.");
				}
				break;
				
			case 's':
				System.out.println(this.catalogue.toString());
				break;
				
			case 'f':
				System.out.print("Enter type of part to view ('all' for no filtering): ");
				String targetType = In.nextLine();
				System.out.print("Enter minimum price ('-1' for no filtering): ");
				double minPrice = In.nextDouble();
				double maxPrice = 0;
				if(minPrice != -1) {
					System.out.print("Enter maximum price: ");
					maxPrice = In.nextDouble();
				}
				if(minPrice > maxPrice) {
					System.out.println("Minimum price shouldn't be greater than maximum price.");
				}
				else {
					String catList = this.catalogue.listItems(targetType, minPrice, maxPrice);
					if(catList != "") {
						System.out.println(catList);
					}
				}
				break;
				
			case '?':
				this.helpCatalogue();
				break;
				
			case 'x':
				run = false;
				break;
			default:
				//Assume correct input
				break;
			}
		}
	}
	public void runBuild() {
		System.out.println("Let's build a 1337 box!");
		char choice;
		boolean run = true;
		while(run) {
			System.out.print("Enter choice (n/a/r/v/c/x): ");
			choice = In.nextChar();
			switch(choice) {
			case 'n': //New build (clears current build)
				this.currentBuild.clear();
				break;
				
			case 'a': //Add part from catalogue to build
				System.out.print("Enter catalogue number of the part: ");
				String numsInput = In.nextLine();
				ArrayList<Integer> catNums = new ArrayList<Integer>();
				int size = this.processList(numsInput, catNums);
				for(int partNum = 0; partNum < size; partNum++) {
					int catNum = catNums.get(partNum);
					if(this.catalogue.partExists(catNum - 1)) {
						this.currentBuild.addPart(this.catalogue.getPart(catNum - 1));
					}
					else {
						System.out.println("There is no part by that number.");
					}
				}
				break;
				
			case 'r': //Remove part from build
				System.out.print("Enter number of part to remove: ");
				int partNum = In.nextInt();
				if(this.currentBuild.partExists(partNum - 1)) {
					this.currentBuild.removePart(partNum - 1);
				}
				else {
					System.out.println("The build has no part with that number.");
				}
				break;
				
			case 'v': //View build parts
				String newOut = this.currentBuild.toString();
				if(newOut != "") {
					System.out.println(newOut);
				}
				System.out.println("Total Price: " + this.currentBuild.printCost());
				break;
				
			case 'c': //Check what parts are missing
				System.out.println(this.currentBuild.getMissingParts());
				break;
				
			case '?':
				this.helpBuild();
				break;
				
			case 'x':
				run = false;
				break;
			default:
				//Assume correct input
				break;
			}
		}
	}
	public void helpContext() {
		System.out.println("1 = interact with the catalogue\n" +  
				"2 = build your computer!\n" + 
				"? = this help message");
	}
	public void helpBuild() {
		System.out.println("n = start a new build (clears old build)\n" +  
				"a = add a part from the catalogue to the build\n" +  
				"r = remove a part from the build\n" +  
				"v = show the current state of the build\n" +  
				"c = check if the build is a functional computer\n" +  
				"? = this help message");
	}
	public void helpCatalogue() {
		System.out.println("a = add a new part to the catalogue\n" + 
				"r = remove a part from the catalogue\n" + 
				"s = show the catalogue\n" + 
				"f = show a filtered view of the catalogue\n" +
				"? = this help message");
	}
	public int processList(String input, ArrayList<Integer> nums) {
		for(int ind = 0; ind < input.length(); ind++) {
			if(Character.isDigit(input.charAt(ind))) {
				String currentNum = Character.toString(input.charAt(ind));
				int numInd;
				for(numInd = ind + 1; numInd < input.length(); numInd++) {
					if(Character.isDigit(input.charAt(numInd))) {
						currentNum += Character.toString(input.charAt(numInd));
					}
					else {
						break;
					}
				}
				ind = numInd;
				nums.add(Integer.parseInt(currentNum));
			}
		}
		return nums.size();
	}
}
