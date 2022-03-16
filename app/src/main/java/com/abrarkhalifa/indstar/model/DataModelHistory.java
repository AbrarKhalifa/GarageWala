package com.abrarkhalifa.indstar.model;

public class DataModelHistory {

    String garageName,
            garageServiceOne, garageServiceTwo, garageServiceThree;

    String garageServicePriceOne, garageServicePriceTwo, garageServicePriceThree,
            garageServiceTotalPrice;

    public DataModelHistory(String garageName, String garageServiceOne, String garageServiceTwo, String garageServiceThree, String garageServicePriceOne, String garageServicePriceTwo, String garageServicePriceThree, String garageServiceTotalPrice) {
        this.garageName = garageName;
        this.garageServiceOne = garageServiceOne;
        this.garageServiceTwo = garageServiceTwo;
        this.garageServiceThree = garageServiceThree;
        this.garageServicePriceOne = garageServicePriceOne;
        this.garageServicePriceTwo = garageServicePriceTwo;
        this.garageServicePriceThree = garageServicePriceThree;
        this.garageServiceTotalPrice = garageServiceTotalPrice;
    }

    public DataModelHistory() {
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getGarageServiceOne() {
        return garageServiceOne;
    }

    public void setGarageServiceOne(String garageServiceOne) {
        this.garageServiceOne = garageServiceOne;
    }

    public String getGarageServiceTwo() {
        return garageServiceTwo;
    }

    public void setGarageServiceTwo(String garageServiceTwo) {
        this.garageServiceTwo = garageServiceTwo;
    }

    public String getGarageServiceThree() {
        return garageServiceThree;
    }

    public void setGarageServiceThree(String garageServiceThree) {
        this.garageServiceThree = garageServiceThree;
    }

    public String getGarageServicePriceOne() {
        return garageServicePriceOne;
    }

    public void setGarageServicePriceOne(String garageServicePriceOne) {
        this.garageServicePriceOne = garageServicePriceOne;
    }

    public String getGarageServicePriceTwo() {
        return garageServicePriceTwo;
    }

    public void setGarageServicePriceTwo(String garageServicePriceTwo) {
        this.garageServicePriceTwo = garageServicePriceTwo;
    }

    public String getGarageServicePriceThree() {
        return garageServicePriceThree;
    }

    public void setGarageServicePriceThree(String garageServicePriceThree) {
        this.garageServicePriceThree = garageServicePriceThree;
    }

    public String getGarageServiceTotalPrice() {
        return garageServiceTotalPrice;
    }

    public void setGarageServiceTotalPrice(String garageServiceTotalPrice) {
        this.garageServiceTotalPrice = garageServiceTotalPrice;
    }
}


