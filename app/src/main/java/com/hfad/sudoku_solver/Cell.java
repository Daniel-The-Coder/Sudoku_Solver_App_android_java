package com.hfad.sudoku_solver;

import java.util.ArrayList;

/**
 * Created by Lord Daniel on 7/4/2016.
 */
public class Cell {
    private int value;
    private boolean solved;
    private ArrayList<Integer> possibleValues;
    private int isSolution;

    public Cell(int value, boolean solved, int isSolution){
        this.value = value;
        this.solved = solved;
        this.isSolution = isSolution;
        this.possibleValues = new ArrayList<>();
        for (int i=1;i<10;i++){
            this.possibleValues.add(i);
        }
    }

    public int getValue(){
        return this.value;
    }

    public boolean getSolved(){
        return this.solved;
    }

    public ArrayList getPossibleValues(){
        return this.possibleValues;
    }

    public void setPossibleValuesEmpty(){
        this.possibleValues = new ArrayList<>();
    }

    public void setValue(int val){
        this.value = val;
    }

    public void setSolved(){
        this.solved=true;
    }

    public void removeFromList(int n){
        if(possibleValues.contains(n)) {
            this.possibleValues.remove(new Integer(n));
        }
    }

    public int isSolution(){
        return this.isSolution;
    }
}
