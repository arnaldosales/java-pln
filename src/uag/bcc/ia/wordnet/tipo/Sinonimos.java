package uag.bcc.ia.wordnet.tipo;

import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IPointer;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import java.util.ArrayList;
import java.util.List;
import uag.bcc.ia.texto.TextoUtil;
import uag.bcc.ia.wordnet.Dicionario;

public class Sinonimos {

    private static Sinonimos instanceSinonimos = null;
    
    private Sinonimos(){
    
    }
    
    public static Sinonimos getInstanceSinonimos(){
    
        if(instanceSinonimos == null){
            
            instanceSinonimos = new Sinonimos();
            
        }
        
        return instanceSinonimos;
    }
    
    private List<IWordID> getListaIWordID(String lemma) {

        //index da palavra 'dog'
        IIndexWord idxWord = Dicionario.getDicionario().getIndexWord(lemma, POS.NOUN);

        //lista dos ids dos sentidos
        List<IWordID> listaWordID = idxWord.getWordIDs();

        return listaWordID;
    }

    private List<IWord> getListaIWord(List<IWordID> listaIDs) {

        List<IWord> listaR = new ArrayList<IWord>();

        for (int i = 0; i < listaIDs.size(); i++) {

            IWord word = Dicionario.getDicionario().getWord(listaIDs.get(i));

            listaR.add(word);

        }

        return listaR;
    }

    private List<ISynset> getListaISynset(List<IWord> listaWord) {

        List<ISynset> listaR = new ArrayList<ISynset>();

        for (int i = 0; i < listaWord.size(); i++) {

            ISynset synset = listaWord.get(i).getSynset();

            listaR.add(synset);

        }

        return listaR;
    }

    private List<List<IWord>> getListaDeListaIWord(List<ISynset> listaSynset) {

        List<IWord> lista;

        List<List<IWord>> listaR = new ArrayList<List<IWord>>();

        for (int i = 0; i < listaSynset.size(); i++) {

            lista = listaSynset.get(i).getWords();

            listaR.add(lista);

        }
        
        return listaR;

    }

    public List<String> getListaSinonimos(String lemma) {

        List<IWordID> l1 = getListaIWordID(lemma);

        List<IWord> l2 = getListaIWord(l1);

        List<ISynset> l3 = getListaISynset(l2);

        List<List<IWord>> l4 = getListaDeListaIWord(l3);

        List<String> listaR = new ArrayList<String>();
        
        for (int i = 0; i < l4.size(); i++) {
          

            for (int j = 0; j < l4.get(i).size(); j++) {

                listaR.add(l4.get(i).get(j).getLemma());

            }

        }
        
        listaR = TextoUtil.getInstanceTextoUtil().eliminarPalavrasRepetidas(listaR);
        
        return listaR;

    }
   
    public boolean areSinonimos(String lemma1, String lemma2) {

        return false;

    }
    
    
    
    
    
    
    
    
    

}
