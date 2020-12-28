package com.example.mycipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main2Activity extends AppCompatActivity {

    TextView text,hillkey1,hillkey2,hill1,hill2,note;
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

    //pallyfair

    private String KeyWord        = new String();
    private String Key            = new String();
    private char   matrix_arr[][] = new char[5][5];




    // hill
    int keymatrix[][];
    int linematrix[];
    int resultmatrix[];
    String invkey = "";

    int keymatrix1[][];
    int linematrix1[];
    int resultmatrix1[];
    String invkey1 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = (TextView)findViewById(R.id.textView2);
        note = (TextView)findViewById(R.id.textView11);

        n1 = (TextView)findViewById(R.id.n1);
        n2 = (TextView)findViewById(R.id.n2);
        n3 = (TextView)findViewById(R.id.n3);
        n4 = (TextView)findViewById(R.id.n4);

        desc=(TextView)findViewById(R.id.textView8);

        hill1 = (TextView)findViewById(R.id.textView9);
        hillkey1 = (TextView)findViewById(R.id.textView3);
        hill2 = (TextView)findViewById(R.id.textView7);
        hillkey2 = (TextView)findViewById(R.id.textView10);





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

    //    splainText = plainText.getText().toString();
      //  String dumy1=key1.getText().toString();
    //    skey1 =Integer.parseInt(dumy1);
   //     scipherText = cipherText.getText().toString();
        //skey2 =Integer.parseInt(key2.getText().toString());
    //    String dumy2=key2.getText().toString();
      //  skey2 =Integer.parseInt(dumy2);


        note.setText("Note :");
        if(str.equals("Ceaser cipher"))
        {
            n1.setText("1.Enter plain text as ur wish.");
            n2.setText("2.Enter key as number.");
            n3.setText("3.Violation of the above rules will lead to malfunction.");
            desc.setText("                      In cryptography, a Caesar cipher, also known as Caesar's cipher, the shift cipher, Caesar's code or Caesar shift, is one of the simplest and most widely known encryption techniques. It is a type of substitution cipher in which each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet. For example, with a left shift of 3, D would be replaced by A, E would become B, and so on. The method is named after Julius Caesar, who used it in his private correspondence.\n" +
                    "\n" +
                    "                         The encryption step performed by a Caesar cipher is often incorporated as part of more complex schemes, such as the Vigenère cipher, and still has modern application in the ROT13 system. As with all single-alphabet substitution ciphers, the Caesar cipher is easily broken and in modern practice offers essentially no communications security.");
        }

        if(str.equals("Playfair cipher"))
        {
            n1.setText("1.The plain text must be even number of digits.");
            n2.setText("2.The key should contain only alphabets");
            n3.setText("3.Violation of the above rules will lead to malfunction.");
            desc.setText("                      The Playfair cipher or Playfair square or Wheatstone-Playfair cipher is a manual symmetric encryption technique and was the first literal digram substitution cipher. The scheme was invented in 1854 by Charles Wheatstone, but bears the name of Lord Playfair for promoting its use.\n" +
                    "\n" +
                    "                       The technique encrypts pairs of letters (bigrams or digrams), instead of single letters as in the simple substitution cipher and rather more complex Vigenère cipher systems then in use. The Playfair is thus significantly harder to break since the frequency analysis used for simple substitution ciphers does not work with it. The frequency analysis of bigrams is possible, but considerably more difficult. With 600[1] possible bigrams rather than the 26 possible monograms (single symbols, usually letters in this context), a considerably larger cipher text is required in order to be useful.");
        }

        if(str.equals("Hill cipher"))
        {
            n1.setText("1.The plain text must be even number of digits.");
            n2.setText("2.The key alphabets length should form a sqare matrix.  ex:mble");
            n3.setText("3.Use the inverse key to decrypt the text.");
            n4.setText("4.Violation of the above rules will lead to malfunction.");
            desc.setText("                      Hill cipher is a polygraphic substitution cipher based on linear algebra.Each letter is represented by a number modulo 26. Often the simple scheme A = 0, B = 1, …, Z = 25 is used, but this is not an essential feature of the cipher. To encrypt a message, each block of n letters (considered as an n-component vector) is multiplied by an invertible n × n matrix, against modulus 26. To decrypt the message, each block is multiplied by the inverse of the matrix used for encryption.\n" +
                    "\n" +
                    "                       The matrix used for encryption is the cipher key, and it should be chosen randomly from the set of invertible n × n matrices (modulo 26)." +
                    "The Playfair cipher was the first practical digraph substitution cipher. The scheme was invented in 1854 by Charles Wheatstone but was named after Lord Playfair who promoted the use of the cipher. In playfair cipher unlike traditional cipher we encrypt a pair of alphabets(digraphs) instead of a single alphabet.\n" +
                    "\n" +
                    "It was used for tactical purposes by British forces in the Second Boer War and in World War I and for the same purpose by the Australians during World War II. This was because Playfair is reasonably fast to use and requires no special equipment.");
        }

        if(str.equals("Vigenere cipher"))
        {
            n1.setText("1.Enter the plain text and key in capital lettters.");
            n2.setText("2.No special characters are allowed.");
            n3.setText("3.Violation of the above rules will lead to malfunction.");
            desc.setText("                      Vigenere Cipher is a method of encrypting alphabetic text. It uses a simple form of polyalphabetic substitution. A polyalphabetic cipher is any cipher based on substitution, using multiple substitution alphabets .The encryption of the original text is done using the Vigenère square or Vigenère table.\n" +
                    "\n" +
                    "           The table consists of the alphabets written out 26 times in different rows, each alphabet shifted cyclically to the left compared to the previous alphabet, corresponding to the 26 possible Caesar Ciphers.\n" +
                    "           At different points in the encryption process, the cipher uses a different alphabet from one of the rows.\n" +
                    "           The alphabet used at each point depends on a repeating keyword.");
        }



        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str.equals("Ceaser cipher")){


                    splainText = plainText.getText().toString();
                    skey1 = key1.getText().toString();
         //           scipherText = cipherText.getText().toString();
                    sencSol=encryptfun(splainText,skey1).toString();
//
                    encSol.setText(sencSol);

                }
                if(str.equals("Playfair cipher"))
                {
                    splainText = plainText.getText().toString();
                    skey1 = key1.getText().toString();

                    setKey(skey1);
                    KeyGen();
                    //           scipherText = cipherText.getText().toString();
                    sencSol=encryptMessage(splainText).toString();
//
                    encSol.setText(sencSol);
                }
                if(str.equals("Hill cipher"))
                {
                    splainText = plainText.getText().toString();
                    skey1 = key1.getText().toString();

                    double sq = Math.sqrt(skey1.length());
                    if (sq != (long) sq)
                        System.out
                                .println("Invalid key length!!! Does not form a square matrix...");
                    else
                    {
                        int s = (int) sq;
                        if (check(skey1, s))
                        {
                            System.out.println("Result:");
                            divide(splainText, s);
                            cofact(keymatrix, s);
                        }
                    }
                    //           scipherText = cipherText.getText().toString();
                 //   sencSol=encryptfun(splainText,skey1).toString();
//
                    encSol.setText(sencSol);
                    hill1.setText("inverse of key : ");
                    hillkey1.setText(invkey);
                }
                if(str.equals("Vigenere cipher"))
                {
                    splainText = plainText.getText().toString();
                    skey1 = key1.getText().toString();


                    //           scipherText = cipherText.getText().toString();
                     sencSol=encryptvg(splainText,skey1).toString();
//
                    encSol.setText(sencSol);
                }
            }
        });

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str.equals("Ceaser cipher")){

                    scipherText = cipherText.getText().toString();
                    skey2 = key2.getText().toString();
                    //           scipherText = cipherText.getText().toString();
                    sdecSol=decryptfun(scipherText,skey2).toString();
//
                    decSol.setText(sdecSol);

                }
                if(str.equals("Playfair cipher"))
                {
                    scipherText = cipherText.getText().toString();
                    skey2 = key2.getText().toString();
                    setKey(skey2);
                    KeyGen();
                    //           scipherText = cipherText.getText().toString();
                    sdecSol=decryptMessage(scipherText).toString();
                    decSol.setText(sdecSol);
                }

                if(str.equals("Hill cipher"))
                {
                    scipherText = cipherText.getText().toString();
                    skey2 = key2.getText().toString();

                    double sq = Math.sqrt(skey2.length());
                    if (sq != (long) sq)
                        System.out
                                .println("Invalid key length!!! Does not form a square matrix...");
                    else
                    {
                        int s = (int) sq;
                        if (check1(skey2, s))
                        {
                            System.out.println("Result:");
                            divide1(scipherText, s);
                            cofact1(keymatrix1, s);
                        }
                    }
                    //           scipherText = cipherText.getText().toString();
                    //   sencSol=encryptfun(splainText,skey1).toString();
//
                    decSol.setText(sdecSol);
                    hill2.setText("inverse of key : ");
                    hillkey2.setText(invkey1);
                    //           scipherText = cipherText.getText().toString();
                //    sdecSol=decryptfun(scipherText,skey2).toString();
//
                  //  decSol.setText(sdecSol);
                }
                if(str.equals("Vigenere cipher"))
                {
                    scipherText = cipherText.getText().toString();
                    skey2 = key2.getText().toString();
                    //           scipherText = cipherText.getText().toString();
                    sdecSol=decryptvg(scipherText,skey2).toString();
//
                    decSol.setText(sdecSol);
                }

            }
        });

    }



    //****************************************************************Caeser cipher********************************
    public static String encryptfun(String message,String sh1)
    {
        String encryptedMessage = "";
        int key=Integer.parseInt(sh1);
        StringBuffer result= new StringBuffer();
        char ch;
        for(int i = 0; i < message.length(); ++i){
            ch = message.charAt(i);

            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch + key);

                if(ch > 'z'){
                    ch = (char)(ch - 'z' + 'a' - 1);
                }

                encryptedMessage += ch;
            }
            else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch + key);

                if(ch > 'Z'){
                    ch = (char)(ch - 'Z' + 'A' - 1);
                }

                encryptedMessage += ch;
            }
            else {
                encryptedMessage += ch;
            }
        }
        return encryptedMessage;
    }
    // Decrypts cipher using shift
    public static String decryptfun(String message, String sh1)
    {
        String decryptedMessage = "";
        int key=Integer.parseInt(sh1);
        StringBuffer result= new StringBuffer();
        char ch;
        for(int i = 0; i < message.length(); ++i){
            ch = message.charAt(i);

            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch - key);

                if(ch < 'a'){
                    ch = (char)(ch + 'z' - 'a' + 1);
                }

                decryptedMessage += ch;
            }
            else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch - key);

                if(ch < 'A'){
                    ch = (char)(ch + 'Z' - 'A' + 1);
                }

                decryptedMessage += ch;
            }
            else {
                decryptedMessage += ch;
            }
        }

        return decryptedMessage;
    }

    //*******************************************************************************************************












   // **************************************************playfair cipher**************************

    public void setKey(String k)
    {
        String K_adjust = new String();
        boolean flag = false;
        K_adjust = K_adjust + k.charAt(0);
        for (int i = 1; i < k.length(); i++)
        {
            for (int j = 0; j < K_adjust.length(); j++)
            {
                if (k.charAt(i) == K_adjust.charAt(j))
                {
                    flag = true;
                }
            }
            if (flag == false)
                K_adjust = K_adjust + k.charAt(i);
            flag = false;
        }
        KeyWord = K_adjust;
    }

    public void KeyGen()
    {
        boolean flag = true;
        char current;
        Key = KeyWord;
        for (int i = 0; i < 26; i++)
        {
            current = (char) (i + 97);
            if (current == 'j')
                continue;
            for (int j = 0; j < KeyWord.length(); j++)
            {
                if (current == KeyWord.charAt(j))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
                Key = Key + current;
            flag = true;
        }
        System.out.println(Key);
        matrix();
    }

    private void matrix()
    {
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                matrix_arr[i][j] = Key.charAt(counter);
                System.out.print(matrix_arr[i][j] + " ");
                counter++;
            }
            System.out.println();
        }
    }

    private String format(String old_text)
    {
        int i = 0;
        int len = 0;
        String text = new String();
        len = old_text.length();
        for (int tmp = 0; tmp < len; tmp++)
        {
            if (old_text.charAt(tmp) == 'j')
            {
                text = text + 'i';
            }
            else
                text = text + old_text.charAt(tmp);
        }
        len = text.length();
        for (i = 0; i < len; i = i + 2)
        {
            if (text.charAt(i + 1) == text.charAt(i))
            {
                text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
            }
        }
        return text;
    }

    private String[] Divid2Pairs(String new_string)
    {
        String Original = format(new_string);
        int size = Original.length();
        if (size % 2 != 0)
        {
            size++;
            Original = Original + 'x';
        }
        String x[] = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++)
        {
            x[i] = Original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
    }

    public int[] GetDiminsions(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix_arr[i][j] == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

    public String encryptMessage(String Source)
    {
        String src_arr[] = Divid2Pairs(Source);
        String Code = new String();
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0])
            {
                if (part1[1] < 4)
                    part1[1]++;
                else
                    part1[1] = 0;
                if (part2[1] < 4)
                    part2[1]++;
                else
                    part2[1] = 0;
            }
            else if (part1[1] == part2[1])
            {
                if (part1[0] < 4)
                    part1[0]++;
                else
                    part1[0] = 0;
                if (part2[0] < 4)
                    part2[0]++;
                else
                    part2[0] = 0;
            }
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code = Code + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return Code;
    }

    public String decryptMessage(String Code)
    {
        String Original = new String();
        String src_arr[] = Divid2Pairs(Code);
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0])
            {
                if (part1[1] > 0)
                    part1[1]--;
                else
                    part1[1] = 4;
                if (part2[1] > 0)
                    part2[1]--;
                else
                    part2[1] = 4;
            }
            else if (part1[1] == part2[1])
            {
                if (part1[0] > 0)
                    part1[0]--;
                else
                    part1[0] = 4;
                if (part2[0] > 0)
                    part2[0]--;
                else
                    part2[0] = 4;
            }
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Original = Original + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return Original;
    }


    //**************************************************************************************************************






    //**************************************************hill cipher**************************

    public void divide(String temp, int s)
    {
        while (temp.length() > s)
        {
            String sub = temp.substring(0, s);
            temp = temp.substring(s, temp.length());
            perform(sub);
        }
        if (temp.length() == s)
            perform(temp);
        else if (temp.length() < s)
        {
            for (int i = temp.length(); i < s; i++)
                temp = temp + 'x';
            perform(temp);
        }
    }

    public void perform(String line)
    {
        linetomatrix(line);
        linemultiplykey(line.length());
        result(line.length());
    }

    public void keytomatrix(String key, int len)
    {
        keymatrix = new int[len][len];
        int c = 0;
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                keymatrix[i][j] = ((int) key.charAt(c)) - 97;
                c++;
            }
        }
    }

    public void linetomatrix(String line)
    {
        linematrix = new int[line.length()];
        for (int i = 0; i < line.length(); i++)
        {
            linematrix[i] = ((int) line.charAt(i)) - 97;
        }
    }

    public void linemultiplykey(int len)
    {
        resultmatrix = new int[len];
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                resultmatrix[i] += keymatrix[i][j] * linematrix[j];
            }
            resultmatrix[i] %= 26;
        }
    }

    public void result(int len)
    {

        for (int i = 0; i < len; i++)
        {
            sencSol += (char) (resultmatrix[i] + 97);
        }
        System.out.print(sencSol);

    }

    public boolean check(String key, int len)
    {
        keytomatrix(key, len);
        int d = determinant(keymatrix, len);
        d = d % 26;
        if (d == 0)
        {
            System.out
                    .println("Invalid key!!! Key is not invertible because determinant=0...");
            return false;
        }
        else if (d % 2 == 0 || d % 13 == 0)
        {
            System.out
                    .println("Invalid key!!! Key is not invertible because determinant has common factor with 26...");
            return false;
        }
        else
        {
            return true;
        }
    }

    public int determinant(int A[][], int N)
    {
        int res;
        if (N == 1)
            res = A[0][0];
        else if (N == 2)
        {
            res = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        }
        else
        {
            res = 0;
            for (int j1 = 0; j1 < N; j1++)
            {
                int m[][] = new int[N - 1][N - 1];
                for (int i = 1; i < N; i++)
                {
                    int j2 = 0;
                    for (int j = 0; j < N; j++)
                    {
                        if (j == j1)
                            continue;
                        m[i - 1][j2] = A[i][j];
                        j2++;
                    }
                }
                res += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1]
                        * determinant(m, N - 1);
            }
        }
        return res;
    }

    public void cofact(int num[][], int f)
    {
        int b[][], fac[][];
        b = new int[f][f];
        fac = new int[f][f];
        int p, q, m, n, i, j;
        for (q = 0; q < f; q++)
        {
            for (p = 0; p < f; p++)
            {
                m = 0;
                n = 0;
                for (i = 0; i < f; i++)
                {
                    for (j = 0; j < f; j++)
                    {
                        b[i][j] = 0;
                        if (i != q && j != p)
                        {
                            b[m][n] = num[i][j];
                            if (n < (f - 2))
                                n++;
                            else
                            {
                                n = 0;
                                m++;
                            }
                        }
                    }
                }
                fac[q][p] = (int) Math.pow(-1, q + p) * determinant(b, f - 1);
            }
        }
        trans(fac, f);
    }

    void trans(int fac[][], int r)
    {
        int i, j;
        int b[][], inv[][];
        b = new int[r][r];
        inv = new int[r][r];
        int d = determinant(keymatrix, r);
        int mi = mi(d % 26);
        mi %= 26;
        if (mi < 0)
            mi += 26;
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                b[i][j] = fac[j][i];
            }
        }
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                inv[i][j] = b[i][j] % 26;
                if (inv[i][j] < 0)
                    inv[i][j] += 26;
                inv[i][j] *= mi;
                inv[i][j] %= 26;
            }
        }
        System.out.println("\nInverse key:");
        matrixtoinvkey(inv, r);
    }

    public int mi(int d)
    {
        int q, r1, r2, r, t1, t2, t;
        r1 = 26;
        r2 = d;
        t1 = 0;
        t2 = 1;
        while (r1 != 1 && r2 != 0)
        {
            q = r1 / r2;
            r = r1 % r2;
            t = t1 - (t2 * q);
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }
        return (t1 + t2);
    }

    public void matrixtoinvkey(int inv[][], int n)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                invkey += (char) (inv[i][j] + 97);
            }
        }
        System.out.print(invkey);
    }



    //decrpt ku thaniyyyaaa codeeeee ...... worst coder da yasinuuuuuuu.. ipdi podurathuku ...ottrai puliya marathhila thoookil thongalaaammmm


    public void divide1(String temp, int s)
    {
        while (temp.length() > s)
        {
            String sub = temp.substring(0, s);
            temp = temp.substring(s, temp.length());
            perform1(sub);
        }
        if (temp.length() == s)
            perform1(temp);
        else if (temp.length() < s)
        {
            for (int i = temp.length(); i < s; i++)
                temp = temp + 'x';
            perform1(temp);
        }
    }

    public void perform1(String line)
    {
        linetomatrix1(line);
        linemultiplykey1(line.length());
        result1(line.length());
    }

    public void keytomatrix1(String key, int len)
    {
        keymatrix1 = new int[len][len];
        int c = 0;
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                keymatrix1[i][j] = ((int) key.charAt(c)) - 97;
                c++;
            }
        }
    }

    public void linetomatrix1(String line)
    {
        linematrix1 = new int[line.length()];
        for (int i = 0; i < line.length(); i++)
        {
            linematrix1[i] = ((int) line.charAt(i)) - 97;
        }
    }

    public void linemultiplykey1(int len)
    {
        resultmatrix1 = new int[len];
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                resultmatrix1[i] += keymatrix1[i][j] * linematrix1[j];
            }
            resultmatrix1[i] %= 26;
        }
    }

    public void result1(int len)
    {

        for (int i = 0; i < len; i++)
        {
            sdecSol += (char) (resultmatrix1[i] + 97);
        }
        System.out.print(sdecSol);

    }

    public boolean check1(String key, int len)
    {
        keytomatrix1(key, len);
        int d = determinant1(keymatrix, len);
        d = d % 26;
        if (d == 0)
        {
            System.out
                    .println("Invalid key!!! Key is not invertible because determinant=0...");
            return false;
        }
        else if (d % 2 == 0 || d % 13 == 0)
        {
            System.out
                    .println("Invalid key!!! Key is not invertible because determinant has common factor with 26...");
            return false;
        }
        else
        {
            return true;
        }
    }

    public int determinant1(int A[][], int N)
    {
        int res;
        if (N == 1)
            res = A[0][0];
        else if (N == 2)
        {
            res = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        }
        else
        {
            res = 0;
            for (int j1 = 0; j1 < N; j1++)
            {
                int m[][] = new int[N - 1][N - 1];
                for (int i = 1; i < N; i++)
                {
                    int j2 = 0;
                    for (int j = 0; j < N; j++)
                    {
                        if (j == j1)
                            continue;
                        m[i - 1][j2] = A[i][j];
                        j2++;
                    }
                }
                res += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1]
                        * determinant1(m, N - 1);
            }
        }
        return res;
    }

    public void cofact1(int num[][], int f)
    {
        int b[][], fac[][];
        b = new int[f][f];
        fac = new int[f][f];
        int p, q, m, n, i, j;
        for (q = 0; q < f; q++)
        {
            for (p = 0; p < f; p++)
            {
                m = 0;
                n = 0;
                for (i = 0; i < f; i++)
                {
                    for (j = 0; j < f; j++)
                    {
                        b[i][j] = 0;
                        if (i != q && j != p)
                        {
                            b[m][n] = num[i][j];
                            if (n < (f - 2))
                                n++;
                            else
                            {
                                n = 0;
                                m++;
                            }
                        }
                    }
                }
                fac[q][p] = (int) Math.pow(-1, q + p) * determinant1(b, f - 1);
            }
        }
        trans1(fac, f);
    }

    void trans1(int fac[][], int r)
    {
        int i, j;
        int b[][], inv[][];
        b = new int[r][r];
        inv = new int[r][r];
        int d = determinant1(keymatrix1, r);
        int mi = mi1(d % 26);
        mi %= 26;
        if (mi < 0)
            mi += 26;
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                b[i][j] = fac[j][i];
            }
        }
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                inv[i][j] = b[i][j] % 26;
                if (inv[i][j] < 0)
                    inv[i][j] += 26;
                inv[i][j] *= mi;
                inv[i][j] %= 26;
            }
        }
        System.out.println("\nInverse key:");
        matrixtoinvkey1(inv, r);
    }

    public int mi1(int d)
    {
        int q, r1, r2, r, t1, t2, t;
        r1 = 26;
        r2 = d;
        t1 = 0;
        t2 = 1;
        while (r1 != 1 && r2 != 0)
        {
            q = r1 / r2;
            r = r1 % r2;
            t = t1 - (t2 * q);
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }
        return (t1 + t2);
    }

    public void matrixtoinvkey1(int inv[][], int n)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                invkey1 += (char) (inv[i][j] + 97);
            }
        }
        System.out.print(invkey1);
    }
  //****************************************************************************************************************


    //********************************************************************VEGENERE CIPHER**********************
    public static String encryptvg(String text, final String key)
    {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

    public static String decryptvg(String text, final String key)
    {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
    //*********************************************************************************************************
}
