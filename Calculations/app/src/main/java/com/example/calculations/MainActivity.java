package com.example.calculations;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnadd,btnsub,btnmul,btndiv;
    EditText editt1,editt2;
    TextView txtres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd=(Button) findViewById(R.id.button1);
        btnadd.setOnClickListener(this);
        btnsub=(Button) findViewById(R.id.btnsub);
        btnsub.setOnClickListener(this);
        btnmul=(Button) findViewById(R.id.btnmul);
        btnmul.setOnClickListener(this);
        btndiv=(Button) findViewById(R.id.btndiv);
        btndiv.setOnClickListener(this);
        editt1=(EditText) findViewById(R.id.edittxt1);
        editt2=(EditText) findViewById(R.id.edittxt2);
        txtres=(TextView) findViewById(R.id.textview);
    }

    @Override
    public void onClick(View v) {
        int num1,num2,res;
        num1=Integer.parseInt(editt2.getText().toString());
        num2=Integer.parseInt(editt1.getText().toString());
        if(v==btnadd)
            res=num1+num2;
        else if(v==btnsub)
            res=num1-num2;
        else if(v==btnmul)
            res=num1*num2;
        else
            res=num1/num2;
        txtres.setText("Result is:"+Integer.toString(res));
    }

}