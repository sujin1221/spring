package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component //사용자 클래스를 빈으로 등록
public class FileHandler {
	private final String UP_DIR = "D:\\_myProject\\_java\\_fileUpload"; //파일경로 변경 못하게...
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		//FileVO 생성, 실제 파일을 경로에 맞춰서 저장, 썸네일 저장
		//날짜를 폴더로 생성하여 그날 그날 업로드 파일을 관리
		LocalDate date = LocalDate.now(); //오늘 날짜를 폴더로 구성
		String today = date.toString(); //string으로 저장
		today = today.replace("-", File.separator); //운영체제마다 모양이 다름! ('-' ->'\\')
		
		//기존라인 뒤에다가 오늘의 날짜를 붙여줄거임 => 폴더
		File folders = new File(UP_DIR, today); //파일의 풀 경로
		
		//폴더 생성
		//exists: 있는지 없는지 확인하는 역할
		//mkdir(): 하나의 폴더만 생성 | mkdirs(): 파일 여러개 동시에 생성
		if(!folders.exists()) {
			//없다면?
			folders.mkdirs();
		}
		//files 객체에 대한 설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO(); //fileVO 하나씩 생성
			fvo.setSaveDir(today); //기본제외 오늘날짜만...
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring
					(originalFileName.lastIndexOf(File.separator)+1);
			log.info(">> fileName >>> {} ",fileName);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			String uuidstr = uuid.toString();
			fvo.setUuid(uuidstr);
			//여기까지 기본 fvo 세팅완료!
			
			//디스크에 저장할 파일 객체 생성
			String fullFileName = uuidstr+"_"+fileName;
			File storeFile = new File(folders, fullFileName); //실제 저장할 객체
			//실제 파일이 저장되려면 첫 경로부터 다 설정되어야 함
			try {
				file.transferTo(storeFile); //저장
				//썸네일 생성 => '이미지 파일'만 썸네일을 생성해야함
				//이미지인지 확인하는 절차가 필요함
				if(isImageFile(storeFile)) {
					fvo.setFileType(1); //이미지파일은 타입이 1임
					//썸네일 생성
					File thumbNail = new File(folders, uuidstr+"_th_"+fileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);//사이즈 줄여서 저장
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("file 생성 오류~!");
			}
			//list에 fvo 추가
			flist.add(fvo); //얘가 없으면 list null임			
		}
		return flist;
	}
	
	//이미지인지 확인하는 메서드
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); //type image/jpg
		return mimeType.startsWith("image") ? true : false; //이미지 포함 되어있는지 확인
	}
	
	
	
}
