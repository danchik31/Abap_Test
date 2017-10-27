package com.dkurdamosov.abap_test;

/**
 * Created by dkurdamosov on 20.10.2017.
 */

public class Question {

    private String question_text;
    private String answers[][] ;

    //конструктор
    public void Question(){
        //инизиализируем массив с вопросами
        answers = new String[4][2];
    }

    //Возвращаем текст вопроса
    public String getQuestion_text() {
        return question_text;
    }

    //Устанавливаем текст вопроса
    public void setQuestion_text(String question_text) {

        this.question_text = question_text;
    }

    //заполняем массив с ответами
    public void setAnswer(int num, String text, String correct){

        switch (num){
            case 1:
                answers[0][0] = text;
                answers[0][1] = correct;
                break;
            case 2:
                answers[1][0] = text;
                answers[1][1] = correct;
                break;
            case 3:
                answers[2][0] = text;
                answers[2][1] = correct;
                break;
            case 4:
                answers[3][0] = text;
                answers[3][1] = correct;
                break;
        }

    }

    //возвращаем текст ответа
    public String getAnswer( int num){
        String val = new String();
        switch (num){
            case 1:
                val = answers[0][0];
                break;
            case 2:
                val = answers[1][0];
                break;
            case 3:
                val = answers[2][0];
                break;
            case 4:
                val = answers[3][0];
                break;
        }

        return val;
    }

    //проверяем правильный ли ответ
    public boolean isCorrectAnswer(int id) {

        boolean result=false;

        switch (id) {
            case 1:
                if (answers[0][1]!=""){result=true;}
                break;
            case 2:
                if (answers[1][1]!=""){result=true;}
                break;
            case 3:
                if (answers[2][1]!=""){result=true;}
                break;
            case 4:
                if (answers[3][1]!=""){result=true;}
                break;

        }
        return result;
    }

    //возвращаем количество правильных ответов
    public int getCorrectAnswersCount(){
        int count=0;
        for (int i=0;i<4;i++){
            if (answers[i][1].equals("x")){
                count+=1;
            }
        }
        return count;
    }
}