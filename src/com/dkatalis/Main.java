package com.dkatalis;

import com.dkatalis.service.CustomerService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner in = new Scanner(System.in);

        System.out.println("======= WELCOME TO ATM =======");
        System.out.println("COMMAND");
        System.out.println("login [name]");
        System.out.println("deposit [amount]");
        System.out.println("withdraw [amount]");
        System.out.println("transfer [target] [amount]");
        System.out.println("logout");
        System.out.println("write your command");

        boolean session = true;
        CustomerService customerService=new CustomerService();
        while(session){


            String command = in.nextLine();
            String [] comm= command.split(" ");
            if(command.contains("login")){

                customerService.login(command.split(" ")[1]);
            }
            if(command.contains("deposit")){


                customerService.deposit(command.split(" ")[1]);
            }

            if(command.contains("withdraw")){


                customerService.withdraw(command.split(" ")[1]);
            }

            if(command.contains("transfer")){


                customerService.transfer(comm[1],comm[2]);
            }
            if(command.contains("logout")){

                customerService.logout();
            }
        }


    }
}
