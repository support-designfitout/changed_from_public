package ru.basecode.ide.rest.plugin;

import java.util.List;

/**
 * @author danblack
 */
public class Request {

    public static class Header {
        private final String name;
        private final String value;

        public Header(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Header header = (Header) o;

            if (name != null ? !name.equals(header.name) : header.name != null) return false;
            return value != null ? value.equals(header.value) : header.value == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }

    enum Method {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }

    public static class Params {
        private final int timeout;

        public Params(int timeout) {
            this.timeout = timeout;
        }

        public int getTimeout() {
            return timeout;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Params params = (Params) o;

            return timeout == params.timeout;

        }

        @Override
        public int hashCode() {
            return timeout;
        }

        @Override

        public String toString() {
            return "Params{" +
                    "timeout=" + timeout +
                    '}';
        }
    }

    private final Method method;
    private final String url;
    private final List<Header> headers;
    private final String body;
    private final Params params;

    public Request(Method method, String url, List<Header> headers, String body) {
        this(method, url, headers, body, null);
    }

    public Request(Method method, String url, List<Header> headers, String body, Params params) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
        this.params = params;
    }

    public Method getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public Params getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "Request{" +
                "method=" + method +
                ", url='" + url + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", params=" + params +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (method != request.method) return false;
        if (url != null ? !url.equals(request.url) : request.url != null) return false;
        if (headers != null ? !headers.equals(request.headers) : request.headers != null) return false;
        if (body != null ? !body.equals(request.body) : request.body != null) return false;
        return params != null ? params.equals(request.params) : request.params == null;

    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }
}
