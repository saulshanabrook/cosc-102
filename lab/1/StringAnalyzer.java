/*
   StringAnalyzer.java

   A program that prints out information about character occurrences in strings.

 */

import java.util.Scanner;  // need this for getting console input
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;


public class StringAnalyzer {

 public static Character repeatedChar(String str) {

  for (int i = 0; i < str.length() - 1; i++) {
   char tryingChar = str.charAt(i);
   char nextChar = str.charAt(i + 1);
   if (tryingChar == nextChar) {
    return tryingChar;
   }
  }
  return null;

 }

 public static Character multipleChar(String str) {

  for (int i = 0; i < str.length() - 1; i++) {
   char tryingChar = str.charAt(i);
   int nextOfChar = str.indexOf(tryingChar, i + 1);
   if (nextOfChar != -1) {
    return tryingChar;
   }
  }
  return null;

 }

 public static int numberRepeatingGroups(String str) {

  int c = 0;
  Character lastRepeatedChar = null;
  for (int i = 0; i < str.length() - 1; i++) {
   char currentChar = str.charAt(i);
   if ((lastRepeatedChar == null) || (currentChar != lastRepeatedChar)) {
    if (currentChar == str.charAt(i+1)) {
     lastRepeatedChar = currentChar;
     c++;
    }
   }
  }
  return c;

 }

 public static Character mostCommon(String str) {
  if (str.isEmpty()) {
   return null;
  }

  // http://stackoverflow.com/a/21388654/907060
  // used linked has map, cause we want it orderedd by entry, so that
  // the first one put in will be the first out when we look for the largest
  LinkedHashMap<Character, Integer> charCounts = new LinkedHashMap<Character, Integer>();

  // first we want to fill up the hashmap with character counts

  for (int i = 0; i < str.length(); i++) {
   char currentChar = str.charAt(i);
   int currentCharCount = charCounts.containsKey(currentChar) ? charCounts.get(currentChar) : 0;
   charCounts.put(currentChar, currentCharCount + 1);
  }

  // now we wanna find the max counted value
  int maxCount = Collections.max(charCounts.values());


  // then we wanna loop through the map and return the first with that max
  // value

  // http://stackoverflow.com/a/46908/907060
  for (Map.Entry<Character, Integer> entry : charCounts.entrySet()) {
   if (entry.getValue() == maxCount) {
    return entry.getKey();
   }
  }

  // should never happen, but required for compilation
  return null;
 }

 public static void main(String[] args) {

  /* This part of the code gets a line of input from the console.
     The string entered on this line will be the one analyzed. */

  System.out.println("Enter some text");
  Scanner scan = new Scanner(System.in);  // This is the object used to get input from the console
  String s = scan.nextLine();  // This gets a line and saves it as the string s


  /* Task 1:

     Output the first repeated character.

   A character is _repeated_ if it occurs at least twice in adjacent positions.
   For example, 'l' is repeated in "hollow", but 'o' is not.

   This code will print the first repeated character on the first line of output
   or will print "none" if there is no repeated character.

  */

  System.out.println(repeatedChar(s) == null ? "none": repeatedChar(s));




  /* Task 2:

     Output the first multiple character.

   A character is a _multiple_ if it occurs at least twice in the string,
   not necessarily in adjacent positions.
   For example, both 'o' and 'l' are multiple in "hollow", but 'h' is not.

   This code should print the first multiple character on the second line of output
   or "none" if there is no multiple.

   */

  System.out.println(multipleChar(s) == null ? "none": multipleChar(s));

  /* Task 3:

     Output how many groups of repeated characters there are.

     For example, "mississippi!!!" has four such groups: ss, ss, pp and !!!.

     The output should always be a non-negative integer
     representing the number of groups.
   */

  System.out.println(numberRepeatingGroups(s));


  /* Task 4:

     Output the most frequently occurring character in the string.
     If there is a tie, then output the character that appears first.

     The fourth line of output should be the first character with highest frequency.
     It can be "none" if the string is empty.

   */

 System.out.println(mostCommon(s) == null ? "none": mostCommon(s));



 } // end of main

} // end of class
