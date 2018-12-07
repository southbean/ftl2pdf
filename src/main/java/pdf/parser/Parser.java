package pdf.parser;

import org.dom4j.Element;

import java.util.Map;

/**
 * Created by yitingq on 2018/11/30.
 */
public interface Parser {

    public Object parse(Element object, Map<String, Object> refMap) throws Exception;
    
    public Object parse(Element object, Map<String, Object> refMap, Element	parent) throws Exception;
}
