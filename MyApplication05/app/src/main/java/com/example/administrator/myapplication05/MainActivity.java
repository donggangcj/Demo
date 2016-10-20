package com.example.administrator.myapplication05;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String str1,str2;

    Button btn_c,btn_jia,btn_jian,btn_chen,btn_chu,btn_den,temp,change,revoke;
    int []btn_s;
    double result0=0,result1,result;
    int flag;
//  boolean change=true;//标志,解决连续输出两个符号错误的问题
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBtn();
        //清空按钮上的点击事件
        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1="";
                str2=""; //清空记录
                tv.setText(str1);
                flag=0;
            }
        });

        //监听
        for(int i=0;i<btn_s.length;i++){
            temp = (Button) findViewById(btn_s[i]);
            temp.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str1 = tv.getText().toString();
                   //         str1 = str1.replaceAll("\\D+"," ");  //使用正则法则对字符串进行处理
                            str1 = str1.trim();
                            str1 = str1 + String.valueOf(((Button) v).getText());
                            tv.setText(str1);
                        }
                    }
            );
        }

        btnListener(btn_jia,1);
        btnListener(btn_jian,2);
        btnListener(btn_chen,3);
        btnListener(btn_chu,4);

        btn_den.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    result1 = Integer.parseInt(str1);               //将第二个值赋值给result1
                result1 = Double.parseDouble(str1);
                if(flag==1){ result=result0+result1;}
                else if(flag==2){ result=result0-result1;}
                else if(flag==3){ result=result0*result1;}
                else if(flag==4){ result=(int) (result0/result1);}
                DecimalFormat df = new DecimalFormat("###.000");
                String str = df.format(result).trim();
//                String str = (result + "").trim();
                tv.setText(str);
            }

        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = tv.getText().toString().trim(); //去掉字段两边的空格
                int result3 = Integer.parseInt(str);
                Stack<Integer> stack = new Stack<Integer>();
                while(result3!=0){
                    stack.push(result3 % 2);
                    result3=result3/2;
                }
                String cha="";
                while(!stack.isEmpty()){
                    cha += stack.pop();
                }
                tv.setText(cha);
            }
        });

        revoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String revoke=tv.getText().toString().trim();
                if(revoke!=""){
                    revoke = revoke.substring(0,revoke.length()-1);
                }
                tv.setText(revoke);
            }
        });

    }


    public  void initBtn(){  //初始化控件资源
        tv = (TextView) findViewById(R.id.tv_show);
//      tv.setText("0");  //初始化值为0:出现05状况,不影响计算
        tv.setHint("0");
        tv.setText("");
        str1 = String.valueOf(tv.getText());
        str2 = "";

        btn_c = (Button) findViewById(R.id.bt_c);
        btn_jia = (Button) findViewById(R.id.bt_jia);
        btn_jian = (Button) findViewById(R.id.bt_jian);
        btn_chen = (Button) findViewById(R.id.bt_chen);
        btn_chu = (Button) findViewById(R.id.bt_chu);
        btn_den = (Button) findViewById(R.id.bt_den);
        change = (Button) findViewById(R.id.bt_chang);
        revoke = (Button) findViewById(R.id.bt_revoke);

        btn_s=new int[]{R.id.bt_0,R.id.bt_1,R.id.bt_2,R.id.bt_3,R.id.bt_4,R.id.bt_5,
                R.id.bt_6,R.id.bt_7,R.id.bt_8,R.id.bt_8,R.id.bt_9,R.id.bt_dian};
    }

    public void btnListener(final Button btn, final int id){
           // final String fh=btn.getText().toString();           //显示符号
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String str = tv.getText().toString().trim(); //去掉字段两边的空格
                    result0 = Double.parseDouble(str);
                    tv.setText(" ");
                    flag=id;
//                    if(str.matches("[0-9]+")){
//                        result0 = Integer.parseInt(str);
//                        tv.setText(btn.getText().toString());
//                        //                   tv.setText("");
//                        //                   tv.setText(fh);
//                        flag=id;
//                    }else{
//                        Toast.makeText(MainActivity.this,"连续输入多为运算符",Toast.LENGTH_LONG).show();
//                        tv.setText(btn.getText().toString());
//                        flag=id;
//                    }

                }
            });
    }

}

