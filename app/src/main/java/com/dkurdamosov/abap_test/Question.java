package com.dkurdamosov.abap_test;

/**
 * Created by dkurdamosov on 20.10.2017.
 */

public class Question {

    private String question_text;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correct_answer;

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public boolean isCorrectAnswer(int id) {
        boolean result=false;

        switch (id) {
            case R.id.answer1:
                if (correct_answer.equals("answer1")){result=true;}
                break;
            case R.id.answer2:
                if (correct_answer.equals("answer2")){result=true;}
                break;
            case R.id.answer3:
                if (correct_answer.equals("answer3")){result=true;}
                break;
            case R.id.answer4:
                if (correct_answer.equals("answer4")){result=true;}
                break;

        }
        return result;
    }
}