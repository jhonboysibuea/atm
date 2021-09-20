package com.dkatalis.service;

import com.dkatalis.model.Customer;
import com.dkatalis.model.TrxCustomer;

import java.util.LinkedList;
import java.util.List;

public class CustomerService {

     static List<Customer> customerList=new LinkedList<>();
     static Customer customerLogin=new Customer();
     static List<TrxCustomer> trxCustomers=new LinkedList<>();

    public CustomerService() {
    }

    public void checkOwed(){
        if(!trxCustomers.isEmpty()){
            for(TrxCustomer trxCustomer : trxCustomers){
                  if(trxCustomer.getCustomerId().getName().equals(customerLogin.getName())){
                      System.out.println(trxCustomer.getOwedTo());
                }
                if(trxCustomer.getReceiverId().getName().equals(customerLogin.getName())){
                    System.out.println(trxCustomer.getOwedFrom());
                }
                break;
            }
        }
    }

    public boolean checkOwedIstrue(){

            if(!trxCustomers.isEmpty()){
                for(TrxCustomer trxCustomer : trxCustomers){

                    if(trxCustomer.getCustomerId().getName().equals(customerLogin.getName()) && !trxCustomer.getOwedTo().isEmpty()){
                        return true;
                    }
                    if(trxCustomer.getReceiverId().getName().equals(customerLogin.getName()) && !trxCustomer.getOwedFrom().isEmpty()){
                       return true;
                    }
                }
            }
        return false;
    }
    public void login(String name){
        if(!customerList.isEmpty()) {
            for (Customer customer : customerList) {
                if (customer.getName().equals(name)) {
                    customerLogin=customer;
                    System.out.println("Hello, " + name);
                    System.out.println("Your Balance is $" + customer.getAmount());
                }else{
                    Customer newCustomer=new Customer();
                    newCustomer.setAmount(0);
                    newCustomer.setName(name);
                    customerList.add(newCustomer);
                    customerLogin = newCustomer;
                    System.out.println("Hello, " + name);
                    System.out.println("Your Balance is $" + newCustomer.getAmount());
                }

                if(checkOwedIstrue()) {
                    for(TrxCustomer  trxCustomer : trxCustomers){
                        if(trxCustomer.getReceiverId().getName().equals(customerLogin.getName())){
                            if(trxCustomer.getOwedFrom().contains(customerLogin.getName())){
                                customerLogin.setAmount(customerLogin.getAmount()+trxCustomer.getValOwedFrom());
                            }
                        }
                    }

                    checkOwed();
                }
                break;
            }
        }else {
            Customer customer = new Customer();
            customer.setAmount(0);
            customer.setName(name);

            customerList.add(customer);
            customerLogin = customer;
            System.out.println("Hello, " + name);
            System.out.println("Your Balance is $" + customer.getAmount());
        }
    }

    public void deposit(String amount){

            if(checkOwedIstrue()){
                for(TrxCustomer trxCustomer : trxCustomers){
                    if(trxCustomer.getCustomerId().getName().equals(customerLogin.getName())){
                        if(Integer.parseInt(amount)> trxCustomer.getValOwedTo()){
                            customerLogin.setAmount(Integer.parseInt(amount) - trxCustomer.getValOwedTo());
                            trxCustomer.setValOwedFrom(0);
                            trxCustomer.setValOwedTo(0);

                        }else{
                            customerLogin.setAmount(trxCustomer.getValOwedTo()-Integer.parseInt(amount));
                            trxCustomer.setValOwedFrom(trxCustomer.getValOwedTo()-Integer.parseInt(amount));
                            trxCustomer.setValOwedTo(trxCustomer.getValOwedTo()-Integer.parseInt(amount));
                            trxCustomer.setOwedFrom("Owed "+ "$" + trxCustomer.getValOwedTo() +" from "+trxCustomer.getReceiverId().getName());
                            trxCustomer.setOwedTo("Owed "+ "$" + trxCustomer.getValOwedTo() +" to "+trxCustomer.getCustomerId().getName());
                        }


                        if(trxCustomer.getValOwedFrom()==0 && trxCustomer.getValOwedTo()==0 ){
                            trxCustomer.setOwedFrom("");
                            trxCustomer.setOwedTo("");

                        }
                    }else{
                        customerLogin.setAmount( customerLogin.getAmount()+ Integer.parseInt(amount));

                    }
                }
                checkOwed();

            }else{
                customerLogin.setAmount( customerLogin.getAmount()+ Integer.parseInt(amount));
            }
        System.out.println( "Your balance is $"+customerLogin.getAmount());
    }

