package com.xfeng.plugin.generator.element;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import com.xfeng.plugin.NewMyBatis3FormattingUtilities;

public class NewWhereElementGenerator extends AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {
        // 添加sql——where
        XmlElement answer = new XmlElement("sql");
        answer.addAttribute(new Attribute("id", "sql_where"));
        XmlElement where = new XmlElement("where");
        StringBuilder sb = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {

            XmlElement isNotNullElement = NewMyBatis3FormattingUtilities.getIsNotNullElement(introspectedColumn);
            where.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(" and ");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(NewMyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
        answer.addElement(where);

        parentElement.addElement(answer);
    }

}
