package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class hospital extends AppCompatActivity {

    EditText edit;
    TextView text;

    XmlPullParser xpp;
    String key="t9%2Blhb301c%2Fi0M6FnWjEbrIx9bgAkvhihFUzuyt4slEIEvGtKYy0UW6SAesXgiITQy2nfQxdz%2FypMcIxkfpH%2Fw%3D%3D";

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        edit= (EditText)findViewById(R.id.edit);
        text= (TextView)findViewById(R.id.result);
    }

    public void mOnClick(View v){
        switch( v.getId() ){
            case R.id.button:


                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        data= getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                text.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }//mOnClick method..



    String getXmlData(){

        StringBuffer buffer=new StringBuffer();

        String str= edit.getText().toString();//EditText에 작성된 Text얻어오기
        String location = URLEncoder.encode(str);//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding
        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl="http://apis.data.go.kr/6300000/hospitalDataService/hospitalDataList?"//요청 URL
                +"addr1="+location
                +"&pageNo=1&numOfRows=100&ServiceKey=" + key;

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("items")) ;// 첫번째 검색결과
                        else if(tag.equals("addr1")){
                            buffer.append("주소 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("etc")){
                            buffer.append("응급실 유무 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("hospitalSeq")){
                            buffer.append("번호 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("nm")){
                            buffer.append("이름 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("phone")){
                            buffer.append("전화번호 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("section")){
                            buffer.append("구분 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("  ,  "); //줄바꿈 문자 추가
                            buffer.append("\n");
                            buffer.append("\n");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }

        buffer.append("파싱 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....






}