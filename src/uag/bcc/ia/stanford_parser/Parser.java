package uag.bcc.ia.stanford_parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreePrint;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

/**
 * 
 * @author Arnaldo Sales
 * @author Eltton Tullyo
 *
 */
public class Parser {

	private LexicalizedParser lp;
	private static Parser instanceParser = null;

	public Parser() {

		lp = LexicalizedParser.loadModel("res/englishPCFG.ser.gz");

	}

	public static Parser getInstanceParser() {

		if (instanceParser == null) {

			instanceParser = new Parser();
		}

		return instanceParser;
	}

	public LexicalizedParser getLp() {
		return lp;
	}

	public Tree getParser(String texto) {

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

	public String Relacao(LexicalizedParser lp, String TextoPrincipal) {
		
		String relacaoFinal = null;
		// This option shows parsing a list of correctly tokenized words
		String[] sent = { "this", "is", "a", "dummy", "test", "." };
		List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);
		Tree parse = lp.apply(rawWords);
		// System.out.println("impressão de rawWord");

		// System.out.println(rawWords.get(0));
		// ArrayList ou_vai_ou_racha = parse.taggedYield();
		// System.out.println(ou_vai_ou_racha.toString());
		// parse.pennPrint();
		// System.out.println("est� na demo API");
	
		

		// This option shows loading and using an explicit tokenizer
		String sent2 = TextoPrincipal;
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		List<CoreLabel> rawWords2 = tokenizerFactory.getTokenizer(
				new StringReader(sent2)).tokenize();
		parse = lp.apply(rawWords2);
		// System.out.println("saida do rawWords2");
		// System.out.println(rawWords2.get(0));

		// parse.pennPrint();
		// System.out.println("impressão do nodo 2 de parse");
		// System.out.println(parse.getNodeNumber(2));
		// System.out.println("o constituents:");
		// System.out.println( parse.constituents().size());
		// System.out.println("o santo tagging:");
		// System.out.println( parse.taggedLabeledYield()) ;
		ArrayList<TaggedWord> listafinal = parse.taggedYield();
		// System.out.println("Impressão de minha morte");
		// System.out.println(listafinal.get(0));

		ArrayList taggs = parse.taggedYield();
		// System.out.println(taggs.toString());
		String s = rawWords2.toString();
		// System.out.println(rawWords2);
		// System.out.println(s);
		// System.out.println("sa apowa");
		String convert1 = taggs.toString().replace("[", "");
		String convert = convert1.replace("]", " ");
		// System.out.println("imprimindo convert");
		// System.out.println(convert);
		//char []powa=ou_vai_ou_racha.toString().toCharArray();
		String[] powa = convert.split(",");
		ArrayList<String> conceito = new ArrayList<>();
		ArrayList<String> predicado = new ArrayList<>();
		ArrayList<String> instancia = new ArrayList<>();
		for (int i = 0; i < powa.length; i++) {
			if (powa[i].contains("NNP")) {
				instancia.add(powa[i].replace("/NNP", ""));

			} else if (powa[i].contains("NNPS")) {
				instancia.add(powa[i].replace("/NNPS", ""));
			} else if (powa[i].contains("NNS")) {
				conceito.add(powa[i].replace("/NNS", ""));
			} else if (powa[i].contains("NN")) {
				conceito.add(powa[i].replace("/NN", ""));
				;
			} else if (powa[i].contains("RB")) {
				conceito.add(powa[i].replace("/RB", ""));

			} else if (powa[i].contains("VBZ")) {
				predicado.add(powa[i].replace("/VBZ", ""));

			} else if (powa[i].contains("VBN")) {
				predicado.add(powa[i].replace("/VBN", ""));

			} else if (powa[i].contains("VBP")) {
				predicado.add(powa[i].replace("/VBP", ""));

			} else if (powa[i].contains("VBG")) {
				predicado.add(powa[i].replace("/VBG", ""));

			} else if (powa[i].contains("VBD")) {
				predicado.add(powa[i].replace("/VBD", ""));

			} else if (powa[i].contains("VB")) {
				predicado.add(powa[i].replace("/VB", ""));

			}

		}

		// System.out.println(powa[0]);

		// System.out.println(rawWords2.get();

		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();

		// System.out.println(tdl);
		String teste = tdl.toString();
		// System.out.println("imprimindo teste");
		// System.out.println(teste);
		String carta = teste.replace(",", "");
		// String carta_que_vai _ser_apagada = teste_inexistente.replace =
		// teste.toString();
		String carta1 = carta.replace("[", "");
		String carta2 = carta1.replace("]", "");
		String carta3 = carta2.replace("(", " ");
		String carta4 = carta3.replace(")", "/");
		// System.out.println(carta4);
		String[] nsubj = carta4.toString().split("/");
		ArrayList<String> separacao = new ArrayList<String>();
		for (int i = 0; i < nsubj.length; i++) {

			if (nsubj[i].contains("nsubj")) {
				separacao.add(nsubj[i].replace("nsubj ", ""));

			}
		}

		String acabando = separacao.toString().replace("[", "");
		String acabando1 = acabando.toString().replace("]", "");

		String acabando2 = acabando1.toString().replace(" ", "");

		String[] finalmente = acabando2.split(",");

		for (int i = 0; i < finalmente.length; i++) {
			// System.out.println("imprimindo Rela��o:"+finalmente[i]);
		}

		FileWriter relacao;
		try {
			relacao = new FileWriter(new File("ArquivoRelacao.txt"));
			for (int i = 0; i < (finalmente.length) - 1; i = i + 2) {
				relacao.write(finalmente[i] + " ");
				relacao.write("have a relationship whith ");
				relacao.write(finalmente[(i + 1)] + "\n\r");
			}

			relacao.close();
		} catch (IOException j) {
			j.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println("est� na estrutura gramatical");
		// System.out.println("");

		TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
		// tp.printTree(parse);

		System.out.println("---------------");
		System.out.println("todos os conceitos");
		for (int i = 0; i < conceito.size(); i++) {
			 System.out.println(conceito.get(i));

		}
		System.out.println("---------------");
		System.out.println("Intancias");
		for (int i = 0; i < instancia.size(); i++) {
			 System.out.println(instancia.get(i));

		}
		System.out.println("---------------");
		System.out.println("Predicados");
		for (int i = 0; i < predicado.size(); i++) {
			 System.out.println(predicado.get(i));

		}

		String textoQueSeraEscrito = "Texto que sera escrito.";

		FileWriter arquivoPredicado;
		try {
			arquivoPredicado = new FileWriter(new File("ArquivoPredicado.txt"));
			for (int i = 0; i < predicado.size(); i++) {
				arquivoPredicado.write(predicado.get(i));

			}

			arquivoPredicado.close();
		} catch (IOException j) {
			j.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileWriter arquivoConceito;
		try {
			arquivoConceito = new FileWriter(new File("ArquivoConceito.txt"));
			for (int i = 0; i < conceito.size(); i++) {
				arquivoConceito.write(conceito.get(i));

			}

			arquivoConceito.close();
		} catch (IOException j) {
			j.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileWriter arquivoInstancia;
		try {
			arquivoInstancia = new FileWriter(new File("ArquivoInstancias.txt"));
			for (int i = 0; i < instancia.size(); i++) {
				arquivoInstancia.write(instancia.get(i));

			}

			arquivoInstancia.close();
		} catch (IOException j) {
			j.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("-------------------------");
		// System.out.println("instancias e suas respecivas ações");

		for (int i = 0; i < instancia.size(); i++) {

			for (int j = 0; j < nsubj.length; j++) {
				for (int k = 0; k < predicado.size(); k++) {
					if (nsubj[j].toString().contains(instancia.get(i))
							&& nsubj[j].toString().contains(predicado.get(k))) {

						String frase = instancia.get(i).toString() + ""
								+ predicado.get(k).toString();
						// System.out.println(frase);

					}

				}

			}

		}
		ArrayList<String> periodosDasFrases = new ArrayList<String>();
		String converteSimbolo = convert.replace("./.", ",/,");
		// System.out.println("impressao de converteSimbolo");
		// System.out.println(converteSimbolo);
		String[] auxiliarConvet = converteSimbolo.split(",/,");
		for (int i = 0; i < auxiliarConvet.length; i++) {
			periodosDasFrases.add(auxiliarConvet[i]);
			// System.out.println("impressão de auxiliar convert");
			// System.out.println(auxiliarConvet[i]);
		}
		System.out.println("Relações-");
		ArrayList<String> imprimirRelacao = new ArrayList<String>();
		for (int i = 0; i < periodosDasFrases.size(); i++) {
			if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NN"))
							&& (periodosDasFrases.get(i).toString()
									.contains("CC")) && (periodosDasFrases.get(
							i).toString().contains("NNP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CC", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);

			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/NN", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NN"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNPS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/PRP", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");

				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/PRP$", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/CD", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}
			// ultima modificação
			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/CD", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");

				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/DT", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			// segundo tipo de verbo (VBP)

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NN"))
							&& (periodosDasFrases.get(i).toString()
									.contains("CC")) && (periodosDasFrases.get(
							i).toString().contains("NNP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CC", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);

			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/NN", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NN"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNPS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/PRP", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");

				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/PRP$", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/CD", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String modi2 = resultado1.toString().replace("/NNS", "");
				String modi3 = modi2.toString().replace("/CD", "");
				String resultado5 = modi3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/DT", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBP")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBP", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			// TERCEIRA DIVISÃO(VBN)
			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NN"))
							&& (periodosDasFrases.get(i).toString()
									.contains("CC")) && (periodosDasFrases.get(
							i).toString().contains("NNP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CC", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);

			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/NN", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NN"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNPS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/PRP", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");

				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/PRP$", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/CD", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String modi2 = resultado1.toString().replace("/NNS", "");
				String modi3 = modi2.toString().replace("/CD", "");
				String resultado5 = modi3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/DT", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBN")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBN", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			// QUARTA DIVISÃO(VBG)

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NN"))
							&& (periodosDasFrases.get(i).toString()
									.contains("CC")) && (periodosDasFrases.get(
							i).toString().contains("NNP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CC", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);

			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/NN", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NN"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNPS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/PRP", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");

				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/PRP$", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/CD", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String modi2 = resultado1.toString().replace("/NNS", "");
				String modi3 = modi2.toString().replace("/CD", "");
				String resultado5 = modi3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/DT", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBG")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBG", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			// QUINTA DIVISÃO(VBD)
			// única diferente dos demais caso(cc,nnp,vbd,dt,nnp)
			else if (((periodosDasFrases.get(i).toString().contains("CC")) && (periodosDasFrases
					.get(i).toString().contains("NNP")))
					&& ((periodosDasFrases.get(i).toString().contains("VBD"))
							&& (periodosDasFrases.get(i).toString()
									.contains("DT")) && (periodosDasFrases.get(
							i).toString().contains("NNP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CC", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);

			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NN"))
							&& (periodosDasFrases.get(i).toString()
									.contains("CC")) && (periodosDasFrases.get(
							i).toString().contains("NNP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CC", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);

			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/NN", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NN"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNPS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/PRP", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");

				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/PRP$", "");
				imprimirRelacao.add(resultado2);
			}
			// aparti daki n tem
			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/CD", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String modi2 = resultado1.toString().replace("/NNS", "");
				String modi3 = modi2.toString().replace("/CD", "");
				String resultado5 = modi3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/DT", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBD")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado0 = resultado.replace("and", "");
				String resultado1 = resultado0.replace("/VBD", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			// SEXTA DIVISÃO(VB)
			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NN"))
							&& (periodosDasFrases.get(i).toString()
									.contains("CC")) && (periodosDasFrases.get(
							i).toString().contains("NNP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CC", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);

			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado3);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");
				String resultado4 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado4);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/NN", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado4 = resultado2.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NN"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("CC") && (periodosDasFrases
							.get(i).toString().contains("NNPS"))))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/NNS", "");
				String resultado4 = resultado3.toString().replace("/CC", "");
				String resultado5 = resultado4.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/PRP", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP", "");

				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/PRP$", "");
				imprimirRelacao.add(resultado2);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NNPS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NN")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("NNS")) && (periodosDasFrases
							.get(i).toString().contains("PRP$")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/PRP$", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/CD", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

			else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/CD", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VBZ")))
					&& ((periodosDasFrases.get(i).toString().contains("CD")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VBZ", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String modi3 = resultado2.toString().replace("/CD", "");
				String resultado5 = modi3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/DT", "");
				String resultado5 = resultado2.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNPS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNPS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NN")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NN", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			} else if (((periodosDasFrases.get(i).toString().contains("NNP")) && (periodosDasFrases
					.get(i).toString().contains("VB")))
					&& ((periodosDasFrases.get(i).toString().contains("DT")) && (periodosDasFrases
							.get(i).toString().contains("NNS")))) {

				String resultado = periodosDasFrases.get(i).toString()
						.replace("/NNP", "");
				String resultado1 = resultado.replace("/VB", "");
				String resultado2 = resultado1.toString().replace("/NNS", "");
				String resultado3 = resultado2.toString().replace("/DT", "");
				String resultado5 = resultado3.toString().replace(",", "");
				imprimirRelacao.add(resultado5);
			}

		}
		// tentativa de retirar o inicio com CC
		for (int i = 0; i < imprimirRelacao.size(); i++) {

			if (imprimirRelacao.get(i).contains("/CC")) {
				imprimirRelacao.remove(i);

			}
		}

		if (((imprimirRelacao.toString().contains("/PRP$"))
				|| ((imprimirRelacao.toString().contains("/DT"))) || (((imprimirRelacao
					.toString().contains("/CD")))))
				|| ((imprimirRelacao.toString().contains("sS")))) {

			try {
				String RelacaoSemPRP$ = imprimirRelacao.toString().replace(
						"/PRP$", "");
				String RelacaoSemDT = RelacaoSemPRP$.replace("/DT", "");
				String RelacaoSemAND = RelacaoSemDT.replace("and", "");
				String RelacaoSemCD = RelacaoSemAND.replace("/CD", "");
				String RelacaoSemS = RelacaoSemCD.replace("sS", "s");
				relacaoFinal = RelacaoSemS;
				 System.out.println("impressão das relações final");
				 System.out.println("-------------------------------------------------------");
				 System.out.println(relacaoFinal);

			} catch (Exception e) {
			}

			/*
			 * for(int i=0;i<imprimirRelacao.size();i++){
			 * 
			 * System.out.println(imprimirRelacao.get(i)); }
			 */

		}
		return relacaoFinal;
	}

	public void setLp(LexicalizedParser lp) {
		this.lp = lp;
	}
}
