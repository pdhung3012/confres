package conll;

import utils.FileIO;

public class GenConllFile {
//	bc/cnn/00/cnn_0005
	String[] strTestContent=new String[]{"*",   "0"  ,"0","For", "IN",
			"(TOP(PP*",   "-" ,  "-"  , "-" ,  "speaker_1"   ,    "*"    ,  "-"};
	public void parseSampleContent(){
		String fpText="/Users/hungphan/git/mlcompetition/conll/king.conll";
		String fpLabel="/Users/hungphan/git/mlcompetition/conll/label.conll";
		String fpOut="/Users/hungphan/git/mlcompetition/conll/out.v4_gold_conll";
		String[] arrText=FileIO.readStringFromFile(fpText).split("\n");
		String[] arrLabel=FileIO.readStringFromFile(fpLabel).split("\n");
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<arrText.length;i++){
			String item= strTestContent[0]+"\t"+strTestContent[1]+"\t"+(i)+"\t"
					+arrText[i].replaceAll(" ","_")+"\t"+strTestContent[4]+"\t"+strTestContent[5]
							+"\t"+strTestContent[6]+"\t"+strTestContent[7]
									+"\t"+strTestContent[8]+"\t"+strTestContent[9]
											+"\t"+strTestContent[10]+"\t"+"("+arrLabel[i].replaceAll("set_", "")+")";
		sb.append(item+"\n");
		
		}
		FileIO.writeStringToFile(sb.toString(), fpOut);
			
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenConllFile gcf=new GenConllFile();
		gcf.parseSampleContent();
	}

}
