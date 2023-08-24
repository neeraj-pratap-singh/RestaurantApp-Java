import java.io.InputStream;
import java.util.Scanner;

public class menu {

 public static void main(String[] args) {
    Scanner scanner = scanner(System.in);
    
    System.out.println(" Welcome to Quick Bite Restaurant ");
    int Choice = 0;

    switch (Choice) {
        case 1:
            MenuBrowsing;
            break;
        case 2:
            orderPlacement;
            break;
        case 3:
            feedbackAndReview;
            break;        
    
        default:
            break;
    }

 }

private static Scanner scanner(InputStream in) {
    return null;
}
}