package aws.s3.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3ServiceImpl implements S3Service {

    @Autowired
    AmazonS3 s3Client;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private Logger logger = LoggerFactory
            .getLogger(S3Service.class);

    @Override
    public S3Object downloadFile(String keyName) {
        try {
            logger.info("Start downloading file!");
            S3Object s3Object =  s3Client.getObject(new GetObjectRequest(bucketName, keyName));
            logger.info("Finished downloading file!");
            return s3Object;
        } catch (AmazonServiceException ase) {
            logger.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        }

        return null;
    }

    @Override
    public void uploadFile(String keyName, String uploadFilePath) {
        try {
            File file = new File(uploadFilePath);
            s3Client.putObject(new PutObjectRequest(bucketName, keyName, file));
            logger.info("Finished uploading file: " + file.getAbsolutePath());
        } catch (AmazonServiceException ase) {
            logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        }
    }
}
