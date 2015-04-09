package br.com.s3service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class S3ServiceTest {

    public static void main(String[] args) {
        try {
            // envio
            S3Service.getBucket(S3Bucket.BPA).envia("DEV/teste.txt", novoArquivo());

            // download
            byte[] bytes = S3Service.getBucket(S3Bucket.BPA).carrega("DEV/teste.txt");
            System.out.println(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File novoArquivo() throws IOException {
        File file = File.createTempFile("teste", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("teste2");
        writer.close();

        return file;
    }
}