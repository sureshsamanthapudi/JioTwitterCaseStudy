package example.com.jiotwittercasestudy.constants;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import example.com.jiotwittercasestudy.model.HttpParameter;
import example.com.jiotwittercasestudy.model.Query;


/**
 * Created by sureshs on 03-01-2018.
 */

public class TwitterConstants {

    public static final String TWEETS = "TWEETS";
    public static final String HASHTAG = "HASHTAG";
    public static final String SELECTED_TAG = "SELECTEDTAG";
    public static final String AUTHORIZATION = "Authorization";

    public static final String CONSUMER_KEY = "6xKnQCfbxUQYJiW6T4LlFC858";
    public static final String CONSUMER_SECRET = "iZ2V8ND1v00FBjNeMHGqDfviFVEAHK2TXPMZg1mVzYKJuft5XV";
    public static final String ACCESS_TOKEN = "501966334-cn9cq1oJYmvbFAG24Bd098pLAjXaMUv75qLQ4AJV";
    public static final String ACCESS_TOKEN_SECRET = "ONcI4wLS3idp5knS2wA0KS5h7g0KOnmvlN7SIgl8ZzTXv";
    public static final String OWNER_ID = "501966334";

    //JIO Credentials
  /*  private static final String CONSUMER_KEY = "SWQ49sXSN5A9Udpqyj9mUuPW5";
    private static final String CONSUMER_SECRET = "UYGWF3kNue55PbsUhMfNIH5mVGtXEIm3bDMWpuarXo4pkf3WHi";
    private static final String ACCESS_TOKEN = "784358090930065408-MuWuOeWfM3HkuHyAyyJONXKoGQ8sTXd";
    private static final String ACCESS_TOKEN_SECRET = "Pgc1sgNGuIQajEhUi06L2gITtaP85RvDGBCWVxNw4vty408";
    public static final String OWNER_ID = "784358090930065408";*/


    private final Random RAND = new Random();
    private static final String HMAC_SHA1 = "HmacSHA1";

