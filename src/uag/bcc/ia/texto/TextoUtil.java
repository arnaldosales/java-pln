package uag.bcc.ia.texto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por modelar, obter, indetificar, manipular e prover Texto.
 * @author Ramon Santos
 * @author Arnaldo Sales
 * @author Wagner Souza
 */
public class TextoUtil {

	//Instância única da classe
    private static TextoUtil instanceTextoUtil = null;

    private TextoUtil() {

    }
    
	/**
	 * Recupera a instância deste classe
	 * @return Instância da classe
	 */
    public static TextoUtil getInstanceTextoUtil() {

        if (instanceTextoUtil == null) {

            instanceTextoUtil = new TextoUtil();

        }

        return instanceTextoUtil;

    }

    /**
     * Lê um arquivo de texto e retorna uma String com seu conteúdo
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

    public List<String> eliminarPalavrasRepetidas(List<String> listaPalavras) {

        List<String> listaR = new ArrayList<String>();

        for (int i = 0; i < listaPalavras.size(); i++) {

            if (listaR.indexOf(listaPalavras.get(i)) == -1) {

                listaR.add(listaPalavras.get(i));
            }
        }

        return listaR;

    }

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

    public boolean isPrimeiraLetraVogal(String palavra) {

        char primeiraLetraPalavra = palavra.charAt(0);

        if (primeiraLetraPalavra == 'a' || primeiraLetraPalavra == 'e' || primeiraLetraPalavra == 'i' || primeiraLetraPalavra == 'o' || primeiraLetraPalavra == 'u') {
            return true;
        } else {
            return false;
        }
    }
    
    //Errado--
    private String[] retirarFraseTexto(String texto, char tipo) {

        String[] frasesR = texto.split(tipo + "");

        for (int i = 0; i < frasesR.length; i++) {

            frasesR[i] = frasesR[i] + tipo;

            if (frasesR[i].indexOf(" ") == 0) {

                frasesR[i] = frasesR[i].substring(1, frasesR[i].length());

            }

        }

        if (!(texto.endsWith(tipo + ""))) {

            int index = frasesR[frasesR.length - 1].lastIndexOf(tipo + "");
            frasesR[frasesR.length - 1] = frasesR[frasesR.length - 1].substring(0, (index - 1));

        }
        return frasesR;
    }

    private List<String> quebrarTextoEmFrases(String texto) {

        return null;

    }
}
