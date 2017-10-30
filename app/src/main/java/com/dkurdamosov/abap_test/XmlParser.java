package com.dkurdamosov.abap_test;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by dkurdamosov on 20.10.2017.
 */

public class XmlParser {
    private XmlPullParser parser;
    private String text;
    private String corr;

    public XmlParser(String fileName) throws IOException, XmlPullParserException {
        parser = Xml.newPullParser();
        // получаем доступ к xml файлу
        InputStream inputStream = Main.getContext().getAssets().open(fileName);
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);
    }

    public ArrayList<Question> getQuestionsList() throws XmlPullParserException, IOException {
        ArrayList<Question> questionList = new ArrayList<Question>();
        // получаем первое событие в xml файле
        int eventType = parser.getEventType();
        Question question = null;

        // итерационно обходим xml файл пока не наткнемся на тип события 'конец документа'
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = "";

            // обрабатываем события начала документа
            if (eventType == XmlPullParser.START_DOCUMENT) {
                // парсим xml файл в список объектов Student
            } else if (eventType == XmlPullParser.START_TAG) {
                name = parser.getName();
                if (name.equals("Question")) {
                    question = new Question( );
                    text = parser.getAttributeValue(null, "text");
                    question.setQuestion_text(text);

                } else if (question != null) {
                    if (name.equals("answer1")) {
                        corr = parser.getAttributeValue(null, "correct");
                        text = parser.nextText();
                        question.setAnswer(1, text, corr);

                    } else if (name.equals("answer2")) {
                        corr = parser.getAttributeValue(null, "correct");
                        text = parser.nextText();
                        question.setAnswer(2, text, corr);

                    } else if (name.equals("answer3")) {
                        corr = parser.getAttributeValue(null, "correct");
                        text = parser.nextText();
                        question.setAnswer(3, text, corr);

                    } else if (name.equals("answer4")) {
                        corr = parser.getAttributeValue(null, "correct");
                        text = parser.nextText();
                        question.setAnswer(4, text, corr);
                    }
                }
                    // доходим до конца XML файла
                } else if (eventType == XmlPullParser.END_TAG) {
                    name = parser.getName();
                    if (name.equalsIgnoreCase("Question") && question != null) {
                        questionList.add(question);
                    }
                }


            // переходим к следующему событию внутри XML
            eventType = parser.next();
        }

        return questionList;
    }
}
