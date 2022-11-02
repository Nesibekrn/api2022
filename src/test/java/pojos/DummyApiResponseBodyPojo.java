package pojos;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyApiResponseBodyPojo {

    private String status;
    private DummyApiDataPojo dataPojo;
    private String message;

    public DummyApiResponseBodyPojo() {
    }

    public DummyApiResponseBodyPojo(String status, DummyApiDataPojo dataPojo, String message) {
        super();
        this.status = status;
        this.dataPojo = dataPojo;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyApiDataPojo getData() {
        return dataPojo;
    }

    public void setData(DummyApiDataPojo data) {
        this.dataPojo = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyApiResponseBodyPojo{" +
                "status='" + status + '\'' +
                ", data=" + dataPojo +
                ", message='" + message + '\'' +
                '}';
    }
}