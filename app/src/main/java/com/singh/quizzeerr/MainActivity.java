package com.singh.quizzeerr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
private TextView noofquestion,question;
private Button option1,option2,option3,option4;
private ArrayList<QuizModel> quizModels;
Random random;

int currentScrore=0,questionAttempted=1,currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        noofquestion=findViewById(R.id.noOfQuestion);
        question=findViewById(R.id.question);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);

        quizModels=new ArrayList<>();
        random=new Random();
        getQuizOpton(quizModels);
        currentPos=random.nextInt(quizModels.size());
        setDattoViw(currentPos);


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModels.get(currentPos).getAnswer().trim().toLowerCase().equals(option1.getText().toString().trim().toLowerCase())){

                    currentScrore++;

                }
                questionAttempted++;
                currentPos=random.nextInt(quizModels.size());
                setDattoViw(currentPos);
            }
        });


}

public void bottomsheet(){

    BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity.this);
    View bottomsheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet, (LinearLayout) findViewById(R.id.llscore));
    TextView scoretv=bottomsheetView.findViewById(R.id.yourscore);
    Button resquiz= bottomsheetView.findViewById(R.id.restart);
    scoretv.setText("Current Score is :"+currentScrore+ "/10");
    resquiz.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            currentPos=random.nextInt(quizModels.size());
            setDattoViw(currentPos);
            currentScrore=1;
            questionAttempted=1;
            bottomSheetDialog.dismiss();

        }
    });
    bottomSheetDialog.setCancelable(false);
    bottomSheetDialog.setContentView(bottomsheetView);
    bottomSheetDialog.show();
    }

  public void setDattoViw(int currentPos){

     noofquestion.setText("Question Attempted : "+questionAttempted+"/10");

if (questionAttempted==3){
bottomsheet();
}else{
    question.setText(quizModels.get(currentPos).getQuestion());
    option1.setText(quizModels.get(currentPos).getOption1());
    option2.setText(quizModels.get(currentPos).getOption2());
    option3.setText(quizModels.get(currentPos).getOption3());
    option4.setText(quizModels.get(currentPos).getOption4());
}

}

    private void getQuizOpton(ArrayList<QuizModel> quizModels) {

        quizModels.add(new QuizModel("Best Scripting Language",
                "Python","JavaScript","Php","Shell","Shell"));
        quizModels.add(new QuizModel("Best Programming Language",
                "Python","JavaScript","Php","Shell","python"));
        quizModels.add(new QuizModel("Best Database Language",
                "DBMS","JavaScript","MongoDB","DBMS ","Shell"));

    }
}