
/**
 * Second part of the second set of week 2 assignments
 * 
 * @Daniel Simone
 * @1.0
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        // Start with zero for the total occurrences of stringa
        int totalMany = 0;
        // Find the first occurrence of stringa
        int oneMany = stringb.indexOf(stringa);
        // While there is one occurrence and there are more occurrences after the first,
        while (oneMany != -1 && stringb.indexOf(stringa, oneMany) > -1) {
            // Add one to the total count
            totalMany = totalMany + 1;
            // Update the position of the occurrence to the next occurrence after the previous
            oneMany = stringb.indexOf(stringa, oneMany + stringa.length());
            }
        // Return the total count
        return totalMany;
        }
    
    public void testHowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println(howMany(stringa, stringb));
        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println(howMany(stringa, stringb));
        stringa = "B";
        stringb = "CCCCCCC";
        System.out.println(howMany(stringa, stringb));
    }
    }
