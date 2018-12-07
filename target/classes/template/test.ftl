<?xml version="1.0" encoding="utf-8" ?>
<PDFRoot>
    <font id="title-font"  font-family="Helvetica" style="normal" size="34"></font>
    <font id="content-font" font-family="Helvetica" style="normal" size="20"></font>
    <page></page>
    <paragraph font-ref="title-font" align="center" space-after="20">
    	<chunk>this is a paragraph!</chunk>
    </paragraph>

    <#--<table space-before="10" space-after="10" num-cols="2">
        <#list testList as item>
            <cell font-ref="content-font">${item.a!""}</cell>
            <cell font-ref="content-font">${item.b!""}</cell>
        </#list>
    </table>-->
    

    
    <table num-cols="5">
    	<row>
    		<cell font-ref="content-font">单元格一</cell>
    		<cell col-span="2"  font-ref="content-font">单元格二次方</cell>
    		<cell col-span="2"  font-ref="content-font">单元格二次方</cell>
    	</row>
    	<#--<row>
    		
    		<cell font-ref="content-font">单元格一</cell>
    		<cell col-span="2"  font-ref="content-font">单元格二次方</cell>
    		<cell font-ref="content-font">单元格er</cell>
    		<cell col-span="2"  font-ref="content-font">单元格二次方</cell>
    	</row>-->
    </table>

    <#--<page></page>
    <paragraph space-after="25" space-before="5" font-ref="content-font"
               first-line-indent="40" align="left" multi-leading="2">
        <chunk font-ref="content-font">dddd1${paragraph}</chunk>
    </paragraph>-->
    
     <#--<chunk>dddd</chunk>   -->
               
    <#--<line align="center" width="10"></line>-->

    <#--<line offset="20.0" width="1"></line>-->

</PDFRoot>