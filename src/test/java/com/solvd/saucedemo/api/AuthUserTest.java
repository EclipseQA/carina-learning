package com.solvd.saucedemo.api;

import com.qaprosoft.apitools.validation.JsonComparatorContext;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;


public class AuthUserTest implements IAbstractTest {

    @Test()
    @MethodOwner(owner = "EclipseQA")
    @TestLabel(name = "authentication", value = {"api", "acceptance"})
    public void testAuthUserWithValidCreds(){
        AuthUserMethod authUserMethod = new AuthUserMethod();
        authUserMethod.setProperties("api/users/user.properties");
        authUserMethod.callAPIExpectSuccess();
        JsonComparatorContext context = JsonComparatorContext.context()
                .<String>withPredicate("isEmailValid", email -> email.contains("@"))
                .<String>withPredicate("isGenderValid", gender -> gender.equals("female") || gender.equals("male"))
                .<String>withPredicate("isImageValid", image -> image.startsWith("https://robohash.org"));
        authUserMethod.validateResponse(JSONCompareMode.STRICT_ORDER, context);
        authUserMethod.validateResponseAgainstSchema("api/users/_post/_auth/authResp.schema");
    }

    @Test()
    @MethodOwner(owner = "EclipseQA")
    @TestLabel(name = "authentication", value = {"api", "acceptance"})
    public void testAuthUserWithInvalidCreds(){
        AuthUserMethod authUserMethod = new AuthUserMethod();
        authUserMethod.setProperties("api/users/generatedUser.properties");
        authUserMethod.expectResponseStatus(HttpResponseStatusType.BAD_REQUEST_400);
        authUserMethod.setResponseTemplate("api/users/_post/_auth/respFail.json");
        authUserMethod.callAPI();
        authUserMethod.validateResponse();
    }
}
