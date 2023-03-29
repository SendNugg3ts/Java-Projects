import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Mortgage Payment Calculator");
        System.out.print("Payment: ");
        Scanner scansthings = new Scanner(System.in);
        int Payment = scansthings.nextInt();
        System.out.print("interest rate: ");
        float MIR = scansthings.nextFloat();
        MIR /= 1200;
        System.out.print("Period in Years: ");
        int Period = scansthings.nextInt();
        Period *=12;
        double Formula = Payment * ((MIR*Math.pow(1+MIR,Period))/Math.pow(1+MIR,Period)-1);
        System.out.println("Your Mortgage Payments: ");
        System.out.println(NumberFormat.getCurrencyInstance().format(Formula));

    }
}