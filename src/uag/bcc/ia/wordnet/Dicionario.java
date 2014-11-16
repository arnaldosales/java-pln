package uag.bcc.ia.wordnet;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Classe de acesso ao Dicionário Wordnet.
 * 
 * @author Ramon Santos
 * @author Wagner Souza
 * @author Isabelle Ferreira
 */
public class Dicionario {

	// Atributos referêntes ao endereço local do dicionário.
	private static String caminhoDicionarioLocal;
	private static URL urlDicionarioLocal;

	private static IDictionary dicionario = null;

	private static void newDicionario() {

		caminhoDicionarioLocal = "";

		// Escolhe o endereço independente do S.O.
		if (System.getProperty("os.name").equals("Linux")) {

			caminhoDicionarioLocal = "/usr/share/wordnet";

		} else {

			caminhoDicionarioLocal = "C:\\Program Files (x86)\\WordNet\\2.1\\dict";

		}

		// Dicionário WordNet local.
		try {

			caminhoDicionarioLocal = caminhoDicionarioLocal + File.separator;
			urlDicionarioLocal = new URL("file", null, caminhoDicionarioLocal);

			// Para acessar o dicionário de dados, tem-se que usar uma
			// implementação da interface IDictionary.
			dicionario = new Dictionary(urlDicionarioLocal);
			// Abre o dicionário de dados.
			dicionario.open();

		} catch (IOException e) {
            
		}
	}

	/**
	 * Método responsável por prover a instância do Dicionário.
	 * 
	 * @return - Instância do Dicionário.
	 */
	public static IDictionary getDicionario() {

		if (dicionario == null) {

			newDicionario();
		}

		return dicionario;
	}

}