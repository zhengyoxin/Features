package com.duowan.mobile.compiler;

import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

/**
 * Created by ericwu on 2017/9/29.
 */

public class FeatureUnit {

    private Element parent;

    private String group;

    private String path;

    private String alias;

    private ArrayList<Element> children = new ArrayList<>();

    private TypeMirror valueType;

    private Object defValue;

    public void setParent(Element parent, TypeMirror valueType) {
        this.parent = parent;
        this.valueType = valueType;
        if (!children.isEmpty()) {
            for (Element child : children) {
                if (!checkChildType(child)) {
                    throw new IllegalArgumentException("setting value type not match!!!");
                }
            }
        }
    }

    public Element getParent() {
        return parent;
    }

    public void addChild(Element child) {
        if (parent == null || checkChildType(child)) {
            children.add(child);
        } else {
            throw new IllegalArgumentException("setting value type not match!!!");
        }

    }

    public ArrayList<Element> getChildren() {
        return children;
    }

    public String getWrapperClzName() {
        return parent.getSimpleName() + "Wrapper";
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGroup() {
        return group;
    }

    public String getPath() {
        return path;
    }

    public String getAlias() {
        return alias;
    }

    public TypeMirror getValueType() {
        return valueType;
    }

    public void setValueType(TypeMirror valueType) {
        this.valueType = valueType;
    }

    public Object getDefValue() {
        return defValue;
    }

    public void setDefValue(Object defValue) {
        this.defValue = defValue;
    }

    // 校验类型
    private boolean checkChildType(Element child) {
        List<? extends AnnotationMirror> mirrors = child.getAnnotationMirrors();
        for (AnnotationMirror mirror : mirrors) {
            if (TypeName.get(mirror.getAnnotationType()).equals(TypeName.get(valueType))) {
                return true;
            }
        }
        return false;
    }
}
