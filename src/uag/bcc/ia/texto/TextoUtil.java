package uag.bcc.ia.texto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por modelar, obter, idetificar, manipular e prover Texto.
 * 
 * @author Ramon Santos
 * @author Arnaldo Sales
 * @author Wagner Souza
 */
public class TextoUtil {

	// Instância única da classe
	private static TextoUtil instanceTextoUtil = null;

	private TextoUtil() {

	}

	/**
	 * Recupera a instância deste classe.
	 * 
	 * @return Instância da classe
	 */
	public static TextoUtil getInstanceTextoUtil() {

		if (instanceTextoUtil == null) {

			instanceTextoUtil = new TextoUtil();

		}

		return instanceTextoUtil;

	}

	/**
	 * Lê um arquivo de texto e retorna uma String com seu conteúdo.
	 * 
	 * @param urlArquivo
	 * @return Texto do arquivo
	 */
	public String lerArquivoTexto(String urlArquivo) {

		String stringResult = "";

		try {

			BufferedReader br = new BufferedReader(new FileReader(urlArquivo));

			while (br.ready()) {

				String linha = br.readLine();
				stringResult = stringResult + linha;
				stringResult = stringResult + "\n";

			}

			br.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return stringResult;
	}

	/**
	 * Método para quebrar uma texto em palavras.
	 * 
	 * @param texto
	 *            - Texto de entrada.
	 * @return - Lista contendo as palavras do texto de entrada.
	 */
	public List<String> quebrarTextoEmPalavras(String texto) {

		String[] textoQuebrado = texto.split(" ");

		List<String> listaR = new ArrayList<String>();

		for (int i = 0; i < textoQuebrado.length; i++) {

			if (textoQuebrado[i].endsWith(",")) {

				listaR.add(textoQuebrado[i].replaceAll(",", ""));
				listaR.add(",");

			} else if (textoQuebrado[i].endsWith("!")) {

				listaR.add(textoQuebrado[i].replaceAll("!", ""));
				listaR.add("!");

			} else if (textoQuebrado[i].endsWith(";")) {

				listaR.add(textoQuebrado[i].replaceAll(";", ""));
				listaR.add(";");

			} else if (textoQuebrado[i].endsWith(":")) {

				listaR.add(textoQuebrado[i].replaceAll(":", ""));
				listaR.add(";");

			} else if (textoQuebrado[i].endsWith(".")) {

				listaR.add(textoQuebrado[i].replaceAll(".", ""));
				listaR.add(".");

			} else if (textoQuebrado[i].endsWith("?")) {

				listaR.add(textoQuebrado[i].replaceAll("?", ""));
				listaR.add("?");

			} else {

				listaR.add(textoQuebrado[i]);

			}
		}

		return listaR;

	}
    
    public String retirarPontuacao(String t ) {

        String r = t;
       
      
        
       // r.replaceAll("!", "");
    
       r.replaceAll(",", " ");
       
        System.out.println(">"+r);
    //    r.replaceAll(";", "");
    //    r.replaceAll(":", "");
     //   r.replaceAll(".", "");
     //   r.replaceAll("?", "");
                
		return r;

	}

	/**
	 * Método para eliminar palavras repetidas em uma lista de palavras.
	 * 
	 * @param listaPalavras
	 *            - lista de entrada.
	 * @return - lista sem repetição de palavras.
	 */
	public List<String> eliminarPalavrasRepetidas(List<String> listaPalavras) {

		List<String> listaR = new ArrayList<String>();

		for (int i = 0; i < listaPalavras.size(); i++) {

			if (listaR.indexOf(listaPalavras.get(i)) == -1) {

				listaR.add(listaPalavras.get(i));
			}
		}

		return listaR;

	}

	/**
	 * Método que transforma uma lista de palavras, em uma String.
	 * 
	 * @param listaPalavras
	 *            - lista das palavras.
	 * @return - String final.
	 */
	public String listaToString(List<String> listaPalavras) {

		String textoR = "";

		for (int i = 0; i < listaPalavras.size(); i++) {

			textoR = textoR + listaPalavras.get(i);

			if (!(i == (i - 1))) {
				textoR = textoR + "\n";
			}
		}

		return textoR;

	}

	/**
	 * Método que verifica se uma palavras começa com uma vogal.
	 * 
	 * @param palavra
	 *            - palavra a ser verificada.
	 * @return - true caso a palavra começe com vogal, false caso contrário.
	 */
	public boolean isPrimeiraLetraVogal(String palavra) {

		char primeiraLetraPalavra = palavra.charAt(0);

		if (primeiraLetraPalavra == 'a' || primeiraLetraPalavra == 'e'
				|| primeiraLetraPalavra == 'i' || primeiraLetraPalavra == 'o'
				|| primeiraLetraPalavra == 'u') {

			return true;

		} else {

			return false;

		}

	}

}
