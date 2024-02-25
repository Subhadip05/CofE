package com.example.cofe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int whippedQuantity=1,chocolateQuantity=1,vanillaQuantity=1;

    TextView whipped_quantityTextView,whipped_priceTextView;
    TextView chocolate_quantityTextView,chocolate_priceTextView;
    TextView vanilla_quantityTextView,vanilla_priceTextView;

    String priceMessage;
    Button order;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        order = findViewById(R.id.bookOrder);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oMsg = OrderClick();

                Intent i = new Intent(MainActivity.this,OrderViewActivity.class);
                i.putExtra("msg",oMsg);
                startActivity(i);
            }
        });


    }


    //whipped cream part start
    public void whipDecrement(View view) {
        if (whippedQuantity <= 0) {
            return;
        }
        whippedQuantity--;
        whipDisplayQuantity(whippedQuantity);
        whipDisplayPrice();
    }

    public void whipIncrement(View view) {
        if (whippedQuantity >= 30) {
            return;
        }
        whippedQuantity++;
        whipDisplayQuantity(whippedQuantity);
        whipDisplayPrice();
    }

    private void whipDisplayQuantity(int quantity) {
        whipped_quantityTextView = findViewById(R.id.whipped_Quantity_Textview);
        whipped_quantityTextView.setText(String.valueOf(quantity));
    }

    private void whipDisplayPrice() {
        whipped_priceTextView = findViewById(R.id.whipped_price);
        whipped_priceTextView.setText(String.valueOf(whippedQuantity*100));
    }
    //Chocolate part start
    public void chocoDecrement(View view) {
        if (chocolateQuantity <= 0) {
            return;
        }
        chocolateQuantity--;
        chocoDisplayQuantity(chocolateQuantity);
        chocoDisplayPrice();
    }

    public void chocoIncrement(View view) {
        if (chocolateQuantity >= 30) {
            return;
        }
        chocolateQuantity++;
        chocoDisplayQuantity(chocolateQuantity);
        chocoDisplayPrice();
    }

    private void chocoDisplayQuantity(int quantity) {
        chocolate_quantityTextView = findViewById(R.id.chocolate_Quantity_Textview);
        chocolate_quantityTextView.setText(String.valueOf(quantity));
    }
    private void chocoDisplayPrice() {
        chocolate_priceTextView= findViewById(R.id.chocolate_price);
        chocolate_priceTextView.setText(String.valueOf(chocolateQuantity*50));
    }

    //chocolate part end

    public void vDecrement(View view) {
        if (vanillaQuantity <= 0) {
            return;
        }
        vanillaQuantity--;
        vanillaDisplayQuantity(vanillaQuantity);
        vanillaDisplayPrice();
    }

    public void vIncrement(View view) {
        if (vanillaQuantity >= 30) {
            return;
        }
        vanillaQuantity++;
        vanillaDisplayQuantity(vanillaQuantity);
        vanillaDisplayPrice();
    }

    private void vanillaDisplayQuantity(int quantity) {
        vanilla_quantityTextView= findViewById(R.id.V_Quantity_Textview);
        vanilla_quantityTextView.setText(String.valueOf(quantity));
    }
    private void vanillaDisplayPrice() {
        vanilla_priceTextView= findViewById(R.id.vanilla_price);
        vanilla_priceTextView.setText(String.valueOf(vanillaQuantity*100));
    }

    @SuppressLint("QueryPermissionsNeeded")
    public String OrderClick() {
        EditText nameField = findViewById(R.id.Name_Field);
        Editable nameEditable = nameField.getEditableText();
        String name = nameEditable.toString().trim();

        CheckBox whippedCreamBox = findViewById(R.id.Whipped_Cream_Checkbox);
        CheckBox chocolateBox = findViewById(R.id.Chocolate_Checkbox);
        CheckBox vanillaBox = findViewById(R.id.Vanilla_Cream_CheckBox);

        Boolean hasWhippedCream = whippedCreamBox.isChecked();
        Boolean hasChocolate = chocolateBox.isChecked();
        Boolean hasVanilla = vanillaBox.isChecked();

        int totalQuantity=calculateTQuantity(hasWhippedCream,hasChocolate,hasVanilla);
        int price = calculatePrice(hasWhippedCream,hasChocolate,hasVanilla);

        return createOrderSummary(name,hasWhippedCream,hasChocolate,hasVanilla,totalQuantity,price);

    }

    @SuppressLint("StringFormatMatches")
    private String createOrderSummary(String name,Boolean hasWhippedCream,Boolean hasChocolate,Boolean hasVanilla,int totalQuantity,int price) {
        priceMessage = getString(R.string.Order_Summary_Name, name);
        if(hasWhippedCream && whippedQuantity>0){
            priceMessage += "\n" + getString(R.string.Order_Summary_whipped_cream, whippedQuantity);
        }
        if(hasChocolate && chocolateQuantity>0){
            priceMessage += "\n" + getString(R.string.Order_Summary_Chocolate,chocolateQuantity);
        }
        if(hasVanilla && vanillaQuantity>0){
            priceMessage += "\n" + getString(R.string.Order_Summary_Vanilla,vanillaQuantity);
        }
        priceMessage += "\n" + getString(R.string.Order_Summary_Quantity,totalQuantity);
        priceMessage += "\n" + getString(R.string.Order_Summary_Price, price);
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    private int calculatePrice(Boolean addWhippedCream, Boolean addChocolate,Boolean addVanilla) {
        int basePrice = 0;

        if (addWhippedCream) {
            basePrice += 100*whippedQuantity;
        }

        if (addChocolate) {
            basePrice += 50*chocolateQuantity;
        }

        if (addVanilla) {
            basePrice += 100*vanillaQuantity;
        }
        return basePrice;
    }

    private int calculateTQuantity(Boolean hasWhippedCream, Boolean hasChocolate, Boolean hasVanilla) {
        int totalQ=0;
        if(hasWhippedCream && whippedQuantity>0){
            totalQ+=whippedQuantity;
        }
        if(hasChocolate && chocolateQuantity>0){
            totalQ+=chocolateQuantity;
        }
        if(hasVanilla && vanillaQuantity>0){
            totalQ+=vanillaQuantity;
        }
        return totalQ;
    }
}
