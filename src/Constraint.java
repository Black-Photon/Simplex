import java.util.ArrayList;

public class Constraint {
	private ArrayList<Variable> variables;
	private ArrayList<Variable> coefficients;
	private Integer size;
	private Variable value;
	public Constraint(){
		variables = new ArrayList<>();
		coefficients = new ArrayList<>();
	}
	public Constraint(Variable ... variables){
		this();
		addVariables(variables);
	}
	public void addVariables(Variable ... variables){
		for(Variable i: variables){
			this.variables.add(i);
			coefficients.add(new Variable(i.getLabel()));
		}
		size = this.variables.size();
	}

	public int size(){
		return size;
	}

	public Variable getValue() {
		return value;
	}

	public void setValue(Variable value) {
		this.value = value;
	}

	public ArrayList<Variable> getVariables() {
		return variables;
	}

	public ArrayList<Variable> getCoefficients() {
		return coefficients;
	}
}
