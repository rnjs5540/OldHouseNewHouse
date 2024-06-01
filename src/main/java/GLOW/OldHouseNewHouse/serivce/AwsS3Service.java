package GLOW.OldHouseNewHouse.serivce;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;


    /**
     * epxlain: store photo at S3
     * @param:file
     * @return finleName at S3
     */
    public String storePhoto(MultipartFile file) {
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try(InputStream inputStream = file.getInputStream()){
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        return fileName;
    }

//    public List<String> uploadFile(List<MultipartFile> multipartFiles){
//        List<String> fileNameList = new ArrayList<>();
//
//        // forEach 구문을 통해 multipartFiles 리스트로 넘어온 파일들을 순차적으로 fileNameList 에 추가
//        multipartFiles.forEach(file -> {
//            String fileName = createFileName(file.getOriginalFilename());
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(file.getSize());
//            objectMetadata.setContentType(file.getContentType());
//
//            try(InputStream inputStream = file.getInputStream()){
//                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));
//            } catch (IOException e){
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
//            }
//            fileNameList.add(fileName);
//
//        });
//
//        return fileNameList;
//    }

    // 먼저 파일 업로드시, 파일명을 난수화하기 위해 UUID 를 활용하여 난수를 돌린다.
    public String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }


    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기위해, "."의 존재 유무만 판단하였습니다.
    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일" + fileName + ") 입니다.");
        }
    }

    /**
     * epxlain: return photo from S3
     * @param:fileName
     * @return photo
     */
//    public MultipartFile getFile(String fileName){
//        S3Object s3Object = amazonS3.getObject(bucket, fileName);
//        try {
//            S3ObjectInputStream inputStream = s3Object.getObjectContent();
//            return new MockMultipartFile(fileName, inputStream);
//        } catch (IOException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 다운로드에 실패했습니다.");
//        }
//    }

    /**
     * epxlain: return photoURL from S3
     * @param:fileName
     * @return photoURL
     */
    public String getFileUrl(String fileName){
        try {
            // 객체의 URL을 생성하는 요청 생성
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, fileName);

            // URL 만료 기간 설정 (예: 1시간)
            int expiringTimeInHours = 1;
            Date expiration = new Date(System.currentTimeMillis() + expiringTimeInHours * 3600 * 1000);
            request.setExpiration(expiration);

            // URL 생성
            URL url = amazonS3.generatePresignedUrl(request);

            // URL 문자열로 반환
            return url.toString();
        } catch (AmazonServiceException e) {
            // Amazon 서비스 오류 처리
            e.printStackTrace();
            return null;
        } catch (SdkClientException e) {
            // Amazon S3 클라이언트 오류 처리
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            // 그 외 예외 처리
            e.printStackTrace();
            return null;
        }
    }



    /**
     * epxlain: delete photo from S3
     * @param:filename
     * @return null
     */
    public void deleteFile(String fileName){
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        System.out.println(bucket);
    }
}