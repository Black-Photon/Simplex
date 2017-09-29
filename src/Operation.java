public class Operation {
	private Operations o;
	private Variable v;
	private int order;

	public Operation(Operations o, Variable v, int order){
		this.o = o;
		this.v = v;
		this.order = order;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
