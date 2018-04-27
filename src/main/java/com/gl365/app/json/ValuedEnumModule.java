package com.gl365.app.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleSerializers;

import java.util.Collections;

/**
 * @author Chen, Zhuhui
 */
public class ValuedEnumModule extends Module {
    /**
     * Method that returns a display that can be used by Jackson
     * for informational purposes, as well as in associating extensions with
     * module that provides them.
     */
    @Override
    public String getModuleName() {
        return "ValuedEnum";
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }

    /**
     * Method called by {@link ObjectMapper} when module is registered.
     * It is called to let module register functionality it provides,
     * using callback methods passed-in context object exposes.
     *
     * @param context
     */
    @Override
    public void setupModule(SetupContext context) {
        context.addBeanDeserializerModifier(new ValuedEnumDeserializerModifier());
        SimpleSerializers serializers = new SimpleSerializers(Collections.singletonList(new ValuedEnumSerializer()));
        context.addSerializers(serializers);
    }
}
