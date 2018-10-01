import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int first = dna.indexOf("ATG",startIndex);
        if(first == -1)
            return dna.length();
        int last = dna.indexOf(stopCodon, first);
        if (last == -1 || (last-first)%3 != 0)
            return dna.length();
        return last;
    }
    public void testFindStopCodon(){
        int startIndex = 0;
        String dna = "HKMKCATGBDESVPTAAIOP";
        int gene = findStopCodon(dna, startIndex, "TAA");
        if(gene == dna.length())
            System.out.println("ERROR 1");
        dna = "HKMKCATGDESBVPTAGIOP";
        gene = findStopCodon(dna, startIndex, "TAG");
        if(gene == dna.length())
            System.out.println("ERROR 2");
        dna = "HKMKCATGBDESVPTGAIOP";
        gene = findStopCodon(dna, startIndex, "TGA");
        if(gene == dna.length())
            System.out.println("ERROR 3");
        dna = "HKMKCATGBDEVTAAIOP";
        gene = findStopCodon(dna, startIndex, "TAA");
        if(gene != dna.length())
            System.out.println("ERROR 4");
        dna = "HKMKCATBBDEVTAAIOP";
        gene = findStopCodon(dna, startIndex, "TAA");
        if(gene != dna.length())
            System.out.println("ERROR 5");  
        dna = "HKMKCATGBDEVTBBAIOP";
        gene = findStopCodon(dna, startIndex, "TAA");
        if(gene != dna.length())
            System.out.println("ERROR 6");    
        System.out.println("Testing Completed!");
    }
    public String findGene(String dna,int atgIndex) {
        int atg = atgIndex;
        if (atg == -1) 
            return "";
        int taaCodon = findStopCodon(dna, atg, "TAA");
        int tagCodon = findStopCodon(dna, atg, "TAG");
        int tgaCodon = findStopCodon(dna, atg, "TGA");
        int minGene = 0;
        if(taaCodon == dna.length() || (tagCodon != -dna.length() && tagCodon < taaCodon))
            minGene  = tagCodon;
        else
            minGene = taaCodon;
        if(minGene == dna.length() ||(tgaCodon != dna.length() && tgaCodon < minGene))
            minGene = tgaCodon;
        if (minGene == dna.length())
            return "";
        return dna.substring(atg,(minGene +3));
    }
    public void testFindGene() {
        String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcsctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaagcagatgatgtttcctgtccacttctaaattcttgtcttagtgaaagtcctgttgttctacaatgtacacatgtaacaccacaaagagataagtcagtggtatgtgggagtttgtttcatacaccaaagtttgtgaagggtcgtcagacaccaaaacatatttctgaaagtctaggagctgaggtggatcctgatatgtcttggtcaagttctttagctacaccacccacccttagttctactgtgctcatagtcagaaatgaagaagcatctgaaactgtatttcctcatgatactactgctaatgtgaaaagctatttttccaatcatgatgaaagtctgaagaaaaatgatagatttatcgcttctgtgacagacagtgaaaacacaaatcaaagagaagctgcaagtcatggatttggaaaaacatcagggaattcatttaaagtaaatagctgcaaagaccacattggaaagtcaatgccaaatgtcctagaagatgaagtatatgaaacagttgtagatacctctgaagaagatagtttttcattatgtttttctaaatgtagaacaaaaaatctacaaaaagtaagaactagcaagactaggaaaaaaattttccatgaagcaaacgctgatgaatgtgaaaaatctaaaaaccaagtgaaagaaaaatactcatttgtatctgaagtggaaccaaatgatactgatccattagattcaaatgtagcaaatcagaagccctttgagagtggaagtgacaaaatctccaaggaagttgtaccgtctttggcctgtgaatggtctcaactaaccctttcaggtctaaatggagcccagatggagaaaatacccctattgcatatttcttcatgtgaccaaaatatttcagaaaaagacctattagacacagagaacaaaagaaagaaagattttcttacttcagagaattctttgccacgtatttctagcctaccaaaatcagagaagccattaaatgaggaaacagtggtaaataagagagatgaagagcagcatcttgaatctcatacagactgcattcttgcagtaaagcaggcaatatctggaacttctccagtggcttcttcatttcagggtatcaaaaagtctatattcagaataagagaatcacctaaagagactttcaatgcaagtttttcaggtcatatgactgatccaaactttaaaaaagaaactgaagcctctgaaagtggactggaaatacatactgtttgctcacagaaggaggactccttatgtccaaatttaattgataatggaagctggccagccaccaccacacagaattctgtagctttgaagaatgcaggtttaatatccactttgaaaaagaaaacaaataagtttatttatgctatacatgatgaaacatcttataaaggaaaaaaaataccgaaagaccaaaaatcagaactaattaactgttcagcccagtttgaagcaaatgcttttgaagcaccacttacatttgcaaatgctgattcaggtttattgcattcttctgtgaaaagaagctgttcacagaatgattctgaagaaccaactttgtccttaactagctcttttgggacaattctgaggaaatgttctagaaatgaaacatgttctaataatacagtaatctctcaggatcttgattataaagaagcaaaatgtaataaggaaaaactacagttatttattaccccagaagctgattctctgtcatgcctgcaggaaggacagtgtgaaaatgatccaaaaagcaaaaaagtttcagatataaaagaagaggtcttggctgcagcatgtcacccagtacaacattcaaaagtggaatacagtgatactgactttcaatcccagaaaagtcttttatatgatcatgaaaatgccagcactcttattttaactcctacttccaaggatgttctgtcaaacctagtcatgatttctagaggcaaagaatcatacaaaatgtcagacaagctcaaaggtaacaattatgaatctgatgttgaattaaccaaaaatattcccatggaaaagaatcaagatgtatgtgctttaaatgaaaattataaaaacgttgagctgttgccacctgaaaaatacatgagagtagcatcaccttcaagaaaggtacaattcaaccaaaacacaaatctaagagtaatccaaaaaaatcaagaagaaactacttcaatttcaaaaataactgtcaatccagactctgaagaacttttctcagacaatgagaataattttgtcttccaagtagctaatgaaaggaataatcttgctttaggaaatactaaggaacttcatgaaacagacttgacttgtgtaaacgaacccattttcaagaactctaccatggttttatatggagacacaggtgataaacaagcaacccaagtgtcaattaaaaaagatttggtttatgttcttgcagaggagaacaaaaatagtgtaaagcagcatataaaaatgactctaggtcaagatttaaaatcggacatctccttgaatatagataaaataccagaaaaaaataatgattacatgaacaaatgggcaggactcttaggtccaatttcaaatcacagttttggaggtagcttcagaacagcttcaaataaggaaatcaagctctctgaacataacattaagaagagcaaaatgttcttcaaagatattgaagaacaatatcctactagtttagcttgtgttgaaattgtaaataccttggcattagataatcaaaagaaactgagcaagcctcagtcaattaatactgtatctgcacatttacagagtagtgtagttgtttctgattgtaaaaatagtcatataacccctcagatgttattttccaagcaggattttaattcaaaccataatttaacacctagccaaaaggcagaaattacagaactttctactatattagaagaatcaggaagtcagtttgaatttactcagtttagaaaaccaagctacatattgcagaagagtacatttgaagtgcctgaaaaccagatgactatcttaaagaccacttctgaggaatgcagagatgctgatcttcatgtcataatgaatgccccatcgattggtcaggtagacagcagcaagcaatttgaaggtacagttgaaattaaacggaagtttgctggcctgttgaaaaatgactgtaacaaaagtgcttctggttatttaacagatgaaaatgaagtggggtttaggggcttttattctgctcatggcacaaaactgaatgtttctactgaagctctgcaaaaagctgtgaaactgtttagtgatattgagaatattagtgaggaaacttctgcagaggtacatccaataagtttatcttcaagtaaatgtcatgattctgttgtttcaatgtttaagatagaaaatcataatgataaaactgtaagtgaaaaaaataataaatgccaactgatattacaaaataatattgaaatgactactggcacttttgttgaagaaattactgaaaattacaagagaaatactgaaaatgaagataacaaatatactgctgccagtagaaattctcataacttagaatttgatggcagtgattcaagtaaaaatgatactgtttgtattcataaagatgaaacggacttgctatttactgatcagcacaacatatgtcttaaattatctggccagtttatgaaggagggaaacactcagattaaagaagatttgtcagatttaacttttttggaagttgcgaaagctcaagaagcatgtcatggtaatacttcaaataaagaacagttaactgctactaaaacggagcaaaatataaaagattttgagacttctgatacattttttcagactgcaagtgggaaaaatattagtgtcgccaaagagtcatttaataaaattgtaaatttctttgatcagaaaccagaagaattgcataacttttccttaaattctgaattacattctgacataagaaagaacaaaatggacattctaagttatgaggaaacagacatagttaaacacaaaatactgaaagaaagtgtcccagttggtactggaaatcaactagtgaccttccagggacaacccgaacgtgatgaaaagatcaaagaacctactctattgggttttcatacagctagcgggaaaaaagttaaaattgcaaaggaatctttggacaaagtgaaaaacctttttgatgaaaaagagcaaggtactagtgaaatcaccagttttagccatcaatgggcaaagaccctaaagtacagagaggcctgtaaagaccttgaattagcatgtgagaccattgagatcacagctgccccaaagtgtaaagaaatgcagaattctctcaataatgataaaaaccttgtttctattgagactgtggtgccacctaagctcttaagtgataatttatgtagacaaactgaaaatctcaaaacatcaaaaagtatctttttgaaagttaaagtacatgaaaatgtagaaaaagaaacagcaaaaagtcctgcaacttgttacacaaatcagtccccttattcagtcattgaaaattcagccttagctttttacacaagttgtagtagaaaaacttctgtgagtcagacttcattacttgaagcaaaaaaatggcttagagaaggaatatttgatggtcaaccagaaagaataaatactgcagattatgtaggaaattatttgtatgaaaataattcaaacagtactatagctgaaaatgacaaaaatcatctctccgaaaaacaagatacttatttaagtaacagtagcatgtctaacagctattcctaccattctgatgaggtatataatgattcaggatatctctcaaaaaataaacttgattctggtattgagccagtattgaagaatgttgaagatcaaaaaaacactagtttttccaaagtaatatccaatgtaaaagatgcaaatgcatacccacaaactgtaaatgaagatatttgcgttgaggaacttgtgactagctcttcaccctgcaaaaataaaaatgcagccattaaattgtccatatctaatagtaataattttgaggtagggccacctgcatttaggatagccagtggtaaaatcgtttgtgtttcacatgaaacaattaaaaaagtgaaagacatatttacagacagtttcagtaaagtaattaaggaaaacaacgagaataaatcaaaaatttgccaaacgaaaattatggcaggttgttacgaggcattggatgattcagaggatattcttcataactctctagataatgatgaatgtagcacgcattcacataaggtttttgctgacattcagagtgaagaaattttacaacataaccaaaatatgtctggattggagaaagtttctaaaatatcaccttgtgatgttagtttggaaacttcagatatatgtaaatgtagtatagggaagcttcataagtcagtctcatctgcaaatacttgtgggatttttagcacagcaagtggaaaatctgtccaggtatcagatgcttcattacaaaacgcaagacaagtgttttctgaaatagaagatagtaccaagcaagtcttttccaaagtattgtttaaaagtaacgaacattcagaccagctcacaagagaagaaaatactgctatacgtactccagaacatttaatatcccaaaaaggcttttcatataatgtggtaaattcatctgctttctctggatttagtacagcaagtggaaagcaagtttccattttagaaagttccttacacaaagttaagggagtgttagaggaatttgatttaatcagaactgagcatagtcttcactattcacctacgtctagacaaaatgtatcaaaaatacttcctcgtgttgataagagaaacccagagcactgtgtaaactcagaaatggaaaaaacctgcagtaaagaatttaaattatcaaataacttaaatgttgaaggtggttcttcagaaaataatcactctattaaagtttctccatatctctctcaatttcaacaagacaaacaacagttggtattaggaaccaaagtgtcacttgttgagaacattcatgttttgggaaaagaacaggcttcacctaaaaacgtaaaaatggaaattggtaaaactgaaactttttctgatgttcctgtgaaaacaaatatagaagtttgttctacttactccaaagattcagaaaactactttgaaacagaagcagtagaaattgctaaagcttttatggaagatgatgaactgacagattctaaactgccaagtcatgccacacattctctttttacatgtcccgaaaatgaggaaatggttttgtcaaattcaagaattggaaaaagaagaggagagccccttatcttagtgggagaaccctcaatcaaaagaaacttattaaatgaatttgacaggataatagaaaatcaagaaaaatccttaaaggcttcaaaaagcactccagatggcacaataaaagatcgaagattgtttatgcatcatgtttctttagagccgattacctgtgtaccctttcgcacaactaaggaacgtcaagagatacagaatccaaattttaccgcacctggtcaagaatttctgtctaaatctcatttgtatgaacatctgactttggaaaaatcttcaagcaatttagcagtttcaggacatccattttatcaagtttctgctacaagaaatgaaaaaatgagacacttgattactacaggcagaccaaccaaagtctttgttccaccttttaaaactaaatcacattttcacagagttgaacagtgtgttaggaatattaacttggaggaaaacagacaaaagcaaaacattgatggacatggctctgatgatagtaaaaataagattaatgacaatgagattcatcagtttaacaaaaacaactccaatcaagcagcagctgtaactttcacaaagtgtgaagaagaacctttagatttaattacaagtcttcagaatgccagagatatacaggatatgcgaattaagaagaaacaaaggcaacgcgtctttccacagccaggcagtctgtatcttgcaaaaacatccactctgcctcgaatctctctgaaagcagcagtaggaggccaagttccctctgcgtgttctcataaacagctgtatacgtatggcgtttctaaacattgcataaaaattaacagcaaaaatgcagagtcttttcagtttcacactgaagattattttggtaaggaaagtttatggactggaaaaggaatacagttggctgatggtggatggctcataccctccaatgatggaaaggctggaaaagaagaattttatagggctctgtgtgacactccaggtgtggatccaaagcttatttctagaatttgggtttataatcactatagatggatcatatggaaactggcagctatggaatgtgcctttcctaaggaatttgctaatagatgcctaagcccagaaagggtgcttcttcaactaaaatacagatatgatacggaaattgatagaagcagaagatcggctataaaaaagataatggaaagggatgacacagctgcaaaaacacttgttctctgtgtttctgacataatttcattgagcgcaaatatatctgaaacttctagcaataaaactagtagtgcagatacccaaaaagtggccattattgaacttacagatgggtggtatgctgttaaggcccagttagatcctcccctcttagctgtcttaaagaatggcagactgacagttggtcagaagattattcttcatggagcagaactggtgggctctcctgatgcctgtacacctcttgaagccccagaatctcttatgttaaagatttctgctaacagtactcggcctgctcgctggtataccaaacttggattctttcctgaccctagaccttttcctctgcccttatcatcgcttttcagtgatggaggaaatgttggttgtgttgatgtaattattcaaagagcataccctatacagtggatggagaagacatcatctggattatacatatttcgcaatgaaagagaggaagaaaaggaagcagcaaaatatgtggaggcccaacaaaagagactagaagccttattcactaaaattcaggaggaatttgaagaacatgaagaaaacacaacaaaaccatatttaccatcacgtgcactaacaagacagcaagttcgtgctttgcaagatggtgcagagctttatgaagcagtgaagaatgcagcagacccagcttaccttgagggttatttcagtgaagagcagttaagagccttgaataatcacaggcaaatgttgaatgataagaaacaagctcagatccagttggaaattaggaaggccatggaatctgctgaacaaaaggaacaaggtttatcaagggatgtcacaaccgtgtggaagttgcgtattgtaagctattcaaaaaaagaaaaagattcagttatactgagtatttggcgtccatcatcagatttatattctctgttaacagaaggaaagagatacagaatttatcatcttgcaacttcaaaatctaaaagtaaatctgaaagagctaacatacagttagcagcgacaaaaaaaactcagtatcaacaactaccggtttcagatgaaattttatttcagatttaccagccacgggagccccttcacttcagcaaatttttagatccagactttcagccatcttgttctgaggtggacctaataggatttgtcgtttctgttgtgaaaaaaacaggacttgcccctttcgtctatttgtcagacgaatgttacaatttactggcaataaagttttggatagaccttaatgaggacattattaagcctcatatgttaattgctgcaagcaacctccagtggcgaccagaatccaaatcaggccttcttactttatttgctggagatttttctgtgttttctgctagtccaaaagagggccactttcaagagacattcaacaaaatgaaaaatactgttgagaatattgacatactttgcaatgaagcagaaaacaagcttatgcatatactgcatgcaaatgatcccaagtggtccaccccaactaaagactgtacttcagggccgtacactgctcaaatcattcctggtacaggaaacaagcttctgatgtcttctcctaattgtgagatatattatcaaagtcctttatcactttgtatggccaaaaggaagtctgtttccacacctgtctcagcccagatgacttcaaagtcttgtaaaggggagaaagagattgatgaccaaaagaactgcaaaaagagaagagccttggatttcttgagtagactgcctttacctccacctgttagtcccatttgtacatttgtttctccggctgcacagaaggcatttcagccaccaaggagttgtggcaccaaatacgaaacacccataaagaaaaaagaactgaattctcctcagatgactccatttaaaaaattcaatgaaatttctcttttggaaagtaattcaatagctgacgaagaacttgcattgataaatacccaagctcttttgtctggttcaacaggagaaaaacaatttatatctgtcagtgaatccactaggactgctcccaccagttcagaagattatctcagactgaaacgacgttgtactacatctctgatcaaagaacaggagagttcccaggccagtacggaagaatgtgagaaaaataagcaggacacaattacaactaaaaaatatatctagggcctcatgggcccagctttcttgtacaaagtggt";
        String dnaU = dna.toUpperCase();
        String gene = findGene(dnaU, 1);
        System.out.println(gene);
        dna = "MPOMATGDISNAIUNASD";
        gene = findGene(dna,1);
        if(!gene.isEmpty())
            System.out.println("error");
        dna = findGene("MPOMATGDISNAITAASD", 1);
        if(dna.isEmpty())
            System.out.println("error");
        dna = findGene("MPOMATGTAGNAIUNASD", 1); 
        if(dna.isEmpty())
            System.out.println("error");
        dna = findGene("MPOMATGDISTGAUNASD", 1);
        if(dna.isEmpty())
            System.out.println("error");
        dna = findGene("MPOMATGDITTAGITAASDTGA", 1);
        if(dna.isEmpty())
            System.out.println("error");
        dna = findGene("MPOMATGTTAAGNAIUNASD", 1);
        if(!dna.isEmpty())
            System.out.println("error");
        System.out.println("RUN FINISHED!");
    }
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = dna.indexOf("ATG");
        int atg = 0;
        while (true) {
            atg = dna.indexOf("ATG", startIndex);
            if(atg == -1)
                break;
            String gene = findGene(dna,0);
            sr.add(gene);
            startIndex = atg +1;
        }
        return sr;
    }
    public void testing(String dna) {
        String hey =("The genes in the dna: " + dna + " are: ");
        StorageResource genes = getAllGenes(dna);
        for(String g : genes.data()) {
            hey = hey + g + ".\n";
        }
        System.out.println(hey);
    }
    public void printAllGenesTesting() {
        testing("MPOMSOIDISNAIUNASD");
        testing("MPOMATGDISNAIUNASD");
        testing("MPOMATGDISNAITAASDMPOMATGDISNAITAASDMPOMATGDISNAITAASD");
        testing("MPOMATGTAGNAIUNASDMPOMATGTAGNAIUNASD");
        testing("MPOMATGDISTGAUNASD");
        testing("MPOMATGDITTAGITAASDTGAMPOMATGDITTAGITAASDTGAMPOMATGDITTAGITAASDTGAMPOMATGDITTAGITAASDTGA");
        testing("MPOMATGTTAAGNAIUNASD");
        System.out.println("RUN FINISHED!");
    }
    public int printAllGenes(String dna) {
        int startIndex = dna.indexOf("ATG");
        int atg = 0;
        int genec =0;
        String allGenes = "The dna is: "+ dna + ".\n" + "And the genes are: "; 
        while (true) {
             atg = dna.indexOf("ATG", startIndex);
             if(atg == -1)
                break;
             String gene = findGene(dna,0);
             genec ++;
             allGenes = allGenes + gene + ".\n";
             startIndex = atg + 1;
        }
        return genec;
    }
    public void printAllGenesS(String dna) {
        int startIndex = dna.indexOf("ATG");
        int atg = 0;
        int genec =0;
        int gg=4;
        StorageResource sr = new StorageResource();
        String allGenes = "The dna is: "+ dna + ".\n" + "And the genes are: "; 
        while (true) {
             atg = dna.indexOf("ATG", startIndex);
             if(atg == -1)
                break;
             String gene = findGene(dna,atg);
             if(!gene.isEmpty())
                 gg = gene.length();
             else 
                gg = 4;
             genec ++;
             if(!gene.isEmpty())
                sr.add(gene);
             allGenes = allGenes + gene + ".\n";
             startIndex = atg + gg -3;
        }
        System.out.println(sr.data());
        System.out.println(sr.size());
        processGenes(sr);
    }
    public void printAllGenesTest() {
        FileResource fr = new FileResource("brca1line.fa");
        String frr = fr.asString();
        String frrr = frr.toUpperCase();
        printAllGenesS(frrr);
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
    public void cgRunner(String dna) {
        System.out.println("DNA: " + dna + ".\nRatio: " + cgRatio(dna) + ".");
    }
    public void cgTest() {
        cgRunner("ATGCCATAG");
        cgRunner("MPOMSOIDISNAIUNASD");
        cgRunner("MPOMATGDISNAIUNASD");
        cgRunner("MPOMATGDISNAITAASDMPOMATGDISNAITAASDMPOMATGDISNAITAASD");
        cgRunner("MPOMATGTAGNAIUNASDMPOMATGTAGNAIUNASD");
        cgRunner("MPOMATGDISTGAUNASD");
        cgRunner("MPOMATGDITTAGITAASDTGAMPOMATGDITTAGITAASDTGAMPOMATGDITTAGITAASDTGAMPOMATGDITTAGITAASDTGA");
        cgRunner("MPOMATGTTAAGNAIUNASD");
        System.out.println("RUN FINISHED!");
    }
    public void processGenes(StorageResource sr) {
        int geneCounter =0;
        int longCounter = 0;
        int cgCounter =0;
        for(String gene : sr.data()) {
            geneCounter++;
            if(gene.length() > 60)
                longCounter++;
            double cagRatio = cgRatio(gene) + 0.0;
            if(cagRatio > 0.35)
                cgCounter++;
        }
        System.out.println("The number of genes is: " +geneCounter);
        System.out.println("The number of genes that are longer than 60 are: " +longCounter);
        System.out.println("The number of genes with high ratio: " +cgCounter);
    }
    public void testProcessGenes() {
        String a =("ASFSDASJDJSAJOJHINDIJASNI");
        String b =("NJKSNDJNASJDNSAJNDKJSANDJASN");
        String c =("MNKASN");
        String d =("CCCCGGCGCGCGGCYI");
        String e =("GCGCGGCTA");
        String f =("SATGDONSAIOTAA");
        String g =("SATGDONSAIODDTAA");
        String h =("SATGDONSAITAA");
        StorageResource rs = new StorageResource();
        rs.add(a);
        rs.add(b);
        rs.add(c);
        rs.add(d);
        rs.add(e);
        rs.add(f);
        rs.add(g);
        rs.add(h);
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

