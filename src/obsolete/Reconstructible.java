package obsolete;

public interface Reconstructible {
	
	/**
	 * This interface is used to save objects as strings and to reconstruct them later
	 * @return a String in the utilizing getClass().getName() and then all following
	 * variables should be formatted with a new line before the raw value. Example:
	 * "return this.getClass().getName() + "\n" + this.param1 + "\n" + this.param2;"
	 */
	public default String getClassInfo() {
		return this.getClass().getName();
	}

}

//public Feedforward(String name) throws Exception {
//File file = new File("net"); file.mkdirs();
//Scanner scan = new Scanner(new File(file, name + ".txt"));
//// Look for NetworkSizes
//while (!scan.next().equals("[")) {}
//int[] netSize = new int[] {};
//while (scan.hasNextInt()) {
//	int[] temp = new int[netSize.length+1];
//	for (int i = 0; i < netSize.length; i++) {temp[i] = netSize[i];}
//	temp[netSize.length] = scan.nextInt(); netSize = temp;
//} this.networkSizes = netSize;
//// Find network parameters
//this.initializer = (Initializer) this.search("Initializer Information:", scan);
//this.activation = (Activation) this.search("Activation Information:", scan);
//this.cost = (Cost) this.search("Cost Information:", scan);
//this.optimizer = (Optimizer) this.search("Optimizer Information:", scan);
//// Get cost
//scan.nextLine(); this.currCost = scan.nextFloat();
//// Initialize vectors and matrices
//this.activations = 	  new Vector[this.networkSizes.length];
//	this.biases = 		  new Vector[this.networkSizes.length-1];
//	this.preActivations = new Vector[this.networkSizes.length-1];
//	this.weights =		  new Matrix[this.networkSizes.length-1];
//	// Loop through and propagate arrays
//	for (int i = 1; i < this.networkSizes.length; i++) {
//		this.weights[i-1] = new Matrix(this.networkSizes[i-1], this.networkSizes[i]);
//		this.biases[i-1] = new Vector(this.networkSizes[i]);
//	} // Propagate weights and biases
//	this.textToMatrix(scan, this.weights);
//	this.textToMatrix(scan, this.biases);
//scan.close();
//}
//
//private void textToMatrix(Scanner scan, Matrix[] m) {
//for (int k = 0; k < this.biases.length; k++) {
// 	while (!scan.hasNextFloat()) {scan.nextLine();}
// 	for (int i = 0; i < m[k].getHgt(); i++) {
// 		for (int j = 0; j < m[k].getWid(); j++) {
// 			m[k].set(i, j, scan.nextFloat());
// 		}
// 	}
//	}
//}
//
///**
//* Function for finding and initializing network params
//* @param str Header information
//* @param scan Scanner object
//* @return Class within text file
//* @throws Exception
//*/
//private Object search(String str, Scanner scan) throws Exception {
//while (!scan.nextLine().equals(str)) {} scan.nextLine();
//String className = scan.nextLine(); String[] args = new String[] {};
//String next = scan.nextLine();
//while (!next.isBlank() && !next.contains(":")) {
//	String[] temp = new String[args.length+1];
//	for (int i = 0; i < args.length; i++) {temp[i] = args[i];}
//	temp[args.length] = next; args = temp;
//	next = scan.nextLine();
//} Class<?> t, c = Class.forName(className);  // Get class from string and make temp
//Class<?>[] p = new Class<?>[args.length];  // Create container for params
//Object[] argsReal = new Object[args.length];
//// Iterate through arguments provided
//for (int i = 0; i < args.length; i++) {
//	// Assign temp to arguments class
//	try {argsReal[i] = Float.parseFloat(args[i]); t = float.class;} 
//	catch (NumberFormatException e) {
//		try {argsReal[i] = Integer.parseInt(args[i]); t = int.class;} 
//		catch(NumberFormatException f) {
//			argsReal[i] = Boolean.parseBoolean(args[i]); t = boolean.class;
//		}
//	} p[i] = t;  // Assign  temp to param
//} System.out.print("Found " + className);
//if (args.length > 0) { // Check if parameters were found
//	System.out.print(" with params: ");
//	for (String i : args) {System.out.print(i + " ");} 
//} System.out.println();  // Create new instance of described class
//return c.getDeclaredConstructor(p).newInstance(argsReal);
//}

//@Override
//public void saveNetwork(String name) {	
//BufferedWriter writer;
//try {
//	File file = new File("data"); file.mkdirs();
//    writer = new BufferedWriter(new FileWriter(new File(file, name + ".txt")));
//    // Save network geometry
//	writer.write("Network Sizes:\n\n[\n"+this.networkSizes[0]);
//	for (int i = 1; i < this.networkSizes.length; i++) {
//		writer.write(" "+this.networkSizes[i]);
//	} writer.write("\n]\n\n");
//	// Technically unneeded as network is made as constructor
//	writer.write("Network Information:\n\n"+this.getClassInfo()+"\n\n");
//	// Save important network info
//	writer.write("Initializer Information:\n\n"+this.initializer.getClassInfo()+"\n\n");
//	writer.write("Activation Information:\n\n"+this.activation.getClassInfo()+"\n\n");
//	writer.write("Cost Information:\n\n"+this.cost.getClassInfo()+"\n\n");
//	writer.write("Optimizer Information:\n\n"+this.optimizer.getClassInfo()+"\n\n");
//	// Recomputed at backprop, but if i move this ill have to change my load function
//	writer.write("Last Cost:\n\n"+this.currCost+"\n\n");
//	// Save weights and biases
//	writer.write("Weights:\n"+this.printMatrices(this.weights)+"\n\n");
//	writer.write("Biases:\n"+this.printMatrices(this.biases)+"\n\n");
//	// Also unneeded as these are recomputed in backprop
//	writer.write("Activations:\n"+this.printMatrices(this.activations)+"\n\n");
//	writer.write("Pre Activations:\n"+this.printMatrices(this.preActivations)+"\n\n");
//	writer.close();
//} catch (IOException e) {
//	System.out.println("Save Failed.");
//	e.printStackTrace();
//}
//}
//
//private String printMatrices(Matrix[] m) {
//StringBuilder s = new StringBuilder();
//for (int k = 0; k < m.length; k++) {
//	s.append("\nLayer " + k + ":\n[\n");
//	for (int i = 0; i < m[k].getHgt(); i++) {
//		for (int j = 0; j< m[k].getWid(); j++) {
//			s.append(m[k].get(i, j) + " ");
//		} s.append("\n");
//	} s.append("]\n");
//} return s.toString();
//}
