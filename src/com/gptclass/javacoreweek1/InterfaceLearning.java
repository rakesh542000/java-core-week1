package com.gptclass.javacoreweek1;
interface PaymentService{
     void pay(double amount);
}
class UPIPayment implements PaymentService{

    @Override
    public void pay(double amount) {
        System.out.println("Payment made through UPI of amount: "+amount);
    }
}

class CardPayment implements PaymentService{
    public void pay(double amount) {
        System.out.println("Payment made through Card of amount: "+amount);
    }
}

abstract class Notification{
    protected String recipient;

    public Notification(String recipient) {
        this.recipient = recipient;
    }

    abstract public void send(String message);
    public void logMessage(){
        System.out.println("Notification has been sent to "+recipient);
    }
}
class EmailNotification extends Notification{

    public EmailNotification(String recipient) {
        super(recipient);
    }

    @Override
    public void send(String message) {
        System.out.println("Notification sent to email as: "+message);
    }
}
class SMSNotification extends Notification{

    public SMSNotification(String recipient) {
        super(recipient);
    }

    @Override
    public void send(String message) {
        System.out.println("SMS Sent as: "+message);

    }
}

public class InterfaceLearning {
    public static void main(String[] args) {
        PaymentService upi=new UPIPayment();
        upi.pay(500);
        PaymentService card = new CardPayment();
        card.pay(1000);
        Notification official = new EmailNotification("Rakesh");
        official.send("Email Notification");
        official.logMessage();
        Notification normal = new SMSNotification("Rajesh");
        normal.send("SMS Notification");
        normal.logMessage();
    }
}
