package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    private String uploadFileName;
    private String storeFileName; //같은 파일이름을 업로드하면 덮어지기 때문에 이거로 구분해줌.
}
