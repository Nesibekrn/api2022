package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyApiActualBody {
    private String status;
    private DummyApiResponseBodyPojo data;

    public DummyApiActualBody(String status, DummyApiResponseBodyPojo data) {
        this.status = status;
        this.data = data;
    }

    public DummyApiActualBody() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyApiResponseBodyPojo getData() {
        return data;
    }

    public void setData(DummyApiResponseBodyPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DummyApiActualBody{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}

