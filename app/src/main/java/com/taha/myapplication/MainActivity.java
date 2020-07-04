package com.taha.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffee =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        String summeryMessage = creatOrderSummary();

        Intent email= new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ });
        email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.coffeeorder));
        email.putExtra(Intent.EXTRA_TEXT, summeryMessage);
        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, getString(R.string.email_clint)));


    }
    private void display(int number) {
        if (number>=1&&number<100){
            TextView quantityTextView =(TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);}
        else if (number<1){
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.toastmesseg1);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        else if (number>100){
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.toastmesseg2);
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }



//    private void displayMessage(String message){
//        TextView orderSummeryTextView =(TextView) findViewById(R.id.order_summary_text_view);
//        orderSummeryTextView.setText(message);
//
//    }
    /////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkboxstatus(){
        CheckBox haswippdcream =(CheckBox) findViewById(R.id.checkBox);
       if (haswippdcream.isChecked()){
        return true;}
       else return false;
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkboxstatus2(){
        CheckBox hasChocolate =(CheckBox) findViewById(R.id.checkBox2_chocolate);
        if (hasChocolate.isChecked()){ return true;}
        else {return false;}
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    private String getName(){
        EditText simpleEditText = (EditText) findViewById(R.id.Name_editText);
        String editTextValue = simpleEditText.getText().toString();
        String name =editTextValue;
        return name;
   }
    ////////////////////////////////////////////////////////////////////////////////////////
    public void increment(View view) {

        numberOfCoffee++;
        display(numberOfCoffee);

    }
   ////////////////////////////////////////////////////////////////////////////////////
   public void decrement(View view) {

       numberOfCoffee--;
       display(numberOfCoffee);

   }
   /////////////////////////////////////////////////////////////////////////////////////


   public int calculatePrice(int numberOfCoffee,int pricePercup) {
       boolean wippedCream= checkboxstatus();
       boolean hasChocolate= checkboxstatus2();
       if (wippedCream&&hasChocolate){pricePercup= pricePercup+3;}
       else if (hasChocolate){pricePercup= pricePercup+2;}
       else if (wippedCream){pricePercup= pricePercup+1;}
       else {pricePercup=5;}


        int price =numberOfCoffee*pricePercup;
        return price;
    }
    public String creatOrderSummary(){

        String name=getName();
        boolean wippedCream= checkboxstatus();
        boolean hasChocolate= checkboxstatus2();
        int price = calculatePrice(numberOfCoffee,5);
        String priceMessage =getString(R.string.namejava)+ name+"\n"+getString(R.string.wippdcreamjava)+wippedCream+"\n"+getString(R.string.chocolatejava)+hasChocolate+"\n"+getString(R.string.quantityjava)+numberOfCoffee+"\n"+getString(R.string.totaljava)+price ;
        priceMessage= priceMessage +"\n"+getString(R.string.thank_you);
        //displayMessage(priceMessage);
        return  priceMessage;

    }
}
