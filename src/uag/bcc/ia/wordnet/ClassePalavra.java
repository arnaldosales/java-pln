package uag.bcc.ia.wordnet;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

/**
 * 
 * @author Ramon Santos
 * @author Wagner Souza
 * @author Isabelle Ferreira
 */
public class ClassePalavra {

    /**
     * Objeto que representa o índece de uma linha nos arquivos de base do
     * WordNet
     */
    protected IIndexWord iIndexWord;

    /**
     * Identificador único com o qual é possível recuperar uma determinada
     * palavra na base do WordNet
     */
    protected IWordID iWordID;

    /**
     * Índice da palavra que é emparelhada com um synset
     */
    protected IWord iWord;

    /**
     * Representa um synset
     */
    protected ISynset iSynset;

    /**
     * Este método identifica se determinado lemma é um substantivo
     *
     * @param lemma - palavra a ser pesquisada
     * @return true - se lemma for um substantivo e false caso contrario
     */
    public boolean isSubstantivo(String lemma) {

        if (lemma.equals(",") || lemma.equals("!") || lemma.equals(";") || lemma.equals(":") || lemma.equals(".") || lemma.equals("?")) {
            return false;
        }

        IIndexWord a = Dicionario.getDicionario().getIndexWord(lemma, POS.NOUN);

        if (a == null) {

            return false;

        } else {

            return true;

        }
    }

    public boolean isVerbo(String lemma) {

        return false;
    }

    public boolean isAdverbio(String lemma) {

        return false;
    }

    public boolean isAdjetivo(String lemma) {

        return false;
    }

    protected String getDefinicaoGeral(String lemma, int op) {

        IDictionary dicionario = Dicionario.getDicionario();

        IIndexWord idWord;

        switch (op) {

            case 1:
                idWord = dicionario.getIndexWord(lemma, POS.NOUN);
                break;

            case 2:
                idWord = dicionario.getIndexWord(lemma, POS.ADJECTIVE);
                break;

            case 3:
                idWord = dicionario.getIndexWord(lemma, POS.VERB);
                break;

            case 4:
                idWord = dicionario.getIndexWord(lemma, POS.ADVERB);
                break;

            default:
                return null;
        }

        IWordID idSignificado = idWord.getWordIDs().get(0);

        IWord word = dicionario.getWord(idSignificado);

        String definicao = word.getSynset().getGloss();

        return definicao;

    }

}