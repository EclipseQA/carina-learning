package com.solvd.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.*;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.Configuration;

@Endpoint(url = "${base_url}/users/${id}", methodType = HttpMethodType.PUT)
@RequestTemplatePath(path = "api/users/_put/reqSuc.json")
@ResponseTemplatePath(path = "api/users/_put/respSuc.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
@PropertiesPath(path = "api/users/generatedUser.properties")
public class PutUserMethod extends AbstractApiMethodV2 {

    public PutUserMethod(){
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
