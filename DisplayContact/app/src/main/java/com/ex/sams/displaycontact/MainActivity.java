package com.ex.sams.displaycontact;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;
//import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener, Serializable {

    List<String> name1 = new ArrayList<String>();
    List<String> phno1 = new ArrayList<String>();
    java.util.HashMap<String, String> HashMap=new HashMap<String, String>();
    MyAdapter ma ;
    Button select,selectall;
    JSONObject jsonObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView lv= (ListView) findViewById(R.id.lv);
        ma = new MyAdapter();
        lv.setAdapter(ma);
        lv.setOnItemClickListener(this);
        lv.setItemsCanFocus(false);
        lv.setTextFilterEnabled(true);
        // adding
        select = (Button) findViewById(R.id.button1);
        selectall = (Button) findViewById(R.id.button2);
        getAllContacts(this.getContentResolver());
        select.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                StringBuilder checkedcontacts= new StringBuilder();
                System.out.println(".............."+ma.mCheckStates.size());

                for(int i = 0; i < name1.size(); i++)

                {
                    if(ma.mCheckStates.get(i)==true)
                    {
                        checkedcontacts.append(name1.get(i).toString());
                        checkedcontacts.append(phno1.get(i).toString());
                        checkedcontacts.append("\n");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println(checkedcontacts);

                    }
                    else
                    {
//                        System.out.println("Not Checked......"+name1.get(i).toString());
                    }

                }

                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference ref= database.getReference("Contacts");
                ref.push().setValue(checkedcontacts.toString());
//                ref.setValue(checkedcontacts.toString());

//                Toast.makeText(Display.this, checkedcontacts,1000).show();
            }

        });



        selectall.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                StringBuilder checkedcontacts= new StringBuilder();
                System.out.println(".............."+ma.mCheckStates.size());
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference ref= database.getReference();
                ref.child("contacts").setValue(HashMap);
//                for(int i = 0; i < name1.size(); i++)
//
//                {
//                    if(ma.mCheckStates.get(i)==false)
//                    {
////                        checkedcontacts.append(name1.get(i).toString());
////                        checkedcontacts.append(phno1.get(i).toString());
////                        checkedcontacts.append("\n");
////                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
////                        System.out.println(checkedcontacts);
//
//
//                    }
//                    else
//                    {
////                        System.out.println("Not Checked......"+name1.get(i).toString());
//                    }
//
//                }


//                Toast.makeText(Display.this, checkedcontacts,1000).show();
            }

        });
    }
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        ma.toggle(arg2);
    }
    class MyAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener
    {  private SparseBooleanArray mCheckStates;
        LayoutInflater mInflater;
        TextView tv1,tv;
        CheckBox cb;
        MyAdapter()
        {
            mCheckStates = new SparseBooleanArray(name1.size());
            mInflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return name1.size();
        }
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View vi=convertView;
            if(convertView==null)
                vi = mInflater.inflate(R.layout.row, null);
            TextView tv= (TextView) vi.findViewById(R.id.contact_name);
            tv1= (TextView) vi.findViewById(R.id.phone_number);
            cb = (CheckBox) vi.findViewById(R.id.checkBox_id);
//
//      System.out.println(tv);
//            System.out.println(tv1);
            HashMap.put(name1.get(position), phno1.get(position));


//            FirebaseDatabase database=FirebaseDatabase.getInstance();
//            DatabaseReference ref= database.getReference();
//
////System.out.println(jsonObject);
            tv.setText(" "+ name1.get(position)+"\n");
            tv1.setText(" "+ phno1.get(position)+"\n");
//            ref.setValue(HashMap);
//            for(Map.Entry map  :  HashMap.entrySet() )
//            {
////Step 4: Using getKey and getValue methods to retrieve key and corresponding value
////                System.out.println(map.getKey()+" "+map.getValue());
//                tv.setText(" "+ map.getKey()+"\n");
//            tv1.setText(" "+ map.getValue()+"\n");
//            }
            cb.setTag(position);
            cb.setChecked(mCheckStates.get(position, false));
            cb.setOnCheckedChangeListener(this);
//            System.out.println(jsonObject);
            return vi;
        }

        public boolean isChecked(int position) {
            return mCheckStates.get(position, false);
        }

        public void setChecked(int position, boolean isChecked) {
            mCheckStates.put(position, isChecked);
//            System.out.println("hello...........");
            notifyDataSetChanged();
        }

        public void toggle(int position) {
            setChecked(position, !isChecked(position));
        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub

            mCheckStates.put((Integer) buttonView.getTag(), isChecked);
        }

    }

    public  void getAllContacts(ContentResolver cr) {

        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            System.out.println(".................."+phoneNumber);
//            System.out.println(".................."+name);
            name1.add(name);
            phno1.add(phoneNumber);

//            System.out.println("FGFHKHKJKJKJHJH");

        }

        phones.close();
    }

}