package com.example.asus.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity  {
    ImageView[] iv;
    ImageView image;
    String ivID[];

    int id;
    int resID;
    int i,id1;
    String a;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
//        iv = new ImageView[4];
        tv=(TextView)findViewById(R.id.textView);

        ivID= new String[10];

        for(i=1; i<=4; i++)
            {

                ivID[i] = "i" +i;
               // System.out.println(ivID[i]);
                final int ex = i;
                resID = getResources().getIdentifier(ivID[i], "id", getPackageName());
                image = (ImageView) findViewById(resID);
           //    System.out.println(resID);
            //    System.out.println(image);

                //  System.out.println(ivID[i]);
//                id1= String.valueOf(i);

           //     System.out.println(ivID[i]);
//                id1=Integer.valueOf(ivID[i]);

                    image.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
//                            a+= ivID[i];


                    Intent in=new Intent(RecipeActivity.this, Recipe.class);
                    in.putExtra("ID", String.valueOf(ivID[ex]));
                    startActivity(in);



//                            Toast.makeText(getApplicationContext(), String.valueOf(ivID[ex]), Toast.LENGTH_SHORT).show();


                        }
                    });

//

//        System.out.println(ivID);
//        System.out.println(image);

        }

    }
}
