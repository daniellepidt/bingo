package Bingo;
import java.util.*;
import java.util.concurrent.TimeUnit;
class Bingo{  // מחלקת עצם מסוג בינגו
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    Random rnd = new Random();
    String BingoTable [][]= new String[5][5]; // בניית טבלת בינגו
    Bingo(){ //פעולת הבנאי של הבינגו
        for (int a=0; a<5; a++)
            for (int b=0; b<5; b++) {
                if(a==0){ // עבור עמודה 1 יהיו ערכים 1-15
                    BingoTable[b][a] = ""+(rnd.nextInt(15) + 1);
                    if(Integer.parseInt(BingoTable[b][a])<10) // הזחה
                        BingoTable[b][a]=" "+BingoTable[b][a]+"";
                }
                else if(a==1) // עבור עמודה 2 יהיו ערכים 16-30
                    BingoTable[b][a] = ""+(rnd.nextInt(15) + 16);
                else if(a==2) // עבור עמודה 3 יהיו ערכים 31-45
                    BingoTable[b][a] = ""+(rnd.nextInt(15) + 31);
                else if(a==3) // עבור עמודה 4 יהיו ערכים 46-60
                    BingoTable[b][a] = ""+(rnd.nextInt(15) + 46);
                else if(a==4) // עבור עמודה 5 יהיו ערכים 61-75
                    BingoTable[b][a] = ""+(rnd.nextInt(15) + 61);
            }
        BingoTable[2][2]="**"; // Free Space
    }
    void markerPlayer (int a, int b){BingoTable[a][b]=ANSI_RED+BingoTable[a][b]+ANSI_RESET;} // סימון מספר שהוגרל ויש בטבלה לשחקן
    void markerComputer (int a){ // סימון מספר שהוגרל ויש בטבלה למחשב
        while(true){
            if(a==22) // אם זה התיבה שבה יש רווח חופשי אז שלא יבדוק האם אפשר לסמן מספר
                break;
            int b=a%10;
            a=a/10;
            BingoTable[a][b]=""+0; // במקום לצבוע את הערכים אז שמים 0 כדי שיהיה יותר קל לבדוק האם יש בינגו
            break;
        }
    }
    int checker (int random) { // בדיקה האם יש משהו לסמן
        int c, d;
        for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 5; b++) {
                if (Integer.toString(random) == BingoTable[a][b]) {
                    c = a * 10;
                    d = b;
                    return (c + d);
                }
            }
        }
        c = 2*10; // אם אין משהו לסמו, נרשום שזו תיבה 2,2 כדי שלפעולה יהיה פעולת החזרה
        d = 2;
        return (c + d);
    }
    boolean isBingo (){ // פעולת בדיקה למחשב האם יש לו בינגו
        int counterRow []= new int[5];
        int counterColumn[]= new int[5];
        for (int a = 0; a < 5; a++)
            for (int b = 0; b < 5; b++)
                if(BingoTable[a][b]=="0")
                    counterRow[a]++;
        for (int a = 0; a < 5; a++)
            for (int b = 0; b < 5; b++)
                if(BingoTable[b][a]=="0")
                    counterColumn[a]++;
        for (int a=0; a<5; a++) // בדיקה עבור שורה
            if(counterRow[a]==5)
                return (true);
        for (int a=0; a<5; a++) // בדיקה עבור טור
            if (counterColumn[a]==5)
                return (true);
        return false; // אם אין בינגו, יחזיר שקר
    }
    void toString1() { // הדפסת טבלת הבינגו
        for(int r=0; r<BingoTable.length; r++) {
            for(int c=0; c<BingoTable[r].length; c++)
                System.out.print(BingoTable[r][c] + " ");
            System.out.println();
        }
    }
}
public class Main { // מחלקה ראשית
    public static Scanner in= new Scanner(System.in);
    public static void print(String strToPrint){System.out.println(strToPrint);} // פעולה סטטית להדפסה
    public static void main(String[] args) throws InterruptedException {
        String BINGO[] = {"B", "I", "N", "G", "O"}; // בניית כותרת הטבלת בינגו
        int choice1, choice2, randomNum;
        int choiceRow, choiceColumn ,choiceBingo;
        Random rndFoRaffle = new Random();
        int arrNumbers[] = new int[75]; // בניית מערך לכל הערכים מ1-75
        for (int i = 0; i < 75; i++)
            arrNumbers[i] = i + 1;
        Bingo BingoPlayer = new Bingo(); // בניית טבלת בינגו לשחקן
        print("Welcome to my BINGO! game.\nThe rules are very simple:\nAfter that the game begins, and the computer selects numbers at a random and calls them. As soon as the computer calls one number, all the players mark it on their tickets.\nThe winner is determined when one or several of the players complete the winning bingo pattern. It can be a line, either vertical or horizontal.");
        print("In this game, The system will let you put the number of the row and the column you want to mark, and if you have nothing to mark, put '2'. Moreover, the system will ask you is you have a bingo."); // הסבר על מהלך המשחק וחוקי המשחק
        print("Choose how to play (put the number of your choice):\n\t(1) with the computer.\n\t(2) with you friend.");
        choice1 = in.nextInt(); // בחירת צורת משחק
        if (choice1 == 1) { // משחק עם מחשב
            Bingo BingoComputer = new Bingo(); // בניית טבלת בינגו למחשב
            for (int i = 0; i < 75; i++) {
                randomNum=arrNumbers[rndFoRaffle.nextInt(75)];
                print("The number is: " + randomNum);
                print("\nIt's your turn. Your table:\n" + Arrays.toString(BINGO));
                BingoPlayer.toString1();
                print("Do you want to mark? choose the number:\n\t(1) Yes.\n\t(2) No."); // האם השחקן צריך לסמן מספר?
                choice2 = in.nextInt();
                if (choice2 == 1) { // השחקן בחר לסמן
                    print("Enter the number of the row:(1-5)");
                    choiceRow = in.nextInt();
                    print("Enter the number of the column:(1-5)");
                    choiceColumn = in.nextInt();
                    BingoPlayer.markerPlayer(choiceRow-1, choiceColumn-1);
                    print("Do you have a bingo? Enter the number: \n(1) Yes. \n(2) No."); // האם יש לשחקן בינגו?
                    choiceBingo= in.nextInt();
                    if (choiceBingo==1) {
                        print("You won!!!");
                        break;
                    }
                }
                print("\nIt's computer's turn.\n"); // התור של המחשב
                BingoComputer.markerComputer(BingoComputer.checker(randomNum)); // בדיקה האם אפשר לסמן למחשב
                if (BingoComputer.isBingo() == true) { // בדיקה האם למחשב יש בינגו
                    print("The computer won. Game over.");
                    break;
                }
                TimeUnit.SECONDS.sleep(1);
            }

        }
        if (choice1 == 2) { // משחק עם חבר
            String name1, name2;
            Bingo BingoPlayer2 = new Bingo();
            print("Player 1:\n\tPlease enter your name:"); // שם שחקן 1
            name1 = in.next();
            print("Player 2:\n\tPlease enter your name:"); // שם שחקן 2
            name2 = in.next();
            for (int i = 0; i < 75; i++) { // בניית מערך לכל הערכים מ
                randomNum=arrNumbers[rndFoRaffle.nextInt(75)];
                print("The number is: " + randomNum);
                print("\nIt's " + name1 + " turn. Your table:\n" + Arrays.toString(BINGO)); // הדפסת הבינגו של שחקן 1
                BingoPlayer.toString1();
                print("Do you want to mark? choose the number:\n\t(1) Yes.\n\t(2) No."); // האם השחקן צריך לסמן מספר
                choice2 = in.nextInt();
                if (choice2 == 1) {
                    print("Enter the number of the row:(1-5)");
                    choiceRow = in.nextInt();
                    print("Enter the number of the column:(1-5)");
                    choiceColumn = in.nextInt();
                    BingoPlayer.markerPlayer(choiceRow-1, choiceColumn-1);
                    print("Do you have a bingo? Enter the number: \n(1) Yes. \n(2) No."); // האם יש לשחקן בינגו?
                    choiceBingo= in.nextInt();
                    if (choiceBingo==1) {
                        print("You won!!!");
                        break;
                    }
                }
                print("The number is: " + randomNum);
                print("\nIt's " + name2 + " turn. Your table:\n" + Arrays.toString(BINGO)); // הדפסת הבינגו של שחקן 2
                BingoPlayer2.toString1();
                print("Do you want to mark? choose the number:\n\t(1) Yes.\n\t(2) No."); // האם השחקן צריך לסמן מספר
                choice2 = in.nextInt();
                if (choice2 == 1) {
                    print("Enter the number of the row:(1-5)");
                    choiceRow = in.nextInt();
                    print("Enter the number of the column:(1-5)");
                    choiceColumn = in.nextInt();
                    BingoPlayer2.markerPlayer(choiceRow-1, choiceColumn-1);
                    print("Do you have a bingo? Enter the number: \n(1) Yes. \n(2) No."); // האם יש לשחקן בינגו?
                    choiceBingo= in.nextInt();
                    if (choiceBingo==1) {
                        print("You won!!!");
                        break;
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
            }

        }
    }
}