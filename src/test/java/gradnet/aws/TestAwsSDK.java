package gradnet.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by hanmomhanda on 15. 4. 3.
 */
public class TestAwsSDK {

    private AmazonS3 s3;
    private Region tokyo;
    private String bucketName;

    @Before
    public void setUp() throws Exception {
        s3 = new AmazonS3Client();
        tokyo = Region.getRegion(Regions.AP_NORTHEAST_1);
        s3.setRegion(tokyo);

        bucketName = "upload.gradnet.apexsoft";

    }

    @Test
    public void testS3Put() throws Exception {
        String key = "myFolder/MyObjectKey";

        System.out.println("===========================================");
        System.out.println("Getting Started with Amazon S3");
        System.out.println("===========================================\n");

        try {
            /*
             * Create a new S3 bucket - Amazon S3 bucket names are globally unique,
             * so once a bucket name has been taken by any user, you can't create
             * another bucket with that same name.
             *
             * You can optionally specify a location for your bucket if you want to
             * keep your data closer to your applications or users.
             */
//            System.out.println("Creating bucket " + bucketName + "\n");
//            s3.createBucket(bucketName);

            /*
             * List the buckets in your account
             */
//            System.out.println("Listing buckets");
//            for (Bucket bucket : s3.listBuckets()) {
//                System.out.println(" - " + bucket.getName());
//            }
//            System.out.println();

            /*
             * Upload an object to your bucket - You can easily upload a file to
             * S3, or upload directly an InputStream if you know the length of
             * the data in the stream. You can also specify your own metadata
             * when uploading to S3, which allows you set a variety of options
             * like content-type and content-encoding, plus additional metadata
             * specific to your applications.
             *
             * PutObjectRequest(java.lang.String bucketName, java.lang.String key, java.io.File file)
             * PutObjectRequest(java.lang.String bucketName, java.lang.String key, java.io.InputStream input, ObjectMetadata metadata)
             * PutObjectRequest(java.lang.String bucketName, java.lang.String key, java.lang.String redirectLocation)
             *
             */
            System.out.println("Uploading a new object to S3 from a file\n");

            FileInputStream fis = new FileInputStream(new File("/home/hanmomhanda/지원서.pdf"));

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("application/pdf");
            meta.setContentEncoding("UTF-8");
            meta.setContentLength(fis.available());

            s3.putObject(new PutObjectRequest(bucketName, key, fis, meta)
                    .withCannedAcl(CannedAccessControlList.AuthenticatedRead.PublicRead));

//            s3Client.putObject(
//                    new PutObjectRequest(bucketName, objectKey, inputStream, metadata)
//                            .withCannedAcl(CannedAccessControlList.PublicRead));

            /*
             * Download an object - When you download an object, you get all of
             * the object's metadata and a stream from which to read the contents.
             * It's important to read the contents of the stream as quickly as
             * possibly since the data is streamed directly from Amazon S3 and your
             * network connection will remain open until you read all the data or
             * close the input stream.
             *
             * GetObjectRequest also supports several other options, including
             * conditional downloading of objects based on modification times,
             * ETags, and selectively downloading a range of an object.
             */
//            System.out.println("Downloading an object");
//            S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
//            System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());


            /*
             * List objects in your bucket by prefix - There are many options for
             * listing the objects in your bucket.  Keep in mind that buckets with
             * many objects might truncate their results when listing their objects,
             * so be sure to check if the returned object listing is truncated, and
             * use the AmazonS3.listNextBatchOfObjects(...) operation to retrieve
             * additional results.
             */
            System.out.println("Listing objects");
            ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
                    .withBucketName(bucketName));
//                    .withPrefix("My"));
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                System.out.println(" - " + objectSummary.getKey() + "  " +
                        "(size = " + objectSummary.getSize() + ")");
            }
            System.out.println();

            /*
             * Delete an object - Unless versioning has been turned on for your bucket,
             * there is no way to undelete an object, so use caution when deleting objects.
             */
//            System.out.println("Deleting an object\n");
//            s3.deleteObject(bucketName, key);

            /*
             * Delete a bucket - A bucket must be completely empty before it can be
             * deleted, so remember to delete any objects from your buckets before
             * you try to delete them.
             */
//            System.out.println("Deleting bucket " + bucketName + "\n");
//            s3.deleteBucket(bucketName);
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

//    @Test
//    @Ignore
//    public void testS3Get() throws Exception {
//        String key = "myFolder/MyObjectKey";
//
//        System.out.println("Downloading an object");
//        S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
//        System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
//        InputStream is = object.getObjectContent();
//    }
}
