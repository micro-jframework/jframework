package com.github.neatlife.jframework.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>ExcelLogs</code>
 */
public class ExcelLogs {
    private Boolean hasError;
    private List<ExcelLog> logList;

    /**
     *
     */
    public ExcelLogs() {
        super();
        hasError = false;
    }

    /**
     * @return the hasError
     */
    public Boolean getHasError() {
        return hasError;
    }

    /**
     * @param hasError the hasError to set
     */
    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * @return the logList
     */
    public List<ExcelLog> getLogList() {
        return logList;
    }

    /**
     * @param logList the logList to set
     */
    public void setLogList(List<ExcelLog> logList) {
        this.logList = logList;
    }

    public List<ExcelLog> getErrorLogList() {
        List<ExcelLog> errList = new ArrayList<>();
        for (ExcelLog log : this.logList) {
            if (log != null && ExcelUtil.isNotBlank(log.getLog())) {
                errList.add(log);
            }
        }
        return errList;
    }

}
