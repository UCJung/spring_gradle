package com.spay.core.constants;
import java.nio.charset.Charset;

import com.google.common.base.Charsets;

public class SystemConstants {

    public final static Charset DEFAULT_ENCODE_CHARSET = Charsets.UTF_8;
    public final static String DEFAULT_ENCODE = Charsets.UTF_8.name();
    public final static int PROPERTIES_REFRESH_SECONDS = 5;

    public final static String PROPERTIES_SEPERATOR = "_";
    public final static String DOT = ".";
    public final static String ERROR_PREFIX = "error.core.";

    public final static String SPAY_ENC_KEY = "SPAY_PROJECT_PRIVATE_KEY";
    public final static String ENCYPTION_PRIVATE_KEY_ALGORITHM = "PBEWithMD5AndDES";

    public final static String DEFAULT_PACKAGE_PATTERN = "classpath*:com/spay/**/*.class";
    // public final static String ENCYPTION_PASSWORD_ALGORITHM = "PBEWithMD5AndDES";

    public final static long BASE = 1024;
    public final static long KILOBYTES = BASE;
    public final static long MEGABYTES = BASE * KILOBYTES;
    public final static long GIGABYTES = BASE * MEGABYTES;

    public final static long MAX_UPLOAD_SIZE = 3 * MEGABYTES;
    public final static int MAX_MULTI_UPLOAD_COUNT = 3;

    public final static String DOMAIN = "spay.com";
    public final static String DOT_DOMAIN = ".spay.com";

    public static final String HEADER_KEY = "key";
    public static final String TOKEN_KEY = "token";
    public static final String CLIENT_KEY = "client";
    public static final String HTTP_POST_PARAM_KEY = "params";

    public static final String DEPLOY_PHASE = "deploy";
    public static final String DEPLOY_PHASE_LOCAL = "local";
    public static final String DEPLOY_PHASE_DEV = "dev";
    public static final String DEPLOY_PHASE_ALPHA = "alpha";
    public static final String DEPLOY_PHASE_BETA = "beta";
    public static final String DEPLOY_PHASE_REAL = "real";

}
