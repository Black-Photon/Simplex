import java.util.ArrayList;

public class Start {
	static DualList<Variable> tableau;
	static Variable x;
	static Variable y;
	static Variable value;
	static Profit profit;
	static Constraint a;
	static Constraint b;
	static ArrayList<Constraint> constraints;
	public static void main(String[] args) {
		//Doing it for 2 variables and 2 constraints
		tableau = new DualList();
		x = new Variable("x");
		y = new Variable("y");
		value = new Variable("v");
		profit = new Profit(x, y, value);
		a = new Constraint(x, y, value);
		b = new Constraint(x, y, value);
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

		int column = -1;
		for(int i = 1;i<tableau.getRow(1).size();i++){
			Variable j = tableau.get(1, i);
			if(j.getOperations().size()>0)
			if(j.getOperations().get(0).getV().getValue()==-1 && j.getOperations().size()==1){
				column = i;
				break;
			}
		}
		if(column==-1){
			finish();
			return;
		};

		int row = tableau.getColumn(column).size()-column;

		tableau.get(1,1).printOperationSummary();

		tableau.print();

		doSimplex(row, column);

		tableau.print();

		tableau.get(1,1).printOperationSummary();


	}
	private static void doSimplex(int row, int column){
		Variable pivot = tableau.get(row, column);
		for(int i = 0; i<tableau.getRow(row).size(); i++){
			tableau.set(tableau.get(row, i).performOperation(Operations.DIVIDE, pivot), row, i);
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
				tableau.get(i,j).setLabel(tableau.get(i,j).getLabel()+i);
			}
		}
	}
}
