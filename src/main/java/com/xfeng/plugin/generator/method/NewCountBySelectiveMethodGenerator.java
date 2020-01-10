package com.xfeng.plugin.generator.method;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

public class NewCountBySelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {

        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.add(fqjt);

        Method method = new Method("countBySelective");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Integer"));
        method.setName("countBySelective");
        method.addParameter(new Parameter(fqjt, "record"));
        method.setAbstract(true);

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);

    }
}
