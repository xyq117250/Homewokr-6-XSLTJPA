package model;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@SuppressWarnings("unused")

/*This function is used to create a XML file for sites */
public class SiteXmlCreate {

	public void CreateXmlForSite(SiteList sites, String fileName)
	{
		File file = new File(fileName);
		try {
			JAXBContext context = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sites, file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		SiteXmlCreate sitexml = new SiteXmlCreate();
		List<Equipment> equipment1 = new ArrayList<Equipment>();
		List<Equipment> equipment2 = new ArrayList<Equipment>();
		List<Equipment> equipment3 = new ArrayList<Equipment>();
		List<Equipment> equipment4 = new ArrayList<Equipment>();
		List<Tower> Tower1 = new ArrayList<Tower>();
		List<Tower> Tower2 = new ArrayList<Tower>();
		List<Site> sitelist = new ArrayList<Site>();
		SiteList siteout = new SiteList();
		
		Site site1 = new Site(1, "Site 1", 63.23, 67.28, Tower1);
		Site site2 = new Site(2, "Site 2", 24.32, 39.24, Tower2);
		
		Tower tower1 = new Tower(1, "Tower A", 123.456, 5, equipment1, site1);
		Tower tower2 = new Tower(2, "Tower B", 678.987, 6, equipment2, site1);
		Tower tower3 = new Tower(3, "Tower C", 543.343, 7, equipment3, site2);
		Tower tower4 = new Tower(4, "Tower D", 923.443, 8, equipment4, site2);
		
		Equipment equip1 = new Equipment(1, "Equipment 1", "Brand 1", "Description 1", 1134.56, tower1);
		Equipment equip2 = new Equipment(2, "Equipment 2", "Brand 2", "Description 2", 2845.69, tower1);
		Equipment equip3 = new Equipment(3, "Equipment 3", "Brand 3", "Description 3", 5678.89, tower2);
		Equipment equip4 = new Equipment(4, "Equipment 4", "Brand 4", "Description 4", 5432.54, tower2);
		Equipment equip5 = new Equipment(5, "Equipment 5", "Brand 5", "Description 5", 3282.24, tower3);
		Equipment equip6 = new Equipment(6, "Equipment 6", "Brand 6", "Description 6", 7267.63, tower3);
		Equipment equip7 = new Equipment(7, "Equipment 7", "Brand 7", "Description 7", 9456.81, tower4);
		Equipment equip8 = new Equipment(8, "Equipment 8", "Brand 8", "Description 8", 7516.27, tower4);
		
		equipment1.add(equip1);
		equipment1.add(equip2);
		equipment2.add(equip3);
		equipment2.add(equip4);
		equipment3.add(equip5);
		equipment3.add(equip6);
		equipment4.add(equip7);
		equipment4.add(equip8);
		
		Tower1.add(tower1);
		Tower1.add(tower2);
		Tower2.add(tower3);
		Tower2.add(tower4);
		
		sitelist.add(site1);
		sitelist.add(site2);
		
		siteout.setSites(sitelist);
		
		sitexml.CreateXmlForSite(siteout, "xml/sites.xml");
	}
}
