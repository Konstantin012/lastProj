package com.telesens.framework.model;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class GenderXStreamConverter implements Converter {

    @Override
    public boolean canConvert(Class clazz) {
        return Gender.class.isAssignableFrom(clazz);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext ctx) {
        Gender gender = (Gender)value;
        writer.setValue(gender.toString());
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        context.currentObject();
        return Gender.valueOf(context.currentObject().toString().charAt(0));
    }
}
