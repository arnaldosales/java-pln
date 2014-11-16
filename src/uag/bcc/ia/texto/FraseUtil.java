package uag.bcc.ia.texto;

/**
 * Classe responsável por modelar, indetificar, manipular e prover frases.
 * 
 * @author Ramon Santos
 * @author Arnaldo Sales
 * @author Wagner Souza
 */
public class FraseUtil {

	// Constantes referêntes ao tipo de frase
	private static final int TIPO_EXCLAMACAO = 0;
	private static final int TIPO_NORMAL = 1;
	private static final int TIPO_PERGUNTA = 2;

	// Instancia única da classe
	private static FraseUtil instanceFraseUtil = null;

	private FraseUtil() {

	}

	/**
	 * Recupera a instância desta classe.
	 * 
	 * @return Instância da classe
	 */
	public static FraseUtil getInstanceFraseUtil() {

		if (instanceFraseUtil == null) {

			instanceFraseUtil = new FraseUtil();
		}

		return instanceFraseUtil;
	}

	/*
	 * Verifica qual o tipo de frase baseado no sinal(ponto) final.
	 * 
	 * @param frase Frase a ser analizada.
	 * 
	 * @return Constante inteira representando o tipo de frase ou (-1) caso a
	 * frase em questão seja inválida.
	 */
	private int getTipoFrase(String frase) {

		// Quebra a frase em um array de caracter
		char[] cadeiaFrase = frase.toCharArray();

		int ultimaPosicao = cadeiaFrase.length - 1;

		int penultimaPosicao = ultimaPosicao - 1;

		/*
		 * Testa se o último caracter da frase é um sinal válido, caso não
		 * encontre um sinal válido, a comparação é repassada para o penúltimo
		 * caracter
		 */
		if (cadeiaFrase[ultimaPosicao] == '!') {

			return TIPO_EXCLAMACAO;

		} else if (cadeiaFrase[ultimaPosicao] == '.') {

			return TIPO_NORMAL;

		} else if (cadeiaFrase[ultimaPosicao] == '?') {

			return TIPO_PERGUNTA;

		} else if (cadeiaFrase[penultimaPosicao] == '!') {

			return TIPO_EXCLAMACAO;

		} else if (cadeiaFrase[penultimaPosicao] == '.') {

			return TIPO_NORMAL;

		} else if (cadeiaFrase[penultimaPosicao] == '?') {

			return TIPO_PERGUNTA;

		} else {

			return -1;

		}

	}

	/**
	 * Verifica se determinado frase é do tipo pergunta.
	 * 
	 * @param frase
	 *            Frase a ser analizada
	 * @return resposta - true caso a frase seja uma pergunta ou false caso
	 *         contrário
	 * @throws FraseMalFormuladaException
	 */
	public boolean isPergunta(String frase) throws Exception {

		int tipoFrase = getTipoFrase(frase);

		if (tipoFrase == 0 || tipoFrase == 1) {

			return false;

		} else if (tipoFrase == 2) {

			return true;

		} else {

			throw new Exception("Frase mal formulada!");
		}

	}

}
