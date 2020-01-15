package com.xfeng.mybatis.generator.codegen;

import com.xfeng.mybatis.generator.codegen.javamapper.MyJavaMapperGenerator;
import com.xfeng.mybatis.generator.codegen.xmlmapper.MyXmlMapperGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.codegen.mybatis3.model.BaseRecordGenerator;
import org.mybatis.generator.codegen.mybatis3.model.PrimaryKeyGenerator;
import org.mybatis.generator.codegen.mybatis3.model.RecordWithBLOBsGenerator;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.PropertyHolder;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

public class MyIntrospectedTable extends IntrospectedTableMyBatis3Impl {

    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator, List<String> warnings,
                                               ProgressCallback progressCallback) {
        // if (javaClientGenerator == null) {
        // if (context.getSqlMapGeneratorConfiguration() != null) {
        // xmlMapperGenerator = new XMLMapperGenerator();
        // }
        // } else {
        // xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
        // }

        xmlMapperGenerator = new MyXmlMapperGenerator();

        initializeAbstractGenerator(xmlMapperGenerator, warnings, progressCallback);
    }

    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        }

        // String type = context.getJavaClientGeneratorConfiguration()
        // .getConfigurationType();

        // AbstractJavaClientGenerator javaGenerator;
        // if ("XMLMAPPER".equalsIgnoreCase(type)) {
        // javaGenerator = new JavaMapperGenerator();
        // } else if ("MIXEDMAPPER".equalsIgnoreCase(type)) {
        // javaGenerator = new MixedClientGenerator();
        // } else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) {
        // javaGenerator = new AnnotatedClientGenerator();
        // } else if ("MAPPER".equalsIgnoreCase(type)) {
        // javaGenerator = new JavaMapperGenerator();
        // } else {
        // javaGenerator = (AbstractJavaClientGenerator) ObjectFactory
        // .createInternalObject(type);
        // }
        // javaGenerator = new MyJavaMapperGenerator();

        return new MyJavaMapperGenerator("src/main/java");
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

    public String calculateJavaModelPackage() {
        JavaModelGeneratorConfiguration config = context
                .getJavaModelGeneratorConfiguration();

        return config.getTargetPackage() +
                fullyQualifiedTable.getSubPackageForModel(isSubPackagesEnabled(config));
    }

    private boolean isSubPackagesEnabled(PropertyHolder propertyHolder) {
        return isTrue(propertyHolder.getProperty(PropertyRegistry.ANY_ENABLE_SUB_PACKAGES));
    }

}
