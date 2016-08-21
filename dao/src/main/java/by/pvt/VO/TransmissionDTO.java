package by.pvt.VO;

/**
 * Класс формирует объект Коробки передач для показа на стороне клиента
 */
public class TransmissionDTO {
    private long transId;
    private  String transName;

    public TransmissionDTO() {
    }

    public long getTransId() {
        return transId;
    }

    public void setTransId(long transId) {
        this.transId = transId;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }
}
