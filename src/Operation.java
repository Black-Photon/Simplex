public class Operation {
	private Operations o;
	private Variable v;

	public Operation(Operations o, Variable v){
		this.o = o;
		this.v = v;
	}

	public Operations getO() {
		return o;
	}

	public void setO(Operations o) {
		this.o = o;
	}

	public Variable getV() {
		return v;
	}

	public void setV(Variable v) {
		this.v = v;
	}
}
