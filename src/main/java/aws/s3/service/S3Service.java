package aws.s3.service;

import com.amazonaws.services.s3.model.S3Object;

public interface S3Service {

    S3Object downloadFile(String keyName);
    void uploadFile(String keyName, String filePath);
}
