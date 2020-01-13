package com.xfeng.mybatis.generator.codegen.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class NewCountBySelectiveElementGenerator extends AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select");

        String baseRecordType = introspectedTable.getBaseRecordType();

        answer.addAttribute(new Attribute("id", "countBySelective"));
        answer.addAttribute(new Attribute("parameterType", baseRecordType));
        answer.addAttribute(new Attribute("resultType", "java.lang.Integer"));

        context.getCommentGenerator().addComment(answer);

        String sb = "select count(*) from " +
                introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        answer.addElement(new TextElement(sb));

        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "sql_where"));
        answer.addElement(include);

        parentElement.addElement(answer);
    }

}
