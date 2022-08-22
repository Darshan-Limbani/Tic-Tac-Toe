package com.d.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    static int count = 0;
    int activePlayer = 0;
    //        Player Representation
//    //     0 - X
//    //     1 - O
    int[] tapActiveState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //    //    TapState meaning by means of image representation
//    //    0 - X
//    //    1 - O
//    //    2 - Empty
    int[][] winPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    //    //    Describes the position of winning Conditions
    //TextView status = findViewById(R.id.status);
    String winnerX = "X won the game";
    String winnerY = "O won the game";
    int temp = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void onPlayerTap(View view) {

        ImageView img = (ImageView) view;
        int tappedImagNo = Integer.parseInt(img.getTag().toString());
        TextView status = (TextView) findViewById(R.id.status);
        if (tapActiveState[tappedImagNo] == 2) {
            count++;
            tapActiveState[tappedImagNo] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                status.setText("0's Turn");
                activePlayer = 1;
            } else {
                img.setImageResource(R.drawable.o);
                status.setText("X's Turn");
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(400);
        }

        for (int[] gettappedposition : winPosition) {
            if (tapActiveState[gettappedposition[0]] == tapActiveState[gettappedposition[1]] &&
                    tapActiveState[gettappedposition[1]] == tapActiveState[gettappedposition[2]]
                    && tapActiveState[gettappedposition[1]] != 2) {
                if (tapActiveState[gettappedposition[0]] == 0) {
                    count = 0;
                    //winner = "X won the Game...";
                    AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);
                    builder.setMessage(winnerX).setTitle("Win").setCancelable(false).setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    recreate();
//                            finish();
                                }
                            })
                            .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    temp = 0;
                    // status.setText(winner);
                    break;
                } else if (tapActiveState[gettappedposition[0]] == 1) {
                    count = 0;
                    // winner = "O won the Game...";
                    // status.setText(winner);
                    AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);
                    builder.setMessage(winnerY).setTitle("Win").setCancelable(false).setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    recreate();
//                            finish();
                                }
                            })
                            .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                    temp = 1;
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    break;
                }
            } else if (count == 9 && temp != 0 && temp != 1) {
                count = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);
                builder.setMessage("Draw").setTitle("Draw").setCancelable(false).setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                recreate();
//                            finish();
                            }
                        })
                        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }
}
