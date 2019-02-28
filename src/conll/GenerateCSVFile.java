package conll;

import java.io.BufferedReader;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

import utils.FileIO;

public class GenerateCSVFile {
	
	public static String[] getArrFeatures(String str){
		String result=str.replaceFirst("\\[", "").replaceFirst("\\]", "").replaceAll(",", " ").trim();
		return result.split("\\s+");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fopInput="/Users/hungphan/Downloads/line_dev_unite/";
		String fpOutput="/Users/hungphan/Downloads/line_dev_unite/dev.all.csv";
		String fpId=fopInput+"id.txt";
		String fpLabel=fopInput+"label.txt";
		String fpFeature=fopInput+"feat.txt";
		
		LinkedHashSet<String> setId=new LinkedHashSet<>();
		
		String[] arrId=FileIO.readStringFromFile(fpId).split("\n");
		String[] arrLabel=FileIO.readStringFromFile(fpLabel).split("\n");
		int countLine=0,countIndex=0;
		FileIO.writeStringToFile("", fpOutput);
		int sizeColumn=0;
		StringBuilder sbOutput=new StringBuilder();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fpFeature), StandardCharsets.UTF_8)) {
		    for (String line = null; (line = br.readLine()) != null;) {
				String idTrim=arrId[countIndex].trim();
				String labelTrim=arrLabel[countIndex].trim();
				String featTrim=line.trim();
				
				if(!setId.contains(idTrim)){
					setId.add(idTrim);
					String[] arrItemFeat=getArrFeatures(featTrim);
					sizeColumn=1+arrItemFeat.length+1;
					if(countIndex==0){
						sbOutput.append("ID,");
						for(int j=1;j<=arrItemFeat.length;j++){
							sbOutput.append("col_"+j+",");
						}
						sbOutput.append("Target\n");	
					}
					
					sbOutput.append(idTrim+",");
					for(int j=0;j<sizeColumn-2;j++){
						sbOutput.append(arrItemFeat[j]+",");
					}
					sbOutput.append(labelTrim+"\n");
					
					if(countLine!=0 && countLine%100000==0){
						FileIO.appendStringToFile(sbOutput.toString(), fpOutput);
						System.out.println("countline "+countLine);
						sbOutput=new StringBuilder();
//						break;
					}
					countLine++;
				}
				
				if(countIndex==arrId.length-1 &&!sbOutput.toString().isEmpty()){
					FileIO.appendStringToFile(sbOutput.toString(), fpOutput);
					System.out.println("countline "+countLine);
				}
//		    	System.out.println(countLine+" "+line+" ssdsd");
		    	countIndex++;
		    }
		} catch(Exception ex){
			ex.printStackTrace();
		}
//		String[] arrFeat=FileIO.readStringFromFile(fpFeature).split("\n");
//		int sizeColumn=0;
//		StringBuilder sbOutput=new StringBuilder();
//		int countLine=0;
//		FileIO.writeStringToFile("", fpOutput);
//		for(int i=0;i<arrId.length;i++){
//			String idTrim=arrId[i].trim();
//			String labelTrim=arrLabel[i].trim();
//			String featTrim=arrFeat[i].trim();
//			
//			if(!setId.contains(idTrim)){
//				setId.add(idTrim);
//				String[] arrItemFeat=getArrFeatures(featTrim);
//				sizeColumn=1+arrItemFeat.length+1;
//				if(i==0){
//					sbOutput.append("ID;");
//					for(int j=1;j<=arrItemFeat.length;j++){
//						sbOutput.append("col_"+j+";");
//					}
//					sbOutput.append("Target\n");	
//				}
//				sbOutput.append(idTrim+";");
//				for(int j=0;j<sizeColumn;j++){
//					sbOutput.append(arrItemFeat[j]+";");
//				}
//				sbOutput.append(labelTrim+"\n");
//				countLine++;
//				if(countLine%100000==0){
//					FileIO.appendStringToFile(sbOutput.toString(), fpOutput);
//					System.out.println("countline "+countLine);
//					sbOutput=new StringBuilder();
//				}
//			}
//			
//			if(i==arrId.length-1 &&!sbOutput.toString().isEmpty()){
//				FileIO.appendStringToFile(sbOutput.toString(), fpOutput);
//				System.out.println("countline "+countLine);
//			}
//		}

	}

}
