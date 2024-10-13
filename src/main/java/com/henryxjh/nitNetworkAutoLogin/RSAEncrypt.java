package com.henryxjh.nitNetworkAutoLogin;

import java.io.IOException;
import java.io.InputStream;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.io.IOAccess;

public class RSAEncrypt {
    /**Run {@code consloe.log(document.getElementById("publicKeyExponent").value)} in devtools' console in login.jsp. */
    private static final String pubKeyExponent = "10001";
    /**Run {@code consloe.log(document.getElementById("publicKeyModulus").value)} in devtools' console in login.jsp. */
    private static final String pubKeyModulus = "94dd2a8675fb779e6b9f7103698634cd400f27a154afa67af6166a43fc26417222a79506d34cacc7641946abda1785b7acf9910ad6a0978c91ec84d40b71d2891379af19ffb333e7517e390bd26ac312fe940c340466b4a5d4af1d65c3b5944078f96a1a51a5a53e4bc302818b7c9f63c4a1b07bd7d874cef1c3d4b2f5eb7871";

    /**
     * 
     * @param passwordWithSalt password>mac
     */
    public static String encryptPassword(String passwordWithSalt) {
        String result = "";
        try (Context context = Context.newBuilder("js").allowIO(IOAccess.ALL).build()) {
            InputStream iStreamSec = Main.class.getResourceAsStream("Security.js");
            InputStream iStreamAuth = Main.class.getResourceAsStream("AuthInterface.js");
            String SecScript = new String(iStreamSec.readAllBytes());
            String AuthScript = new String(iStreamAuth.readAllBytes());
            context.eval("js", SecScript);
            context.eval("js", String.format(AuthScript, pubKeyExponent, pubKeyModulus));
            result = context.eval("js", String.format("encryptedPassword(\"%s\")", passwordWithSalt)).asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
