package main;

/* by Qiu Zhen
  */


public class Seat {
	//Variables
	private int row;
	private int col;
	private boolean assigned;
	
	//Constructor
	public Seat(int row, int col) {
		this.row=row;
		this.col=col;
	}
	
	//Getter
	public int getRowNo() {
		return row;
	}
	
	public int getColNo() {
		return col;
	}
	
	public boolean isAssigned() {
		return assigned;
	}
	
	
	//Setter
	
	public void assign() {
		assigned = true;
	}
	
	public void unAssign() {
		assigned= false;
	}
	
}
