import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class bankApp {
    public static final String bankNumber = "bankNumber.txt";
    public static final String userDetalis = "UserDetalis.txt";
    public static final String depositDetalis = "depositDetalis.txt";
    public static final String DepositBill = "depositBill .txt";
    public static final String WithdrawBill = "Withdraw.txt";

    public static void Menu(ArrayList<String> UserDetalis ,ArrayList<Integer> PhoneNumber, ArrayList<Integer>ArrayBankNumber, ArrayList<String> DepositMoney){
        Scanner input = new Scanner(System.in);
        boolean running = true;

        String exitArt = "\r\n" + //
                        " ___    _    _  _  _____  _  _   _  ___            \r\n" + //
                        "(  _`\\ ( )  ( )(_)(_   _)(_)( ) ( )(  _`\\          \r\n" + //
                        "| (_(_)`\\`\\/'/'| |  | |  | || `\\| || ( (_)         \r\n" + //
                        "|  _)_   >  <  | |  | |  | || , ` || |___          \r\n" + //
                        "| (_( ) /'/\\`\\ | |  | |  | || |`\\ || (_, ) _  _  _ \r\n" + //
                        "(____/'(_)  (_)(_)  (_)  (_)(_) (_)(____/'(_)(_)(_)\r\n" + //
                        "                                                   \r\n" + //
                        "                                                   \r\n" + //
                        "";
        while (running) {
            System.out.println("1.Register the System");
            System.out.println("2.Login to the System");
            System.out.println("3.Exit.....");
            System.out.print("Choice the Option : ");
            int Choice = input.nextInt();

            switch (Choice) {
                case 1:
                    System.out.println(".....REGISTER THE BANK SYSTEM........");
                    Register(UserDetalis, PhoneNumber, ArrayBankNumber);
                    break;
                case 2:
                    System.out.println(".....LOGIN TO THE SYSTEM.......");
                    login(UserDetalis, UserDetalis);
                    Mainmenu(UserDetalis, DepositMoney, DepositMoney);
                    running = false;
                    break;
                case 3:
                    System.out.println(".......EXIT......");
                    System.out.println(exitArt);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Choice Number Check Again!!!!");
                    break;
            }
        }

    }


// ================================================================================================================
    public static void Register(ArrayList<String> UserDetalis ,ArrayList<Integer> PhoneNumber, ArrayList<Integer>ArrayBankNumber ){
        Scanner input = new Scanner(System.in);
        // int inBankNumber = 10000000 + ArrayBankNumber.size();
        String lastLine = null;
        int lastNumber;


        System.out.print("Enter A your Name : ");
        String inputName = input.nextLine();
        UserDetalis.add(inputName);

        System.out.print("Enter Your Address : ");
        String inputAddress = input.nextLine();
        UserDetalis.add(inputAddress);

        System.out.print("Enter a Phone Number : ");
        int inputPhoneNumber = input.nextInt();
        PhoneNumber.add(inputPhoneNumber);


        try{
            BufferedReader reader = new BufferedReader(new FileReader(bankNumber));
            String currnetLine;
            while ((currnetLine = reader.readLine()) !=null) {
                lastLine = currnetLine;
            }
            if(lastLine != null){
                // System.out.println(lastLine);
                try{
                    lastNumber = Integer.parseInt(lastLine);
                    lastNumber++;

                    BufferedWriter writer = new BufferedWriter(new FileWriter(userDetalis,true));
                    writer.append( "\n" +"=".repeat(60) + "\n");
                    writer.append("Your Details : " +"\n");
                    writer.write("Your Bank Number : " + (String.valueOf(lastNumber)) + "\n");
                    writer.append("Your Name : " + inputName + "\n");
                    writer.append("Your Address : " + inputAddress + "\n");
                    writer.append("Your Phone Number : " + inputPhoneNumber + "\n");
                    writer.append("=".repeat(60));
                    writer.close();


                    BufferedWriter writer1 = new BufferedWriter(new FileWriter(bankNumber,true));
                    writer1.append(String.valueOf(lastNumber) + "\n");
                    writer1.close();


                    System.out.println("Successfully Register the System.........");
                    System.out.println("=".repeat(60));
                    System.out.println("Here Your Detalis : ");
                    System.out.println("You Bank Number : " + lastNumber);
                    System.out.println("You Name : " + inputName);
                    System.out.println("Your Adress : " + inputAddress);
                    System.out.println("Your Phone Number : " + inputPhoneNumber);
                    System.out.println("=".repeat(60));
        

                }catch(Exception e){
                    System.out.println(e);
                }
               
            }else{
                System.out.println("Check Again!!!");
            }
            reader.close();
        }catch(IOException e){
            System.out.println(e);
        }
        // ArrayBankNumber.add(lastLine);

    }

// ===============================================================================================================
    public static void login(ArrayList<String> BankNumbers,ArrayList<String>UserDetalis){
        Scanner input = new Scanner(System.in);
        boolean loginSuccess = false;

        while (!loginSuccess) {
            System.out.println("You Have to Login to the System Using Bank Number ");
            System.out.print("Enter Your Bank Number : ");
            String inputBankNumber = input.nextLine();

            try{
                BufferedReader reader = new BufferedReader(new FileReader(bankNumber));
                String bankNum;
                while ((bankNum = reader.readLine()) !=null) {
                    BankNumbers.add(bankNum);
                }
                reader.close();
                for(String Login : BankNumbers){
                    if(Login.equals(inputBankNumber)){
                        System.out.println("Login Successfully.....");
                        System.out.println("Your Bank Number : " + inputBankNumber);
                        // System.out.println("Customer Name : " + UserDetalis.get(0));
                        // System.out.println("Customer Address : " + UserDetalis.get(1));
                        loginSuccess = true;
                        break;
                    }
                }

                if(!loginSuccess){
                    System.out.println("Invalid Bank Number Check your Bank Number Again!!!");
                    
                }
            }catch(IOException e){
                System.out.println(e);
            }
        }

    }
// =================================================================================================================
    public static void Mainmenu (ArrayList<String> BankNumbers, ArrayList<String> DepositMoney,ArrayList<String> WithdrawMoney){
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1.Deposit the Money.....");
            System.out.println("2.Withdraw the Money......");
            System.out.println("3.View the Money Balance......");
            System.out.println("4.EXIT......");
            System.out.print("Choice the Option ? ");
            int Choice = input.nextInt();

            switch (Choice) {
                case 1:
                    System.out.println("......DEPOSIT THE MONEY....");
                    Deposit(BankNumbers, DepositMoney);
                    break;
                case 2:
                    System.out.println("......WITHDRAW THE MONEY.....");
                    Withdraw(BankNumbers, WithdrawMoney);
                    break;
                case 3:
                    System.out.println("......VIEW THE MONEY BALANCE......");
                    ViewMoney(BankNumbers, DepositMoney);
                    break;
                case 4:
                    System.out.println("....EXIT....");
                    running = false;
                    break;
                default:
                    System.out.println("ENTER A VALID NUMBER!!!!!");
                    break;
            }
        }
    }
    // ==============================================================================================================
    public static void Deposit(ArrayList<String> BankNumbers, ArrayList<String> DepositMoney){
        Scanner input = new Scanner(System.in);
        boolean login = false;
        int newBalance = 0;

        while (!login) {
            System.out.print("Enter a Bank Number : ");
            String inputBankNumber = input.nextLine();

            try{
                BufferedReader reader = new BufferedReader(new FileReader(bankNumber));
                String bankNumber;
                while ((bankNumber = reader.readLine()) != null) {
                    BankNumbers.add(bankNumber);
                }
                reader.close();
                for(String Login : BankNumbers){
                    if(Login.equals(inputBankNumber)){
                        System.out.println("Valid Bank Number.....");
                        System.out.println("Bank Number : " + Login);
                        login = true;
                        
                        System.out.println("=".repeat(60));
                        System.out.print("How many Money You will deposit ? Rs:");
                        int inputDepositMoney = input.nextInt(); 
                        
                        

                        try{
                            BufferedReader reader1 = new BufferedReader(new FileReader(depositDetalis));
                            String deposit;
                            while ((deposit = reader1.readLine()) !=null) {
                                DepositMoney.add(deposit);
                            }
                            reader1.close();
                            for (int i = 0; i < DepositMoney.size(); i++) {
                                String Money = DepositMoney.get(i);
                                String[] DepositTheMoney = Money.split(" ");
                                if (DepositTheMoney.length == 2 && DepositTheMoney[0].equals(inputBankNumber)) {

                                    int getMoney = Integer.parseInt(DepositTheMoney[1]);
                                    newBalance = getMoney + inputDepositMoney;
                                    // DepositMoney.set(i, inputBankNumber + " " + newBalance);
                                    // System.out.println("New Balance: Rs " + newBalance);


                                }
                            }
                        BufferedWriter writer = new BufferedWriter(new FileWriter(depositDetalis,true));
                        // writer.append("=".repeat(60) +"\n");
                        writer.append(Login +" ");
                        writer.append(newBalance + "\n");
                        // writer.append("=".repeat(60));
                        writer.close();

                        }catch(IOException e){
                            System.out.println(e);
                        }

                        String art =
                        "\r\n" + //
                            "██████╗ ██████╗     ██████╗  █████╗ ███╗   ██╗██╗  ██╗██╗███╗   ██╗ ██████╗ \r\n" + //
                            "██╔══██╗██╔══██╗    ██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝██║████╗  ██║██╔════╝ \r\n" + //
                            "██║  ██║██████╔╝    ██████╔╝███████║██╔██╗ ██║█████╔╝ ██║██╔██╗ ██║██║  ███╗\r\n" + //
                            "██║  ██║██╔═══╝     ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ ██║██║╚██╗██║██║   ██║\r\n" + //
                            "██████╔╝██║         ██████╔╝██║  ██║██║ ╚████║██║  ██╗██║██║ ╚████║╚██████╔╝\r\n" + //
                            "╚═════╝ ╚═╝         ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝ \r\n" + //
                            "                                                                            \r\n" + //
                            "";

                        String tankArt = "\r\n" + //
                                                        "╔╦╗╦ ╦╔═╗╔╗╔╦╔═  ╦ ╦╔═╗╦ ╦\r\n" + //
                                                        " ║ ╠═╣╠═╣║║║╠╩╗  ╚╦╝║ ║║ ║\r\n" + //
                                                        " ╩ ╩ ╩╩ ╩╝╚╝╩ ╩   ╩ ╚═╝╚═╝\r\n" + //
                                                        "";

                        
                        LocalDateTime dateAndTIme = LocalDateTime.now();
                        DateTimeFormatter dateAndTimeFormate = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
                        String formatte = dateAndTIme.format(dateAndTimeFormate);

                        try{
                            BufferedWriter writer = new BufferedWriter(new FileWriter(DepositBill));
                            writer.write(art);
                            writer.write("=".repeat(60) +"\n");
                            writer.write("Date And Time : " + formatte + "\n");
                            writer.write("Your Bank Number 🪪 :" + Login +"\n");
                            writer.write("Deposit Balace Rs: " + inputDepositMoney + "\n");
                            writer.write("Currently Balance💵 Rs: "+ Integer.toString(newBalance) +"\n");
                            writer.write("=".repeat(60));
                            writer.write(tankArt);
                            writer.close();

                        }catch(IOException e){
                            System.out.println(e);
                        }

                        System.out.println(art);
                        System.out.println("=".repeat(60));
                        System.out.println("Date And Time : " + formatte);
                        System.err.println("Your Bank Number 🪪 :" + Login );
                        System.out.println("Deposit Balance Rs: " + inputDepositMoney);
                        System.out.println("Currently Balance💵 Rs: "+ Integer.toString(newBalance));
                        System.out.println("=".repeat(60));
                        System.out.println("Successfully Deposit the Money....");
                        break;
                    }
                }
                if(!login){
                    System.out.println("Invalid Bank Number Check again!!!");
                }
            }catch(IOException e){
                System.out.println(e);
            }
            
        }
        
    }
    // ===============================================================================================================================
    public static void Withdraw(ArrayList<String> BankNumbers,ArrayList<String> WithdrawMoney){
        Scanner input = new Scanner(System.in);
        boolean login = false;
        int newBalance = 0;
        LocalDateTime dateAndTIme = LocalDateTime.now();
        DateTimeFormatter dateAndTimeFormate = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        String formatte = dateAndTIme.format(dateAndTimeFormate);

        String welcomeArt = "\r\n" + //
                        "██████  ██████      ██████   █████  ███    ██ ██   ██ ██ ███    ██  ██████  \r\n" + //
                        "██   ██ ██   ██     ██   ██ ██   ██ ████   ██ ██  ██  ██ ████   ██ ██       \r\n" + //
                        "██   ██ ██████      ██████  ███████ ██ ██  ██ █████   ██ ██ ██  ██ ██   ███ \r\n" + //
                        "██   ██ ██          ██   ██ ██   ██ ██  ██ ██ ██  ██  ██ ██  ██ ██ ██    ██ \r\n" + //
                        "██████  ██          ██████  ██   ██ ██   ████ ██   ██ ██ ██   ████  ██████  \r\n" + //
                        "                                                                            \r\n" + //
                        "                                                                            \r\n" + //
                        "";
        String tankMassage = "\r\n" + //
                        "┏┳┓┓┏┏┓┳┓┓┏┓  ┓┏┏┓┳┳\r\n" + //
                        " ┃ ┣┫┣┫┃┃┃┫   ┗┫┃┃┃┃\r\n" + //
                        " ┻ ┛┗┛┗┛┗┛┗┛  ┗┛┗┛┗┛\r\n" + //
                        "                    \r\n" + //
                        "";

        while (!login) {
            System.out.print("Enter Your Bank Number : ");
            String inputBankNumber = input.nextLine();

            try{
                BufferedReader reader = new BufferedReader(new FileReader(bankNumber));
                String bankNum;
                while ((bankNum = reader.readLine()) !=null) {
                    BankNumbers.add(inputBankNumber);    
                }
                reader.close();
                for(String Login : BankNumbers){
                    if(Login.equals(inputBankNumber)){
                        System.out.println("Valid Bank Number.....");
                        System.out.println("Bank Number : " + Login);
                        login = true;

                        System.out.println("=".repeat(60));
                        System.out.print("How Mach Money Do You Want TO Withdraw ? Rs");
                        int inputWithdrawMoney = input.nextInt();

                        try{
                            BufferedReader reader1 = new BufferedReader(new FileReader(depositDetalis));
                            String withdraw;
                            while ((withdraw = reader1.readLine()) != null) {
                                WithdrawMoney.add(withdraw);
                            }
                            reader1.close();
                            for(int i = 0; i < WithdrawMoney.size(); i++){
                                String Money = WithdrawMoney.get(i);
                                String [] WithdraThewMoney = Money.split(" ");
                                if(WithdraThewMoney.length == 2 && WithdraThewMoney[0].equals(inputBankNumber)){
                                    int getMoney = Integer.parseInt(WithdraThewMoney[1]);
                                    newBalance = getMoney - inputWithdrawMoney;
                                    // System.out.println("New Balance : Rs" + newBalance);
                                }
                            }
                        BufferedWriter writer = new BufferedWriter(new FileWriter(depositDetalis,true));
                        writer.append(Login +" ");
                        writer.append(newBalance + "\n");
                        writer.close();

                        }catch(IOException e){
                            System.out.println(e);
                        }
                        BufferedWriter writer = new BufferedWriter(new FileWriter(WithdrawBill));
                        writer.write(welcomeArt);
                        writer.write("=".repeat(60) + "\n");
                        writer.write("Date And Time : " + formatte + "\n");
                        writer.write("Your Bank Number : " + Login + "\n");
                        writer.write("Withdraw Balance : Rs" + inputWithdrawMoney + "\n");
                        writer.write("Currently Balance💵 Rs:" + newBalance + "\n");
                        writer.write("=".repeat(60) + "\n");
                        writer.write(tankMassage);
                        writer.close();

                        System.out.println(welcomeArt);
                        System.out.println("=".repeat(60));
                        System.out.println("Date And Time : " + formatte);
                        System.out.println("Your Bank Number : " + Login);
                        System.out.println("Withdraw Balance : Rs" + inputWithdrawMoney);
                        System.out.println("Currently Balance💵 Rs:" + newBalance);
                        System.out.println("=".repeat(60));
                    
                        break;
                        
                    }
                }
            }catch(IOException e){
                System.out.println(e);
            }
            
        }
    }
    public static void ViewMoney(ArrayList<String> BankNumbers,  ArrayList<String> DepositMoney){
        System.out.println("\r\n" + //
                        "██╗   ██╗██╗███████╗██╗    ██╗    ██████╗  █████╗ ██╗      █████╗ ███╗   ██╗ ██████╗███████╗\r\n" + //
                        "██║   ██║██║██╔════╝██║    ██║    ██╔══██╗██╔══██╗██║     ██╔══██╗████╗  ██║██╔════╝██╔════╝\r\n" + //
                        "██║   ██║██║█████╗  ██║ █╗ ██║    ██████╔╝███████║██║     ███████║██╔██╗ ██║██║     █████╗  \r\n" + //
                        "╚██╗ ██╔╝██║██╔══╝  ██║███╗██║    ██╔══██╗██╔══██║██║     ██╔══██║██║╚██╗██║██║     ██╔══╝  \r\n" + //
                        " ╚████╔╝ ██║███████╗╚███╔███╔╝    ██████╔╝██║  ██║███████╗██║  ██║██║ ╚████║╚██████╗███████╗\r\n" + //
                        "  ╚═══╝  ╚═╝╚══════╝ ╚══╝╚══╝     ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝╚══════╝\r\n" + //
                        "                                                                                            \r\n" + //
                        "");
        Scanner input = new Scanner(System.in);
        boolean login = false;

        while (!login) {
            System.out.print("Enter a Bank Number : ");
            String inputBankNumber = input.nextLine();

            try{
                BufferedReader reader = new BufferedReader(new FileReader(bankNumber));
                String bankNumber;
                while ((bankNumber = reader.readLine()) != null) {
                    BankNumbers.add(bankNumber);
                }
                reader.close();
                for(String Login : BankNumbers){
                    if(Login.equals(inputBankNumber)){
                        System.out.println("Valid Bank Number.....");
                        System.out.println("Bank Number : " + Login);
                        login = true;

                        try{
                            BufferedReader reader1 = new BufferedReader(new FileReader(depositDetalis));
                            String deposit;
                            while ((deposit = reader1.readLine()) !=null) {
                                DepositMoney.add(deposit);
                            }
                            reader1.close();
                            for (int i = 0; i < DepositMoney.size(); i++) {
                                String Money = DepositMoney.get(i);
                                String[] DepositTheMoney = Money.split(" ");
                                if (DepositTheMoney.length == 2 && DepositTheMoney[0].equals(inputBankNumber)) {
                                    int getMoney = Integer.parseInt(DepositTheMoney[1]);
                                    System.out.println("Here A Your a Deposit And Withdraw Money 👇👇");
                                    System.out.println(getMoney);
                                }
                            }
                        }catch(IOException e){
                            System.out.println(e);
                        }


                        break;
                    }
                }
            }catch(IOException e){
                System.out.println(e);
            }
        }


    }
    public static void main(String[] args) throws Exception {
        ArrayList<String> BankNumbers = new ArrayList<>(); 
        ArrayList<String> UserDetalis = new ArrayList<>();
        ArrayList<Integer> PhoneNumber = new ArrayList<>();
        ArrayList<Integer> ArrayBankNumber = new ArrayList<>();
        ArrayList<String> DepositMoney = new ArrayList<>();
        ArrayList<String> WithdrawMoney = new ArrayList<>();

        String art =
        "\r\n" + //
            "██████╗ ██████╗     ██████╗  █████╗ ███╗   ██╗██╗  ██╗██╗███╗   ██╗ ██████╗ \r\n" + //
            "██╔══██╗██╔══██╗    ██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝██║████╗  ██║██╔════╝ \r\n" + //
            "██║  ██║██████╔╝    ██████╔╝███████║██╔██╗ ██║█████╔╝ ██║██╔██╗ ██║██║  ███╗\r\n" + //
            "██║  ██║██╔═══╝     ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ ██║██║╚██╗██║██║   ██║\r\n" + //
            "██████╔╝██║         ██████╔╝██║  ██║██║ ╚████║██║  ██╗██║██║ ╚████║╚██████╔╝\r\n" + //
            "╚═════╝ ╚═╝         ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝ \r\n" + //
            "                                                                            \r\n" + //
            "";

        System.out.println(art);
        Menu(UserDetalis, PhoneNumber, ArrayBankNumber, DepositMoney);
    }
}
