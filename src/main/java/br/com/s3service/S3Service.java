package br.com.s3service;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import java.io.File;
//import org.apache.commons.io.IOUtils;

public class S3Service {

    private final AmazonS3 s3 = new AmazonS3Client();
    
    private final S3Bucket bucket;

    public static S3Service getBucket(S3Bucket bucket) {
        return new S3Service(bucket);
    }

    private S3Service(S3Bucket bucket) {
        this.bucket = bucket;
        s3.setRegion(Region.getRegion(Regions.US_EAST_1));
    }

    public void envia(File arquivo) {
        envia(arquivo.getName(), arquivo);
    }
    public void envia(String nomeArquivo, File arquivo) {
        s3.putObject(new PutObjectRequest(bucket.getPath(), nomeArquivo, arquivo));
    }
    
    public byte[] carrega(String nomeArquivo) {
        try {
            S3Object object = s3.getObject(new GetObjectRequest(bucket.getPath(), nomeArquivo));
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
