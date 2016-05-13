package com.firebox80.myapplication4;

/**
 * Created by user on 28.09.2015.
 */
public class MyPsw {
    private char randomChar;
    private int randomCodeLower;
    private int randomCodeUpper;
    private int randomCodeNumber;
    private int randomCodeSymbol;
    private int selectLetter;
    int passLength = 6;
    char passwordChars[];

    void lowerCase(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {
            randomCodeLower = (int) ( Math.random() * 26) + 97;
            randomChar = (char)randomCodeLower;

            passwordChars[i] = randomChar;
        }
    }

    void upperCase(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            randomCodeUpper = (int) ( Math.random() * 26) + 65;
            randomChar = (char)randomCodeUpper;

            passwordChars[i] = randomChar;
        }
    }

    void letters(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 2);

            if (selectLetter == 1){
                randomCodeLower = (int) ( Math.random() * 26) + 97;
                randomChar = (char)randomCodeLower;
            } else {
                randomCodeUpper = (int) ( Math.random() * 26) + 65;
                randomChar = (char)randomCodeUpper;
            }
            passwordChars[i] = randomChar;
        }
    }


    void lowerCaseDigit(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 2);

            if (selectLetter == 1){
                randomCodeLower = (int) ( Math.random() * 26) + 97;
                randomChar = (char)randomCodeLower;
            } else {
                randomCodeNumber = (int) ( Math.random() * 10) + 48;
                randomChar = (char)randomCodeNumber;
            }
            passwordChars[i] = randomChar;
        }
    }

    void upperCaseDigit(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 2);

            if (selectLetter == 1){
                randomCodeUpper = (int) ( Math.random() * 26) + 65;
                randomChar = (char)randomCodeUpper;
            } else {
                randomCodeNumber = (int) ( Math.random() * 10) + 48;
                randomChar = (char)randomCodeNumber;
            }
            passwordChars[i] = randomChar;
        }
    }

    void digit(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 3);

            if (selectLetter == 1){
                randomCodeLower = (int) ( Math.random() * 26) + 97;
                randomChar = (char)randomCodeLower;
            } else if (selectLetter == 2){
                randomCodeUpper = (int) ( Math.random() * 26) + 65;
                randomChar = (char)randomCodeUpper;
            } else {
                randomCodeNumber = (int) ( Math.random() * 10) + 48;
                randomChar = (char)randomCodeNumber;
            }
            passwordChars[i] = randomChar;
        }
    }

    void lowerCaseSymbol(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 2);

            if (selectLetter == 1){
                randomCodeLower = (int) ( Math.random() * 26) + 97;
                randomChar = (char)randomCodeLower;
            } else {
                randomCodeSymbol = (int) ( Math.random() * 15) + 33;
                randomChar = (char)randomCodeSymbol;
            }
            passwordChars[i] = randomChar;
        }
    }

    void upperCaseSymbol(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 2);

            if (selectLetter == 1){
                randomCodeUpper = (int) ( Math.random() * 26) + 65;
                randomChar = (char)randomCodeUpper;
            } else {
                randomCodeSymbol = (int) ( Math.random() * 15) + 33;
                randomChar = (char)randomCodeSymbol;
            }
            passwordChars[i] = randomChar;
        }
    }

    void symbol(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 3);

            if (selectLetter == 1){
                randomCodeLower = (int) ( Math.random() * 26) + 97;
                randomChar = (char)randomCodeLower;
            } else if (selectLetter == 2){
                randomCodeUpper = (int) ( Math.random() * 26) + 65;
                randomChar = (char)randomCodeUpper;
            } else {
                randomCodeSymbol = (int) ( Math.random() * 15) + 33;
                randomChar = (char)randomCodeSymbol;
            }
            passwordChars[i] = randomChar;
        }
    }

    void lowerCaseHeavy(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 3);

            if (selectLetter == 1){
                randomCodeLower = (int) ( Math.random() * 26) + 97;
                randomChar = (char)randomCodeLower;
            } else if (selectLetter == 2){
                randomCodeNumber = (int) ( Math.random() * 10) + 48;
                randomChar = (char)randomCodeNumber;
            } else {
                randomCodeSymbol = (int) ( Math.random() * 15) + 33;
                randomChar = (char)randomCodeSymbol;
            }
            passwordChars[i] = randomChar;
        }
    }

    void upperCaseHeavy(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 3);

            if (selectLetter == 1){
                randomCodeUpper = (int) ( Math.random() * 26) + 65;
                randomChar = (char)randomCodeUpper;
            } else if (selectLetter == 2){
                randomCodeNumber = (int) ( Math.random() * 10) + 48;
                randomChar = (char)randomCodeNumber;
            } else {
                randomCodeSymbol = (int) ( Math.random() * 15) + 33;
                randomChar = (char)randomCodeSymbol;
            }
            passwordChars[i] = randomChar;
        }
    }

    void heavy(){
        passwordChars = new char [passLength];

        for (int i = 0; i < passwordChars.length; i++) {

            selectLetter = (int)( Math.random() * 4);

            if (selectLetter == 1){
                randomCodeLower = (int) ( Math.random() * 26) + 97;
                randomChar = (char)randomCodeLower;
            } else if (selectLetter == 2){
                randomCodeUpper = (int) ( Math.random() * 26) + 65;
                randomChar = (char)randomCodeUpper;
            } else if (selectLetter == 3){
                randomCodeNumber = (int) ( Math.random() * 10) + 48;
                randomChar = (char)randomCodeNumber;
            } else {
                randomCodeSymbol = (int) ( Math.random() * 15) + 33;
                randomChar = (char)randomCodeSymbol;
            }
            passwordChars[i] = randomChar;
        }
    }
}
