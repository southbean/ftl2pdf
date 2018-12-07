package pdf.parser.element;

import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;

import pdf.parser.BaseParser;
import pdf.parser.ParserUtils;

/**
 * Created by yitingq on 2018/11/30.
 */
public class PdfChunkParser extends BaseParser {

    private final static PdfChunkParser CHUNK_PARSER = new PdfChunkParser();

    private PdfChunkParser(){

    }

    public static PdfChunkParser getInstance(){
        return CHUNK_PARSER;
    }

    @Override
    public Object parse(Element XmlElement, Map<String,Object> refMap) throws Exception {
        String text = "";
        Font font = new Font();
        Attribute fontAttr = XmlElement.attribute("font-ref");
        
        text = XmlElement.getTextTrim();

        if(fontAttr != null){
            font =  ((Map<String,Font>)refMap.get("fontMap")).get(fontAttr.getValue());
        }

        Chunk chunk = new Chunk(text,font);

        Iterator attrIt = XmlElement.attributeIterator();

        while (attrIt.hasNext()){
            Attribute attr = (Attribute) attrIt.next();
            String attrName = attr.getName();
            String attrValue = attr.getValue();
            
            String[] arrayStr = null;
            if(attrValue.contains(",")) {
            	arrayStr = attrValue.split(",");
            }

            switch (attrName){
                case "font-ref":
                    break;
                case "background":
                	chunk.setBackground(ParserUtils.getBaseColor(attrValue));
                    break;
                case "underline":
                	if(arrayStr.length==2) {
                		chunk.setUnderline(Float.parseFloat(arrayStr[0]), Float.parseFloat(arrayStr[1]));
                	}
                    break;
                case "line_height":
            		chunk.setLineHeight(Float.parseFloat(attrValue));
                    break;
                case "word-spacing":
                	chunk.setWordSpacing(Float.parseFloat(attrValue));
                    break;
                default:
                    throw new Exception("未知的Chunk属性：" + attrName);
            }
        }

        return chunk;
    }
}
