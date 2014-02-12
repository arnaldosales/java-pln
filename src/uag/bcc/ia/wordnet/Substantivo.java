package uag.bcc.ia.wordnet;

public class Substantivo extends ClassePalavra {

    private static Substantivo instanceSubstantivo = null;

    private Substantivo() {

    }

    public static Substantivo getInstanceSubstantivo() {

        if (instanceSubstantivo == null) {

            instanceSubstantivo = new Substantivo();

        }

        return instanceSubstantivo;
    }

    public String getDefinicao(String lemma) {

        String definicaoR = this.getDefinicaoGeral(lemma, 1);

        definicaoR = this.retirarExemplosResposta(definicaoR);
        
        return definicaoR;

    }

    private String retirarExemplosResposta(String frase) {

        String fraseR = frase;
        int indexA = fraseR.indexOf(";");
        int indexB = fraseR.indexOf(":");

        if (indexA != -1) {

            fraseR = fraseR.substring(0, indexA);

        }
        if (indexB != -1) {

            fraseR = fraseR.substring(0, indexB);

        }

        return fraseR;

    }
    
}