package com.mycompany.app;

import java.io.*;
import java.util.Properties;

import java.util.Random;
import java.util.Scanner;
import com.google.common.math.*;

/**
 * Hello world!
 *
 */
public class App 
{

    int num = 0 ;
    String flag = null ;

    public App()
    {
        num = 6 ;//默认产生6位数的密码
    }
    public App(int num,String flag)
    {
        this.num = num ;//代表输入密码长度
        this.flag = flag ;//标记是否包含ASCII可见字符
    }

    public App(String flag,int num)
    {
        this.flag = flag ;
        this.num = num;
    }
    public App(int num){
        this.num=num ;
    }

    public  String genRandomNum(){

       int  pwd_len =num ;
       // File f = new File("src/main/resources/application.properties");
        InputStream in = null ;
       // URL u =App.class.getClassLoader().getResource("application.properties");
       // System.out.println(App.class.getClassLoader().getResource("application.properties"));
        try{
            //in = new new BufferedInputStream(new FileInputStream(u));
            in =App.class.getClassLoader().getResourceAsStream("application.properties");
            //in = new BufferedInputStream(new FileInputStream(f));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        Properties p = new Properties();

       try{
           p.load(in);
       }catch(IOException e)
       {
            e.printStackTrace();
       }
        //System.out.println("得到的配置是："+p.getProperty("case-sensitive"));

        int i;  //生成的随机数
        int count = 0; //生成的密码的长度

        StringBuffer pwd = new StringBuffer("");

        Random r = new Random();

        if(p.getProperty("case-sensitive").equals("true")&&flag==null )
        {
            while(count < pwd_len){
                i=Math.abs(r.nextInt(95)+32);

                if((i>=48&&i<=57)||(i>=65&&i<=90)||(i>=97&&i<=122)){

                    pwd.append((char)i);
                    count++;
                }
            }
        }
        else if(p.getProperty("case-sensitive").equals("false")&&flag==null) {
            while (count < pwd_len) {
                i=Math.abs(r.nextInt(95)+32);
                if((i>=48&&i<=57)||(i>=97&&i<=122)){
                    pwd.append((char)i);
                    count++;
                }
            }
        }
        else if(p.getProperty("case-sensitive").equals("true")&&(flag.equals("+p")||flag.equals("+P")))
        {

            while(count<pwd_len)
            {
                i=Math.abs(r.nextInt(95)+32);
               if(i>=32&&i<126){
                   pwd.append((char)i);
                   count++;
               }
            }
        }
        else if(p.getProperty("case-sensitive").equals("false")&&(flag.equals("+p")||flag.equals("+P")))
        {

            while(count<pwd_len)
            {
                i=Math.abs(r.nextInt(95)+32);
                if((i>=32&&i<=64)||(i>=91&&i<=126))
                {
                    pwd.append((char)i);
                    count++;
                }
            }
        }
        else
        {
            pwd=new StringBuffer("输入有误 或 配置文件有误");
        }
        return pwd.toString();
    }












    public static void main( String[] args )
    {

        App a = null;
        if(args.length==0)
        {
            a=new App();
        }
       else if(args.length==1) {
         a=new App(Integer.parseInt(args[0]));
       }
        else if(args.length==2)
       {
           a= new App(Integer.parseInt(args[0]),args[1]);
       }


        System.out.println( "得到的密码是："+a.genRandomNum() );
    }
}
