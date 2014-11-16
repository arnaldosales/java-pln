package uag.bcc.ia.stanford_parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;
import org.semanticweb.owlapi.model.OWLClass;
import uag.bcc.ia.owl.OWLHelper;

/**
 *
 * @author Arnaldo Sales
 * @author Eltton Tullyo
 * @author ramonsantos
 * @author ramonsantos
 * 
 */
public class SPHelper {

    private static LexicalizedParser lp;
    private static SPHelper instanceParser = null;

    private SPHelper() {

        lp = LexicalizedParser.loadModel("res/englishPCFG.ser.gz");

    }

    public static SPHelper getInstanceParser() {

        if (instanceParser == null) {

            instanceParser = new SPHelper();
        }

        return instanceParser;

    }

    public LexicalizedParser getLp() {

        return lp;

    }

    public Tree getTree(String texto) {

        //TreeSet
        TokenizerFactory<CoreLabel> tokens = PTBTokenizer.factory(
            new CoreLabelTokenFactory(), "");

        List<CoreLabel> listaCorelabel = tokens.getTokenizer(
            new StringReader(texto)).tokenize();

        Tree parseTree = lp.apply(listaCorelabel);

        return parseTree;

    }

    public List<String> getListaConceito(String frase) {

        List<TaggedWord> listaConceito = this.getListTaggerWord(frase);

        List<String> listaR = new ArrayList<>();

        for (TaggedWord iLista : listaConceito) {

            if (iLista.toString().endsWith("NNS")) {

                listaR.add(iLista.toString().replace("/NNS", ""));

            } else if (iLista.toString().endsWith("NN")) {

                listaR.add(iLista.toString().replace("/NN", ""));

            } else if (iLista.toString().endsWith("RB")) {

                listaR.add(iLista.toString().replace("/RB", ""));

            } else {

            }

        }

        return listaR;

    }

    public List<String> getListaInstancia(String frase) {

        List<TaggedWord> listaInstancia = this.getListTaggerWord(frase);

        List<String> listaR = new ArrayList<>();

        for (TaggedWord iLista : listaInstancia) {

            if (iLista.toString().endsWith("NNPS")) {

                listaR.add(iLista.toString().replace("/NNPS", ""));

            } else if (iLista.toString().endsWith("NNP")) {

                listaR.add(iLista.toString().replace("/NNP", ""));

            } else {

            }

        }

        return listaR;

    }

    public List<String> getListaPredicado(String frase) {

        List<TaggedWord> listaPredicado = this.getListTaggerWord(frase);

        List<String> listaR = new ArrayList<>();

        for (TaggedWord iLista : listaPredicado) {

            if (iLista.toString().endsWith("VB")) {

                listaR.add(iLista.toString().replace("/VB", ""));

            } else if (iLista.toString().endsWith("VBD")) {

                listaR.add(iLista.toString().replace("/VBD", ""));

            } else if (iLista.toString().endsWith("VBZ")) {

                listaR.add(iLista.toString().replace("/VBZ", ""));

            } else if (iLista.toString().endsWith("VBN")) {

                listaR.add(iLista.toString().replace("VBN", ""));

	private SPHelper() {
            } else if (iLista.toString().endsWith("VBP")) {

                listaR.add(iLista.toString().replace("/VBP", ""));

            } else if (iLista.toString().endsWith("VBG")) {

                listaR.add(iLista.toString().replace("/VBG", ""));

            } else {

            }

		return instanceParser;

	}
        }

	public LexicalizedParser getLp() {

		return lp;

	}
        return listaR;

    }

		// TreeSet
		TokenizerFactory<CoreLabel> tokens = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");

		List<CoreLabel> listaCorelabel = tokens.getTokenizer(
				new StringReader(texto)).tokenize();

		Tree parseTree = lp.apply(listaCorelabel);
    public List<Relacao> gerarRelacaoOWL(String frase) {

		return parseTree;

	}
        List<Relacao> listaR = new ArrayList<>();

