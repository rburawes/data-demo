package com.demo.model;

import javax.persistence.ParameterMode;

/**
 * Holds the parameter value, type mode for stored procedures.
 */
public class StoredProcParameter {

    private String name;

    private Object paramValue;

    private ParameterMode mode;

    private Class<?> clazz;

    private StoredProcParameter(ParamBuilder builder) {
        name = builder.name;
        paramValue = builder.paramValue;
        mode = builder.mode;
        clazz = builder.clazz;
    }

    public String getName() {
        return name;
    }

    public Object getParamValue() {
        return paramValue;
    }

    public ParameterMode getMode() {
        return mode;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public static class ParamBuilder {

        private String name;

        private Object paramValue;

        private ParameterMode mode;

        private Class<?> clazz;

        public ParamBuilder(String name){
            this.name = name;
        }

        public ParamBuilder value(Object value) {
            paramValue = value;
            return this;
        }

        public ParamBuilder mode(ParameterMode value) {
            mode = value;
            return this;
        }

        public ParamBuilder clazz(Class<?> value) {
            clazz = value;
            return this;
        }

        public StoredProcParameter build() {
            return new StoredProcParameter(this);
        }
    }
}
