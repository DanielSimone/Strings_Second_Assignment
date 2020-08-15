
/**
 * First part of the second set of week 2 assignments
 * 
 * @Daniel Simone
 * @1.0
 */
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        // Define currIndex as the location of the first stop codon after the start codon
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        // As long as there is a stop codon,
        while (currIndex != -1) {
            // The length of the gene is the difference between the stop and start codon positions,
            int lengthOfGene = currIndex - startIndex;
            // If the length of the gene is a multiple of 3,
            if (lengthOfGene % 3 == 0) {
                // Return the location of the stop codon
                return currIndex;
            }
            // Else,
            else {
                // Make currIndex the position of the next stopCodon
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        // If there is no stop codon, return the length of the dna strand
        return dna.length();
    }
    
    public String findGene (String dna) {
        // Define ATGIndex as the position of the first ATG
        int ATGIndex = dna.indexOf ("ATG");
        // If there is no ATG,
        if (ATGIndex == -1) {
            // Return the empty string
            return "";
        }
        // Find the positions of the first TAA, TAG, and TGA after the first ATG
        int TAAIndex = findStopCodon (dna, ATGIndex, "TAA");
        int TAGIndex = findStopCodon (dna, ATGIndex, "TAG");
        int TGAIndex = findStopCodon (dna, ATGIndex, "TGA");
        // Find the minimum of the TAA and TAG indices
        int TAATAGMinimum = Math.min (TAAIndex, TAGIndex);
        // Find the minimum between the minimum of the TAA and TAG indices as well as the TGA index
        int AllMinimum = Math.min (TAATAGMinimum, TGAIndex);
        // If the minimum returns the length of DNA (signifying no stop index),
        if (AllMinimum == dna.length()) {
            // Return the empty string
            return "";
        }
        // Otherwise, return the gene including the ATG and stop codons
        return dna.substring (ATGIndex, AllMinimum + 3);
    }
    
    public void testFindStopCodon() {
        //            012345678901234567890123456
        String dna = "ACTGTAGCATGCCCTTTAAAGGGCTAA";
        int result = findStopCodon(dna, 0, "TAA");
        if (result != 24) {System.out.println("Did not find TAA on index 24");}
        result = findStopCodon(dna, 1, "TAA");
        if (result != 16) {System.out.println("Did not find TAA on index 16)");}
        System.out.println("Completed tests of FindStopCodon");
    }
    
    public void testFindGene() {
        //            012345678901234567890123456789012345678
        String dna = "CCCATGGGGTTTCCCAAAAAATTTTGACCCTAGAAATAA";
        System.out.println (dna);
        String gene = findGene(dna);
        System.out.println (gene);
        if (! gene.equals("ATGGGGTTTCCCAAAAAATTTTGA")) {
            System.out.println ("Your code is bugged chief");
        }
        System.out.println ("Completed tests of FindGene");
    }
}
