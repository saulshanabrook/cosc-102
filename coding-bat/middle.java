
class middle {
  public static boolean xyzMiddle(String str) {
    String target = "xyz";
    int index;
    for (index = 0; index < (str.length() - target.length()); index++) {
      String stringToCheck = str.substring(index, index + target.length());
      System.out.println(stringToCheck);

      if (stringToCheck.equals(target)) {
        int charsOnLeft = index;
        int charsOnRight = str.length() - index - target.length();
        System.out.println(Math.abs(charsOnLeft - charsOnRight) );

        if (Math.abs(charsOnLeft - charsOnRight) < 2) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    String a = "xyz";
    System.out.println(xyzMiddle(a));
  }

}
