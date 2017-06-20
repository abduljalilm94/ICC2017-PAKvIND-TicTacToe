package io.abduljalil.projects.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        ImageButton b[][] = new ImageButton[3][3];
        b[0][0] = (ImageButton) findViewById(R.id.imageButton00);
        b[0][1] = (ImageButton) findViewById(R.id.imageButton01);
        b[0][2] = (ImageButton) findViewById(R.id.imageButton02);
        b[1][0] = (ImageButton) findViewById(R.id.imageButton10);
        b[1][1] = (ImageButton) findViewById(R.id.imageButton11);
        b[1][2] = (ImageButton) findViewById(R.id.imageButton12);
        b[2][0] = (ImageButton) findViewById(R.id.imageButton20);
        b[2][1] = (ImageButton) findViewById(R.id.imageButton21);
        b[2][2] = (ImageButton) findViewById(R.id.imageButton22);

        Button reset = (Button) findViewById(R.id.resetButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });


        View.OnClickListener myListener = new View.OnClickListener() {
            int state = 0; // 0: playing, 1: draw, 2: kohli won, 3: sarfraz won
            int turn = 0; // 0: kohli's turn, 1: sarfraz's turn
            int condition[][] = new int[3][3]; // 0: unmarked, 1: kohli, 2: sarfraz

            @Override
            public void onClick(View v) {
                ImageButton myButton = (ImageButton) v;
                int buttonId = myButton.getId();
                int opp;
                int team;
                int winState;
                boolean draw;
                boolean topCond;
                boolean midCond;
                boolean bottomCond;
                boolean leftCond;
                boolean midVerCond;
                boolean rightCond;
                boolean diagCond;
                boolean antiDiadCond;

                if(state == 0) {
                    //set turn based variables
                    if(turn == 0) {
                        team = 1;
                        opp = 2;
                        winState = 2;
                        textView.setText("Sarfraz's turn...");
                    } else {
                        team = 2;
                        opp = 1;
                        winState = 3;
                        textView.setText("Kohli's turn...");
                    }

                    //set image and data in array
                    if(buttonId == R.id.imageButton00 && condition[0][0] != opp) {
                        condition[0][0] = team;
                    } else if(buttonId == R.id.imageButton01 && condition[0][1] != opp) {
                        condition[0][1] = team;
                    } else if(buttonId == R.id.imageButton02 && condition[0][2] != opp) {
                        condition[0][2] = team;
                    } else if(buttonId == R.id.imageButton10 && condition[1][0] != opp) {
                        condition[1][0] = team;
                    } else if(buttonId == R.id.imageButton11 && condition[1][1] != opp) {
                        condition[1][1] = team;
                    } else if(buttonId == R.id.imageButton12 && condition[1][2] != opp) {
                        condition[1][2] = team;
                    } else if(buttonId == R.id.imageButton20 && condition[2][0] != opp) {
                        condition[2][0] = team;
                    } else if(buttonId == R.id.imageButton21 && condition[2][1] != opp) {
                        condition[2][1] = team;
                    } else if(buttonId == R.id.imageButton22 && condition[2][2] != opp) {
                        condition[2][2] = team;
                    } else {
                        return;
                    }
                    myButton.setImageResource((team == 1) ? R.drawable.kohli : R.drawable.sarfraz);

                    //check draw condition
                    draw = true;
                    for(int a=0; a<3; a++)
                        for(int b=0; b<3; b++) {
                            if(condition[a][b] == 0) draw = false;
                        }

                    //set win conditions
                    topCond = (condition[0][0] == team && condition[0][1] == team && condition[0][2] == team);
                    midCond = (condition[1][0] == team && condition[1][1] == team && condition[1][2] == team);
                    bottomCond = (condition[2][0] == team && condition[2][1] == team && condition[2][2] == team);
                    leftCond = (condition[0][0] == team && condition[1][0] == team && condition[2][0] == team);
                    midVerCond = (condition[0][1] == team && condition[1][1] == team && condition[2][1] == team);
                    rightCond = (condition[0][2] == team && condition[1][2] == team && condition[2][2] == team);
                    diagCond = (condition[0][0] == team && condition[1][1] == team && condition[2][2] == team);
                    antiDiadCond = (condition[0][2] == team && condition[1][1] == team && condition[2][0] == team);

                    //check win condition
                    if(topCond || midCond || bottomCond || leftCond || midVerCond || rightCond || diagCond || antiDiadCond) {
                        state = winState;
                        textView.setText((turn == 0)?"KOHLI WON!!":"SARFRAZ WON!!");
                    } else if(draw) {
                        state = 1;
                        textView.setText("Game drawn!");
                        Log.d("game", "****GAME DRAWN!****");
                    }
                    turn = (turn == 0)?1:0;

                } else if(state == 2) {
                    Log.d("game","****KOHLI WON****");
                } else if(state == 3) {
                    Log.d("game","****SARFRAZ WON****");
                } else {
                    Log.d("game", "****GAME DRAWN!****");
                }
            }
        };

        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++) {
                b[i][j].setOnClickListener(myListener);
            }

    }

}
