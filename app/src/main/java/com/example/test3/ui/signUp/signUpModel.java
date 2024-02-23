package com.example.test3.ui.signUp;public class signUpModel
{


    public boolean EmailCheck(String semail)
    {
        if (semail.length() ==0)
        {
            return  false;
        }
        //check if . and @ is not the first char or the last char
        if (semail.charAt(semail.length() - 1) == '.' || semail.charAt(semail.length() - 1) == '@' || semail.charAt(0) == '.' || semail.charAt(0) == '@') {
        return false;
    }
        int counter = 0;
        for (int i = 0; i < semail.length() - 2; i++) {
            //checks if . come after @
            if (semail.charAt(i) == '.' && semail.charAt(i + 1) == '@') {
                return false;
            }
            //count every @ symbol
            if (semail.charAt(i) == '@')
                counter++;
            //check if exist .com or .co.
            if (!semail.contains(".com") && !semail.contains(".co.")) {
                return false;
            }
            //check if the distance between . and @ is less than 3
            if (semail.indexOf(".") - semail.indexOf("@") <= 3) {
                return false;
            }

        }
        //check if @ exist more than one time
        if (counter != 1) {
            return false;
        }
        return true;
    }

}
