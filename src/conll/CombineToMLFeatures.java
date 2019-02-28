package conll;

import java.io.File;
import java.util.Scanner;

import utils.FileIO;

public class CombineToMLFeatures {

	public static String[] getFeature(String content){
		String strResult=content;
		strResult=strResult.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "").trim();
		String[] arr=strResult.split("\\s+");
		return arr;
	}
	
	public static String combineFeature(String[] arr){
		StringBuilder sbResult=new StringBuilder();
		for(int i=0;i<arr.length;i++){
			sbResult.append(arr[i]+" ");
		}
		return sbResult.toString().trim();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fopMention="/Users/hungphan/git/mlcompetition/WikiCoref/Evaluation/key_split/train_wikicoref/feature_mentions/";
		String fopOutput="/Users/hungphan/git/mlcompetition/WikiCoref/Evaluation/key_split/mldata/";
		String type="train";
		String fpID=fopOutput+type+".id.txt";
		String fpFeatures=fopOutput+type+".feature.txt";
		String fpLabel=fopOutput+type+".label.txt";
		
		String fpSampleID=fopOutput+"sample."+type+".id.txt";
		String fpSampleFeatures=fopOutput+"sample."+type+".feature.txt";
		String fpSampleLabel=fopOutput+"sample."+type+".label.txt";
		
		File folder=new File(fopMention);
		File[] arrFiles=folder.listFiles();
		
		StringBuilder sbID=new StringBuilder();
		StringBuilder sbFeature=new StringBuilder();
		StringBuilder sbLabel=new StringBuilder();
		
		StringBuilder sbSampleID=new StringBuilder();
		StringBuilder sbSampleFeature=new StringBuilder();
		StringBuilder sbSampleLabel=new StringBuilder();
		
		int countLine=0;
		
		FileIO.writeStringToFile("", fpSampleID);
		FileIO.writeStringToFile("", fpSampleFeatures);
		FileIO.writeStringToFile("", fpSampleLabel);
		
		FileIO.writeStringToFile("", fpID);
		FileIO.writeStringToFile("", fpFeatures);
		FileIO.writeStringToFile("", fpLabel);
		
		for(int i=0;i<arrFiles.length;i++){
			if(arrFiles[i].getAbsolutePath().endsWith(".txt")){
				String[] arrContent=FileIO.readStringFromFile(arrFiles[i].getAbsolutePath()).split("\n");
				String strC=FileIO.readStringFromFile(arrFiles[i].getAbsolutePath());
				System.out.println(strC);
				Scanner sc=new Scanner(System.in);
				sc.next();
				if(arrContent.length>=6){
					String label=arrContent[4].trim();
					
					String id=arrFiles[i].getName();
					String[] arrFeature=getFeature(arrContent[5].trim());
//					System.out.println("feature "+arrFeature.length);
					String strFeature=combineFeature(arrFeature);
//					System.out.println(label+" aaaa "+id+" bbbb "+strFeature);
					sbID.append(id+"\n");
					sbFeature.append(strFeature+"\n");
					sbLabel.append(label+"\n");
					countLine++;
					if(countLine<=1000){
						sbSampleID.append(id+"\n");
						sbSampleFeature.append(strFeature+"\n");
						sbSampleLabel.append(label+"\n");
					} else if(countLine==1000){
						FileIO.appendStringToFile(sbSampleID.toString(), fpSampleID);
						FileIO.appendStringToFile(sbSampleFeature.toString(), fpSampleFeatures);
						FileIO.appendStringToFile(sbSampleLabel.toString(), fpSampleLabel);
						
					}
					
					if(countLine%10000==0 ){
						FileIO.appendStringToFile(sbID.toString(), fpID);
						FileIO.appendStringToFile(sbFeature.toString(), fpFeatures);
						FileIO.appendStringToFile(sbLabel.toString(), fpLabel);
						System.out.println("Complete line "+countLine);
						sbID=new StringBuilder();
						sbFeature=new StringBuilder();
						sbLabel=new StringBuilder();
					}
					
				}
				
				if(i==arrFiles.length-1){
					FileIO.appendStringToFile(sbID.toString(), fpID);
					FileIO.appendStringToFile(sbFeature.toString(), fpFeatures);
					FileIO.appendStringToFile(sbLabel.toString(), fpLabel);
					System.out.println("Complete line "+countLine);
					sbID=new StringBuilder();
					sbFeature=new StringBuilder();
					sbLabel=new StringBuilder();
				}
			}
		}
		

		
		
		System.out.println("number line "+countLine);
		
		
		
	}

}
