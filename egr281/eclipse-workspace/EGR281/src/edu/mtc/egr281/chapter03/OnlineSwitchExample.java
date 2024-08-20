package edu.mtc.egr281.chapter03;

public class OnlineSwitchExample {
	
    public static void main(String[] args) {

        int month = 4;
        String monthString;
        switch (month) {
            case 1:  monthString = "January";
                   
            case 2:  monthString = "February";
                     
            case 3:  monthString = "March";
                     
            case 4:  monthString = "April";
                     
            case 5:  monthString = "May";
                    
            case 6:  monthString = "June";
                     break;
            case 7:  monthString = "July";
                     break;
            case 8:  monthString = "August";
                     break;
            case 9:  monthString = "September";
                     break;
            case 10: monthString = "October";
                     break;
            case 11: monthString = "November";
                     break;
            case 12: monthString = "December";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        System.out.println(monthString);
    }
}
	
