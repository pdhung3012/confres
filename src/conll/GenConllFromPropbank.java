package conll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoNLLOutputter;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;

public class GenConllFromPropbank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fpOutput="/Users/hungphan/git/mlcompetition/outputConll.txt";
		Annotation germanAnnotation = new Annotation("Hello, nice to meet you.");
		Properties germanProperties = StringUtils.argsToProperties("-props", "edu/stanford/nlp/coref/properties/deterministic-english.properties");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(germanProperties);
		pipeline.annotate(germanAnnotation);

		StringBuilder trees = new StringBuilder("");
		for (CoreMap sentence : germanAnnotation.get(CoreAnnotations.SentencesAnnotation.class)) {
		     Tree sentenceTree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
		     trees.append(sentenceTree).append("\n");
		}
		try{
			OutputStream outputStream = new FileOutputStream(new File(fpOutput));
			CoNLLOutputter.conllPrint(germanAnnotation, outputStream, pipeline);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
