package com.solvd.api;

import com.qaprosoft.apitools.validation.JsonComparatorContext;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;


public class PutUserTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "EclipseQA")
    @TestLabel(name = "update", value = {"api", "acceptance"})
    public void updateUserByFirstNameAndLastName(){
        PutUserMethod putUserMethod = new PutUserMethod();
        putUserMethod.replaceUrlPlaceholder("id", "1");
        putUserMethod.getProperties().remove("email");

        putUserMethod.callAPIExpectSuccess();
        putUserMethod.validateResponse(JSONCompareMode.LENIENT);

    }


    @Test
    @MethodOwner(owner = "EclipseQA")
    @TestLabel(name = "update", value = {"api", "acceptance"})
    public void updateUserByLastName(){
        PutUserMethod putUserMethod = new PutUserMethod();
        putUserMethod.replaceUrlPlaceholder("id", "2");
        putUserMethod.getProperties().remove("email");
        putUserMethod.getProperties().remove("first_name");

        putUserMethod.callAPIExpectSuccess();
        putUserMethod.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    @MethodOwner(owner = "EclipseQA")
    @TestLabel(name = "update", value = {"api", "acceptance"})
    public void updateUserByFirstNameAndEmail(){
        PutUserMethod putUserMethod = new PutUserMethod();
        putUserMethod.replaceUrlPlaceholder("id", "3");
        putUserMethod.getProperties().remove("last_name");

        putUserMethod.callAPIExpectSuccess();
        putUserMethod.validateResponse(JSONCompareMode.LENIENT);
    }
}