	public List<String> getListaConceito(String frase) {

		List<TaggedWord> listaConceito = this.getListTaggerWord(frase);

		List<String> listaR = new ArrayList<>();
        List<TaggedWord> lTags = this.getListTaggerWord(frase);

		for (TaggedWord iLista : listaConceito) {
        for (int i = 0; i < lTags.size(); i++) {

			if (iLista.toString().endsWith("NNS")) {
            try {

				listaR.add(iLista.toString().replace("/NNS", ""));
                //Bolinha is a cat
                if ((lTags.size() >= (i + 3))
                    && lTags.get(i).tag().equals("NNP")
                    && lTags.get(i + 1).tag().equals("VBZ")
                    && lTags.get(i + 2).tag().equals("DT")
                    && lTags.get(i + 3).tag().equals("NN")) {

			} else if (iLista.toString().endsWith("NN")) {
                    OWLClass classe = OWLHelper.getOWLHelper().addClasse(lTags.get(i + 3).value());
                    OWLHelper.getOWLHelper().addInstancia(lTags.get(i).value(), classe);

				listaR.add(iLista.toString().replace("/NN", ""));
                    listaR.add(new Relacao("\"" + lTags.get(i).value() + "\"", "is", lTags.get(i + 3).value()));

			} else if (iLista.toString().endsWith("RB")) {
                }

				listaR.add(iLista.toString().replace("/RB", ""));
                //Bolinha and Baleia are dogs
                if (lTags.size() >= (i + 4)
                    && (lTags.get(i).tag().equals("NNP")
                    && lTags.get(i + 1).tag().equals("CC")
                    && lTags.get(i + 2).tag().equals("NNP")
                    && lTags.get(i + 3).tag().equals("VBP")
                    && (lTags.get(i + 4).tag().equals("NNS") || lTags.get(i + 4).tag().equals("NN")))) {

			} else {
                    OWLClass classe = OWLHelper.getOWLHelper().addClasse(lTags.get(i + 4).value());
                    OWLHelper.getOWLHelper().addInstancia(lTags.get(i).value(), classe);
                    OWLHelper.getOWLHelper().addInstancia(lTags.get(i + 2).value(), classe);

			}

		}
                    listaR.add(new Relacao("\"" + lTags.get(i).value() + "\"", "is", lTags.get(i + 4).value()));
                    listaR.add(new Relacao("\"" + lTags.get(i + 2).value() + "\"", "is", lTags.get(i + 4).value()));

		return listaR;

	}
                }

	public List<String> getListaInstancia(String frase) {

		List<TaggedWord> listaInstancia = this.getListTaggerWord(frase);
                //Bolinha eat meat
                if ((lTags.size() >= (i + 3))
                    && lTags.get(i).tag().equals("NNP")
                    && lTags.get(i + 1).tag().equals("VBP")
                    && lTags.get(i + 2).tag().equals("NN")) {

		List<String> listaR = new ArrayList<>();
                    OWLClass classe = OWLHelper.getOWLHelper().addClasse(lTags.get(i + 2).value());

		for (TaggedWord iLista : listaInstancia) {
                    if (!OWLHelper.getOWLHelper().isInstancia(lTags.get(i).value())) {

			if (iLista.toString().endsWith("NNPS")) {
                        OWLHelper.getOWLHelper().addInstancia(lTags.get(i).value());

				listaR.add(iLista.toString().replace("/NNPS", ""));
                    }

			} else if (iLista.toString().endsWith("NNP")) {
                    listaR.add(new Relacao("\"" + lTags.get(i).value() + "\"", lTags.get(i + 1).value(), lTags.get(i + 2).value()));

				listaR.add(iLista.toString().replace("/NNP", ""));
                }

                //Bolinha is a dog and eat meat
                if (lTags.size() >= (i + 6)
                    && (lTags.get(i).tag().equals("NNP")
                    && lTags.get(i + 1).tag().equals("VBZ")
                    && lTags.get(i + 2).tag().equals("DT")
                    && lTags.get(i + 3).tag().equals("NN")
                    && lTags.get(i + 4).tag().equals("CC")
                    && lTags.get(i + 5).tag().equals("VBP")
                    && lTags.get(i + 6).tag().equals("NN"))) {

			}

		}
                    Relacao r1 = new Relacao("\"" + lTags.get(i).value() + "\"", lTags.get(i + 5).value(), lTags.get(i + 6).value());
                    Relacao r2 = new Relacao(lTags.get(i + 3).value(), lTags.get(i + 5).value(), lTags.get(i + 6).value());

		return listaR;

	}
                    OWLHelper.getOWLHelper().addClasse(lTags.get(i + 6).value());
                    listaR.add(r1);
                    listaR.add(r2);

	public List<String> getListaPredicado(String frase) {

		List<TaggedWord> listaPredicado = this.getListTaggerWord(frase);
                }

		List<String> listaR = new ArrayList<>();
                //white shark eat people
                if (lTags.size() >= (i + 3)
                    && (lTags.get(i).tag().equals("JJ")
                    && lTags.get(i + 1).tag().equals("NN")
                    && lTags.get(i + 2).tag().equals("VBP")
                    && lTags.get(i + 3).tag().equals("NNS"))) {

		for (TaggedWord iLista : listaPredicado) {
                    OWLHelper.getOWLHelper().addClasse(lTags.get(i).value() + " " + lTags.get(i + 1).value());
                    OWLHelper.getOWLHelper().addClasse(lTags.get(i + 1).value());
                    OWLHelper.getOWLHelper().addClasse(lTags.get(i + 3).value());

			if (iLista.toString().endsWith("VB")) {
                    Relacao r1 = new Relacao(lTags.get(i).value() + " " + lTags.get(i + 1).value(), lTags.get(i + 2).value(), lTags.get(i + 3).value());
                    Relacao r2 = new Relacao(lTags.get(i + 1).value(), lTags.get(i + 2).value(), lTags.get(i + 3).value());

				listaR.add(iLista.toString().replace("/VB", ""));
                    listaR.add(r1);
                    listaR.add(r2);

			} else if (iLista.toString().endsWith("VBD")) {
                }
                
                //cow eats meat and drinking water
                if (lTags.size() >= (i + 5)
                    && (lTags.get(i).tag().equals("NN")
                    && lTags.get(i + 1).tag().equals("VBZ")
                    && lTags.get(i + 2).tag().equals("NN")
                    && lTags.get(i + 3).tag().equals("CC")
                    && lTags.get(i + 4).tag().equals("VBZ")
                    && lTags.get(i + 5).tag().equals("NN"))) {
                
                
                    Relacao r1 = new Relacao(lTags.get(i).value(), lTags.get(i + 1).value(), lTags.get(i + 2).value());
                    Relacao r2 = new Relacao(lTags.get(i).value(), lTags.get(i + 4).value(), lTags.get(i + 5).value());

				listaR.add(iLista.toString().replace("/VBD", ""));
                    listaR.add(r1);
                    listaR.add(r2);
                    
                }

			} else if (iLista.toString().endsWith("VBZ")) {
            } catch (Exception e) {

				listaR.add(iLista.toString().replace("/VBZ", ""));
            }

			} else if (iLista.toString().endsWith("VBN")) {
        }

				listaR.add(iLista.toString().replace("VBN", ""));
        return listaR;

			} else if (iLista.toString().endsWith("VBP")) {
    }

				listaR.add(iLista.toString().replace("/VBP", ""));
    public List<TaggedWord> getListTaggerWord(String frase) {

			} else if (iLista.toString().endsWith("VBG")) {
        // Retirar pontuação
        // String l = TextoUtil.getInstanceTextoUtil().retirarPontuacao(frase);
        // String textLimpo = TextoUtil.getInstanceTextoUtil().listaToString(l);
        String[] sent = frase.split(" ");

				listaR.add(iLista.toString().replace("/VBG", ""));
        List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);

        Tree parse = lp.apply(rawWords);

			}

		}

