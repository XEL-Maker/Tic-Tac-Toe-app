package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    int[][]winingPositions=
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

        resetGame=(Button)findViewById(R.id.resetGame);

        for(int i=0;i<buttons.length;i++){
            String btnID="btn_"+i;
            int resId=getResources().getIdentifier(btnID,"id",getPackageName());
            buttons[i]=(Button)findViewById(resId);
            buttons[i].setOnClickListener(this);
        }

        roundCount=0;
        playerOneScoreCount=0;
        playerTwoScoreCount=0;
        activePlayer=true;
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        String btnId=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer= Integer.parseInt(btnId.substring(btnId.length()-1,btnId.length()));
        if(activePlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer]=0;
        }else{
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gameStatePointer]=1;
        }
        roundCount++;
        if(checkWinner()){
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player One Won!", Toast.LENGTH_SHORT).show();
                //playAgain();
            }else{
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player Two Won!", Toast.LENGTH_SHORT).show();
                //playAgain();
            }
        }else if(roundCount==9){
            Toast.makeText(this,"No Winner!", Toast.LENGTH_SHORT).show();
        }
        else{
            activePlayer=!activePlayer;
        }
        if(playerOneScoreCount>playerTwoScoreCount){
            playerStatus.setText("Player one is Winning!");
        }
        else if(playerTwoScoreCount>playerOneScoreCount){
            playerStatus.setText("Player Two is Winning!");
        }
        else{
            playerStatus.setText("");
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                /*
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
                playerStatus.setText("");
                updatePlayerScore();
                */
            }
        });
    }
    public boolean checkWinner(){
        boolean winnerResult = false;
        for(int [] winningPos : winingPositions){
            if(gameState[winningPos[0]]==gameState[winningPos[1]]&&
                    gameState[winningPos[1]]==gameState[winningPos[2]]&&
                    gameState[winningPos[0]]!=2){
                winnerResult=true;
            }
        }
        return winnerResult;
    }
    public  void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        roundCount=0;
        activePlayer=true;
        for(int i=0;i<buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
    }
}