package com.hfad.sudoku_solver;

import android.content.res.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by Lord Daniel on 7/3/2016.
 */
public class SudokuConfig {

    private int[][] grid;
    private int row;
    private int col;

    public SudokuConfig(int[][] grid){
        this.grid = grid;
    }

    public SudokuConfig(SudokuConfig other){
        int[][] ar = new int[9][9];
        for (int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                ar[i][j]=other.grid[i][j];
            }
        }
        this.grid = ar;
        this.row=other.row;
        this.col=other.col;
    }

    public Collection<SudokuConfig> getSuccessors(){
        ArrayList<SudokuConfig> ar = new ArrayList<>();
        boolean flag=false;
        for (int i=this.row;i<9;i++){
            if(flag){
                break;
            }
            for(int j=0;j<9;j++){
                if(flag){
                    break;
                }
                if(this.grid[i][j]==0){
                    for(int k=1;k<10;k++){
                        SudokuConfig SC = new SudokuConfig(this);
                        SC.grid[i][j]=k;
                        SC.row=i;
                        SC.col=j;
                        ar.add(SC);
                    }
                    flag=true;
                    break;
                }
            }
        }
        return ar;
    }

    public boolean isValid(){
        int r;
        int c;

        r=this.row;
        c=this.col;
        //System.out.println("this.row: "+this.row+"   this.column: "+this.col);

        int n=this.grid[r][c];
        //System.out.println("in isValid(): "+r+" "+c+"  element: "+grid[r][c]);

        //check all values in the current column, to make sure
        //that all values in this column are different
        for (int i=0;i<9;i++){
            if (this.grid[i][c]==n && i!=r){
                return false;
            }
        }
        //check all values in the current row, to make sure
        //that all values in this row are different
        for (int i=0;i<9;i++){
            //System.out.println(grid[r][i]+ " n: "+n+"   i: "+i+"   c: "+c);
            if (this.grid[r][i]==n && i!=c){
                return false;
            }
        }

        //check all values in current 3x3 region
        int row1;
        int row2;
        if (r>=0 && r <=2){
            row1 = 0; row2 = 2;
        }
        else if (r>=3 && r <=5){
            row1 = 3; row2 = 5;
        }
        else{
            row1 = 6; row2 = 8;
        }

        int col1;
        int col2;
        if (c>=0 && c <=2){
            col1 = 0; col2 = 2;
        }
        else if ((c>=3 && c <=5)){
            col1 = 3; col2 = 5;
        }
        else{
            col1 = 6; col2 = 8;
        }

        for(int i=row1;i<=row2;i++){
            for(int j=col1;j<=col2;j++){
                if(this.grid[i][j]==n && i!=r && j!=c){
                    return false;
                }
            }
        }


        return true;
    }

    public boolean isGoal(){
        for (int i = 0;i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }

    public String toString(){
        String st="";
        for (int i=0;i<9;i++){
            st+="| ";
            for(int j=0;j<9;j++) {
                if ((j + 1) % 3 == 0) {
                    st += this.grid[i][j] + "  |  ";
                } else {
                    st += this.grid[i][j] + "  ";
                }
            }
            st += "\n";
        }
        return st;
    }

    public int[][] getGrid(){
        return this.grid;
    }



}
