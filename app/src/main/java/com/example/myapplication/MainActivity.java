package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name1, name2;
    TextView textView;
    EditText inputText1, inputText2;
    Button submitButton;

    @SuppressLint("MissingInflatedId")
    private int findUncommon(String a, String b)
    {
        int unC = 0;
        int[] arr = new int[26];

        for(int i=0; i<a.length(); i++)
        {
            if(a.charAt(i) != ' ')
            {
                char ch = Character.toLowerCase(a.charAt(i));

                arr[ch-'a']++;
            }
        }

        for(int i=0; i<b.length(); i++)
        {
            if(b.charAt(i) != ' ')
            {
                char ch = Character.toLowerCase(b.charAt(i));

                arr[ch-'a']++;
            }
        }

        for(int i : arr)
            if(i != 0)
                unC += Math.abs(i);

        return unC;
    }
    private int findRelation(int c)
    {
        int[] flames = new int[6];

        int count = 0, check = 0;
        int i = 0;

        while(check != 5)
        {
            if(flames[i] == -1) {
                i = (i+1)%6;
                continue;
            }

            count++;
            if(count == c) {
                flames[i] = -1;

                check++;
                count = 0;
            }

            i = (i+1)%6;
        }

        for(int j=0; j<6; j++)
            if(flames[j] == 0)
                return j;

        return -1;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = (TextView) findViewById(R.id.textView);
//        inputText = (EditText) findViewById(R.id.inputText);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                inputText1 = (EditText) findViewById(R.id.inputText1);
                inputText2 = (EditText) findViewById(R.id.inputText2);

                textView = (TextView) findViewById(R.id.textView);

                name1 = inputText1.getText().toString();
                name2 = inputText2.getText().toString();

                inputText1.setText("");
                inputText2.setText("");

                // Code for Calculating Flames

                int unCommon = findUncommon(name1, name2);
                int relation = findRelation(unCommon);

                String ans;
                switch(relation)
                {
                    case 0:
                        ans = "Friends";
                        break;

                    case 1:
                        ans = "Lovers";
                        break;

                    case 2:
                        ans = "Affection";
                        break;

                    case 3:
                        ans = "Marriage";
                        break;

                    case 4:
                        ans = "Enemies";
                        break;

                    case 5:
                        ans = "Siblings";
                        break;

                    default:
                        ans = "ERROR";
                        break;
                }

//                Toast.makeText(getApplicationContext(), "Both ends in " , Toast.LENGTH_LONG).show();
                textView.setText("Both ends in " + ans);
            }
        });
    }
}