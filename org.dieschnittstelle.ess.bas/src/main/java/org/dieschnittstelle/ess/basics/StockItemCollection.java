package org.dieschnittstelle.ess.basics;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class StockItemCollection {
	
	// the log4j logger	
	protected static Logger logger = LogManager.getLogger(StockItemCollection.class);

	// the file to read in
	private String collectionFilePath;
	
	// the builder to use for creating the stockitems
	private IStockItemBuilder builder; 
	
	// the elements we will read in from the xml file
	private List<IStockItem> stockitems = new ArrayList<IStockItem>();
	
	public StockItemCollection(String collectionFilePath,IStockItemBuilder builder) {
		this.collectionFilePath = collectionFilePath;
		this.builder = builder;
	}
	
	public void load() {
		logger.debug("load()");
		
		try {
			// obtain the builtin document builder factory
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			// obtain a builder from the factory
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            
            // obtain an input stream for the document to be read in from the classpath
            InputStream is = getClass().getClassLoader().getResourceAsStream(collectionFilePath);
            logger.debug("obtained an input stream: " + is);
            
            Document doc = docBuilder.parse (is);
            logger.debug("read document from input stream: " + doc);
            
            // now we obtain all stockitem elements from the file (for simplicity, we use string literals here, but note that constants would be more appropriate!)
            NodeList stockitemElements  = doc.getElementsByTagName("stockitem");
            logger.debug("got " + stockitemElements.getLength() + " stockitem elements");
            
            for (int i=0; i< stockitemElements.getLength();i++) {
            	// let the builder create the stockitem and add it to the list
            	stockitems.add(builder.buildStockItemFromElement((Element)stockitemElements.item(i)));
            }
            
            logger.debug("read in stockitems: " + this.stockitems);
		}
		catch (Exception e) {
			String err = "got exception trying to read in stockitems: " + e;
			logger.error(err,e);
			throw new RuntimeException(err);
		}
	}	
	
	public List<IStockItem> getStockItems() {
		return this.stockitems;
	}
	
}
