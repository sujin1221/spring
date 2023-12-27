console.log("board modify in~");

async function removeImgFromServer(uuid){
    try {
        const url = "/board/"+uuid;
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
    if(e.target.classList.contains('file-x')){
        let uuid = e.target.dataset.uuid; //uuid 가져와서 넣어주기...
        let li = e.target.closest('li'); //가장 가까운 li 가져오기...
        li.remove(); //지우기
        removeImgFromServer(uuid).then(result=>{
            if(result === "1"){
                alert("첨부파일 삭제 완료!");
            }
        })
    }
   
})

