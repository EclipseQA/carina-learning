package com.solvd.saucedemo.web.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.saucedemo.web.components.HeaderMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoppingPage extends AbstractPage {

    @FindBy(id = "shopping_cart_container")
    private ExtendedWebElement productsHeader;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<ExtendedWebElement> productsPrice;
    @FindBy(className = "product_sort_container")
    private ExtendedWebElement filterSortContainer;
    @FindBy(xpath = "//div[@id='inventory_container']//button[contains(@id,'add-to-cart')]")
    private List<ExtendedWebElement> addToCartButtons;

    @FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item']")
    private List<ExtendedWebElement> products;
    @FindBy(xpath = "//div[@id='inventory_container']//button[contains(@id,'remove')]")
    private List<ExtendedWebElement> removeButtons;
    @FindBy(id = "shopping_cart_container")
    private ExtendedWebElement shoppingCartButton;
    @FindBy(xpath = "//button[contains(@id,'remove')]/ancestor::div[@class='inventory_item']")
    private List<ExtendedWebElement> addedProducts;
    @FindBy(id = "react-burger-menu-btn")
    private HeaderMenu headerMenu;
    private int expectedAmountOfAddedProducts;

    public HeaderMenu switchToHeaderMenu() {
        return new HeaderMenu(driver);
    }

    public List<String> getAddedProductsNames() {
        List<String> listOfNames = new ArrayList<>();
        for (ExtendedWebElement element :
                addedProducts) {
            String nameToFormat = element.getText();
            int indexOfCut = nameToFormat.indexOf("\n");
            String formattedName = nameToFormat.substring(0, indexOfCut);
            listOfNames.add(formattedName);
        }
        return listOfNames;
    }

    public CartPage goToCartPage() {
        shoppingCartButton.click();
        return new CartPage(driver);
    }

    public void addProductToCart() {
        expectedAmountOfAddedProducts = new Random().nextInt(3) + 1;
        for (int i = 0; i <= expectedAmountOfAddedProducts - 1; i++) {
            addToCartButtons.get(i).click();
        }
    }

    public void deleteProductsFromCart() {
        for (ExtendedWebElement removeButton :
                removeButtons) {
            removeButton.click();
        }
    }

    public List<Float> getProductsPrices() {
        List<Float> prices = new ArrayList<>();
        for (ExtendedWebElement product :
                productsPrice) {
            prices.add(Float.valueOf(product.getText().replace("$", "")));
        }
        return prices;
    }

    public int getActualAmountOfAddedProducts() {
        String textAmount = shoppingCartButton.getText();
        int actualAmountValue;
        if (textAmount.equals("")) {
            return 0;
        } else {
            actualAmountValue = Integer.parseInt(textAmount);
            return actualAmountValue;
        }

    }

    public int getExpectedAmountOfAddedProducts() {
        return expectedAmountOfAddedProducts;
    }


    public void sortProducts(SortProductsType sortType) {
        filterSortContainer.select(sortType.getSortType());
    }


    public ShoppingPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(productsHeader);
    }

    public enum SortProductsType {

        NAME_ASC("Name (A to Z)"),
        NAME_DESC("Name (Z to A)"),
        PRICE_ASC("Price (low to high)"),
        PRICE_DESC("Price (high to low)");

        private final String sortType;

        SortProductsType(String sortType) {
            this.sortType = sortType;
        }

        public String getSortType() {
            return sortType;
        }
    }
}
