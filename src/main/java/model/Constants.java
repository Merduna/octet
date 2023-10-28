package model;

public final class Constants {
    
    // All individual conversion options
    public static final char DECIMAL_TO_BINARY = 'B';
    public static final char BINARY_TO_DECIMAL = 'b';
    public static final char DECIMAL_TO_HEX = 'H';
    public static final char HEX_TO_DECIMAL = 'h';
    public static final char BINARY_TO_HEX = 'N';
    public static final char HEX_TO_BINARY = 'n';
    public static final char RANDOM_TO_RANDOM = 'R';

    // Default false value
    public static final int DEFAULT_INVALID_VALUE = -1;
    
    // Char array with all the conversion options
    public static final char[] QUESTION_TYPE_OPTIONS = {
        DECIMAL_TO_BINARY,
        BINARY_TO_DECIMAL,
        DECIMAL_TO_HEX,
        HEX_TO_DECIMAL,
        BINARY_TO_HEX,
        HEX_TO_BINARY
    };
    
    // Char array with the hexadecimal digits
    public static final char[] HEX_CHARS = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
}