    public void logout(){

        System.out.println("Goodbye,"+customerLogin.getName());
        customerLogin=new Customer();
    }

    public void withdraw(String amount){

        if(customerLogin.getAmount() > Integer.parseInt(amount)) {
            customerLogin.setAmount(customerLogin.getAmount() - Integer.parseInt(amount));
            System.out.println("Your Balance is $" + customerLogin.getAmount());
        }else{
            System.out.println("the balance is not sufficient");
        }
    }

    public void transfer(String target,String amount){
        for(Customer customer : customerList) {
            if (!customer.getName().equals(target)) {
                System.out.println("No target customer");
            } else {
                int amt=0;
                if(!trxCustomers.isEmpty()){
                    for(TrxCustomer trxCustomer : trxCustomers){
                        if(trxCustomer.getCustomerId().getName().equals(customerLogin.getName()) && trxCustomer.getReceiverId().getName().equals(target)) {
                            trxCustomer.setAmount(trxCustomer.getAmount() + Integer.parseInt(amount));
                            if (customerLogin.getAmount() < Integer.parseInt(amount)) {
                                amt = Integer.parseInt(amount) - customerLogin.getAmount();
                                System.out.println("Transferred " + "$" + amount + " to " + target);
                                System.out.println("Owed " + "$" + amt + " to " + target);
                                trxCustomer.setOwedFrom("Owed " + "$" + amt + " from " + customerLogin.getName());
                                trxCustomer.setOwedTo("Owed " + "$" + amt + " to " + target);
                                trxCustomer.setValOwedTo(amt);
                                trxCustomer.setValOwedTo(amt);
                                customerLogin.setAmount(0);

                            }else{
                                System.out.println("Transferred "+ "$"+amount +" to " +target);
                                trxCustomer.setValOwedTo(0);
                                trxCustomer.setValOwedTo(0);
                                customerLogin.setAmount(customerLogin.getAmount()-Integer.parseInt(amount));
                            }
                        }
                    }

                }else{
                    TrxCustomer trxCustomer=new TrxCustomer();
                    trxCustomer.setCustomerId(customerLogin);
                    trxCustomer.setAmount(Integer.parseInt(amount));
                    trxCustomer.setMethod("transfer");
                    trxCustomer.setReceiverId(customer);

                    if(Integer.parseInt(amount) > customerLogin.getAmount()){
                        amt= Integer.parseInt(amount)-customerLogin.getAmount();
                        trxCustomer.setOwedFrom("Owed "+ "$" + amt +" from "+customerLogin.getName());
                        trxCustomer.setOwedTo("Owed "+ "$" + amt +" to "+target);
                        System.out.println("Transferred "+ "$"+amount +" to " +target);
                        trxCustomer.setValOwedTo(amt);
                        trxCustomer.setValOwedTo(amt);
                        customerLogin.setAmount(0);
                        System.out.println("Owed "+ "$" + amt +" to "+target);

                    }else{
                        System.out.println("Transferred "+ "$"+amount +" to " +target);
                        trxCustomer.setValOwedTo(0);
                        trxCustomer.setValOwedTo(0);
                        customerLogin.setAmount(customerLogin.getAmount()-Integer.parseInt(amount));
                    }
                    trxCustomers.add(trxCustomer);
                }

                for(Customer cust :customerList){
                    if(target.equals(cust.getName())){
                        cust.setAmount(cust.getAmount()+Integer.parseInt(amount));
                    }
                }
                System.out.println("your balance is $"+customerLogin.getAmount());
            }
            break;
        }
    }
}
