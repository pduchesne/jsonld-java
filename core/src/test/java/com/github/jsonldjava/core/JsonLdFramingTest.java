package com.github.jsonldjava.core;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.github.jsonldjava.utils.JsonUtils;
import org.junit.Test;

/**
 * 
 * @author Philippe Duchesne pduchesne@gmail.com
 */
public class JsonLdFramingTest {

    @Test
    public void testFrame0001() throws IOException, JsonLdError {
        final Object frame = JsonUtils
                .fromInputStream(getClass().getResourceAsStream("/custom/frame-0001-frame.jsonld"));
        final Object in = JsonUtils
                .fromInputStream(getClass().getResourceAsStream("/custom/frame-0001-in.jsonld"));

        final Map<String, Object> frame2 = JsonLdProcessor.frame(in, frame, new JsonLdOptions());

        assertEquals(2, frame2.size());
    }

    @Test
    public void testFrame0002() throws IOException, JsonLdError {
        final Object frame = JsonUtils
                .fromInputStream(getClass().getResourceAsStream("/custom/frame-0002-frame.jsonld"));
        final Object in = JsonUtils
                .fromInputStream(getClass().getResourceAsStream("/custom/frame-0002-in.jsonld"));

        JsonLdOptions opts = new JsonLdOptions();
        opts.setCompactArrays(false);
        final Map<String, Object> frame2 = JsonLdProcessor.frame(in, frame, opts);

        final Object out = JsonUtils
                .fromInputStream(getClass().getResourceAsStream("/custom/frame-0002-out.jsonld"));
        System.out.println(JsonUtils.toPrettyString(out));
        System.out.println(JsonUtils.toPrettyString(frame2));
        assertEquals(out, frame2);
    }

    @Test
    public final void test1() throws Exception {
        Object frame = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0001-frame.jsonld"));
        Object input = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0001-in.jsonld"));
        Object expected = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0001-out.jsonld"));

        JsonLdOptions opts = new JsonLdOptions();

        Object framed = JsonLdProcessor.frame(input, frame, opts);

        Assert.assertTrue(JsonLdUtils.deepCompare(expected, framed));
        //System.out.println(JsonUtils.toPrettyString(framed));


        // test the reverse transformation (flatten + compact)
        // take the original input context to do the compacting
        Map context = new HashMap();
        context.put("@context", ((Map)input).get("@context"));
        Object flattened = JsonLdProcessor.flatten(framed, opts);
        Object compacted = JsonLdProcessor.compact(flattened, context, opts);

        Assert.assertTrue(JsonLdUtils.deepCompare(input, compacted));
    }

    @Test
    public final void test2() throws Exception {
        Object frame = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0002-frame.jsonld"));
        Object input = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0002-in.jsonld"));
        Object expected = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0002-out.jsonld"));

        JsonLdOptions opts = new JsonLdOptions();

        Object framed = JsonLdProcessor.frame(input, frame, opts);

        Assert.assertTrue(JsonLdUtils.deepCompare(expected, framed));
        //System.out.println(JsonUtils.toPrettyString(framed));


        // test the reverse transformation (flatten + compact)
        // take the original input context to do the compacting
        Map context = new HashMap();
        context.put("@context", ((Map)input).get("@context"));
        Object flattened = JsonLdProcessor.flatten(framed, opts);
        Object compacted = JsonLdProcessor.compact(flattened, context, opts);

        Assert.assertTrue(JsonLdUtils.deepCompare(input, compacted));


    }

}
