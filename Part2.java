import edu.duke.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int last = dna.indexOf(stopCodon, startIndex);
        if (last == -1 )
            return dna.length();
        return last;
    }
    public String findGene(String dna,int atgIndex) {
        if(atgIndex == -1)
            return "";
        int taaCodon = findStopCodon(dna, atgIndex, "TAA");
        int tagCodon = findStopCodon(dna, atgIndex, "TAG");
        int tgaCodon = findStopCodon(dna, atgIndex, "TGA");
        int minGene = dna.length();
        while (true) {
            if((taaCodon - atgIndex)%3 ==0 && taaCodon < minGene)
                minGene = taaCodon;
            if((tagCodon - atgIndex)%3 ==0 && tagCodon < minGene)
                minGene = tagCodon;
            if((tgaCodon - atgIndex)%3 ==0 && tgaCodon < minGene)
                minGene = tgaCodon;
            if(taaCodon == dna.length() && tagCodon == dna.length() && tgaCodon == dna.length())
                return "";
            if(minGene != dna.length())
                break;
            if(taaCodon != dna.length())
                taaCodon= findStopCodon(dna,taaCodon +1, "TAA");
            if(tagCodon != dna.length())
                tagCodon= findStopCodon(dna,tagCodon +1, "TAG");
            if(tgaCodon != dna.length())
                tgaCodon= findStopCodon(dna,tgaCodon +1, "TGA");
        }
        return dna.substring(atgIndex,minGene+3);
    }
    public void getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = dna.indexOf("ATG");
        int atg = 0;
        int geneL = 0;
        while (true) {
            atg = dna.indexOf("ATG", startIndex);
            if(atg == -1)
                break;
            String gene = findGene(dna, atg);
            if(!gene.isEmpty()){
                sr.add(gene);
                geneL = gene.length();
            }
            else
                geneL= 5;
            startIndex = atg + geneL - 4;
        }
        System.out.println(sr.data());
        genesData(sr);
    }
    public void caller() {
        /*FileResource fr = new FileResource("brca1line.fa");
        String frr = fr.asString();
        String frrr = frr.toUpperCase();
        getAllGenes(frrr);*/
        URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String urr = ur.asString();
        String urrr = urr.toUpperCase();
        getAllGenes(urrr);
    }
    public String longerThan60(String gene){
        if(gene.length() > 60)
            return gene;
        return "";
    }
    public double cgRatio(String dna) {
        int cIndex = dna.indexOf("C");
        int c= 0;
        int gIndex = dna.indexOf("G");
        int g= 0;
        int cCounter = 0;
        int gCounter = 0;
        while (true) {
            c = dna.indexOf("C", cIndex);
            if(c==-1)
                break;
            cCounter = cCounter +1;
            cIndex = c +1;
        }
        while (true) {
            g = dna.indexOf("G", gIndex);
            if(g==-1)
                break;
            gCounter++;
            gIndex = g +1;
        }
        double realC = cCounter + gCounter + 0.0;
        return (realC/dna.length());
    }
    public int ctgCounter(String gene) {
        int startIndex = gene.indexOf("CTG");
        int ctg = 0;
        int ctgCount = 0;
        while (true) {
            ctg = gene.indexOf("CTG",startIndex);
            if(ctg == -1)
                break;
            ctgCount++;
            startIndex = ctg+1;
        }
        return ctgCount;
    }
    public void genesData(StorageResource sr){
        int geneCounter = 0;
        int longCounter =0;
        int ratioCounter =0;
        StorageResource longSr = new StorageResource();
        StorageResource ratioSr = new StorageResource();
        int ctgCount = 0;
        int longest = 0;
        for (String gene : sr.data()) {
            geneCounter++;
            if(!longerThan60(gene).isEmpty()){
                longCounter++;
                longSr.add(gene);
            }
            if( ((double)cgRatio(gene)) > 0.35) {
             ratioCounter++;
             ratioSr.add(gene);
            }
            ctgCount = ctgCount + ctgCounter(gene);
            if (gene.length() > longest){
                longest = gene.length();
            }
        }
        System.out.println("The number of genes in the dna is: " + geneCounter +
                            ".\nThe number of genes which are longer than 60 is: " + longCounter +
                            ".\nThe number of gene with a high ratio is: " + ratioCounter +
                            ".\nThe number of ctg codons is: " + ctgCount + 
                            ".\nThe length of the longes gene is: " + longest + ".\n");
    }
    public void youtube() {
       URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
       String url ="";
       int startIndex = 0;
       int stopIndex = 0;
       for(String word : ur.lines()) {
           String lower = word.toLowerCase();
           startIndex = lower.indexOf("youtube");
           if(startIndex != -1) {
           stopIndex = lower.indexOf("\"",startIndex +1);
           url = url + word.substring(startIndex, stopIndex) + "\n";
           }
       }
       System.out.println(url);
    }
}
