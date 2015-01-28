package owl;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class Main {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException, OWLOntologyCreationException {

		URL url = new URL("http://localhost/ia/novo.owl");
		IRI iri = IRI.create(url);
		
		OWLOntologyManager m = OWLManager.createOWLOntologyManager();
		OWLOntology o = m.loadOntologyFromOntologyDocument(iri);


		String s = o.toString();
		
		System.out.println(s);
	}

}
