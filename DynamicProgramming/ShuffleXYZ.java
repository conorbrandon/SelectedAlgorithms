public class ShuffleXYZ {
    public static void main(String[] args) {
        String x = "chocolate";
        String y = "chips";
        String z = "chocochilatspe";
        System.out.println(checkValidShuffle(x, y, z));
    }
    public static boolean checkValidShuffle(String x, String y, String z) {
        int j = 0;
        int k = j;
        for (int i = 0; i < z.length(); i++) {
            char c = z.charAt(i);
            if(c == x.charAt(j)) {if (j < x.length()-1) j++;}
            else if(c == y.charAt(k)) {if (k < y.length()-1) k++;}
        }
        if(j + 1 == x.length() && k + 1 == y.length()) return true;
        else return false;
    }
}
