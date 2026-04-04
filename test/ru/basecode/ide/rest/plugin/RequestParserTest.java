package ru.basecode.ide.rest.plugin;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author danblack
 */
public class RequestParserTest extends Assert{

    @Test
    public void shouldNotChangeTheUrl() {
        assertEquals("param", RequestParser.encode("param"));
        assertEquals("?param", RequestParser.encode("?param"));
        assertEquals("?param=", RequestParser.encode("?param="));
        assertEquals("http://www.site.com?param", RequestParser.encode("http://www.site.com?param"));
        assertEquals("http://www.site.com?param=", RequestParser.encode("http://www.site.com?param="));
        assertEquals("http://www.site.com?param=value", RequestParser.encode("http://www.site.com?param=value"));
        assertEquals("http://www.site.com?param=value", RequestParser.encode("http://www.site.com?param=value"));
        assertEquals("http://www.site.com?param=value&param2=value2", RequestParser.encode("http://www.site.com?param=value&param2=value2"));
    }

    @Test
    public void shouldTrimSpacesInParamName() {
        assertEquals("?param", RequestParser.encode("?param "));
        assertEquals("?param", RequestParser.encode("? param "));
        assertEquals("?param", RequestParser.encode("? param"));
        assertEquals("?param=", RequestParser.encode("?param ="));
        assertEquals("?param=", RequestParser.encode("? param ="));
        assertEquals("?param=", RequestParser.encode("? param="));
    }

    @Test
    public void shouldEncodeSpaces() {
        assertEquals("?param=1+2", RequestParser.encode("?param=1 2"));
        assertEquals("?p+aram=1+2", RequestParser.encode("?p aram=1 2"));
    }

    @Test
    public void shouldEncodeSymbols() {
        assertEquals("?param=%D1%8B%D1%91", RequestParser.encode("?param=ыё"));
    }

    @Test
    public void requestShouldHaveNullParamsByDefault() {
        Request request = new Request(Request.Method.GET, "http://example.com", java.util.Collections.emptyList(), null);
        assertNull(request.getParams());
    }

    @Test
    public void requestParamsShouldStoreTimeout() {
        Request.Params params = new Request.Params(5000);
        assertEquals(5000, params.getTimeout());

        Request request = new Request(Request.Method.GET, "http://example.com", java.util.Collections.emptyList(), null, params);
        assertNotNull(request.getParams());
        assertEquals(5000, request.getParams().getTimeout());
    }

    @Test
    public void requestParamsToStringShouldIncludeTimeout() {
        Request.Params params = new Request.Params(321);
        assertTrue(params.toString().contains("321"));
    }

    @Test
    public void requestEqualityShouldIncludeParams() {
        Request.Params params = new Request.Params(1000);
        Request withParams = new Request(Request.Method.GET, "http://example.com", java.util.Collections.emptyList(), null, params);
        Request withoutParams = new Request(Request.Method.GET, "http://example.com", java.util.Collections.emptyList(), null);
        assertNotEquals(withParams, withoutParams);

        Request withSameParams = new Request(Request.Method.GET, "http://example.com", java.util.Collections.emptyList(), null, new Request.Params(1000));
        assertEquals(withParams, withSameParams);
    }

}