package com.xfeng.plugin.generator;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import com.xfeng.plugin.generator.element.*;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.SimpleXMLMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.*;

public class NewXmlMapperGenerator extends SimpleXMLMapperGenerator {

    public NewXmlMapperGenerator() {
        super();
    }

    protected XmlElement getSqlMapElement() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString("Progress.12", table.toString()));
        XmlElement answer = new XmlElement("mapper");
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
        answer.addAttribute(new Attribute("namespace", namespace));

        context.getCommentGenerator().addRootComment(answer);

        addBaseColumnListElement(answer);
        addBlobColumnListElement(answer);
        addResultMapElement(answer);
        addWhereElement(answer);

        addInsertSelectiveElement(answer);
        addInsertSelectiveListElement(answer);

        addDeleteByPrimaryKeyElement(answer);

        addUpdateByPrimaryKeySelectiveElement(answer);

        addSelectByPrimaryKeyElement(answer);
        addSelectBySelectiveElement(answer);
        addSelectPagedBySelectiveElement(answer);
        addSelectOneBySelectiveElement(answer);
        addSelectAllElement(answer);
        addCountOneBySelectiveElement(answer);

        return answer;
    }

    protected void addBaseColumnListElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addBlobColumnListElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new BlobColumnListElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addResultMapElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewResultMapElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addWhereElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewWhereElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addInsertSelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewInsertSelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addInsertSelectiveListElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewInsertSelectiveListElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(true);
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addUpdateByPrimaryKeySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewUpdateByPrimaryKeySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewSelectByPrimaryKeyElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addSelectBySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewSelectBySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addSelectPagedBySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewSelectPagedBySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addSelectOneBySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewSelectOneBySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addSelectAllElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewSelectAllElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addCountOneBySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new NewCountBySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

}