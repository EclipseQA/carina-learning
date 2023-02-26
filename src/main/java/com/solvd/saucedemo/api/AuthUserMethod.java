package com.solvd.saucedemo.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.Configuration;

@Endpoint(url = "${base_url}/auth/login", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/users/_post/_auth/reqSuc.json")
@ResponseTemplatePath(path = "api/users/_post/_auth/respSuc.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class AuthUserMethod extends AbstractApiMethodV2 {

    public AuthUserMethod(){
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
