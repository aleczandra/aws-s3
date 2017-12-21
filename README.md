# aws-s3

Try to play a bit with AWS S3.
To let the project run is simple, but there are a few prerequisites:
1. Have an aws account
2. Have a user that has rights to read/write into S3 (check IAM service from AWS in order to create a user and give the rights)
    - this user has to have the rights to programatically access AWS resources
    - a access key and a secret key will be generated for this user; these keys will be later used inside the code when
    creating the client that is supposed to programatically connect to S3
3. Have a group which has the AmazonS3FullAccess roles assigned to it and then assign the user to this group.
4. Go to S3 and create a bucket. When creating the bucket you will choose a region. That region has to be specified in application.properties of your application.
5. Play a bit with the permissions related to that bucket.

On the Java project side, I just used Spring Boot to generate the project. If using Spring Initializr, select the Cloud AWS dependency that will come with the Amazon SDK to access the AWS resources. (Amazon S3 for example).
The rest is Java coding: 
      - creating an AmazonS3 client used to connect to AWS (use the access key and secret key that you could download when creating the user using IAM service from AWS
      - perform actions using this client.
