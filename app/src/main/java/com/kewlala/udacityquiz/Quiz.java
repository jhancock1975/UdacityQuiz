package com.kewlala.udacityquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz extends AppCompatActivity {

    String userAnswersKeys[] = {"answer1a", "answer1b", "answer1c", "answer2a", "answer2b",
            "answer2c", "answer3a", "answer3b", "answer3c"};
    public static final String ANSWER1A = "answer1a";
    public static final String ANSWER1B = "answer2a";
    public static final String ANSWER1C = "answer3a";

    public static final String ANSWER2A = "answer2a";
    public static final String ANSWER2B = "answer2b";
    public static final String ANSWER2C = "answer2c";

    public static final String ANSWER3A = "answer3a";
    public static final String ANSWER3B = "answer3b";
    public static final String ANSWER3C = "answer3c";

    public static final String ANSWER1 = "answer1";
    public static final String ANSWER2 = "answer2";
    public static final String ANSWER3 = "answer3";

    private Map<String, Boolean> userAnswers = new HashMap<String, Boolean>();

    private void clearButton(int id){
        RadioButton rb = findViewById(id);
        rb.setChecked(false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);
        clearButton(R.id.q1_a_radioButton);
        clearButton(R.id.q1_b_radioButton);
        clearButton(R.id.q1_c_radioButton);

        clearButton(R.id.q2_a_radioButton);
        clearButton(R.id.q2_b_radioButton);
        clearButton(R.id.q2_c_radioButton);

        clearButton(R.id.q3_a_radioButton);
        clearButton(R.id.q3_b_radioButton);
        clearButton(R.id.q3_c_radioButton);

        userAnswers = new HashMap<String, Boolean>();

    }

    private void setAllAnswerValuesFalse(String startsWith) {
        if (userAnswers.keySet() != null && userAnswers.keySet().size() > 0) {
            for (String key : userAnswers.keySet()) {
                if (key.startsWith(startsWith)){
                    userAnswers.put(key, false);
                }
            }
        }
    }

    private void updateAnswerMap(String key){
        userAnswers.put(key, true);
    }
    private void logAnswerMap(){
        for (String key: userAnswers.keySet()){
            Log.d(this.getClass().getSimpleName(), "key: " + key + " value: "
                    + userAnswers.get(key));
        }
        Log.d(this.getClass().getSimpleName(), "***");
    }
    public void setAnswer1a(View view) {
        setAllAnswerValuesFalse(ANSWER1);
        updateAnswerMap(ANSWER1A);
        logAnswerMap();
    }

    public void setAnswer1b(View view) {
        setAllAnswerValuesFalse(ANSWER1);
        updateAnswerMap(ANSWER1B);
        logAnswerMap();
    }

    public void setAnswer1c(View view) {
        setAllAnswerValuesFalse(ANSWER1);
        updateAnswerMap(ANSWER1C);
        logAnswerMap();
    }

    public void setAnswer2a(View view) {
        setAllAnswerValuesFalse(ANSWER2);
        updateAnswerMap(ANSWER2A);
        logAnswerMap();
    }

    public void setAnswer2b(View view) {
        setAllAnswerValuesFalse(ANSWER2);
        updateAnswerMap(ANSWER2B);
        logAnswerMap();
    }

    public void setAnswer2c(View view) {
        setAllAnswerValuesFalse(ANSWER2);
        updateAnswerMap(ANSWER2C);
        logAnswerMap();
    }

    public void setAnswer3a(View view) {
        setAllAnswerValuesFalse(ANSWER3);
        updateAnswerMap(ANSWER3A);
        logAnswerMap();
    }

    public void setAnswer3b(View view) {
        setAllAnswerValuesFalse(ANSWER3);
        updateAnswerMap(ANSWER3B);
        logAnswerMap();
    }

    public void setAnswer3c(View view) {
        setAllAnswerValuesFalse(ANSWER3);
        updateAnswerMap(ANSWER3C);
        logAnswerMap();
    }
    private boolean checkIfQuestionAnswered(String ans1, String ans2, String ans3){
        logAnswerMap();
        return userAnswers.containsKey(ans1) || userAnswers.containsKey(ans2)
                || userAnswers.containsKey(ans3);
    }
     class StrDoublePair {
        private  String str;
        private double dbl;
        public StrDoublePair(String str, double dbl){
            this.str = str;
            this.dbl = dbl;
        }
        public String getStr(){
            return str;
        }

        public double getDbl(){
            return dbl;
        }

        public void setStr(String s){
            this.str  = s;
        }

        public void setDbl(double d){
            this.dbl=d;
        }
    }
    private StrDoublePair checkAnswer(String curQuestion, String correctAns, String ans1,
                                      String ans2, String ans3, String msgText, double score){

        if (! checkIfQuestionAnswered(ans1, ans2, ans3)){
            msgText += "You did not answer " + curQuestion + ". ";
        } else if (userAnswers.containsKey(correctAns) && (userAnswers.get(correctAns)==true)){
            msgText += "You answered " + curQuestion + " correctly.";
            score += 33.3;
        } else {
            msgText += "You answered " + curQuestion + " incorrectly. ";
        }
        return new StrDoublePair(msgText, score);
    }

    public void submitAnsers(View view) {
        String msgText = "";
        double score = 0.0;

        StrDoublePair p = checkAnswer("question 1", ANSWER1A, ANSWER1A, ANSWER1B,
                ANSWER1C, msgText, score);

        p = checkAnswer("question 2", ANSWER2B, ANSWER2A, ANSWER2B,
                ANSWER2C, p.getStr(), p.getDbl());


        p = checkAnswer("question 3", ANSWER3C, ANSWER3A, ANSWER3B,
                ANSWER3C, p.getStr(), p.getDbl());


        if (p.getDbl() >= 90){
            p.setDbl(100.0);
        }

        Toast.makeText(this, p.getStr() + " Your score is: " + p.getDbl() + "%.",
                Toast.LENGTH_LONG).show();


    }
}