    //Encoding
    private static final char last2byte = (char) Integer.parseInt("00000011", 2);
    private static final char last4byte = (char) Integer.parseInt("00001111", 2);
    private static final char last6byte = (char) Integer.parseInt("00111111", 2);
    private static final char lead6byte = (char) Integer.parseInt("11111100", 2);
    private static final char lead4byte = (char) Integer.parseInt("11110000", 2);
    private static final char lead2byte = (char) Integer.parseInt("11000000", 2);
    private static final char[] encodeTable = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};


    public String getAuthHeader(String url){
        Log.i("TwitterConstants","getAuthHeader:"+url);
        HttpParameter[] httpParameters = new HttpParameter[1];
        HttpParameter httpParameter = new HttpParameter("include_my_retweet","false");
        httpParameters[0] = httpParameter;
        String headers = generateAuthorizationHeader("GET",url,httpParameters);
        return headers;
    }

    String generateAuthorizationHeader(String method, String url, HttpParameter[] params) {
        long timestamp = System.currentTimeMillis() / 1000;
        long nonce = timestamp + RAND.nextInt();
        return generateAuthorizationHeader(method, url, params, String.valueOf(nonce), String.valueOf(timestamp));
    }

    String generateAuthorizationHeader(String method, String url, HttpParameter[] params, String nonce, String timestamp) {
        if (null == params) {
            params = new HttpParameter[0];
        }
        List<HttpParameter> oauthHeaderParams = new ArrayList<HttpParameter>(5);
        oauthHeaderParams.add(new HttpParameter("oauth_consumer_key", CONSUMER_KEY));
        oauthHeaderParams.add(new HttpParameter("oauth_signature_method", "HMAC-SHA1"));
        oauthHeaderParams.add(new HttpParameter("oauth_timestamp", timestamp));
        oauthHeaderParams.add(new HttpParameter("oauth_nonce", nonce));
        oauthHeaderParams.add(new HttpParameter("oauth_version", "1.0"));
        oauthHeaderParams.add(new HttpParameter("oauth_token", ACCESS_TOKEN));
        List<HttpParameter> signatureBaseParams = new ArrayList<HttpParameter>(oauthHeaderParams.size() + params.length);
        signatureBaseParams.addAll(oauthHeaderParams);

        parseGetParameters(url, signatureBaseParams);
        StringBuilder base = new StringBuilder(method).append("&")
                .append(HttpParameter.encode(constructRequestURL(url))).append("&");
        base.append(HttpParameter.encode(normalizeRequestParameters(signatureBaseParams)));
        String oauthBaseString = base.toString();
        System.out.println("OAuth base string: "+ oauthBaseString);
        String signature = generateSignature(oauthBaseString);
        System.out.println("OAuth signature: "+ signature);

        oauthHeaderParams.add(new HttpParameter("oauth_signature", signature));

        // http://oauth.net/core/1.0/#rfc.section.9.1.1
	       /* if (realm != null) {
	            oauthHeaderParams.add(new HttpParameter("realm", realm));
	        }*/
        return "OAuth " + encodeParameters(oauthHeaderParams, ",", true);
    }

    String generateSignature(String data/*, OAuthToken token*/) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance(HMAC_SHA1);
            SecretKeySpec spec;
	           /* if (null == token) {
	                String oauthSignature = HttpParameter.encode(CONSUMER_SECRET) + "&";
	                spec = new SecretKeySpec(oauthSignature.getBytes(), HMAC_SHA1);
	            } else {*/
            //spec = token.getSecretKeySpec();
            //if (null == spec) {
            String oauthSignature = HttpParameter.encode(CONSUMER_SECRET) + "&" + HttpParameter.encode(ACCESS_TOKEN_SECRET);
            spec = new SecretKeySpec(oauthSignature.getBytes(), HMAC_SHA1);
            //token.setSecretKeySpec(spec);
            //}
            // }
            mac.init(spec);
            byteHMAC = mac.doFinal(data.getBytes());
        } catch (InvalidKeyException ike) {
            System.out.println("Failed initialize \"Message Authentication Code\" (MAC) "+ ike);
            throw new AssertionError(ike);
        } catch (NoSuchAlgorithmException nsae) {
            System.out.println("Failed to get HmacSHA1 \"Message Authentication Code\" (MAC) "+ nsae);
            throw new AssertionError(nsae);
        }
        return encode(byteHMAC);
    }

    public static String encode(byte[] from) {
        StringBuilder to = new StringBuilder((int) (from.length * 1.34) + 3);
        int num = 0;
        char currentByte = 0;
        for (int i = 0; i < from.length; i++) {
            num = num % 8;
            while (num < 8) {
                switch (num) {
                    case 0:
                        currentByte = (char) (from[i] & lead6byte);
                        currentByte = (char) (currentByte >>> 2);
                        break;
                    case 2:
                        currentByte = (char) (from[i] & last6byte);
                        break;
                    case 4:
                        currentByte = (char) (from[i] & last4byte);
                        currentByte = (char) (currentByte << 2);
                        if ((i + 1) < from.length) {
                            currentByte |= (from[i + 1] & lead2byte) >>> 6;
                        }
                        break;
                    case 6:
                        currentByte = (char) (from[i] & last2byte);
                        currentByte = (char) (currentByte << 4);
                        if ((i + 1) < from.length) {
                            currentByte |= (from[i + 1] & lead4byte) >>> 4;
                        }
                        break;
                }
                to.append(encodeTable[currentByte]);
                num += 6;
            }
        }
        if (to.length() % 4 != 0) {
            for (int i = 4 - to.length() % 4; i > 0; i--) {
                to.append("=");
            }
        }
        return to.toString();
    }


    private void parseGetParameters(String url, List<HttpParameter> signatureBaseParams) {
        int queryStart = url.indexOf("?");
        if (-1 != queryStart) {
            url.split("&");
            String[] queryStrs = url.substring(queryStart + 1).split("&");
            try {
                for (String query : queryStrs) {
                    String[] split = query.split("=");
                    if (split.length == 2) {
                        signatureBaseParams.add(
                                new HttpParameter(URLDecoder.decode(split[0],
                                        "UTF-8"), URLDecoder.decode(split[1],
                                        "UTF-8"))
                        );
                    } else {
                        signatureBaseParams.add(
                                new HttpParameter(URLDecoder.decode(split[0],
                                        "UTF-8"), "")
                        );
                    }
                }
            } catch (UnsupportedEncodingException ignore) {
            }

        }

    }
    String constructRequestURL(String url) {
        int index = url.indexOf("?");
        if (-1 != index) {
            url = url.substring(0, index);
        }
        int slashIndex = url.indexOf("/", 8);
        String baseURL = url.substring(0, slashIndex).toLowerCase();
        int colonIndex = baseURL.indexOf(":", 8);
        if (-1 != colonIndex) {
            // url contains port number
            if (baseURL.startsWith("http://") && baseURL.endsWith(":80")) {
                // http default port 80 MUST be excluded
                baseURL = baseURL.substring(0, colonIndex);
            } else if (baseURL.startsWith("https://") && baseURL.endsWith(":443")) {
                // http default port 443 MUST be excluded
                baseURL = baseURL.substring(0, colonIndex);
            }
        }
        url = baseURL + url.substring(slashIndex);

        return url;
    }

    String normalizeRequestParameters(HttpParameter[] params) {
        return normalizeRequestParameters(toParamList(params));
    }

    private String normalizeRequestParameters(List<HttpParameter> params) {
        Collections.sort(params);
        return encodeParameters(params);
    }

    private List<HttpParameter> toParamList(HttpParameter[] params) {
        List<HttpParameter> paramList = new ArrayList<HttpParameter>(params.length);
        paramList.addAll(Arrays.asList(params));
        return paramList;
    }

    public String encodeParameters(List<HttpParameter> httpParams) {
        return encodeParameters(httpParams, "&", false);
    }

    public String encodeParameters(List<HttpParameter> httpParams, String splitter, boolean quot) {
        StringBuilder buf = new StringBuilder();
        for (HttpParameter param : httpParams) {
            if (!param.isFile()) {
                if (buf.length() != 0) {
                    if (quot) {
                        buf.append("\"");
                    }
                    buf.append(splitter);
                }
                buf.append(HttpParameter.encode(param.getName())).append("=");
                if (quot) {
                    buf.append("\"");
                }
                buf.append(HttpParameter.encode(param.getValue()));
            }
        }
        if (buf.length() != 0) {
            if (quot) {
                buf.append("\"");
            }
        }
        return buf.toString();
    }

    public String getQuery(String hashTag){
        int count = 20;
        int sinceId = 0;
        Query querySince = new Query("#"+hashTag);
        querySince.setCount(count);
        querySince.setSinceId(sinceId);

        HttpParameter[] params = querySince.asHttpParameterArray();

        String httpParameters = HttpParameter.encodeParameters(params);
        Log.i("TwitterConstants","QueryParameteres"+httpParameters);
        return httpParameters;
    }
}
