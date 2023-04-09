import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ResumeScanner {
    
    public static void main(String[] args) throws IOException {
    	 Scanner scanner = new Scanner(System.in);

         System.out.print("Enter the file path to the resume file: ");
         String resumePath = scanner.nextLine();

         Properties props = new Properties();
         String filePath = System.getProperty("user.dir") + "/src/main/java/properties/keyword.properties";
         try {
             FileInputStream fis = new FileInputStream(filePath);
             props.load(fis);
         } catch (IOException e) {
             e.printStackTrace();
         }
        
        String keywords = props.getProperty("keywords");

         scanner.close();
        
        File resumeFile = new File(resumePath);
        String resumeText = extractTextFromPDF(resumeFile);
        
        // Find matching keywords
        
        String[] keywordList = keywords.split(",\\s*");
        List<String> matchingResults = findMatchingResults(resumeText, keywordList);
        System.out.println("Matching results:");
        for (String result : matchingResults) {
            System.out.println("- " + result);
        }
        
        // Calculate match percentage
        double matchPercentage = calculateMatchPercentage(resumeText, keywordList);
        System.out.println("\n");
        System.out.printf("Resume Matched Percentage :"+"%.2f%%\n", matchPercentage);
        
        // Extract , email and LinkedIn URL
        String linkedIn = extractLinkedIn(resumeText);
        String email = extractEmail(resumeText);
        
        System.out.println("\n");
        System.out.println("Email: " + email);
        
        System.out.println("\n");
        System.out.println("LinkedIn: " + linkedIn);
       
     
        
       
        
   
    }
   
    	private static String extractLinkedIn(String resumeText) {
    	    int startIndex = resumeText.indexOf("www.linkedin.com");
    	    if (startIndex != -1) {
    	        int endIndex = resumeText.indexOf(" ", startIndex);
    	        if (endIndex == -1) {
    	            endIndex = resumeText.length();
    	        }
    	        return resumeText.substring(startIndex, endIndex);
    	    } else {
    	        System.out.println("LinkedIn URL not found in the resume.");
    	        return "";
    	    }
    	}

    




	private static String extractTextFromPDF(File file) throws IOException {
        RandomAccessRead randomAccessRead = new RandomAccessBufferedFileInputStream(file);
        PDFParser parser = new PDFParser(randomAccessRead);
        parser.parse();
        COSDocument cosDoc = parser.getDocument();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        PDDocument pdDoc = new PDDocument(cosDoc);
        return pdfStripper.getText(pdDoc);
    }
    
    private static List<String> findMatchingResults(String resumeText, String[] keywordList) {
        List<String> matchingResults = new ArrayList<>();
        
        for (String keyword : keywordList) {
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(resumeText);
            while (matcher.find()) {
                String matchingResult = matcher.group();
                if (!matchingResults.contains(matchingResult)) {
                    matchingResults.add(matchingResult);
                }
            }
        }
        
        return matchingResults;
    }
    
    private static double calculateMatchPercentage(String resumeText, String[] keywordList) {
        int totalKeywords = keywordList.length;
        int matchedKeywords = 0;
        
        for (String keyword : keywordList) {
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(resumeText);
            if (matcher.find()) {
                matchedKeywords++;
            }
        }
        
        return ((double) matchedKeywords / totalKeywords) * 100;
    }
    private static String extractEmail(String resumeText) {
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");
        Matcher matcher = pattern.matcher(resumeText);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }
        
       
    
   

    }
   
}
        
