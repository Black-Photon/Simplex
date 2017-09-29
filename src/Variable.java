import java.util.ArrayList;

public class Variable {
	private double value;
	private String label;
	private ArrayList<Operation> operations;
	private Variable original;

	public Variable(){
		operations = new ArrayList<>();
	}
	public Variable(double i){
		this();
		value = i;
	}
	public Variable(String i){
		this();
		label = i;
	}
	public Variable(double i, String j){
		this();
		value = i;
		label = j;
	}
	public Variable(Variable old){
		this.value = old.value;
		this.label = old.label;
		this.operations = old.operations;
	}


	public Variable performOperation(Operations o, Variable v){
		ArrayList<Operation> ops = new ArrayList<>();
		ops.addAll(v.getOperations());
		Variable variable = new Variable(this);
		if(original==null) variable.setOriginal(this);
		int order = operations.size()+1;
		variable.getOperations().add(new Operation(o, v, order));

		for(Operation i: ops){
			i.setOrder(operations.size()+1);
			variable.operations.add(i);
		}

		return variable;
	}

	public ArrayList<Operation> getOperations() {
		return operations;
	}

	public void setOriginal(Variable original) {
		this.original = original;
	}

	public void printOperationSummary(){
		StringBuilder sb = new StringBuilder();
		sb.append(label);
		for(Operation i: operations){
			switch(i.getO()){
				case MULTIPLY:
					sb.append(" X ");
					break;
				case ADD:
					sb.append(" + ");
					break;
				case SUBTRACT:
					sb.append(" - ");
					break;
				case DIVIDE:
					sb.append(" / ");
					break;

			}
			sb.append(i.getV().getLabel());
		}
		System.out.println(sb.toString());
	}
	private int count = 1;
	private ArrayList<Operation> getOperationsInOrder(){
		boolean success = false;
		ArrayList<Operation> operationsO = new ArrayList();
		for(Operation i: operations){
			if(i.getOrder()==count){
				count++;
				success = true;
				operationsO.add(i);
			}
		}
		if(success){
			operationsO.addAll(getOperationsInOrder());
		}else{
			return operationsO;
		}
		return null;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
