package Model;

import Model.Enum.QueryClassification;

public class Response {

    private QueryClassification responseType;
    private String message;

    public Response() {

    }

    public Response(QueryClassification responseType, String message) {
        this.responseType = responseType;
        this.message = message;
    }

    public QueryClassification getResponseType() {
        return responseType;
    }

    public void setResponseType(QueryClassification responseType) {
        this.responseType = responseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
