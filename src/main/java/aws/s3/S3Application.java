package aws.s3;

import aws.s3.service.S3Service;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S3Application implements CommandLineRunner {

	@Autowired
	S3Service s3Service;

	private String fileName = "test2.txt";

	@Value("${aws.s3.file.path.upload}")
	private String filePath;

	public static void main(String[] args) {
		SpringApplication.run(S3Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		//upload something to S3
		System.out.println(filePath);
		s3Service.uploadFile(fileName, filePath);

		System.out.println("Start Downloading from S3");
		S3Object s3Object = s3Service.downloadFile(fileName);
		System.out.println("Finished Downloading from S3");
		System.out.println("Content-Type: "  + s3Object.getObjectMetadata().getContentType());

	}
}
