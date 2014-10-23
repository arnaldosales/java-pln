/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

/**
 *
 * @author ramonsantos
 */
public class TesteOWL {

    public static void generateOntology() throws OWLOntologyCreationException, OWLOntologyStorageException {

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        IRI ontologyIRI = IRI.create("http://e-solutions.com.br");
        OWLOntology ontology = manager.createOntology(ontologyIRI);
        OWLDataFactory factory = manager.getOWLDataFactory();
        PrefixManager prefixManager = new DefaultPrefixManager(null, null, ontologyIRI + "#");

        OWLClass owlClass = null;

        OWLClass classCurrent = factory.getOWLClass(":Car", prefixManager);
        OWLDeclarationAxiom declaration = factory.getOWLDeclarationAxiom(classCurrent);
        manager.addAxiom(ontology, declaration);

        owlClass = classCurrent;

		/////
        OWLIndividual individuo = factory.getOWLNamedIndividual(":Joana", prefixManager);
        OWLClassAssertionAxiom assertionAxiom = factory.getOWLClassAssertionAxiom(owlClass, individuo);
        manager.addAxiom(ontology, assertionAxiom);
		///////	
       
        System.out.println("RDF/XML: ");
        manager.saveOntology(ontology, new StreamDocumentTarget(System.out));

        try {
            manager.saveOntology(ontology, new FileOutputStream("Masha.owl"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] a) {
        try {
            generateOntology();
        } catch (Exception c) {

        }
    }

}
