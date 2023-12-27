package com.ezen.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.www.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@AllArgsConstructor
@Slf4j
@Component
public class FileHandler {
	//파일 객체를 파라미터로 받아서 flist로 리턴하는 역할
	//파일을 저장하고 fvo를 생성하는 역할
	
	//실제 파일이 저장되는 경로
	private final String UP_DIR = "D:\\_myProject\\_java\\_fileUpload";
    
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<FileVO>();
		//multipartFile를 받아서 FileVO 형태로 저장한 후 list를 리턴
		//오늘 날짜로 경로(가변 형태로 저장)를 만들어서 save_dir에 저장, 기본 경로 제외
		
		//오늘 날짜 경로 생성
		LocalDate date = LocalDate.now(); //오늘 날짜
		String today = date.toString();
		log.info(">>> today: "+today);
		
		//오늘 날짜(today)의 폴더 구성
		today = today.replace("-",File.separator);
		
		File folders = new File(UP_DIR, today);
		if(!folders.exists()) {
			//폴더가 없다면
			//mkdir:폴더 1개 생성
			//mkdirs: 하위폴더까지 생성
			//exists: 존재여부 체크
			folders.mkdirs(); //폴더 생성 명령
		}
		//리스트 설정
		for(MultipartFile file: files) {
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today); //UP_DIR 제외 오늘날짜 경로만 set
			fvo.setFile_size(file.getSize()); //file.size return long
			log.info("getName >>> {} "+ file.getName());
			log.info("getOriginalName >>> {} "+ file.getOriginalFilename());
			
			//파일이름(originalName()): 파일 경로를 포함해서 들어올 수 있음!
			String originalFileName = file.getOriginalFilename(); //이름
			String onlyFileName = originalFileName.substring
					(originalFileName.lastIndexOf
							(File.separator)+1); //실 파일명만 추출
			fvo.setFile_name(onlyFileName); //파일 이름 설정
			
			//UUID 생성
			//중복방지를 위해 생성
			UUID uuid = UUID.randomUUID();
			log.info(">>> uuid >> {}", uuid.toString());
			String uuidStr = uuid.toString();
			fvo.setUuid(uuidStr);
			
			//<-------------여기까지 fvo setting 완료------------->
			//디스크에 저장할 파일 객체를 생성한 다음 저장
			//uuid_fileName / uuid_th_fileName
			String fullFileName = uuidStr+"_"+onlyFileName;
			File storeFile = new File(folders, fullFileName);
			
			//저장 => 폴더가 없거나 저장 파일이 없다면 io exception 발생
			try {
				file.transferTo(storeFile); //실제 저장 형태
				//파일 타입을 결정 => 이미지 파일일때만 썸네일 설정
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					//썸네일 생성
					File thumbNail = new File(folders, uuidStr+"_th_"+onlyFileName);
				Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
				
			} catch (Exception e) {
				log.info(">>> file 저장 에러");
				e.printStackTrace();
			}
			flist.add(fvo);
		}	
		return flist;
	}
	//tika를 활용하여 파일 형식 체크 => 이미지 파일이 맞는지 확인
	public boolean isImageFile(File storeFile) throws IOException{
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image") ? true : false;
	}
	
}
