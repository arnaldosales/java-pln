package uag.bcc.ia.stanford_parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;

/**
 * 
 * @author Arnaldo Sales
 * @author Eltton Tullyo
 *
 */
public class SPHelper {

	private static LexicalizedParser lp;
	private static SPHelper instanceParser = null;

	public SPHelper() {

		lp = LexicalizedParser.loadModel("res/englishPCFG.ser.gz");

	}

	public static SPHelper getInstanceParser() {

		if (instanceParser == null) {

			instanceParser = new SPHelper();
		}

		return instanceParser;
		
	}

	public LexicalizedParser getLp() {
		
		return lp;
		
	}

	public Tree getTree(String texto) {

		//TreeSet
		TokenizerFactory<CoreLabel> tokens = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		List<CoreLabel> listaCorelabel = tokens.getTokenizer(
				new StringReader(texto)).tokenize();
		Tree parseTree = lp.apply(listaCorelabel);

		return parseTree;
		
	}

	public List<String> getListaConceito(List<TaggedWord> listaConceito) {

		List<String> listaR = new ArrayList<String>();

		for (int i = 0; i < listaConceito.size(); i++) {

			if (listaConceito.get(i).toString().contains("NNS")) {

				listaR.add(listaConceito.get(i).toString().replace("/NNS", ""));

			} else if (listaConceito.get(i).toString().contains("NN")) {

				listaR.add(listaConceito.get(i).toString().replace("/NN", ""));

			} else if (listaConceito.get(i).toString().contains("RB")) {

				listaR.add(listaConceito.get(i).toString().replace("/RB", ""));
			} else {

			}
			
		}

		return listaR;
		
	}

	public List<String> getListaInstancia(List<TaggedWord> listaInstancia) {

		List<String> listaR = new ArrayList<String>();

		for (int i = 0; i < listaInstancia.size(); i++) {

			if (listaInstancia.get(i).toString().contains("NNP")) {

				listaR.add(listaInstancia.get(i).toString().replace("/NNP", ""));

			} else if (listaInstancia.get(i).toString().contains("NNPS")) {

				listaR.add(listaInstancia.get(i).toString()
						.replace("/NNPS", ""));

			} else {

			}
			
		}

		return listaR;
		
	}

	public List<String> getListaPredicado(List<TaggedWord> listaPredicado) {

		List<String> listaR = new ArrayList<String>();

		for (int i = 0; i < listaPredicado.size(); i++) {

			if (listaPredicado.get(i).toString().contains("VB")) {

				listaR.add(listaPredicado.get(i).toString().replace("/VB", ""));

			} else if (listaPredicado.get(i).toString().contains("VBD")) {

				listaR.add(listaPredicado.get(i).toString().replace("/VBD", ""));

			} else if (listaPredicado.get(i).toString().contains("VBZ")) {

				listaR.add(listaPredicado.get(i).toString().replace("/VBZ", ""));

			} else if (listaPredicado.get(i).toString().contains("VBN")) {

				listaR.add(listaPredicado.get(i).toString().replace("VBN", ""));

			} else if (listaPredicado.get(i).toString().contains("VBP")) {

				listaR.add(listaPredicado.get(i).toString().replace("/VBP", ""));

			} else if (listaPredicado.get(i).toString().contains("VBG")) {

				listaR.add(listaPredicado.get(i).toString().replace("/VBG", ""));

			} else {

			}
			
		}

		return listaR;
		
	}

	public List<TaggedWord> getListTaggerWord(String[] frase){
		
		String[] sent = frase;
		List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);
		Tree parse = lp.apply(rawWords);
		
		return  parse.taggedYield();
		
	}
	
}
