package com.xfeng.mybatis.generator.codegen.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

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

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(listType);
        method.addParameter(new Parameter(inputType, "record"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }
}
