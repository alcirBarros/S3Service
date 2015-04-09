package br.com.s3service;

public enum S3Bucket {

    BPA("pasta1"), CONTROLADOS("pasta2"), RAAS("pasta3");

    private String path;
    
    private S3Bucket(String bucket) {
        this.path = bucket;
    }

    public String getPath() {
        return path;
    }
}
