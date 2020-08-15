
/**
 * Third part of the second set of week 2 assignments
 * 
 * @Daniel Simone
 * @1.0
 */
public class Part3 {
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
    
    public String findGene (String dna, int start) {
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
    
    public int countGenes(String dna) {
        // Start with zero for the starting index
        int startIndex = 0;
        // Start with zero genes counted
        int totalGenes = 0;
        // While the following is true,
        while (true) {
            // The current gene is the first gene found after startIndex
            String currGene = findGene(dna, startIndex);
            // If there are no more genes,
            if (currGene.isEmpty()) {
                // Break the loop
                break;
            }
            // Add one to the gene total
            totalGenes = totalGenes + 1;
            // The startIndex will be updated to just after the current gene
            startIndex = startIndex + currGene.length();
            }
        return totalGenes;
        }
    
    public void testFindStopCodon() {
        //            012345678901234567890123456
        String dna = "ACTGTAGCATGCCCTTTAAAGGGCTAA";
        int result = findStopCodon(dna, 0, "TAA");
        if (result != 24) {System.out.println("Did not find TAA on index 24");}
        result = findStopCodon(dna, 1, "TAA");
        if (result != 16) {System.out.println("Did not find TAA on index 16)");}
        System.out.println ("Completed tests of FindStopCodon");
    }
    
    public void testCountGenes() {
        //            012345678901234567890123456789012345678
        String dna = "CCCATGGGGTAGCCCTAAATGTTTTGACCCATGAAATAA";
        System.out.println (dna);
        int geneCount = countGenes(dna);
        System.out.println(geneCount);
        if (geneCount != 3) {
            System.out.println ("Your code is bugged chief");
        }
        System.out.println ("Completed tests of FindGene");
    }
}
