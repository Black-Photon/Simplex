import java.util.ArrayList;

public class Start {
	static DualList<Variable> tableau;
	static Variable x,y,z, value;
	static Profit profit;
	static Constraint a, b;
	static ArrayList<Constraint> constraints;
	public static void main(String[] args) {
		/* TESTING!!!

		Variable X = new Variable("x");
		Variable one = new Variable(1,"1");
		Variable two = new Variable(2,"2");
		Variable four = new Variable(4,"4");
		Variable five = new Variable(5,"5");
		Variable brackets = X.performOperation(Operations.MULTIPLY, X.performOperation(Operations.ADD, one));
		Variable left = brackets.performOperation(Operations.DIVIDE, two);
		Variable top = X.performOperation(Operations.SUBTRACT, four);
		Variable bottom = X.performOperation(Operations.ADD, five);
		Variable right = top.performOperation(Operations.DIVIDE, bottom);
		Variable whole = left.performOperation(Operations.ADD, right);
		whole.printOperationSummary();*/

		//Doing it for 2 variables and 2 constraints
		tableau = new DualList();
		x = new Variable("x");
		y = new Variable("y");
		z = new Variable("z");
		value = new Variable("v");
		profit = new Profit(x, y, z, value);
		a = new Constraint(x, y, z, value);
		b = new Constraint(x, y, z, value);
		constraints = new ArrayList();
		constraints.add(a);
		constraints.add(b);

		for (int i = 0; i < profit.getVariables().size(); i++) {
			tableau.set(profit.getVariables().get(i), 0, i);
			tableau.set(profit.getCoefficients().get(i), 1, i);
		}
		for (int i = 2; i < constraints.size() + 2; i++) {
			int temp = 1;
			for (int j = 0; j < constraints.get(i - 2).size(); j++) {
				tableau.set(constraints.get(i - 2).getCoefficients().get(j), i, j + 1);
			}
			tableau.set(new Variable(0, "P"), i, 0);
		}

		setTableauNames();

		tableau.set(tableau.get(1,1).performOperation(Operations.MULTIPLY, new Variable(-1,"-1")),1,1);
		tableau.set(tableau.get(1,2).performOperation(Operations.MULTIPLY, new Variable(-1,"-1")),1,2);

		for(int j = 0; j<profit.getVariables().size()-2;j++) {
			int column = calculateColumn(j);

			int row = calculateRow(column,j);

			doSimplex(row, column);


			//System.out.println("---------TESTING---------");
			//tableau.get(2,3).printOperationSummary();
			//System.out.println("---------TESTING---------");

			//Answers to First Run

			//Profit
			System.out.println("Profit:");
			tableau.get(1, tableau.getRow(1).size() - 1).printOperationSummary();

			//Variables
			System.out.println("\nVariables:");
			for (int i = 0; i < profit.getVariables().size() - 2; i++) {
				System.out.println(tableau.get(0, i + 1).getLabel() + ":");
				//System.out.println("TEST:");
				//tableau.get(calculateRow(i + 1,j), i + 1).printOperationSummary();
				System.out.println("VALUE:");
				tableau.get(calculateRow(i + 1,j), tableau.getRow(calculateRow(i + 1,j)).size() - 1).printOperationSummary();
				System.out.println("\n");
			}
		}

	}
	private static void doSimplex(int row, int column){
		Variable pivot = tableau.get(row, column);
		Variable staticPivot = new Variable(pivot);
		for(int i = 0; i<tableau.getRow(row).size(); i++){
			tableau.set(tableau.get(row, i).performOperation(Operations.DIVIDE, staticPivot), row, i);
		}
		for(int i = 1; i<tableau.getColumn(column).size(); i++){
			Variable pivotInRow = tableau.get(i, column);
			if(i!=row){
				for(int j = 0; j<tableau.getRow(i).size();j++){
					tableau.set(tableau.get(i, j).performOperation(Operations.SUBTRACT, pivotInRow.performOperation(Operations.MULTIPLY, tableau.get(row, j))), i, j);
				}
			}
		}
	}
	private static void finish(){

	}
	private static void setTableauNames(){
		for(int i = 1; i<tableau.getRow(0).size(); i++){
			for(int j = 0; j<tableau.getColumn(0).size();j++){
				tableau.get(j,i).setLabel(tableau.get(j,i).getLabel()+i);
			}
		}
	}

	//Calculates pivot rows and columns
	private static int calculateRow(int iteration){
		int column = calculateColumn(iteration);
		return tableau.getColumn(column).size()-column;
	}
	private static int calculateRow(int column, int iteration){
		return tableau.getColumn(column).size()-column;
	}
	private static int calculateColumn(int iteration){
		return iteration+1;
		/*int column = -1;
		iteration = iteration+1;
		Variable j = tableau.get(1, iteration);
		if(j.getOperations()!=null)
			if(j.getOperations().get(0).getV().getValue()==-1){
				column = iteration;
			}
		if(column==-1){
			finish();
		}
		return column;*/
	}
}
