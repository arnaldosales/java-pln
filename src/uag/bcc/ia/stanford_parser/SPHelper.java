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
import org.semanticweb.owlapi.model.OWLClass;
import uag.bcc.ia.owl.OWLHelper;
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

    public void gerarRelacaoOWL(String frase) {

        List<TaggedWord> lTags = this.getListTaggerWord(frase);

        //Bolinha is a cat.
        for (int i = 0; i < lTags.size(); i++) {

            try {
                //Bolinha is a cat.
                if (lTags.get(i).tag().equals("NNP") && lTags.get(i + 1).tag().equals("VBZ") && lTags.get(i + 2).tag().equals("DT") && lTags.get(i + 3).tag().equals("NN")) {

                    OWLClass classe = OWLHelper.getOWLHelper().addClasse(lTags.get(i + 3).value());
                    OWLHelper.getOWLHelper().addInstancia(lTags.get(i).value(), classe);

                }

                //Bolinha and Baleia are dog
                if (lTags.get(i).tag().equals("NNP")
                    && lTags.get(i + 1).tag().equals("CC")
                    && lTags.get(i + 2).tag().equals("NNP") 
                    && lTags.get(i + 3).tag().equals("VBP") 
                    && (lTags.get(i + 4).tag().equals("NNS") || lTags.get(i + 4).tag().equals("NN"))) {

                    OWLClass classe = OWLHelper.getOWLHelper().addClasse(lTags.get(i + 4).value());
                    OWLHelper.getOWLHelper().addInstancia(lTags.get(i).value(), classe);
                    OWLHelper.getOWLHelper().addInstancia(lTags.get(i + 2).value(), classe);

                }

            } catch (Exception e) {

            }

        }

    }

    public List<TaggedWord> getListTaggerWord(String frase) {

        // Retirar pontuação
        // String l = TextoUtil.getInstanceTextoUtil().retirarPontuacao(frase);
        // String textLimpo = TextoUtil.getInstanceTextoUtil().listaToString(l);
        String[] sent = frase.split(" ");

        List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);

        Tree parse = lp.apply(rawWords);

        return parse.taggedYield();

    }

}
