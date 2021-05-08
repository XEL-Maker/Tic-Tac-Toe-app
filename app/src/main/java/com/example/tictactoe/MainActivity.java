1package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playerOneScore, playerTwoScore,playerStatus;
    private Button [] buttons= new Button[9];
    private  Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    boolean activePlayer;

    //p1 => 0
    //p2 => 1
    //공백 => 2
    int[]gameState={2,2,2,2,2,2,2,2,2};
    int[][]winingPosition=
            {{0,1,2},{3,4,5},{6,7,8},//가로
                    {0,3,6},{1,4,7},{2,5,8},//세로
                    {0,4,8},{2,4,6}//대각선
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerOneScore=(TextView)findViewById(R.id.playerOneScore);
        playerTwoScore=(TextView)findViewById(R.id.playerTwoScore);
        playerStatus=(TextView)findViewById(R.id.playerStatus);

        resetGame=(Button)findViewById(R.id.resetGane);

        for(int i=0;i<buttons.length;i++){
            String btnID="btn_"+i;
            int resId=getResources().getIdentifier(btnID,"id",getPackageName());
            buttons[i]=(Button)findViewById(resId);
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
    }
}