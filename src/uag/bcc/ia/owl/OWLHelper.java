/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uag.bcc.ia.owl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

/**
 *
 * @author ramonsantos
 */
public class OWLHelper {

    private final OWLOntologyManager manager;
    private final IRI ontologyIRI;
    private final OWLOntology ontology;
    private final OWLDataFactory factory;
    private final PrefixManager prefixManager;
    private final String URI = "http://e-solutions.com.br";
    private final OWLClass classeGenerica;
    private static OWLHelper inst = null;
    public HashMap<String, OWLIndividual> instanciasL;

    public static OWLHelper getOWLHelper() {

        try {
            if (inst == null) {

                inst = new OWLHelper();

            }

        } catch (OWLOntologyCreationException e1) {

        } catch (OWLOntologyStorageException e2) {

        }

        return inst;

    }

    private OWLHelper() throws OWLOntologyCreationException, OWLOntologyStorageException {

        manager = OWLManager.createOWLOntologyManager();
        ontologyIRI = IRI.create(URI);
        ontology = manager.createOntology(ontologyIRI);
        factory = manager.getOWLDataFactory();
        prefixManager = new DefaultPrefixManager(null, null, ontologyIRI + "#");

        classeGenerica = factory.getOWLClass("Thing", prefixManager);
        instanciasL = new HashMap<>();

    }

    public OWLClass addClasse(String conceito) {

        OWLClass classe = factory.getOWLClass(":" + conceito, prefixManager);
        OWLDeclarationAxiom declaration = factory.getOWLDeclarationAxiom(classe);
        manager.addAxiom(ontology, declaration);

        return classe;

    }

    public OWLIndividual addInstancia(String instancia, OWLClass classe) {

        OWLIndividual i = factory.getOWLNamedIndividual(":" + instancia, prefixManager);
        OWLClassAssertionAxiom assertionAxiom = factory.getOWLClassAssertionAxiom(classe, i);
        manager.addAxiom(ontology, assertionAxiom);
        instanciasL.put(instancia, i);

        return i;

    }

	public OWLSubClassOfAxiom addSubClasse(String instancia, OWLClass classe){
		
		OWLClass sub = factory.getOWLClass(":" + instancia, prefixManager);
	
		
		OWLSubClassOfAxiom subC = factory.getOWLSubClassOfAxiom(sub, classe);
		manager.addAxiom(ontology, subC);
	
			
			
	return  subC;
		
	}
	
	
    public OWLIndividual addInstancia(String instancia) {

        if (!instanciasL.containsKey(instancia)) {
            OWLIndividual i = factory.getOWLNamedIndividual(":" + instancia, prefixManager);
            OWLClassAssertionAxiom assertionAxiom = factory.getOWLClassAssertionAxiom(classeGenerica, i);
            manager.addAxiom(ontology, assertionAxiom);

            return i;
            
        } else {
            
            return null;
            
        }
        
    }

    public boolean isInstancia(String instanciaN){
        
        return instanciasL.containsKey(instanciaN);
    }
    
    public void gerarArquivo(String nomeArquivo) {

        try {

            manager.saveOntology(ontology, new StreamDocumentTarget(System.out));

            manager.saveOntology(ontology, new FileOutputStream(nomeArquivo + ".owl"));

        } catch (FileNotFoundException e) {

        } catch (OWLOntologyStorageException e1) {

        }

    }

}