		return listaR;

	}

	public List<Relacao> gerarRelacaoOWL(String frase) {

		List<Relacao> listaR = new ArrayList<>();

		List<TaggedWord> lTags = this.getListTaggerWord(frase);

		for (int i = 0; i < lTags.size(); i++) {

			try {

				// Bolinha is a cat
				if ((lTags.size() >= (i + 3))
						&& lTags.get(i).tag().equals("NNP")
						&& lTags.get(i + 1).tag().equals("VBZ")
						&& lTags.get(i + 2).tag().equals("DT")
						&& lTags.get(i + 3).tag().equals("NN")) {

					OWLClass classe = OWLHelper.getOWLHelper().addClasse(
							lTags.get(i + 3).value());
					OWLHelper.getOWLHelper().addInstancia(lTags.get(i).value(),
							classe);

					listaR.add(new Relacao("\"" + lTags.get(i).value() + "\"",
							"is", lTags.get(i + 3).value()));

				}

				// shark is a fish
				if ((lTags.size() >= (i + 3))
						&& lTags.get(i).tag().equals("NN")
						&& lTags.get(i + 1).tag().equals("VBZ")
						&& lTags.get(i + 2).tag().equals("DT")
						&& lTags.get(i + 3).tag().equals("NN")) {

					OWLHelper.getOWLHelper().addClasse(lTags.get(i).value());
					OWLHelper.getOWLHelper()
							.addClasse(lTags.get(i + 3).value());

					listaR.add(new Relacao("\"" + lTags.get(i).value() + "\"",
							"is", lTags.get(i + 3).value()));

				}

				// shark is a fish and eats meat
				if ((lTags.size() >= (i + 3))
						&& lTags.get(i).tag().equals("NN")
						&& lTags.get(i + 1).tag().equals("VBZ")
						&& lTags.get(i + 2).tag().equals("DT")
						&& lTags.get(i + 3).tag().equals("NN")
						&& lTags.get(i + 4).tag().equals("CC")
						&& lTags.get(i + 5).tag().equals("VBZ")
						&& lTags.get(i + 6).tag().equals("NN")) {

					OWLHelper.getOWLHelper().addClasse(lTags.get(i).value());
					OWLHelper.getOWLHelper()
							.addClasse(lTags.get(i + 3).value());
					OWLHelper.getOWLHelper()
							.addClasse(lTags.get(i + 6).value());

					listaR.add(new Relacao("\"" + lTags.get(i).value() + "\"",
							"is", lTags.get(i + 3).value()));
					listaR.add(new Relacao("\"" + lTags.get(i).value(), lTags
							.get(i + 5).value(), lTags.get(i + 6).value()));

				}

				// Bolinha and Baleia are dogs
				if (lTags.size() >= (i + 4)
						&& (lTags.get(i).tag().equals("NNP")
								&& lTags.get(i + 1).tag().equals("CC")
								&& lTags.get(i + 2).tag().equals("NNP")
								&& lTags.get(i + 3).tag().equals("VBP") && (lTags
								.get(i + 4).tag().equals("NNS") || lTags
								.get(i + 4).tag().equals("NN")))) {

					OWLClass classe = OWLHelper.getOWLHelper().addClasse(
							lTags.get(i + 4).value());
					OWLHelper.getOWLHelper().addInstancia(lTags.get(i).value(),
							classe);
					OWLHelper.getOWLHelper().addInstancia(
							lTags.get(i + 2).value(), classe);

					listaR.add(new Relacao("\"" + lTags.get(i).value() + "\"",
							"is", lTags.get(i + 4).value()));
					listaR.add(new Relacao("\"" + lTags.get(i + 2).value()
							+ "\"", "is", lTags.get(i + 4).value()));

				}

				// Bolinha eat meat
				if ((lTags.size() >= (i + 3))
						&& lTags.get(i).tag().equals("NNP")
						&& lTags.get(i + 1).tag().equals("VBP")
						&& lTags.get(i + 2).tag().equals("NN")) {

					OWLHelper.getOWLHelper()
							.addClasse(lTags.get(i + 2).value());

					if (!OWLHelper.getOWLHelper().isInstancia(
							lTags.get(i).value())) {

						OWLHelper.getOWLHelper().addInstancia(
								lTags.get(i).value());

					}

					listaR.add(new Relacao("\"" + lTags.get(i).value() + "\"",
							lTags.get(i + 1).value(), lTags.get(i + 2).value()));

				}

				// shark eats meat
				if ((lTags.size() >= (i + 3))
						&& lTags.get(i).tag().equals("NN")
						&& lTags.get(i + 1).tag().equals("VBZ")
						&& lTags.get(i + 2).tag().equals("NN")) {

					OWLHelper.getOWLHelper().addClasse(lTags.get(i).value());
					OWLHelper.getOWLHelper()
							.addClasse(lTags.get(i + 2).value());

					if (!OWLHelper.getOWLHelper().isInstancia(
							lTags.get(i).value())) {

						OWLHelper.getOWLHelper().addInstancia(
								lTags.get(i).value());

					}

					listaR.add(new Relacao(lTags.get(i).value(), lTags.get(
							i + 1).value(), lTags.get(i + 2).value()));

				}

				// Bolinha is a dog and eat meat
				if (lTags.size() >= (i + 6)
						&& (lTags.get(i).tag().equals("NNP")
								&& lTags.get(i + 1).tag().equals("VBZ")
								&& lTags.get(i + 2).tag().equals("DT")
								&& lTags.get(i + 3).tag().equals("NN")
								&& lTags.get(i + 4).tag().equals("CC")
								&& lTags.get(i + 5).tag().equals("VBP") && lTags
								.get(i + 6).tag().equals("NN"))) {

					Relacao r1 = new Relacao(
							"\"" + lTags.get(i).value() + "\"", lTags
									.get(i + 5).value(), lTags.get(i + 6)
									.value());
					Relacao r2 = new Relacao(lTags.get(i + 3).value(), lTags
							.get(i + 5).value(), lTags.get(i + 6).value());

					OWLHelper.getOWLHelper()
							.addClasse(lTags.get(i + 6).value());
					listaR.add(r1);
					listaR.add(r2);

				}

				// white shark eat people
				if (lTags.size() >= (i + 3)
						&& (lTags.get(i).tag().equals("JJ")
								&& lTags.get(i + 1).tag().equals("NN")
								&& lTags.get(i + 2).tag().equals("VBP") && lTags
								.get(i + 3).tag().equals("NNS"))) {

					OWLHelper.getOWLHelper().addClasse(
							lTags.get(i).value() + " "
									+ lTags.get(i + 1).value());
					OWLHelper.getOWLHelper()
							.addClasse(lTags.get(i + 1).value());
					OWLHelper.getOWLHelper()
							.addClasse(lTags.get(i + 3).value());

					Relacao r1 = new Relacao(lTags.get(i).value() + " "
							+ lTags.get(i + 1).value(), lTags.get(i + 2)
							.value(), lTags.get(i + 3).value());
					Relacao r2 = new Relacao(lTags.get(i + 1).value(), lTags
							.get(i + 2).value(), lTags.get(i + 3).value());

					listaR.add(r1);
					listaR.add(r2);

				}

				// cow eats meat and drinking water
				if (lTags.size() >= (i + 5)
						&& (lTags.get(i).tag().equals("NN")
								&& lTags.get(i + 1).tag().equals("VBZ")
								&& lTags.get(i + 2).tag().equals("NN")
								&& lTags.get(i + 3).tag().equals("CC")
								&& lTags.get(i + 4).tag().equals("VBZ") && lTags
								.get(i + 5).tag().equals("NN"))) {

					Relacao r1 = new Relacao(lTags.get(i).value(), lTags.get(
							i + 1).value(), lTags.get(i + 2).value());
					Relacao r2 = new Relacao(lTags.get(i).value(), lTags.get(
							i + 4).value(), lTags.get(i + 5).value());

					listaR.add(r1);
					listaR.add(r2);

				}

			} catch (Exception e) {

			}

		}
        return parse.taggedYield();

		return listaR;

	}
    }

	public List<TaggedWord> getListTaggerWord(String frase) {

		// Retirar pontuação
		// String l = TextoUtil.getInstanceTextoUtil().retirarPontuacao(frase);
		// String textLimpo = TextoUtil.getInstanceTextoUtil().listaToString(l);
		String[] sent = frase.split(" ");

		List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);

		Tree parse = lp.apply(rawWords);

		return parse.taggedYield();

	}

}
