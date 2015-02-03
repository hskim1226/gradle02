package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 15. 1. 25.
 */
public class CustomApplicationExperience extends ApplicationExperience {
    // 항목 별 처리 상태
    private boolean checkedFg;
    private boolean saveFg =false;
    private boolean fileUploadFg=false;

    private UserCUDType userCUDType;

    public boolean isSaveFg() {
        return saveFg;
    }

    public void setSaveFg(boolean saveFg) {
        this.saveFg = saveFg;
    }

    public boolean isCheckedFg() {
        return checkedFg;
    }

    public void setCheckedFg(boolean checkedFg) {
        this.checkedFg = checkedFg;
    }

    public boolean isFileUploadFg() {
        return fileUploadFg;
    }

    public void setFileUploadFg(boolean fileUploadFg) {
        this.fileUploadFg = fileUploadFg;
    }
}
