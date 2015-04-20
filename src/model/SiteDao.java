package model;

import java.io.*;
import java.util.*;

import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.persistence.*;

public class SiteDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA2"); //open the Managerfactory
	EntityManager em = null;

	// returns an instance of Site for the given siteId
	public Site findSite(int siteId) {
		Site site = null;

		em = factory.createEntityManager();
		em.getTransaction().begin();

		site = em.find(Site.class, siteId);

		em.getTransaction().commit();
		em.close();

		return site;
	}

	// returns a list of Site instances
	@SuppressWarnings("unchecked")  // problem , not sure
	public List<Site> findAllSites() {
		List<Site> s = new ArrayList<Site>(); // the definition of s should be the same as the NameQuery of Site.java
		em = factory.createEntityManager();
		
		em.getTransaction().begin();

		s =em.createNamedQuery("findAllSites").getResultList();
		

		em.getTransaction().commit();
		em.close();

		return s;
	}

	/* transfer list parameter into a file called xmlFileName */
	public void exportSiteDatabaseToXmlFile(SiteList lists, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(lists, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public void convertXmlFileToOutputFile
	 * 
	 * - transfer  inputXmlFileName into  outputXmlFileName 
	 * using XSLT file: xsltFileName
	 */
	public void convertXmlFileToOutputFile(
			String inputXmlFileName,
			String outputXmlFileName,
			String xsltFileName)
	{
		//open the file
		File inputXmlFile = new File(inputXmlFileName);
		File outputXmlFile = new File(outputXmlFileName);
		File xsltFile = new File(xsltFileName);
		//read the file
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt   = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);

		TransformerFactory factory = TransformerFactory.newInstance();
		
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		SiteDao dao = new SiteDao();
	
		//test findAllSites()
		List<Site> sites = dao.findAllSites();
/*		for(Site s : sites) {
			System.out.println(s.getName());
		}
*/	
		//test exportSiteListToXmlFile()
		SiteList list = new SiteList();
		list.setSites(sites);
		
		dao.exportSiteDatabaseToXmlFile(list, "xml/sites.xml");
		
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
		
	}
}
