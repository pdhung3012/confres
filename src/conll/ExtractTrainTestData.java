package conll;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.stanford.nlp.parser.lexparser.Item;
import utils.FileIO;

public class ExtractTrainTestData {
	public static String[] arrSpaces={"   ","   ","          ", "   ", "  *   -   -   -   speaker_1       *      " };
	public static void collectData(){
		// TODO Auto-generated method stub
				String fpInput="/Users/hungphan/git/mlcompetition/WikiCoref/Evaluation/key-OntoNotesScheme";
				String fpSort="/Users/hungphan/git/mlcompetition/WikiCoref/Evaluation/key-sort.txt";
				String fopDocuments="/Users/hungphan/git/mlcompetition/WikiCoref/Evaluation/key_train_test/";
				new File(fopDocuments).mkdir();
				String[] arrContents=FileIO.readStringFromFile(fpInput).split("\n");
				int index=0;
				StringBuilder sbResult=new StringBuilder();
				int numberLineInDoc=0;
				ArrayList<ItemDoc> list=new ArrayList<ItemDoc>();
				for(int i=0;i<arrContents.length;i++){
					String line=arrContents[i].trim();	
					sbResult.append(line+"\n");
					numberLineInDoc++;
					if(line.startsWith("#begin document ")){
//						sbResult=new StringBuilder();				
					} else if(line.startsWith("#end document")){
						index++;
						String nameFile=String.format("%03d",index);
						ItemDoc it=new ItemDoc();
						it.index=nameFile;
						it.numline=numberLineInDoc;
						
						list.add(it);
						FileIO.writeStringToFile(sbResult.toString(), fopDocuments+"/"+nameFile+".txt");
						sbResult=new StringBuilder();
						numberLineInDoc=0;
						
					} 			
				}		
				Collections.sort(list,new Sortbyroll());
				String strSort="";
				for(ItemDoc it:list){
					strSort+=it.index+"\t"+it.numline+"\n";
				}
				FileIO.writeStringToFile(strSort, fpSort);
	}
	
	public static void generateConllForm(){
		String fopTrainFolder="/Users/hungphan/git/mlcompetition/WikiCoref/Evaluation/key_split/test/";
		String fpOutTrain="/Users/hungphan/git/mlcompetition/WikiCoref/Evaluation/key_split/test.v4_gold_conll";
		File fTrain=new File(fopTrainFolder);
		File[] arrFolder=fTrain.listFiles();
		FileIO.writeStringToFile("", fpOutTrain);
		int indexPart=-1;
		for(int i=0;i<arrFolder.length;i++){
			if(!arrFolder[i].getAbsolutePath().endsWith(".txt")){
				continue;
			} else{
				indexPart++;
			}
			String[] arrContent=FileIO.readStringFromFile(arrFolder[i].getAbsolutePath()).split("\n");
			StringBuilder sb=new StringBuilder();
			System.out.print(arrFolder[i].getAbsolutePath()+" aaaa "+arrContent.length);
			for(int j=0;j<arrContent.length;j++){
				String item=arrContent[j];
				
				String line="";
				if(item.startsWith("#begin document ")){
					line="#begin document (bc/cnn/00/cnn_0006); part "+String.format("%03d", 0)+"\n";
				}else if(item.startsWith("#end document")){
					line="\n#end document\n";
				}
				else if(item.trim().isEmpty()){
					line="\n";
				}
				else{
					String[] arrItemContent=item.split("\\s+");
					if(arrItemContent.length>=5){
						line="bc/cnn/00/cnn_0006"+arrSpaces[0]
								+arrItemContent[1].trim()+arrSpaces[1]+(Integer.parseInt(arrItemContent[2])-1)
									+arrSpaces[2]+arrItemContent[3]+arrSpaces[3]	+"(NP)"+arrSpaces[4]
											+arrItemContent[4]+"\n";
					}
				}
				sb.append(line);
			}
			FileIO.appendStringToFile(sb.toString(), fpOutTrain);
//			break;
		}
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generateConllForm();
	}
	
	

}
class ItemDoc{
	public String index="";
	public int numline=0;
}

class Sortbyroll implements Comparator<ItemDoc> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(ItemDoc a, ItemDoc b) 
    { 
        return a.numline - b.numline; 
    } 
} 
