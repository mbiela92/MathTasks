package de.biela.math;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;





public class Math
{
    /*
     * Setter und Getter habe ich weggelassen, weil es soll ja nichts mehr verändert werden. Dann verwirrt es auch niemanden
     * 
     * 
     */
    private static String []arrayOperator;



    private int firstOperand;
    private int secondOperand;
    private static int MAX_VALUE=20;
    private int result=0;
    private static int amountTasks=50;
    private String operator;
    private final static String path=System.getProperty("user.dir") + "/result.pdf";
    private Random random = new Random ( ) ;
    
    protected Math() 
    {
        firstOperand = random.nextInt(MAX_VALUE)+1;                       //Zahl zwischen 1-20
        operator = arrayOperator[random.nextInt(arrayOperator.length)];
        secondOperand = determineSecondOperand(MAX_VALUE);
    }
    
    protected static final void setArrayOperator(String[] arrayOperator)
    {
        Math.arrayOperator = arrayOperator;
    }
    
    protected int determineSecondOperand(int value)
    {
        int zweiterOperand = 999;
        switch(operator)
        {
            case"+":
                zweiterOperand = random.nextInt(value+1-firstOperand);      
                break;
            case"-":
                zweiterOperand = random.nextInt(firstOperand);       
                break;
            case"*":
                zweiterOperand = random.nextInt((value/firstOperand)+1);   
                break;
            case":":
                while(result==0||result==1) 
                {
                    zweiterOperand = random.nextInt(firstOperand)+1;
                    result =  firstOperand/zweiterOperand;
                }
                firstOperand = result*zweiterOperand;
                result=0;
                break;
            }
        return zweiterOperand;
    }
    
    private static String formatTasks(List liste) 
    {
        StringBuilder sb= new StringBuilder();
        while(!liste.isEmpty()) 
        {
            for(int i=0;i<3;i++) 
            {
                if(!liste.isEmpty())sb.append(String.format("%-30s",liste.remove(0).toString()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    protected static String[] FormatArithmeticOperations(String input) {
        String [] tmp = input.split("");
        return tmp;
    }
    
    protected static int queryAmount() 
    {
        int tmp = 999;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte tipp eine Zahl zwischen 1-50 ein um zu waehlen wie viele Aufgaben erstellt werden sollen");
        while (scanner.hasNext()) 
        {
            if (scanner.hasNextInt()) 
            {
               int input = scanner.nextInt();
               if(input>0&&input<=50)
               {
                   System.out.println(input + " Aufgaben werden erstellt");
                   tmp=input;
                   break;
               }
               else{
                   System.out.println("Bedingungen wurden nicht erfuellt");
               }
            }else{
                System.out.println(scanner.next() + " das ist keine reine Zahl");
            }
        }
        return tmp;
    }
 
    
    protected static String queryArithmeticOperand() //Mache ich die Methode protected oder package?
    {
        String tmp="999";
        String input="";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte waehle bis zu 4 Rechenoperationen aus + - * :\nNur die Rechenzeichen eingeben ohne Leerstelle");
        while(scanner.hasNext()) 
        {
            if(scanner.hasNext()) {
                input = scanner.next();
            }
            if(input.matches("[\\+\\-\\*:]{0,4}")) {
                tmp=input;
                break;
            }
            else {System.out.println("Das passt nicht nur die Rechenzeicheneingeben");} 
        }
        System.out.println("Sie haben folgende Rechenzeichen gewaehlt " + tmp + "\nund das Dokument wurde erstellt");
        scanner.close();
        return tmp;
    }
    
    protected static List<Math> createTasks(int amount) 
    {
        List<Math> tasks = new ArrayList<>();
        for(int i = 0; i<amount;i++) 
        {
            tasks.add(new Math());
        }
        return tasks;
    }
    
    private static void createDocument(String text, String path) {
        Document document = new Document(PageSize.A4, 50,50,50,50);
        try
        {
            PdfWriter.getInstance(document, new FileOutputStream(path));
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch (DocumentException e1)
        {
            e1.printStackTrace();
        }
        document.open();
        try
        {
            document.add(new Paragraph(text));
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        document.close();
    }
    
    protected static int queryMaxValue()
    {
        int tmp=999;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte tippe eine Zahl zwischen 1-100 ein um zu waehlen wie hoch die Rechenoperationen sein sollen");
        while (scanner.hasNext()) 
        {
            if (scanner.hasNextInt()) 
            {
               int input = scanner.nextInt();
               if(input>0&&input<=100)
               {
                   tmp=input;
                   System.out.println("Aufgaben in Hoehe von maximal " + tmp + " werden erstellt");
                   break;
               }
               else{
                   System.out.println("Bedingungen wurden nicht erfuellt");
               }
            }else{
                System.out.println(scanner.next() + " das ist keine reine Zahl");
            }
            
        }
        return tmp;
    }
    

    public String toString() 
    {
        return firstOperand + " " + operator + " " + secondOperand + " = ____";
    }
    

    public static void main(String[] args)
    {
        System.out.println(System.getProperty("user.dir"));
        amountTasks = queryAmount();
        MAX_VALUE = queryMaxValue();
        arrayOperator = Math.FormatArithmeticOperations(queryArithmeticOperand());
        String text = Math.formatTasks(createTasks(amountTasks));
        Math.createDocument(text, path);

    }

    

    

}
