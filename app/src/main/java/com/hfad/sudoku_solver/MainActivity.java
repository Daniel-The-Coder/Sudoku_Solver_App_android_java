package com.hfad.sudoku_solver;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  boolean error = false;
    private EditText editTextClicked;
    private int bgColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editTextClicked = (EditText)findViewById(R.id.noneSelected);
    }

    public boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public int[][] createGrid(View view){
        int[][] grid = new int[9][9];

        TableLayout table = (TableLayout)findViewById(R.id.grid);
        for(int i=0;i<3;i++){
            TableRow tableRow = (TableRow)table.getChildAt(i);
            for(int j=0;j<3;j++){
                LinearLayout region = (LinearLayout)tableRow.getChildAt(j);
                for(int k=0;k<3;k++){
                    LinearLayout row = (LinearLayout)region.getChildAt(k);
                    for(int m=0;m<3;m++){
                        EditText cell = (EditText)row.getChildAt(m);
                        int r = i*3 + k;
                        int c = j*3 + m;
                        String txt = cell.getText().toString();
                        if(!isNumeric(txt)) {
                            grid[r][c] = 0;
                        }
                        else{
                            int n = Integer.parseInt(txt);
                            if (n>0 && n<10) {
                                grid[r][c] = Integer.parseInt(txt);
                            }
                            else{
                                this.error=true;
                            }
                        }
                    }
                }
            }
        }

        return grid;
    }

    public void solve(View view){
        this.error = false;
        int[][] grid = createGrid(view);
        if(this.error){
            CharSequence text = "Error: Numbers must be 1-9.";
            Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Storage.grid = grid;
            Intent intent = new Intent(this, SudokuSolverActivity.class);
            startActivity(intent);
        }
    }

    public void fieldClicked(View view){
        System.out.println("CELL CLICKED");
        //reverse previously selected cell's background
        if(this.editTextClicked.equals(findViewById(R.id.noneSelected))){
            if(this.bgColor == 1){
                this.editTextClicked.setBackground(getResources().getDrawable(R.drawable.textfields));
            }
            else{
                this.editTextClicked.setBackground(getResources().getDrawable(R.drawable.textfields2));
            }
        }
        else{
            this.editTextClicked.setBackground(getResources().getDrawable(R.drawable.textfields2));
        }

        this.editTextClicked = (EditText)view;
        if(this.editTextClicked.getBackground() == getResources().getDrawable(R.drawable.textfields)) {
            bgColor = 1;
        }
        else{
            bgColor = 0;
        }
        this.editTextClicked.setBackgroundColor(Color.parseColor("#0000ff"));
    }

    public void numberSelected(View view){
        System.out.println("NUMBER CLICKED");
        if(this.bgColor == 1){
            this.editTextClicked.setBackground(getResources().getDrawable(R.drawable.textfields));
        }
        else{
            this.editTextClicked.setBackground(getResources().getDrawable(R.drawable.textfields2));
        }
        if(this.editTextClicked.equals(findViewById(R.id.noneSelected))){
            CharSequence text = "First select a cell.";
            Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            this.editTextClicked.setText(((Button) view).getText());
            this.editTextClicked = (EditText)findViewById(R.id.noneSelected);
        }
    }
}
