console.log("boardRegister js in~!");

//트리거 버튼 처리
document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('files').click();

})

//실행파일, 파일최대사이즈 정규표현식 작성
const regExp = new RegExp("\.(exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024*1024*20; //파일 최대크기

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    } else if(fileSize > maxSize){
        return 0;
    } else {
        return 1;
    }
}

document.addEventListener('change',(e)=>{
    console.log(e.target);
    if(e.target.id == 'files'){
        //files가 변경됐다면...
        //input file element에 저장된 file의 정보를 가져오는 property
        const fileObj = document.getElementById('files').files;
        console.log(fileObj);

        //한번 disabled 되면 혼자 풀어질 수 없음
        //버튼을 원래 상태로 복구시켜줘야함
        document.getElementById('regBtn').disabled = false;

        //첨부파일에 대한 정보를 fileZone에 기록
        let div = document.getElementById('fileZone');
        div.innerHTML = ''; //값이 있다면 지우기
        
        //ul > li로 값을 추가
        //<ul class="list-group list-group-flush">
        //<li class="list-group-item">An item</li> 이모양으로 넣을거임
        
        //여러파일에 대한 검증을 모두 통과하기 위해서 * 연산자를 이용해 각 파일마다
        //통과여부를 확인할것임
        let isOk = 1; //시작
        let ul = `<ul class="list-group list-group-flush">`;
         for(let file of fileObj){
            //배열로 만들어서 하나씩 가져오기
            let vaildResult = fileValidation(file.name, file.size); //0또는1로 리턴, 개별적인 파일의 결과
            isOk *= vaildResult; //첨부되는 모든 파일의 결과
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`; 
            ul += `${vaildResult ? '<div class="fw-bold">업로드 가능</div>' : '<div class="fw-bold text-danger">업로드 불가능</div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge text-bg-${vaildResult ? 'success' : 'danger'}">${file.size}Byte</span>`;
            ul += `</li>`;
         }
        ul += `</ul>`;
        div.innerHTML = ul;
        if(isOk == 0){
            //하나의 파일이라도 검증에 통과하지 못한다면 버튼을 비활성화 시킬것임
            document.getElementById('regBtn').disabled = true;
        }        
    }
})
