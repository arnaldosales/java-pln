package uag.bcc.ia.wordnet;

import java.util.List;

/**
 *
 * @author ramonsantos
 *
 */
public class WNHelper {

    private static WNHelper instance = null;
    private final Sinonimos sin;
    private final ClassePalavra pal;
    private final Substantivo sub;

    public WNHelper() {

        sin = Sinonimos.getInstanceSinonimos();
        pal = new ClassePalavra();
        sub = Substantivo.getInstanceSubstantivo();

    }
    
    public static WNHelper getInstance(){
        
        if(instance == null){
            
            instance = new WNHelper();
            
        }
        
        return instance;
        
    }

    /**
     * Método que fornece uma lista de sinônimos, dado uma palavra.
     *
     * @param lemma - palavra de entrada.
     * @return - lista de sinônimos da palavra de entrada.
     */
    public List<String> getListaSinonimos(String lemma) {

        return sin.getListaSinonimos(lemma);

    }

    /**
     * Este método identifica se determinado lemma é um substantivo.
     *
     * @param lemma - palavra a ser pesquisada.
     * @return true - se lemma for um substantivo e false caso contrario.
     */
    public boolean isSubstantivo(String lemma) {

        return pal.isSubstantivo(lemma);

    }

    /**
     * Método responsável por prover a definição de uma palavra.
     *
     * @param lemma - palavra de entrada
     * @return definição da palavra de entrada
     */
    public String getDefinicao(String lemma) {

        return sub.getDefinicao(lemma);

    }

}
