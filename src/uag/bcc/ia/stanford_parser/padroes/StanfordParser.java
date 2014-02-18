package uag.bcc.ia.stanford_parser.padroes;

/**
 * 
 * @author Paulinely Morgan
 * @author Raphael Cordeiro
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public class StanfordParser {

	private String taggingFrase;

	/**
	 * demoAPI demonstrates other ways of calling the parser with already
	 * tokenized text, or in some cases, raw text that needs to be tokenized as
	 * a single sentence. Output is handled with a TreePrint object. Note that
	 * the options used when creating the TreePrint can determine what results
	 * to print out. Once again, one can capture the output by passing a
	 * PrintWriter to TreePrint.printTree.
	 */
	public static String demoAPI(LexicalizedParser lp, String localArquivoFrase) {
		// This option shows parsing a list of correctly tokenized words
		String[] sent = { "this", "is", "a", "dummy", "test", "." };
		List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);
		Tree parse = lp.apply(rawWords);

		// ArrayList ou_vai_ou_racha = parse.taggedYield();
		// System.out.println(ou_vai_ou_racha.toString());
		// parse.pennPrint();
		// System.out.println("está na demo API");
		String temp = null;
		byte[] buffer = new byte[1000];
		InputStream in;
		try {
			in = new FileInputStream(localArquivoFrase);
			in.read(buffer);
			temp = new String(buffer).trim().toString();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		// **********TAGGING*********TAGGING*********TAGGING*********TAGGING*********TAGGING********************//
		// This option shows loading and using an explicit tokenizer
		String sent2 = temp;
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		List<CoreLabel> rawWords2 = tokenizerFactory.getTokenizer(
				new StringReader(sent2)).tokenize();
		parse = lp.apply(rawWords2);
		// System.out.println("a porra do tagging:");
		ArrayList strTagging = parse.taggedYield();
		// a string com o tagging "strTagging.toString()"
		// System.out.println(strTagging.toString());
		String palavra = strTagging.toString().replaceAll(",", "");
		palavra = palavra.substring(1, (palavra.length() - 1));
		return palavra;

	}
}