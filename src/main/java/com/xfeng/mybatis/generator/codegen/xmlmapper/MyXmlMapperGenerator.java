package com.xfeng.mybatis.generator.codegen.xmlmapper;

import com.xfeng.mybatis.generator.codegen.xmlmapper.elements.*;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.SimpleXMLMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BaseColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BlobColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByPrimaryKeyElementGenerator;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

public class MyXmlMapperGenerator extends SimpleXMLMapperGenerator {

    public MyXmlMapperGenerator() {
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
        if (introspectedTable.hasBLOBColumns()) {
            addBlobColumnListElement(answer);
        }
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
        AbstractXmlElementGenerator elementGenerator = new MyResultMapElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addWhereElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MyWhereElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addInsertSelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MyInsertSelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addInsertSelectiveListElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MyInsertSelectiveListElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(true);
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addUpdateByPrimaryKeySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MyUpdateByPrimaryKeySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MySelectByPrimaryKeyElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addSelectBySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MySelectBySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addSelectPagedBySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MySelectPagedBySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addSelectOneBySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MySelectOneBySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addSelectAllElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MySelectAllElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addCountOneBySelectiveElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new MyCountBySelectiveElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

}