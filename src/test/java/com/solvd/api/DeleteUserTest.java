package com.solvd.api;

import com.qaprosoft.apitools.validation.JsonComparatorContext;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DeleteUserTest implements IAbstractTest {

    @Test()
    @MethodOwner()
    public void testDeleteUserById() {
        DeleteUserMethod deleteUserMethod = new DeleteUserMethod();
        deleteUserMethod.replaceUrlPlaceholder("id", "1");

        deleteUserMethod.callAPIExpectSuccess();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedTime = formatter.format(Calendar.getInstance().getTime());
        JsonComparatorContext context = JsonComparatorContext.context()
                .<String>withPredicate("isCurrentTime",
                        date -> date.startsWith(formattedTime));
        System.out.println(formattedTime);
        deleteUserMethod.validateResponse(JSONCompareMode.LENIENT, context);
    }
}
