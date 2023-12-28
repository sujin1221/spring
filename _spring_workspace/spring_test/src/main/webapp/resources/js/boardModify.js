console.log("board modify in~");

async function removeImgFromServer(uuid){
    try {
        const url = "/board/file/"+uuid;
        const config = {
            method: "delete"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click',(e)=>{
    console.log (e.target); //내가 가져오려는 객체 찍어보기
    if(e.target.classList.contains('file-x')){ //file-x에 해당하는 클래스가 있다면...
        let uuid = e.target.dataset.uuid; //uuid 가져와서 넣어주기...
        console.log(uuid);
        removeImgFromServer(uuid).then(result=>{
            if(result === "1"){
                alert("첨부파일 삭제 완료!");
                e.target.closest('li').remove(); //li 삭제
                location.reload(); //새로고침
            } else {
                alert("파일 삭제 실패..");
            }
        })
    }   
})

