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
import uag.bcc.ia.texto.TextoUtil;

/**
 *
 * @author Arnaldo Sales
 * @author Eltton Tullyo
 * @author ramonsantos
 *
 */
public class SPHelper {

    private static LexicalizedParser lp;
    private static SPHelper instanceParser = null;

    private SPHelper() {

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

    public List<String> getListaConceito(String frase) {

        List<TaggedWord> listaConceito = this.getListTaggerWord(frase);

        List<String> listaR = new ArrayList<>();

        for (TaggedWord iLista : listaConceito) {

            if (iLista.toString().endsWith("NNS")) {

                listaR.add(iLista.toString().replace("/NNS", ""));

            } else if (iLista.toString().endsWith("NN")) {

                listaR.add(iLista.toString().replace("/NN", ""));

            } else if (iLista.toString().endsWith("RB")) {

                listaR.add(iLista.toString().replace("/RB", ""));

            } else {

            }

        }

        return listaR;

    }

    public List<String> getListaInstancia(String frase) {

        List<TaggedWord> listaInstancia = this.getListTaggerWord(frase);

        List<String> listaR = new ArrayList<>();

        for (TaggedWord iLista : listaInstancia) {

            if (iLista.toString().endsWith("NNPS")) {

                listaR.add(iLista.toString().replace("/NNPS", ""));

            } else if (iLista.toString().endsWith("NNP")) {

                listaR.add(iLista.toString().replace("/NNP", ""));

            } else {

            }

        }

        return listaR;

    }

    public List<String> getListaPredicado(String frase) {

        List<TaggedWord> listaPredicado = this.getListTaggerWord(frase);

        List<String> listaR = new ArrayList<>();

        for (TaggedWord iLista : listaPredicado) {

            if (iLista.toString().endsWith("VB")) {

                listaR.add(iLista.toString().replace("/VB", ""));

            } else if (iLista.toString().endsWith("VBD")) {

                listaR.add(iLista.toString().replace("/VBD", ""));

            } else if (iLista.toString().endsWith("VBZ")) {

                listaR.add(iLista.toString().replace("/VBZ", ""));

            } else if (iLista.toString().endsWith("VBN")) {

                listaR.add(iLista.toString().replace("VBN", ""));

            } else if (iLista.toString().endsWith("VBP")) {

                listaR.add(iLista.toString().replace("/VBP", ""));

            } else if (iLista.toString().endsWith("VBG")) {

                listaR.add(iLista.toString().replace("/VBG", ""));

            } else {

            }

        }

        return listaR;

    }

    public List<String> getListaRelacao(String frase) {

        List<String> listaR = new ArrayList<>();

        return listaR;

    }

    public List<TaggedWord> getListTaggerWord(String frase) {

        //Retirar pontuação
        String l = TextoUtil.getInstanceTextoUtil().retirarPontuacao(frase);
      //  String textLimpo = TextoUtil.getInstanceTextoUtil().listaToString(l);
System.out.println(l);
        String[] sent = l.split(" ");

        List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);

        Tree parse = lp.apply(rawWords);

        return parse.taggedYield();

    }

}
