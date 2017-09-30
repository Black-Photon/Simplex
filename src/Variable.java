import java.util.ArrayList;

public class Variable {
	private double value;
	private String label;
	private ArrayList<Operation> operations;
	private String operationsApplied;

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
		operationsApplied = label;
	}
	public Variable(double i, String j){
		this();
		value = i;
		label = j;
		operationsApplied = label;
	}
	public Variable(Variable old){
		this.value = old.value;
		this.label = old.label;
		this.operations = new ArrayList<>(old.operations);
		this.operationsApplied = old.operationsApplied;
	}


	public Variable performOperation(Operations o, Variable v){
		Variable thisVariable = new Variable(this);
		Variable thatVariable = new Variable(v);
		thisVariable.getOperations().add(new Operation(o, thatVariable));
		thisVariable.setOperationsApplied();
		return thisVariable;
	}
	public void setOperationsApplied(){
		if(operations.size()==0)return;
		String operation = "";
		if(operations.size()==1){
			operation = stringFromOp(operations.get(0));
			if(operations.get(0).getV().getOperations().size()==0) operationsApplied = operationsApplied + operation + operations.get(0).getV().getOperationsApplied();
			else operationsApplied = operationsApplied + operation + "(" + operations.get(0).getV().getOperationsApplied()+")";
		}else{
			StringBuilder sb = new StringBuilder();
			for(Operation i: operations){
				if(!operations.get(operations.size()-1).equals(i)) sb.append("(");
			}
			sb.append(label);
			for(Operation i:operations){
				operation = stringFromOp(i);
				sb.append(operation);
				if(i.getV().getOperations().size()==0) sb.append(i.getV().getOperationsApplied());
				else sb.append("("+i.getV().getOperationsApplied()+")");

				if(!operations.get(operations.size()-1).equals(i)) sb.append(")");
			}
			operationsApplied = sb.toString();
		}
	}

	private String stringFromOp(Operation i){
		switch (i.getO()) {
			case MULTIPLY:
				return " X ";
			case ADD:
				return " + ";
			case SUBTRACT:
				return " - ";
			case DIVIDE:
				return " / ";
		}
		return null;
	}

	public String getOperationsApplied() {
		return operationsApplied;
	}

	public ArrayList<Operation> getOperations() {
		return operations;
	}

	public void setOperations(ArrayList<Operation> operations) {
		this.operations = operations;
	}
	public void printOperationSummary(){
		System.out.println(operationsApplied);
	}
	private void addOperationsToString(StringBuilder sb, Variable v){
		for(Operation i: v.getOperations()) {
			sb.append(stringFromOp(i));
			if(v.getOperations().size()>1) sb.append("(");
			Variable variable = i.getV();
			if (variable.getOperations().size()!=0) {
				sb.append("(" + variable.getLabel());
				addOperationsToString(sb, variable);
				sb.append(") ");
			} else {
				sb.append(variable.getLabel());
			}
			if(v.getOperations().size()>1)sb.append(")");
		}
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
