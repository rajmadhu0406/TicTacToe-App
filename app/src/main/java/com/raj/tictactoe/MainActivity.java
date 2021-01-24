package com.raj.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.view.View;

import android.util.Log;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,pointsreset;
    TextView headerText, xpoint, opoint;



    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    int PLAYER_O = 0;
    int PLAYER_X = 1;

    int activePlayer = PLAYER_O;

    int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean game = true;

    int count = 0;

    int xp = 0;
    int op = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerText = findViewById(R.id.header_text);
        xpoint = findViewById(R.id.xpoint);
        opoint = findViewById(R.id.opoint);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        pointsreset = findViewById(R.id.pointsreset);


        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        //code for when the buttons are clicked
        if(!game){
            return;
        }

        Button clickedBtn = findViewById(v.getId());  //get btn id of the button which is pressed
        int clickedTag = Integer.parseInt(v.getTag().toString()); //get tag of that pressed button



        if(filledPos[clickedTag] != -1) {
            return;
            //buttons does not override themselves when clicked twice
        }

        filledPos[clickedTag] = activePlayer;

        if (activePlayer == PLAYER_O) {
            count++;
            clickedBtn.setText("O");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_blue_bright));
            activePlayer = PLAYER_X;
            headerText.setText("X's Turn");

        } else {
            count++;
            clickedBtn.setText("X");
            activePlayer = PLAYER_O;
            clickedBtn.setBackground(getDrawable(android.R.color.holo_orange_light));
            headerText.setText("O's Turn");
        }

        Log.v(LOG_TAG, " count before winner : " + xp + "--" + op);

        Winner();

        Log.v(LOG_TAG, " count after winner : " + xp + "==" + op);
    }

    private void Winner(){
        //who won the  game logic
        boolean tie = true;

        int[][] winCondition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int i = 0; i < 8; i++)
        {
            int v1 = winCondition[i][0];
            int v2 = winCondition[i][1];
            int v3 = winCondition[i][2];

            if(filledPos[v1] == filledPos[v2] && filledPos[v2] == filledPos[v3] &&  filledPos[v1] != -1){

                game = false;

                if(filledPos[v1] == PLAYER_O){
                    tie = false;
                    op++;
                   opoint.setText(String.valueOf(op));
                    Dialog("Player O is Winner!!!");
                    restartGame();
                }
                else{
                    tie = false;
                    xp++;
                    xpoint.setText(String.valueOf(xp));
                    Dialog("Player X is Winner!!!");
                    restartGame();
                }
            }
        }//for

        if(count == 9 && tie == true){
            count = 0;
            Dialog("It's a Tie!!!");
            restartGame();
        }

    }//Winner()

    private void Dialog(String s){
        new AlertDialog.Builder(this)
                .setTitle(s)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                })
                .show();
    }

    private void restartGame(){
        activePlayer = PLAYER_O;
        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        game = true;

       // #66CDAA
        int x = Color.rgb(70,130,180 );
        btn0.setBackgroundColor(x);
        btn1.setBackgroundColor(x);
        btn2.setBackgroundColor(x);
        btn3.setBackgroundColor(x);
        btn4.setBackgroundColor(x);
        btn5.setBackgroundColor(x);
        btn6.setBackgroundColor(x);
        btn7.setBackgroundColor(x);
        btn8.setBackgroundColor(x);

        count = 0;

        headerText.setText("TicTacToe");
    }

    public void resetpoints(View view){
        xp = 0;
        op = 0;
        xpoint.setText("0");
        opoint.setText("0");
        restartGame();
    }


}