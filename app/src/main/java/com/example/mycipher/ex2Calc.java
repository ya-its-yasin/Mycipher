package com.example.mycipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ex2Calc extends AppCompatActivity {

    public static String selectedKey;
    public static char   sortedKey[];
    public static int    sortedKeyPos[];

    TextView text,note;
    TextView n1,n2,n3,n4,desc;

    EditText plainText,Tinvkey,Dinvkey;
    EditText key1;
    Button encrypt;

    EditText cipherText;
    EditText key2;
    Button decrypt;

    EditText encSol;
    EditText decSol;

    String sencSol="",sdecSol="",str,splainText,scipherText;
    String skey1;
    String skey2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2_calc);

        text = (TextView)findViewById(R.id.textView2);
        note = (TextView)findViewById(R.id.textView11);

        n1 = (TextView)findViewById(R.id.n1);
        n2 = (TextView)findViewById(R.id.n2);
        n3 = (TextView)findViewById(R.id.n3);
        n4 = (TextView)findViewById(R.id.n4);

        desc=(TextView)findViewById(R.id.textView8);

        plainText=(EditText)findViewById(R.id.editText7);
        key1=(EditText) findViewById(R.id.editText4);
        encrypt=(Button) findViewById(R.id.button2);

        cipherText=(EditText) findViewById(R.id.editText6);
        key2=(EditText) findViewById(R.id.editText2);
        decrypt=(Button) findViewById(R.id.button3);

        encSol=(EditText) findViewById(R.id.editText5);
        decSol=(EditText) findViewById(R.id.editText8);

        Intent intent = getIntent();
        str = intent.getStringExtra("message");
        text.setText(str);

        note.setText("Note :");
        if(str.equals("Rail fence cipher"))
        {
            n1.setText("1.Enter plain text as ur wish.");
            n2.setText("2.Enter key as number.");
            n3.setText("3.Violation of the above rules will lead to malfunction.");
            desc.setText("The rail fence cipher (also called a zigzag cipher) is a form of transposition cipher. It derives its name from the way in which it is encoded.\n In the rail fence cipher, the plain text is written downwards and diagonally on successive \"rails\" of an imaginary fence, then moving up when the bottom rail is reached. When the top rail is reached, the message is written downwards again until the whole plaintext is written out. The message is then read off in rows.");
        }

        if(str.equals("Row and column transposition cipher"))
        {
            n1.setText("1.The plain text can be of text annd numbers");
            n2.setText("2.The key must be of number sequence");
            n3.setText("3.Violation of the above rules will lead to malfunction.");
            desc.setText("This is a java program to implement transposition technique. In cryptography, a transposition cipher is a method of encryption by which the positions held by units of plaintext (which are commonly characters or groups of characters) are shifted according to a regular system, so that the ciphertext constitutes a permutation of the plaintext. That is, the order of the units is changed (the plaintext is reordered). Mathematically a bijective function is used on the characters’ positions to encrypt and an inverse function to decrypt.");
        }


        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str.equals("Rail fence cipher")){


                    splainText = plainText.getText().toString();
                    skey1 = key1.getText().toString();

                    sencSol =getEncryptedData(splainText,skey1);
//
                    encSol.setText(sencSol);

                }
                if(str.equals("Row and column transposition cipher"))
                {
                    splainText = plainText.getText().toString();
                    skey1 = key1.getText().toString();
                    selectedKey = skey1;
                    sortedKeyPos = new int[selectedKey.length()];
                    sortedKey = selectedKey.toCharArray();

                    sencSol=doEncryption(splainText);

//
                    encSol.setText(sencSol);
                }

            }
        });


        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str.equals("Rail fence cipher")){

                    scipherText = cipherText.getText().toString();
                    skey2 = key2.getText().toString();
                    sdecSol =getDecryptedData(scipherText,skey2);

//
                    decSol.setText(sdecSol);

                }
                if(str.equals("Row and column transposition cipher"))
                {
                    scipherText = cipherText.getText().toString();
                    skey2 = key2.getText().toString();

                    selectedKey = skey2;
                    sortedKeyPos = new int[selectedKey.length()];
                    sortedKey = selectedKey.toCharArray();

                    sdecSol=doDecryption(scipherText);
                    decSol.setText(sdecSol);
                }

            }
        });
    }

    //************************************************************************************************************************
        String getDecryptedData(@org.jetbrains.annotations.NotNull String data, String numm) {
            char[] decrypted = new char[data.length()];
            int n = 0;
            int numRails=Integer.parseInt(numm);
            for(int k = 0 ; k < numRails; k ++) {
                int index = k;
                boolean down = true;
                while(index < data.length() ) {
                    //System.out.println(k + " " + index+ " "+ n );
                    decrypted[index] = data.charAt(n++);

                    if(k == 0 || k == numRails - 1) {
                        index = index + 2 * (numRails - 1);
                    }
                    else if(down) {
                        index = index +  2 * (numRails - k - 1);
                        down = !down;
                    }
                    else {
                        index = index + 2 * k;
                        down = !down;
                    }
                }
            }
            return new String(decrypted);
        }


        String getEncryptedData(String data, String numm) {
            char[] encrypted = new char[data.length()];
            int n = 0;
            int numRails=Integer.parseInt(numm);


            for(int k = 0 ; k < numRails; k ++) {
                int index = k;
                boolean down = true;
                while(index < data.length() ) {
                    //System.out.println(k + " " + index+ " "+ n );
                    encrypted[n++] = data.charAt(index);

                    if(k == 0 || k == numRails - 1) {
                        index = index + 2 * (numRails - 1);
                    }
                    else if(down) {
                        index = index +  2 * (numRails - k - 1);
                        down = !down;
                    }
                    else {
                        index = index + 2 * k;
                        down = !down;
                    }
                }
            }
            return new String(encrypted);
        }

        //**********************************************************************************************

    public static void doProcessOnKey()
    {
        // Find position of each character in selected key and arrange it on
        // alphabetical order
        int min, i, j;
        char orginalKey[] = selectedKey.toCharArray();
        char temp;
        // First Sort the array of selected key
        for (i = 0; i < selectedKey.length(); i++)
        {
            min = i;
            for (j = i; j < selectedKey.length(); j++)
            {
                if (sortedKey[min] > sortedKey[j])
                {
                    min = j;
                }
            }
            if (min != i)
            {
                temp = sortedKey[i];
                sortedKey[i] = sortedKey[min];
                sortedKey[min] = temp;
            }
        }
        // Fill the position of array according to alphabetical order
        for (i = 0; i < selectedKey.length(); i++)
        {
            for (j = 0; j < selectedKey.length(); j++)
            {
                if (orginalKey[i] == sortedKey[j])
                    sortedKeyPos[i] = j;
            }
        }
    }

    // to encrypt the targeted string
    public static String doEncryption(String plainText)
    {
        int min, i, j;
        char orginalKey[] = selectedKey.toCharArray();
        char temp;
        doProcessOnKey();
        // Generate encrypted message by doing encryption using Transpotion
        // Cipher
        int row = plainText.length() / selectedKey.length();
        int extrabit = plainText.length() % selectedKey.length();
        int exrow = (extrabit == 0) ? 0 : 1;
        int rowtemp = -1, coltemp = -1;
        int totallen = (row + exrow) * selectedKey.length();
        char pmat[][] = new char[(row + exrow)][(selectedKey.length())];
        char encry[] = new char[totallen];
        int tempcnt = -1;
        row = 0;
        for (i = 0; i < totallen; i++)
        {
            coltemp++;
            if (i < plainText.length())
            {
                if (coltemp == (selectedKey.length()))
                {
                    row++;
                    coltemp = 0;
                }
                pmat[row][coltemp] = plainText.charAt(i);
            }
            else
            { // do the padding ...
                pmat[row][coltemp] = '*';
            }
        }
        int len = -1, k;
        for (i = 0; i < selectedKey.length(); i++)
        {
            for (k = 0; k < selectedKey.length(); k++)
            {
                if (i == sortedKeyPos[k])
                {
                    break;
                }
            }
            for (j = 0; j <= row; j++)
            {
                len++;
                encry[len] = pmat[j][k];
            }
        }
        String p1 = new String(encry);
        return (new String(p1));
    }

    // to decrypt the targeted string
    public static String doDecryption(String s)
    {
        int min, i, j, k;
        char key[] = selectedKey.toCharArray();
        char encry[] = s.toCharArray();
        char temp;
        doProcessOnKey();
        // Now generating plain message
        int row = s.length() / selectedKey.length();
        char pmat[][] = new char[row][(selectedKey.length())];
        int tempcnt = -1;
        for (i = 0; i < selectedKey.length(); i++)
        {
            for (k = 0; k < selectedKey.length(); k++)
            {
                if (i == sortedKeyPos[k])
                {
                    break;
                }
            }
            for (j = 0; j < row; j++)
            {
                tempcnt++;
                pmat[j][k] = encry[tempcnt];
            }
        }
        // store matrix character in to a single string
        char p1[] = new char[row * selectedKey.length()];
        k = 0;
        for (i = 0; i < row; i++)
        {
            for (j = 0; j < selectedKey.length(); j++)
            {
                if (pmat[i][j] != '*')
                {
                    p1[k++] = pmat[i][j];
                }
            }
        }
        p1[k++] = '\0';
        return (new String(p1));
    }
//******************************************************************************************************************
}
