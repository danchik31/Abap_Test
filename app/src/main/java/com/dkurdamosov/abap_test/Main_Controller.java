package com.dkurdamosov.abap_test;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by dkurdamosov on 19.10.2017.
 */

class Main_Controller implements View.OnClickListener {

    private TextView questionText;
    private TextView question_cnt;
    private TextView answer_1;
    private TextView answer_2;
    private TextView answer_3;
    private TextView answer_4;
    private TextView answer_active;
    private XmlParser xmlParser;
    private ArrayList<Question> questionList;
    final private String fileName = "questions.xml";
    private int questionNumber;
    private int countOfCorrectAnswers;

    private Question question;

    public static int getCountTrue() {
        return countTrue;
    }

    public static int getCountFalse() {
        return countFalse;
    }

    private static int countTrue;
    private static int countFalse;

    public static int getPercent() {
        return percent;
    }

    private static int percent;
    private int allQuestionCount;

    public Main_Controller(Main main) {

        questionText = (TextView) main.findViewById(R.id.question);
        question_cnt = (TextView) main.findViewById(R.id.question_count);

        answer_1 = (TextView) main.findViewById(R.id.answer1);
        answer_2 = (TextView) main.findViewById(R.id.answer2);
        answer_3 = (TextView) main.findViewById(R.id.answer3);
        answer_4 = (TextView) main.findViewById(R.id.answer4);

        countTrue = 0;
        countFalse = 0;
//устанавливаем Listener
        answer_1.setOnClickListener(this);
        answer_2.setOnClickListener(this);
        answer_3.setOnClickListener(this);
        answer_4.setOnClickListener(this);

//считываем все вопросы из xml
        try {
            xmlParser = new XmlParser(fileName);
            questionList = xmlParser.getQuestionsList();
            allQuestionCount = questionList.size();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        newQuestion();
    }

    @Override
    public void onClick(View view) {

        int id = 0;
        countOfCorrectAnswers -= 1;

        answer_active = (TextView) view;

        switch (view.getId()) {
            case R.id.answer1:
                id = 1;
                break;
            case R.id.answer2:
                id = 2;
                break;
            case R.id.answer3:
                id = 3;
                break;
            case R.id.answer4:
                id = 4;
                break;
        }

        if (question.isCorrectAnswer(id)) {
            answer_active.setBackgroundResource(R.drawable.back_true);
            countTrue += 1;
        } else {
            answer_active.setBackgroundResource(R.drawable.back_false);
            countFalse += 1;
        }


        //если исчерпали количество правильных ответов, то запускаем новый вопрос
        if (countOfCorrectAnswers == 0) {
            setNoClicable();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    newQuestion();
                }
            }, 1000);

        }

    }

    private void newQuestion() {
        Random random = new Random();
        int size = questionList.size();
        int index;

        if (size != 0) {
            questionNumber += 1;
            String val = questionNumber + "/" + allQuestionCount;
            //index = random.nextInt(size);
            index = 1;
            question = questionList.get(index);

            //получаем кол-во правильных ответов
            countOfCorrectAnswers = question.getCorrectAnswersCount();
            //устанавливаем текстовки
            question_cnt.setText(val);
            questionText.setText(question.getQuestion_text());
            answer_1.setText(question.getAnswer(1));
            answer_2.setText(question.getAnswer(2));
            answer_3.setText(question.getAnswer(3));
            answer_4.setText(question.getAnswer(4));
            answer_1.setBackgroundResource(R.drawable.back);
            answer_2.setBackgroundResource(R.drawable.back);
            answer_3.setBackgroundResource(R.drawable.back);
            answer_4.setBackgroundResource(R.drawable.back);
            //удаляем вопрос
            questionList.remove(index);
        } else {
//Выдаем результаты
            //// TODO: 24.10.2017  start activity results
            percent = (countTrue * 100) / allQuestionCount;
            Intent intent = new Intent(Main.getContext(), Result.class);

            Main.getContext().startActivity(intent);
        }
    }
    private void setClicable(boolean val){
        answer_1.setClickable(val);
        answer_2.setClickable(val);
        answer_3.setClickable(val);
        answer_4.setClickable(val);
    }
}

