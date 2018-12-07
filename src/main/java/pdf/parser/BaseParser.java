package pdf.parser;

import org.dom4j.Element;

import java.util.Map;

/**
 * Created by yitingq on 2018/11/30.
 */
public class BaseParser implements Parser{

    @Override
    public Object parse(Element element, Map<String,Object> refMap) throws Exception {
        return null;
    }
    
    @Override
    public Object parse(Element element, Map<String,Object> refMap, Element	parent) throws Exception {
        return null;
    }
}
