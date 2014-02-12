package uag.bcc.ia.texto;

import uag.bcc.ia.exception.FraseMalFormuladaException;

public class FraseUtil {

    private int getTipoFrase(String frase) {

        char[] cadeiaFrase = frase.toCharArray();

        int ultimaPosicao = cadeiaFrase.length - 1;

        int penultimaPosicao = ultimaPosicao - 1;

        if (cadeiaFrase[ultimaPosicao] == '!') {

            return 0;

        } else if (cadeiaFrase[ultimaPosicao] == '.') {

            return 1;

        } else if (cadeiaFrase[ultimaPosicao] == '?') {

            return 2;

        } else if (cadeiaFrase[penultimaPosicao] == '!') {

            return 0;

        } else if (cadeiaFrase[penultimaPosicao] == '.') {

            return 1;

        } else if (cadeiaFrase[penultimaPosicao] == '?') {

            return 2;

        } else {

            return -1;

        }

    }

    public boolean isPergunta(String frase) throws FraseMalFormuladaException {

        int tipoFrase = getTipoFrase(frase);

        if (tipoFrase == 0 || tipoFrase == 1) {

            return false;

        } else if (tipoFrase == 2) {

            return true;

        } else {

            throw new FraseMalFormuladaException("Frase mal formulada!");
        }

    }
   
}