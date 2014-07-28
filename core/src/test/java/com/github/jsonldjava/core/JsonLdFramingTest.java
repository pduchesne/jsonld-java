package com.github.jsonldjava.core;

import com.github.jsonldjava.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;

/**
 * 
 * @author Philippe Duchesne pduchesne@gmail.com
 */
public class JsonLdFramingTest {

    @Test
    public final void test1() throws Exception {
        Object frame = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0001-frame.jsonld"));
        Object input = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0001-in.jsonld"));
        Object expected = JsonUtils.fromInputStream(this.getClass().getResourceAsStream("/custom/framing-reverse-0001-out.jsonld"));

        JsonLdOptions opts = new JsonLdOptions();

        Object framed = JsonLdProcessor.frame(input, frame, opts);

        Assert.assertTrue(JsonLdUtils.deepCompare(expected, framed));
        //System.out.println(JsonUtils.toPrettyString(framed));
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
    }

}
