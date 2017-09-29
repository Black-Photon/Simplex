import java.awt.*;
import java.util.ArrayList;

public class DualList<E> extends List{
	ArrayList<ArrayList<E>> rows;
	public DualList(){
		rows = new ArrayList<>();
	}

	public void set(E i, int row, int column){
		while(rows.size()<row+1){
			rows.add(new ArrayList());
		}
		while(rows.get(row).size()<column+1){
			rows.get(row).add(null);
		}
		rows.get(row).set(column, i);
	}

	public E get(int row, int column) throws IndexOutOfBoundsException{
		return rows.get(row).get(column);
	}
	public void print(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<rows.size();i++){
			for(int j = 0; j<rows.get(i).size();j++){
				if(j!=0) sb.append(", ");
				sb.append(rows.get(i).get(j));
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	public ArrayList<E> getRow(int i){
		return rows.get(i);
	}
	public ArrayList<E> getColumn(int i){
		ArrayList<E> column = new ArrayList<>();
		for(ArrayList<E> row: rows){
			column.add(row.get(i));
		}
		return column;
	}
}
