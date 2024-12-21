package vistula.jk.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import org.mariuszgromada.math.mxparser.*;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (getString(R.string.HintEnter).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }
    private void updatingDisplay(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPosition);
        String rightStr = oldStr.substring(cursorPosition);
        if(getString(R.string.HintEnter).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPosition+1);
        }
        else{
            display.setText(String.format("%s%s%s",leftStr, strToAdd, rightStr));
            display.setSelection(cursorPosition+1);
        }
    }
    public void Clear(View view){
        display.setText("");
    }
    public void btnOne (View view){
        updatingDisplay("1");
    }
    public void btnTwo (View view){
        updatingDisplay("2");
    }
    public void btnThree (View view){
        updatingDisplay("3");
    }
    public void btnFour (View view){
        updatingDisplay("4");
    }
    public void btnFive (View view){
        updatingDisplay("5");
    }
    public void btnSix (View view){
        updatingDisplay("6");
    }
    public void btnSeven (View view){
        updatingDisplay("7");
    }
    public void btnEight (View view){
        updatingDisplay("8");
    }
    public void btnNine (View view){
        updatingDisplay("9");
    }
    public void btnZero (View view){
        updatingDisplay("0");
    }
    public void btnAdd (View view){
        updatingDisplay("+");
    }
    public void btnSubtract (View view){
        updatingDisplay("-");
    }
    public void btnMultiply (View view){
        updatingDisplay("⨉");
    }
    public void btnDivide (View view){
        updatingDisplay("/");
    }
    public void btnPi (View view){
        updatingDisplay("π");
    }
    public void btnSquaredRoot(View view){
        updatingDisplay("√");
    }
    public void btnEqual (View view){
        String userExp = display.getText().toString();
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }
    public void btnBracket (View view){
        int cursorPos = display.getSelectionStart();
        int openBracket = 0;
        int closedBracket = 0;
        int textLen = display.getText().length();

        for(int i = 0; i < cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openBracket += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                closedBracket += 1;
            }
        }
        if (openBracket == closedBracket || display.getText().toString().substring(textLen-1, textLen).equals("")){
            updatingDisplay("(");
            display.setSelection(cursorPos+1);
        }
        else if (closedBracket < openBracket && !display.getText().toString().substring(textLen-1, textLen).equals("")){
            updatingDisplay(")");
        }
        display.setSelection(cursorPos+1);
    }
    public void btnComa (View view){
        updatingDisplay(".");
    }

}