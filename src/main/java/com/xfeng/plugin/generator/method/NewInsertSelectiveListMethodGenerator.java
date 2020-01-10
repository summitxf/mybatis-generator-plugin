package com.xfeng.plugin.generator.method;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

public class NewInsertSelectiveListMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Method method = new Method("insertSelectiveList");

        method.setReturnType(new FullyQualifiedJavaType("Integer"));
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("insertSelectiveList");
        method.setAbstract(true);

        FullyQualifiedJavaType inputType = FullyQualifiedJavaType.getNewListInstance();
        FullyQualifiedJavaType listType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

        inputType.addTypeArgument(listType);

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.add(listType);
        method.addParameter(new Parameter(inputType, "record"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }
}
