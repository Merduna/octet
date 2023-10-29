package model;

public class Conversion {
    private String originalValue;
    private boolean decimal;
    private boolean hexadecimal;
    private boolean binary;

    public Conversion(String userNumber) {
        this.originalValue = userNumber;
    }

    public String getOriginalValue() {
        return this.originalValue;
    }

    public void setOriginalValue(String value) {
        this.originalValue = value;
    }

    public boolean isDecimal() {
        return this.decimal;
    }

    public boolean getDecimal() {
        return this.decimal;
    }

    public void setDecimal(boolean decimal) {
        this.decimal = decimal;
    }

    public boolean isHexadecimal() {
        return this.hexadecimal;
    }

    public boolean getHexadecimal() {
        return this.hexadecimal;
    }

    public void setHexadecimal(boolean hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

    public boolean isBinary() {
        return this.binary;
    }

    public boolean getBinary() {
        return this.binary;
    }

    public void setBinary(boolean binary) {
        this.binary = binary;
    }

}
