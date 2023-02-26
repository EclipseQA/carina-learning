package com.solvd.api;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import io.restassured.path.json.JsonPath;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetProductTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "EclipseQA")
    @TestLabel(name = "get", value = {"api", "acceptance"})
    public void testGetAllProducts() {
        GetProductMethod getProductMethod = new GetProductMethod();
        getProductMethod.callAPIExpectSuccess();
        getProductMethod.validateResponse(JSONCompareMode.STRICT_ORDER);
        getProductMethod.validateResponseAgainstSchema("api/products/_get/respSuc.schema");
    }

    @Test
    @MethodOwner(owner = "EclipseQA")
    @TestLabel(name = "get", value = {"api", "acceptance"})
    public void testThumbnailContainsProductId(){
        GetProductMethod getProductMethod = new GetProductMethod();
        getProductMethod.callAPIExpectSuccess();
        JsonPath productPath = getProductMethod.callAPI()
                .body()
                .jsonPath();
        List<Integer> listId = productPath
                .get("products.id");
        List<String> listThumbnail = productPath
                .get("products.thumbnail");

        for (int i = 0; i < listId.size(); i++) {
            Assert.assertTrue(listThumbnail.get(i).contains(listId.get(i).toString()));
        }
    }
}
