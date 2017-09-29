public class Profit extends Constraint{
	public Profit(Variable ... variables){
		super();
		Variable profitV = new Variable("P");
		addVariables(profitV);
		addVariables(variables);
	}
}
