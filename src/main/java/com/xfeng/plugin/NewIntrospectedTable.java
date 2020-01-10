package com.xfeng.plugin;

import java.util.List;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import com.xfeng.plugin.generator.NewJavaMapperGenerator;
import com.xfeng.plugin.generator.NewXmlMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.model.BaseRecordGenerator;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.mybatis.generator.codegen.mybatis3.model.PrimaryKeyGenerator;
import org.mybatis.generator.codegen.mybatis3.model.RecordWithBLOBsGenerator;

public class NewIntrospectedTable extends IntrospectedTableMyBatis3Impl {

    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator, List<String> warnings,
            ProgressCallback progressCallback) {
        // if (javaClientGenerator == null) {
        // if (context.getSqlMapGeneratorConfiguration() != null) {
        // xmlMapperGenerator = new XMLMapperGenerator();
        // }
        // } else {
        // xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
        // }

        xmlMapperGenerator = new NewXmlMapperGenerator();

        initializeAbstractGenerator(xmlMapperGenerator, warnings, progressCallback);
    }

    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        }

        // String type = context.getJavaClientGeneratorConfiguration()
        // .getConfigurationType();

        // AbstractJavaClientGenerator javaGenerator;
        // if ("XMLMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
        // javaGenerator = new JavaMapperGenerator();
        // } else if ("MIXEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
        // javaGenerator = new MixedClientGenerator();
        // } else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
        // javaGenerator = new AnnotatedClientGenerator();
        // } else if ("MAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
        // javaGenerator = new JavaMapperGenerator();
        // } else {
        // javaGenerator = (AbstractJavaClientGenerator) ObjectFactory
        // .createInternalObject(type);
        // }
        // javaGenerator = new NewJavaMapperGenerator();

        return new NewJavaMapperGenerator("src/main/java");
    }

    protected void calculateJavaModelGenerators(List<String> warnings,
                                      ProgressCallback progressCallback) {

        if (getRules().generatePrimaryKeyClass()) {
            AbstractJavaGenerator javaGenerator = new PrimaryKeyGenerator(getModelProject());
            initializeAbstractGenerator(javaGenerator, warnings,
                    progressCallback);
            javaGenerators.add(javaGenerator);
        }

        if (getRules().generateBaseRecordClass()) {
            AbstractJavaGenerator javaGenerator = new BaseRecordGenerator(getModelProject());
            initializeAbstractGenerator(javaGenerator, warnings,
                    progressCallback);
            javaGenerators.add(javaGenerator);
        }

        if (getRules().generateRecordWithBLOBsClass()) {
            AbstractJavaGenerator javaGenerator = new RecordWithBLOBsGenerator(getModelProject());
            initializeAbstractGenerator(javaGenerator, warnings,
                    progressCallback);
            javaGenerators.add(javaGenerator);
        }
    }

}
