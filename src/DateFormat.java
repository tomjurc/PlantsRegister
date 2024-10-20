public enum DateFormat {
    YYYYMMDD("([20]{2}[0-9]{2})"+
            "([-]{1})"+
            "([0]{1}[1-9]{1}|[1]{1}[0-2]{1}|[1-9]{1})"+
            "([-]{1})"+
            "([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-9]{1}|[2]{1}[0-9]{1}|[3]{1}[0-1]{1})");
    private final String dateFormat;

    DateFormat(String dateFormat){
        this.dateFormat = dateFormat;
    }
    public String getDateFormat(){
        return this.dateFormat;
    }
}

