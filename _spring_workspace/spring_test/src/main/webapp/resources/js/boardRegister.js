document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
})

//정규 표현식을 사용하여 파일 형식 제한 함수 만들기
//실행파일 막기(exe, bat, sh, mis, dll, jar)
//파일 사이즈 체크(max size 20M 이상 막기)
//이미지 파일'만' 올리기(jpg, jpeg, gif, png, bmp)
const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$"); //실행 파일 패턴
const regExpImg = new RegExp("\.(jpeg|jpg|gif|png|bmp)$"); //이미지 파일 패턴
const maxSize = 1024*1024*20; //20MB 사이즈로 설정, 20MB 이상 불가능

//Validation: 규칙 설정할때 사용함
//return 0/1로 리턴 (true/flase로 해도 ㅇㅋ)
function fileValidation(name,fileSize){
    let fileName = name.toLowerCase();
    if(regExp.test(fileName)){ //파일이름이 실행파일인지 확인
        return 0;
    } else if(fileSize > maxSize){
        return 0;
    } else if(!regExpImg.test(fileName)){
        return 0;
    } else {
        return 1; //나머지는 ㅇㅋ
    }
}

//첨부파일에 따라 등록가능한지 체크하는 함수
document.addEventListener('change',(e)=>{
    console.log(e.target);
    if(e.target.id === 'file'){
        //여러개의 파일이 배열로 들어옴
        const fileObject = document.getElementById('file').files;
        console.log(fileObject);
        //한번 true가 되면 다시 false로 돌아오지 않음 => 버튼 활성화 해줘야함
        document.getElementById('regBtn').disabled = false;
        let div = document.getElementById('fileZone');
        div.innerHTML = ''; //기존 추가 파일 삭제 => 화면에서만 삭제 처리
        let ul = `<ul>`;
        //fileValidation 함수의 리턴 여부를 체크
        //모든 파일이 1이어야 가능
        //*로 isOk를 처리하여 모든 파일이 1이어야 통과되도록 설정
        let isOk = 1; 
        for(let file of fileObject){
            //한 파일에 대한 결과
            let ValidResult = fileValidation(file.name, file.size);
            isOk *= ValidResult;
            ul += `<li>`;
            //업로그 가능 여부 각자 표시
            ul += `<div>${ValidResult ? '업로드 가능':'업로드 불가능'}</div>`;
            ul += `${file.name}`;
            ul += `<span class="badge text-bg-${ValidResult ? 'success':'danger'}">${file.size}Byte</span>`;
            ul += `</li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;
        //업로드 불가능한 파일이 1개라도 있다면...
        if(isOk == 0){
            document.getElementById('regBtn').disabled = true; //버튼 비활성화
        }
    }
});

//fileZone > ul > li (파일 갯수만큼 li가 추가되는 형식)

 
