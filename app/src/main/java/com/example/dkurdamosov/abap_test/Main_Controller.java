package com.example.dkurdamosov.abap_test;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dkurdamosov on 19.10.2017.
 */

 class Main_Controller implements  View.OnClickListener, Animation.AnimationListener {
    Animation animAlpha;
    TextView questionText;
    TextView question_cnt;
    TextView answer_1;
    TextView answer_2;
    TextView answer_3;
    TextView answer_4;
    TextView answer_active;
    private  XmlParser xmlParser;
    private ArrayList<Question> questionList;
    final private  String fileName = "questions";
    private Context context;
    private int questionNumber;
    private String correctAnswer;
    private Question question;


    public Main_Controller(Main main){
        animAlpha = AnimationUtils.loadAnimation( main, R.anim.alpha);
        questionText = (TextView) main.findViewById(R.id.question);
        question_cnt = (TextView) main.findViewById(R.id.question_count);
        answer_1 = (TextView) main.findViewById(R.id.answer1);
        answer_2 = (TextView) main.findViewById(R.id.answer2);
        answer_3 = (TextView) main.findViewById(R.id.answer3);
        answer_4 = (TextView) main.findViewById(R.id.answer4);

//устанавливаем Listener
        answer_1.setOnClickListener(this);
        answer_2.setOnClickListener(this);
        answer_3.setOnClickListener(this);
        answer_4.setOnClickListener(this);
        animAlpha.setAnimationListener(this);
//считываем все вопросы из xml
        try {
            xmlParser = new XmlParser( fileName);
            questionList = xmlParser.getQuestionsList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        newQuestion();
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        answer_active.setBackgroundResource(R.drawable.back);
        newQuestion( );
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View view) {
        answer_active=(TextView) view;

        if (question.isCorrectAnswer(view.getId())){
            answer_active.setBackgroundResource(R.drawable.back_true);
        }
            else{
            answer_active.setBackgroundResource(R.drawable.back_false);
        }

        answer_active.startAnimation(animAlpha);
        answer_1.setClickable(false);
        answer_2.setClickable(false);
        answer_3.setClickable(false);
        answer_4.setClickable(false);
    }
    private void newQuestion() {
        Random random = new Random();
        int size = questionList.size();
        int index;
        questionNumber+=1;
        String val="Вопрос № "+questionNumber;
        if (size != 0) {
            index = random.nextInt(size);
            question = questionList.get(index);
            correctAnswer=question.getCorrect_answer();
            question_cnt.setText(val);
            questionText.setText(question.getQuestion_text());
            answer_1.setText(question.getAnswer1());
            answer_2.setText(question.getAnswer2());
            answer_3.setText(question.getAnswer3());
            answer_4.setText(question.getAnswer4());
            answer_1.setClickable(true);
            answer_2.setClickable(true);
            answer_3.setClickable(true);
            answer_4.setClickable(true);

            questionList.remove(index);
        }
        else {
//Выдаем результаты
            AlertDialog.Builder builder = new AlertDialog.Builder(Main.getContext());
            builder.setTitle("Ваши результаты")
                    .setMessage("Все очень хуево")

                    .setCancelable(false)
                    .setNegativeButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
