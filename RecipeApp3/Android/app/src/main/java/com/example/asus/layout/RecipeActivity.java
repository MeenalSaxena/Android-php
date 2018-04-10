package com.example.asus.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity  {
    ImageView[] iv;
    ImageView image;
    String ivID[], id1;
    int id;
    int resID,i;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
//        iv = new ImageView[4];
        tv=(TextView)findViewById(R.id.textView);
        image=(ImageView)findViewById(R.id.i1);

        ivID= new String[10];

        for(i=1; i<=4; i++)
            {

                ivID[i] = "i" +i;
                resID = getResources().getIdentifier(ivID[i], "id", getPackageName());
                image = (ImageView) findViewById(resID);
                id1=String.valueOf(ivID[i]);


//                id1=Integer.valueOf(ivID[i]);
                image.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

//                    image.setId(id1);
//                    Intent in=new Intent(RecipeActivity.this, Recipe.class);
//                in.putExtra("ID", String.valueOf(image));
//                startActivity(in);

                    Toast.makeText(getApplicationContext(), resID, Toast.LENGTH_SHORT).show();


                }
             });
//

//        System.out.println(ivID);
//        System.out.println(image);

        }

    }
}
