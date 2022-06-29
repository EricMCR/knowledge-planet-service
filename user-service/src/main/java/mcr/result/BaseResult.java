package mcr.result;

import lombok.Data;

import java.util.Date;

@Data
public class BaseResult {

    /**
     * Request Status Code
     */
    private int status;

    /**
     * Whether the request was successful or not
     */
    private boolean success;

    /**
     * return message
     */
    private String msg;

    /**
     * return details
     */
    private String desc;

    /**
     * Service time
     */
    private Date serviceTime;

    /**
     * Return data
     */
    private Object data;

    /**
     * Default request successful
     */
    public BaseResult() {
        this.setStatus(200);
        this.setSuccess(true);
        this.setMsg("Request successful");
        this.setServiceTime(getCurrentTime());
    }

    /**
     * Passing in data indicates a successful request
     */
    public BaseResult(Object data) {
        this.setStatus(200);
        this.setSuccess(true);
        this.setMsg("Request successful");
        this.setData(data);
        this.setServiceTime(getCurrentTime());
    }

    /**
     * Request failed
     */
    public BaseResult(int errorCode, String desc){
        this.setStatus(errorCode);
        this.setSuccess(false);
        this.setMsg("Request failed");
        this.setDesc(desc);
        this.setServiceTime(getCurrentTime());
    }

    /**
     * Get the current time
     * @return Current time Date object
     */
    private static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    public static BaseResult getSuccessResult() {
        return new BaseResult();
    }

    public static BaseResult getSuccessResult(Object data) {
        return new BaseResult(data);
    }

    public static BaseResult getFailedResult() {
        return new BaseResult(500, "");
    }

    public static BaseResult getFailedResult(int errorCode, String desc) {
        return new BaseResult(errorCode, desc);
    }
}
