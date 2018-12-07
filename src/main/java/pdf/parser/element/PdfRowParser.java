package pdf.parser.element;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;

import pdf.parser.BaseParser;
import pdf.parser.ParserFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yitingq on 2018/11/30.
 */
public class PdfRowParser extends BaseParser {

    private final static PdfRowParser ROW_PARSER = new PdfRowParser();

    private PdfRowParser(){

    }

    public static PdfRowParser getInstance(){
        return ROW_PARSER;
    }

    @Override
    public Object parse(Element XmlElement, Map<String,Object> refMap, Element parentElement) throws Exception {
    	
    	 List<PdfPCell> cells = new ArrayList<PdfPCell>();
    	
    	//父节点，即表的节点
    	int tableColsNum = 0;
    	Attribute numColsAttr = parentElement.attribute("num-cols");
    	if(numColsAttr != null){
    		tableColsNum = Integer.parseInt(numColsAttr.getValue());
        }
    	
    	int nodeNum = 0;
    	for(int i = 0;  i < XmlElement.nodeCount();  i++) {
    		Node node = XmlElement.node(i);
    		if(node instanceof Element) {
    			nodeNum++;
    		}
    	}
    	
        Iterator elemIt = XmlElement.elementIterator();
       
        while (elemIt.hasNext()){
            Element subElem = (Element) elemIt.next();

            if(subElem.getName() != "cell") throw new Exception("row中发现无效子标签：" + subElem.getName());
            
            PdfCellParser parser = (PdfCellParser) ParserFactory.getParser("cell");
            cells.add((PdfPCell) parser.parse(subElem,refMap));
        }
        
        int loseColsNum = tableColsNum-nodeNum;
        System.out.println(loseColsNum);
        if(loseColsNum>0) {
         	for(int i=0; i<loseColsNum; i++) {
         		cells.add(null);
         	}
        }

        // 构造PdfPRow
        PdfPCell[] cellArrays = cells.toArray(new PdfPCell[]{});
        
        PdfPRow row = new PdfPRow(cellArrays);
        return row;
    }
}
