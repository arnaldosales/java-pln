package uag.bcc.ia.wordnet;

/**
 * Classe que modela um substantivo do dicionário.
 * 
 * @author Ramon Santos
 * @author Wagner Souza
 * @author Isabelle Ferreira
 */
public class Substantivo extends ClassePalavra {

	private static Substantivo instanceSubstantivo = null;

	private Substantivo() {

	}

	/**
	 * Método responsável por prover a instância desta classe.
	 * 
	 * @return
	 */
	public static Substantivo getInstanceSubstantivo() {

		if (instanceSubstantivo == null) {

			instanceSubstantivo = new Substantivo();

		}

		return instanceSubstantivo;
	}

	/**
	 * Método responsável por prover a definição de uma palavra.
	 * 
	 * @param lemma
	 *            - palavra de entrada
	 * @return definição da palavra de entrada
	 */
	public String getDefinicao(String lemma) {

		String definicaoR = this.getDefinicaoGeral(lemma);

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