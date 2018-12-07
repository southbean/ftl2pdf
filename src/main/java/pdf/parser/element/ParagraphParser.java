package pdf.parser.element;

import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import pdf.parser.BaseParser;
import pdf.parser.ParserFactory;
import pdf.parser.ParserUtils;

/**
 * Created by yitingq on 2018/11/30.
 */
public class ParagraphParser extends BaseParser {

    private static final ParagraphParser PARAGRAPH_PARSER = new ParagraphParser();

    private ParagraphParser(){

    }

    public static ParagraphParser getInstance(){
        return PARAGRAPH_PARSER;
    }

    @Override
    public Object parse(Element XmlElement, Map<String,Object> refMap)  throws Exception {
    	Iterator elemIt = XmlElement.elementIterator();
    	
        // 构造函数参数
        String text = "";
//        Attribute textAttr = XmlElement.attribute("text");
        Attribute fontAttr = XmlElement.attribute("font-ref");
        
        Font font = new Font();

//        if(textAttr != null){
//            text = textAttr.getValue();
//        }

        if(fontAttr != null){
            font =  ((Map<String,Font>)refMap.get("fontMap")).get(fontAttr.getValue());
        }

        Paragraph paragraph = new Paragraph(text,font);

        Iterator attrIt = XmlElement.attributeIterator();

        while (attrIt.hasNext()){
            Attribute attr = (Attribute)attrIt.next();
            String attrName = attr.getName();
            String attrValue = attr.getValue();
            switch (attrName){
//                case "text":
//                    break;
                case "font-ref":
                    //paragraph.setFont(this.fontMap.get(attrValue));
                    break;
                case "align"://位置
                    paragraph.setAlignment(ParserUtils.getAlignment(attrValue));
                    break;
                case "space-after"://设置下一段落页面空白宽度
                    paragraph.setSpacingAfter(Float.parseFloat(attrValue));
                    break;
                case "space-before"://设置和上一段落页面空白宽度
                    paragraph.setSpacingBefore(Float.parseFloat(attrValue));
                    break;
                case "first-line-indent"://首行缩进
                    paragraph.setFirstLineIndent(Float.parseFloat(attrValue));
                    break;
                case "indentation_left"://左缩进
                    paragraph.setIndentationLeft(Float.parseFloat(attrValue));
                    break;
                case "indentation_right"://右缩进
                    paragraph.setIndentationRight(Float.parseFloat(attrValue));
                    break;
                case "leading"://段落内行间距
                    paragraph.setLeading(Float.parseFloat(attrValue));
                    break;
                case "multi-leading"://段落内多行间距
                    paragraph.setMultipliedLeading(Float.parseFloat(attrValue));
                    break;
                default:
                    throw new Exception("未知的paragraph属性："+attrName);
            }
        }
        
        // 向段落中添加元素
        while (elemIt.hasNext()){
            Element subElem = (Element) elemIt.next();

            String nodeName = subElem.getName();

            if(!"chunk".equals(nodeName)) throw new Exception("paragraph中发现无效标签：" + nodeName);

            if("chunk".equals(nodeName)){
            	paragraph.add((Chunk) ParserFactory.getParser("chunk").parse(subElem,refMap));
            }
        }

        return paragraph;
    }
}
