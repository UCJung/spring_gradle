package com.spay.web.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class AuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
    private String domain;

    /**
     * @param request
     */
    public AuthenticationDetails(HttpServletRequest request) {
        super(request);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }	
}
