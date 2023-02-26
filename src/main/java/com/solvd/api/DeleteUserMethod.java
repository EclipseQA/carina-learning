package com.solvd.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.Configuration;

@Endpoint(url = "${base_url}/users/${id}", methodType = HttpMethodType.DELETE)
@ResponseTemplatePath(path = "api/users/_delete/respSuc.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class DeleteUserMethod extends AbstractApiMethodV2 {

    public DeleteUserMethod(){
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
